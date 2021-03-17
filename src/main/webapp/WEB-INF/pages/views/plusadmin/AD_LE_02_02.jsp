<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>모의진단관리</h2>

	        <div class="tab_menu_wrap mt30">
		        <ul class="tab_menu qTab">
<%--		        	<li class="tab tab1"><a href="AD_LE_02_03.html">문항정보</a></li>--%>
<%--					<li class="tab tab2"><a href="AD_LE_02_04.html">통계치 등록</a></li>--%>
<%--					<li class="tab tab3"><a href="AD_LE_02_05.html">채점현황</a></li>--%>
					<li class="tab tab4 active"><a href="#">진단총평관리</a></li>
				</ul>
			</div>
		<div id="wrapList">
			<table class="tbl-st mt30" id="gridElement">
				<colgroup>
					<col width="12%">
					<col width="20%">
					<col width="17%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>순번</th>
						<th>레벨명</th>
						<th>상태</th>
						<th>등록/수정</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
	        <div class="tbl-foot">
				<div class="paging">
					<a href="#" class="prev">
						<i class="fas fa-caret-left"></i>
					</a>
					<span class="on">1</span>
					<a href="#">2</a>
					<a href="#">3</a>
					<a href="#">4</a>
					<a href="#">5</a>
					<a href="#">6</a>
					<a href="#">7</a>
					<a href="#">8</a>
					<a href="#">9</a>
					<a href="#">10</a>
					<a href="#" class="next">
						<i class="fas fa-caret-right"></i>
					</a>
				</div>
				<a href="javascript:;" class="btn btn-offer btnReg">등록</a>

			</div>
		</div>
		<form id="wrapEdit" class="hidden" action="/plusadmin/ajax/user/evaluationExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="evtId" id="evtId" value="" />
			<input type="hidden" name="levName" id="levName" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
		<table class="tbl-st writeT">
	        	<colgroup>
	        		<col width="20%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr class="EDIT hidden">
	        			<th>번호 <em class="point">*</em></th>
	        			<td class="levId">112</td>
	        		</tr>
	        		<tr>
	        			<th>레벨 <em class="point">*</em></th>
	        			<td>
	        				<div class="mb10">
		        				<select class="s_p1 code LEV" type="select" data="levId">
		        					<option>레벨</option>
		        				</select>
<%--		        				<select class="s_p1">--%>
<%--		        					<option>과목</option>--%>
<%--		        				</select>--%>
	        				</div>
	        			</td>
	        		</tr>

	        		<tr>
	        			<th>내용 <em class="point">*</em></th>
	        			<td><textarea class="form-control html-editor" name="evtText" id="evtText" style="height:200px;">에디터 영역</textarea></td>
	        		</tr>
	        		<tr>
	        			<th>상태 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code USE_YN" type="radio" data="useYn"></div>
							<p class="point mt5">※ 공개 시 바로 사용자에게 문제가 노출됩니다.</p>
	        			</td>
	        		</tr>
					<tr class="EDIT hidden">
						<th>등록/수정</th>
						<td class="show_iddate">이지현(admin) yyyy-mm-dd hh:mm:ss</td>
					</tr>
	        	</tbody>
	        </table>

	        <div class="tbl-foot">
				<a href="AD_LE_01_01.html" class="btn btn-list btnList">목록</a>
				<a href="#" class="btn btn-offer btnSubmit">저장</a>
			</div>
		</form>
<script type="text/javascript">
var gridElement =null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,ACV,LEV,ACA,UNIT,SUB,CPT,CONTYPEALL'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v,true)
      $('.codex.'+k).addCodeItem(v,true)
    });
    $('.code.CONTYPEALL :radio').click(function(){
    	$('tr.CONTYPEALL').hide();
		$('tr.CONTYPEALL.'+$(this).attr('value')).show()
	})





    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            bbTitle:{required:true}
            ,bbOpen:{required:true}
        };
        pageContentLast.find('form').data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);


    }

    //페이지 래디 정의
    plus.event.tabReady=function(){
        plus.evnet.submitAfter=function(){
            $('#wrapList').show();
            $('#wrapEdit').hide();

            var info = gridElement.page.info();
            //console.log(info);
            //alert(JSON.stringify(info));
            gridElement.ajax.reload(null,false);
        }
        /* 그리드완료된후 호출 */
        plus.event.gridComplet=function(){
            //전체선택 체크박스 클릭
        }
        plus.event.seqCheckBox=function(d, t, r){
            var div = plus.makeElement('div','',{'class':'custom-control custom-checkbox'});
            var id = plus.getId(r.bbSeq);
            plus.makeInput('checkbox','',{'class':"custom-control-input biSeq",id:"bbSeq"+(id),value:r.bbSeq}).appendTo(div);
            plus.makeElement('label','',{'class':'custom-control-label',for:'bbSeq'+(id)}).appendTo(div);
            return div.prop('outerHTML')
        }
        plus.event.clickbox=function(d, t, r){
          var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
          return div.prop('outerHTML')
        }
		// <th>개념코드</th>
		// 				<th>과목</th>
		// 				<th>성취기준</th>
		// 				<th>개념요소명</th>
		// 				<th>콘텐츠타입</th>
		// 				<th>내용</th>
		// 				<th>상태</th>
		// 				<th>등록/수정</th>
        // gridColumn.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data':'entId','title':'순번',render:plus.event.rownum});
        gridColumn.push({'data':'levName','title':'레벨명'});

        gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});

        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/evaluationList',{},'resultList'),{attr:'속성'});



        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.clickkbox',function () {
            var rowData =  gridElement.row( $(this).closest('tr') ).data();
            var info = gridElement.page.info();
            console.log(info);
            var tabTitle  = String.format('[{0}] {1}',rowData['abName'],'');
            //console.log(rowData);
            //plus.frontTab.addTab(tabTitle,rowData,$('#wrapEdit').tmpl({updateUrl:'/front/ajax/assets/buildingExcute',deleteUrl:'/front/ajax/assets/buildingDelete'}));
            $('#wrapList').hide();
            rowData['start']=info['start']
            rowData['length']=info['length']
          console.log(rowData);
            plus.frontPage.show($('#wrapEdit'),rowData,'EDIT');

        });
    }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();


    /* 등록 버튼*/
    $('.btnReg').click(function(){
    	$('#wrapList').hide();
      plus.frontPage.show($('#wrapEdit'), {evtId:'0','start':0,length:0,useYn:'y',evtText:'',bbDate:(new Date()).format('yyyy-MM-dd')},'NEW');
    });
    /* 삭제 버튼*/
    $('.btnDelete').click(function(){
          var delBbsSeqs = [];
          $.each($('#gridElement').find(':checked'),function(){
            delBbsSeqs.push($(this).val());
          });
          if(delBbsSeqs.length==0){
            alert('1건 이상의 데이터를 선택해주세요');
          }
          // plus.event.tplDelete($(this).closest('form'),);
          // $(this).closest('form').submit();

        if(confirm('삭제 하시겠습니까?')) {
          $.call('/plusadmin/ajax/bbs/bbsDelete', {delBbsSeqs: delBbsSeqs.join(',')}, function(r) {
            plus.event.tplCallback(r)
            gridElement.ajax.reload();
          })
        }
    });
    $('.btnList').click(function() {
      $('#wrapList').show();
      $('#wrapEdit').hide();
    });
    $('.btnSubmit').click(function(){


    	// var contypeall =   $('.code.CONTYPEALL :radio:checked').val();
    	// if(contypeall=='T'){
		// 	var editorId = $('.html-editor').attr('id');
		// 	oEditors.getById[editorId].exec("UPDATE_CONTENTS_FIELD", []);
		// 	if($('#notText').val()==""){
		// 		Swal.fire(
		// 				'[내용] 항목은 필수입니다.',
		// 				'',
		// 				'error'
		// 		)
		// 		return false;
		// 	}
		// }
      $(this).closest('form').submit();
      return false;
    });
    $(':file').change(function(){
      var fileOne = $(this).get(0).files[0];
      if(fileOne){
        $(this).closest('td').find('.upload-name').val(fileOne.name);
        $(this).closest('td').find('img').attr('src',URL.createObjectURL(fileOne));
      }
      console.log(fileOne);
    });


});

</script>