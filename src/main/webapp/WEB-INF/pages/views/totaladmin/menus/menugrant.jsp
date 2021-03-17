<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<div class="content-wrapper">
    <div class="content-header">
        <div class="container-fluid">
            <div class="row page-titles">
                <div class="col-md-7">
                        <h2>권한 목록 </h2>
                </div>
                <div class="col-md-2 text-right">
                    <button type="button" class="btn btn-block btn-sm btn-outline-info" data-toggle="modal" data-target="#addGroup">그룹추가</button>
                </div>

                <div class="col-md-2 text-right">
                    <button type="button" class="btn btn-block btn-sm btn-outline-info btnGrantAction">권한변경</button>
                </div>

            </div>

        </div>
    </div>

    <section class="content" id="wrapList">
        <div class="row page-titles">

            <!-- .modal for addGroup -->
            <div class="modal fade" id="addGroup" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">그룹 추가</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form class="modalForm" method="post">
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <label>그룹명</label>
                                        <input type="text" class="form-control" placeholder="Enter Your Name">
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label>그룹순서</label>
                                        <input type="text" class="form-control" placeholder="Enter Your Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>그룹설명</label>
                                    <input type="email" class="form-control" placeholder="Enter email">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-success" data-dismiss="modal">Submit</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
            <div class="col-12">
                <div class="card-body-line" style=""></div>

            </div>
        </div>
        <!-- End Bread crumb and right sidebar toggle -->
        <!-- Start Page Content -->
        <div class="row">

            <div class="col-4">
                    <div class="wrapElement ">
                        <table id="groupElement" class=" nowrap table table-striped table-bordered table-hover dataTable no-footer dtr-inline" role="grid">
                            <tbody class="tableElement">
                            </tbody>
                        </table>
                    </div>
            </div>
            <div class="col-8">
                    <div class="wrapElement ">
                        <table id="gridElement" class=" nowrap table table-striped table-bordered table-hover dataTable no-footer dtr-inline" role="grid">
                            <tbody class="tableElement">
                            </tbody>
                        </table>
                    </div>
            </div>
        </div>
    </section>
</div>

        <script type="text/javascript">
var setData = {};
var gridElement = null;
$(document).ready(function(){
    $('.btnGrantAction').click(function(){
        var trElement = $('#gridElement tbody tr');
        console.log(trElement.length);
        var putData = [];
        var ugName = '';
        $.each(gridElement.data(),function(i,v){
             var rowData =  gridElement.row( trElement.eq(i) ).data();
             if(!rowData['ugSeq'] || rowData['ugSeq']=='0') return true;
             putData.push({muSeq:rowData['muSeq'],'ugSeq':rowData['ugSeq'],mgGrant:$('.mg_grant').eq(i).val()});
             ugName = rowData['ugName'];

        });
        if(putData.length==0){
            alert('그룹을 선핵해주세요');
            return false;
        }
        if(confirm('메뉴권한을 그룹명 ['+ugName +']에 등록하시겠습니까?')){
            $.call('/totaladmin/ajax/menus/menuGrantBatch',{'putData':JSON.stringify(putData)},function(r){

            });
        }
    });










	var groupElement =null,gridElement,gridColumn =[];
	gridColumn.push({'data':'ugName','title':'그룹명','type':''});
	gridColumn.push({'data':'ugIsDefault','title':'가입시_디폴트그룹','type':'','hidden':true});
	gridColumn.push({'data':'ugPermission','title':'권한','type':'select','hidden':true});
	gridColumn.push({'data':'ugOrder','title':'그룹순서','type':'','hidden':false});
	gridColumn.push({'data':'ugDesc','title':'설명','type':''});
	groupElement = plus.makeGrid('#groupElement',gridColumn,plus.makeAjax('/totaladmin/ajax/users/usergrouplist',{data:'data'},'resultList'),{bPaginate:false,pageLength:100,attr:'속성'});


	$('#groupElement tbody').on('click','tr.even,tr.odd',function () {
        var rowData = groupElement.row( this ).data();
        //
        // $('#gridElement').resetGrid();
        // gridElement = plus.evnet.chageGrid(rowData);

        plus.ready['gridElement']=function(){
            $('.mg_status_all').change(function() {
                var statusValue = $(this).val();
                $('.mg_grant').each(function(){
                   $(this).val(statusValue);
                });
            });

        };

        plus.event.gridComplet=function(id){
            $('#gridElement tbody tr').each(function(i){
                var rowData =  $('#gridElement').DataTable().row( $(this) ).data();

                $(this).find('select').val(rowData['mgGrant']);
            });
        }
        var gridColumn =[];
        gridColumn.push({'data':'muParent','title':'메뉴루트','type':'','hidden':true});
        gridColumn.push({'data':'muCode','title':'메뉴코드','type':'','hidden':true});

        gridColumn.push({'data':'muModule','title':'모듈명','type':''});
        gridColumn.push({'data':'muName','title':'메뉴명','type':''});
        gridColumn.push({'data':'muLevel','title':'메뉴레벨','type':'','hidden':false});
        gridColumn.push({'data':'muPath','title':'메뉴경로','type':'','hidden':true});
        gridColumn.push({'data':'muSort','title':'정렬','type':'','hidden':true});
        gridColumn.push({'data':'muIsUse','title':'사용여부'});
        gridColumn.push({'data':'ugName','title':'그룹명'});
        gridColumn.push({'data':'mgStatus','title':plus.makeSelect('mg_status_all.mg_status_all',{'':'일괄변경선택','R':'일괄변경:읽기','A':'일괄변경:전체','N':'일괄변경:권한없음'}).prop("outerHTML"),'type':'',render:function(d, t, r){return plus.makeSelect('mg_grant.mg_grant',{'':'선택하세요','R':'읽기만','A':'전체','N':'접근권한없음'}).prop("outerHTML")}});
        // gridColumn.push({'data':'muIcon','title':'메뉴아이콘','type':'','hidden':true});
        // gridColumn.push({'data':'regDate','title':'등록일','type':'','hidden':true});
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/totaladmin/ajax/menus/menuGrant',{ugSeq:rowData['ugSeq']},'resultList'),{bPaginate:false,pageLength:100,attr:'속성',drawCallback:function(settings, json){

                console.log('eeeeeeeeeee')
            }});


    });



	plus.evnet.chageGrid=function(data){
	    if(!data)  data ={};


    }

});
        </script>