<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="row page-titles">

    <div class="col-md-5 align-self-center">
        <h3 class="text-themecolor">목록</h3>
    </div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><%//1라인 삭제금지%></li>
            <li class="breadcrumb-item">
                <a href="javascript:void(0);"><button type="button" class="btn btn-block btn-sm btn-outline-info changeGroup">그룹변경</button></a>
            </li>
        </ol>
    </div>
    <div class="col-12">
            <div class="card-body-line" style=""></div>
    </div>
</div>
<!-- End Bread crumb and right sidebar toggle -->
<!-- Start Page Content -->
<div class="row">

    <div class="col-sm-5">
        <div class="card">
            <div class="card-body">

                <div class="group_02 dataTables_wrapper ">
                    <div class="wrapElement ">
                    <table id="gridElement" class=" nowrap table table-striped table-bordered table-hover dataTable no-footer dtr-inline" role="grid">
                        <tbody class="tableElement">
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-sm-7">
        <div class="card">
            <div class="card-body">

                <div class="group_02 dataTables_wrapper ">
                    <div class="wrapElement ">
                    <table id="gridGrantElement" class=" nowrap table table-striped table-bordered table-hover dataTable no-footer dtr-inline" role="grid">
                        <tbody class="tableElement">
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $.call('/ajax/codeList',{'codes':'UG'},function(r){
        $.extend(plus.codes,r.codes);
    });
    plus.event.seqCheckBox=function(d, t, r){

        return plus.makeInput('checkbox',"umSeq",{'class':"umSeq",id:"umSeq"+(r.umSeq),value:r.umSeq}).prop("outerHTML")
            +plus.makeElement('label',r.umSeq,{for:'umSeq'+(r.umSeq)}).prop("outerHTML");
    }
    plus.event.groupSelectBox=function(d, t, r){
        return plus.makeSelect('ugSeq',plus.codes.UG,r.ugSeq).prop("outerHTML");
    }
    $(document).ready(function(){
        $('.changeGroup').click(function(){
            if($('#gridGrantElement .umSeq:checked').length ==0){
                alert('한개이상의 아이디를 선택해주세요');
                return false;
            }
            if(confirm('선택한 한 아이디의 그룹을 변경하시겠습니까')){

                var updateGroup = [];
                $('#gridGrantElement .umSeq:checked').each(function(){

                    var tr = $(this).closest('tr');
                    var umSeq = $(this).val();
                    var ugSeq = tr.find(':selected').val();
                    updateGroup.push({umSeq:umSeq,ugSeq:ugSeq});
                });
                $.call('/totaladmin/ajax/users/userPermissionExcute',{updateGroup:JSON.stringify(updateGroup)},function(r){

                });
            }
        });
        var gridElement = null, gridColumn = [];
        gridColumn.push({'data':'ugName','title':'그룹명','type':''});
        gridColumn.push({'data':'ugIsDefault','title':'디폴트그룹','type':''});
        gridColumn.push({'data':'ugPermission','title':'권한','type':'select'});
        gridColumn.push({'data':'ugOrder','title':'그룹순서','type':''});
        gridElement = plus.makeGrid('#gridElement', gridColumn, plus.makeAjax('/totaladmin/ajax/users/userGroup', {data: 'data'}, 'resultList'), {attr: '속성'});
        console.log(gridElement);

        var gridElement = null, gridColumn = [];
        gridColumn.push({'data': 'umSeq', 'title': '시퀀스', 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data': 'biName', 'title': '기본사업장', 'type': 'select', hidden:false});
        gridColumn.push({'data': 'ugSeq', 'title': '회원그룹', 'type': 'select', hidden: true});
        gridColumn.push({'data': 'ugName', 'title': '회원그룹', 'type': 'select'});
        gridColumn.push({'data': 'umId', 'title': '회원아이디', 'type': ''});
        gridColumn.push({'data': 'umName', 'title': '이름', 'type': ''});
        gridColumn.push({'data': 'umName', 'title': '변경할그룹', 'type': '',render:plus.event.groupSelectBox});
        gridElement = plus.makeGrid('#gridGrantElement', gridColumn, plus.makeAjax('/totaladmin/ajax/users/userList', {data: 'data'}, 'resultList'), {pageLength:200,attr: '속성'});
        console.log(gridElement);
    });
</script>