<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="row page-titles">

    <div class="col-md-5 align-self-center">
        <h3 class="text-themecolor">공지사항목록</h3>
    </div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><%//1라인 삭제금지%>?></li>
            <li class="breadcrumb-item">
                <a href="/totaladmin/sites/write"><button type="button" class="btn btn-block btn-sm btn-outline-info">신규등록</button></a>
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

    <div class="col-12">
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
</div>

        <script type="text/javascript">
var setData = {};
$(document).ready(function(){

    plus.event.gridComplet = function(){
         $('#gridElement tbody tr').click(function () {

            $('#viewTr').remove();
            var tdCount =  $(this).find('td').length;
            var index = $('#gridElement tbody tr').index(this);
            var rowData =  gridElement.row( this ).data();
            var tplTrView = $('#tplTrView').tmpl();

            var tr,tableElement = $('.tableElement',tplTrView),wrapElement = tplTrView;

            tr = plus.makeElement('colgroup').appendTo(tableElement);
		plus.makeElement('col','',{width:'10%'}).appendTo(tr);
		plus.makeElement('col','',{width:'15%'}).appendTo(tr);
		plus.makeElement('col','',{width:'10%'}).appendTo(tr);
		plus.makeElement('col','',{width:'15%'}).appendTo(tr);
		plus.makeElement('col','',{width:'10%'}).appendTo(tr);
		plus.makeElement('col','',{width:'15%'}).appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('select','si_seq','사이트별').appendTo(tr);
		plus.makeTd('select','bb_type','공지구분').appendTo(tr);
		plus.makeTd('select','bb_bbs','NOTICE').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','bb_title','공지타이틀').appendTo(tr).last().attr('colspan',5);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','bb_contents','공지글').appendTo(tr).last().attr('colspan',5);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','bb_user','작성자').appendTo(tr);
		plus.makeTd('text','bb_view','조회수').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','reg_date','등록일').appendTo(tr);
		plus.makeTd('text','upt_date','업데이트일자').appendTo(tr);

            var trView = $('<tr id="viewTr"><td></td></tr>').find('td').attr('colspan',tdCount).html(tplTrView).parent();
            trView.insertAfter(this);
            $.each(rowData,function(i,v){
                $('#'+i,wrapElement).setValue(v);
            })
            // location.href ='/totaladmin/sites/write?si_seq='+rowData['si_seq'];
        });
    }
	var gridElement =null,gridColumn =[];

	gridColumn.push({'data':'bb_seq','title':'시퀀스','type':'hidden'});
	gridColumn.push({'data':'si_seq','title':'사이트별','type':'select'});
	gridColumn.push({'data':'bb_type','title':'공지구분','type':'select'});
	gridColumn.push({'data':'bb_bbs','title':'NOTICE','type':'select'});
	gridColumn.push({'data':'bb_title','title':'공지타이틀','type':''});
	gridColumn.push({'data':'bb_user','title':'작성자','type':''});
	gridColumn.push({'data':'bb_view','title':'조회수','type':''});
	gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/totaladmin/ajax/boards/boardlist',{data:'data'},'boardList'),{attr:'속성'});
	console.log(gridElement);






});
        </script>
