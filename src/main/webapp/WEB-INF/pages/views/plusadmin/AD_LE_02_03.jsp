<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>해설관리</h2>
<table class="srchT">
	<colgroup>
		<col width="150px">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th rowspan="2">검색</th>
			<td>
				<div class="mb10">
					<span class="tit">등록일</span>
					<div class="date_div">
						<span class="ipt_dates"><input type="date" class="ipt_date" id="sdate" name="sdate"></span><span>~</span>
						<span class="ipt_dates"><input type="date" class="ipt_date" id="edate" name="edate"></span>
					</div>
				</div>
				<div>
					<span class="tit">상태</span>
					<div class="radio_wrap rdo_wrap1 codex USE_YN_ALL" type="radio" data="searchUseYn"></div>

				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="srch_div mb10">
					<select class="s_p1 codex LEV" type="select" data="levId" name="levId">
						<option>레벨</option>
					</select>
					<select class="s_p1 codex SUB" type="select" data="subId" name="subId">
						<option>과목</option>
					</select>
					<select class="s_p2 codex UNIT" type="select" data="unitId" name="unitId">
						<option>대단원</option>
					</select>
					<select class="s_p4 codex ACV" type="select" data="acvId" name="acvId">
						<option>성취기준</option>
					</select>
				</div>
				<div class="srch_div">
					<select class="s_p1" name="searchType">
						<option value="">검색구분</option>
						<option value="qstKey">문항코드</option>
						<option value="cmtrKey">해설코드</option>
						<option value="notKey">개념코드</option>
					</select>
					<input type="text" name="searchString" class="ipt3">
					<button type="submit" class="btn btn-st1 search">검색</button>
				</div>
			</td>
		</tr>
	</tbody>
</table>

<table id="dataT" class="table table-bordered table-hover dataTable dtr-inline fs15 gridElement">
	<colgroup>
		<col width="10%">
		<col width="20%">
		<col width="20%">
		<col width="15%">
		<col width="*">
	</colgroup>
	<thead>
		<tr>
			<th class="sort_x">번호</th>
			<th>문항코드</th>
			<th>해설코드</th>
			<th>상태</th>
			<th class="sort_x">등록/수정</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<div class="tbl-foot">
	<a href="#" class="btn btn-offer btnReg">등록</a>
</div>

<!-- 해설 등록/수정영역 -->
<div class="modal-wrap pop01 commentaryEditPopup">
	<div class="modal">
		<div class="modal-header">
			<h1>해설등록/수정</h1>
		</div>
		<form id="wrapEdit" action="/plusadmin/ajax/aigo/commentaryExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cmtrId" id="cmtrId" value="" />
			<input type="hidden" name="qstId" id="qstId" value="" />
			<input type="hidden" name="dataJson" id="dataJson" value="" />

			<div class="content">
				<table class="srchT mb">
					<colgroup>
						<col width="120px">
						<col width="*">
					</colgroup>
					<tbody>
						<tr class="EDIT">
							<th>검색</th>
							<td>
								<div class="srch_div mb10">
									<select class="s_p2 codex LEV" type="select" id="subId" name="searchSubjectPopup">
										<option>과목</option>
									</select>
									<select class="s_p4 codex ACV" type="select" id="acvId" name="searchAchievePopup">
										<option>성취기준</option>
									</select>
								</div>
								<div class="srch_div">
									<select class="s_p2" name="searchTextTypePopup">
										<option value="questionCode">문항코드</option>
									</select>
									<input type="text" name="searchTextPopup" id="qstKey" class="ipt4">
									<button type="button" class="btn btn-st1 searchEditPopup">검색</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<h2>문항정보</h2>
				<table class="popT2 mb qstInfo">
					<colgroup>
						<col width="20%">
						<col width="*">
						<col width="20%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr class="EDIT NEW">
							<th>문항코드</th>
							<td class="qstKey" colspan="3"></td>
						</tr>
						<tr class="EDIT NEW">
							<th>과목 <em class="point">*</em></th>
							<td class="subName clearx" ></td>
							<th>레벨 <em class="point">*</em></th>
							<td class="levName clearx"></td>
						</tr>
						<tr class="EDIT NEW">
							<th>단원분류 <em class="point">*</em></th>
							<td class="unitName clearx"></td>
							<th>성취기준 <em class="point">*</em></th>
							<td class="acvName clearx"></td>
						</tr>
						<tr class="EDIT NEW">
							<th>연결개념</th>
							<td class="notName clearx" colspan="3"></td>
						</tr>
					</tbody>
				</table>
				<h2>해설등록/수정</h2>
				<table class="popT2 writeT mb">
					<colgroup>
						<col width="20%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr class="EDIT ">
							<th>콘텐츠타입</th>
							<td>
								<div class="radio_wrap rdo_wrap1 code CONTYPEALL" type="radio" data="cmtrType"></div>

							</td>
						</tr>
						<tr class="CONTYPEALL I">
							<td colspan="2" class="image file" data="image">

							</td>
						</tr>

						<tr class="CONTYPEALL T">
							<td colspan="2">
								<div class="editor_div">
									<textarea class="form-control html-editor" name="cmtrText" id="cmtrText" style="height:200px;"></textarea>
								</div>
							</td>
						</tr>

						<tr class="CONTYPEALL P">
							<th>썸네일 <em class="point">*</em></th>
							<td class="fileMap">
								<div class="add_file_div inlineB">
									<div class="filebox">
										<label for="pcthumb" class="btn btn-st1">파일찾기</label>
										<input class="upload-name" value="파일명" disabled="disabled">
										<i class="fas fa-times fa-times2"></i>
										<label for="pcthumb" class="file-bg hidden">Download</label>
										<input type="file" id="pcthumb" name="pcthumb" class="upload-hidden file-upload">
									</div>
	<%--		        				<p class="point">※ 이미지 사이즈 000*000</p>--%>
								</div>
								<div class="thumb_img file-img2"><img src="/admassets/images/tmp_thumb.jpg" onerror="this.src='/admassets/images/tmp_thumb.jpg'"></div>
							</td>
						</tr>
						<tr class="CONTYPEALL P">
							<th>영상타이틀</th>
							<td>
								<input type="text" name="cmtrPlayName" id="cmtrPlayName" placeholder="미입력시 개념요소명으로 노출됩니다">
								<p class="point mt5">※ 공백포함 최대 00자</p>
							</td>
						</tr>
						<tr class="CONTYPEALL P">
							<th>영상경로</th>
							<td>
								<input type="text" name="cmtrPlayPath" id="cmtrPlayPath">
							</td>
						</tr>
						<tr class="CONTYPEALL P">
							<th>영상정보</th>
							<td>
								<div class="mb10">
									<span class="tit">런타임</span>
									<input type="text" name="cmtrPlayRunTime" id="cmtrPlayRunTime" class="ipt_m">
									<span class="point">※ hh:mm:ss 형식으로 입력</span>
								</div>
								<div>
									<span class="tit">화질</span>
									<input type="text" name="cmtrPlayRunRate" id="cmtrPlayRunRate" class="ipt_m">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				
				<table class="popT2 writeT mb">
					<colgroup>
						<col width="20%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr class="EDIT">
							<th>상태</th>
							<td>
								<div class="radio_wrap rdo_wrap1 code USE_YN" type="radio" data="useYn"></div>
							</td>
						</tr>
						<tr class="EDIT hidden">
							<th>등록/수정</th>
							<td class="show_iddate"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary fR btnSubmit">저장</button>
			</div>
			</form>
			<a href="#" class="close-pop close-btn">&#10005;</a>
		</div>
	</div> 


<script>

var gridElement = null, gridColumn = [];

$(document).ready(function(){

	/* 코드 사용 */
	$.call('/ajax/codeList',{codes:'BI,USE_YN,ACV,LEV,ACA,UNIT,SUB,CONTYPEALL,USE_YN_ALL'},function(r){
		$.extend(plus.codes,r.codes);
	});
	$.each(plus.codes,function(k,v){
		$('.code.'+k).addCodeItem(v);
		$('.codex.'+k).addCodeItem(v,true)
	});
	$('.code.CONTYPEALL :radio').click(function(){
		$('tr.CONTYPEALL').hide();
		$('tr.CONTYPEALL.'+$(this).attr('value')).show()
	})

	/* 날짜값 세팅 */
	var sDate = new Date();
	var eDate = new Date();
	sDate.setMonth(sDate.getMonth()-1);

	$("#sdate").val(sDate.format('yyyy-MM-dd'));
	$("#edate").val(eDate.format('yyyy-MM-dd'));
	
	/* tab 생성후 초기이벤트*/
	plus.event.tabAfter=function(pageContentLast, rowData, mode){
		// 유효성검사 할 항목 정의
		var rules = {
			subName:{required:true}
			,levName:{required:true}
			,unitName:{required:true}
			,acvName:{required:true}
			, useYn:{required:true}
		};
		$('.clearx').html('');
		pageContentLast.data({rules:rules,'mode':mode});
		var tableElement =pageContentLast.find('table');

		rowData['cmtrType'] = (rowData['cmtrType']||'I');
		rowData['cmtrType'] = rowData['cmtrType']=='NULL'?'I':rowData['cmtrType'];

		plus.event.formAfter(pageContentLast,rowData,mode);
		plus.event.bbsfile(rowData);
	};
	
	// 페이지 초기 데이터 로드
	plus.event.tabReady = function(){
		$('.USE_YN_ALL :radio:eq(0)').click();
		plus.evnet.submitAfter = function(){
			$('.close-btn').click();
			var info = gridElement.page.info();
			gridElement.ajax.reload(null,false);
		}
		/* 그리드 완료된 후 호출 */
		plus.event.gridComplet=function(){
		}
		//전체선택 체크박스 클릭
		/*
		plus.event.seqCheckBox = function(d, t, r){
			var div = plus.makeElement('div','',{'class':'custom-control custom-checkbox'});
			var id = plus.getId(r.bbSeq);
			plus.makeInput('checkbox','',{'class':"custom-control-input biSeq",id:"bbSeq"+(id),value:r.bbSeq}).appendTo(div);
			plus.makeElement('label','',{'class':'custom-control-label',for:'bbSeq'+(id)}).appendTo(div);
			return div.prop('outerHTML');
		}
		*/
		
		// plus.event.clickbox = function(d, t, r){
		// 	var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
		// 	return div.prop('outerHTML');
		// }
		
		gridColumn.push({'data':'cmtrId',	'title':'번호'});
		gridColumn.push({'data':'qstKey',	'title':'문항코드',		render:plus.renderer.clickbox});
		gridColumn.push({'data':'cmtrKey',	'title':'해설코드',		render:plus.renderer.clickbox});
		gridColumn.push({'data':'useYn',	'title':'상태',			render:plus.renderer.code,			code:plus.codes['USE_YN']});
		gridColumn.push({'data':'regDate',	'title':'등록/수정',		render:plus.renderer.iddate});	// 등록일 부분 수정해야함
		gridElement = plus.makeGrid('.gridElement', gridColumn, plus.makeAjax('/plusadmin/ajax/aigo/commentaryList', {}, 'resultList'), {pageLength : 10, attr : '속성'});
	}
	
	/* 페이지 초기에 실행할 이벤트*/
	plus.event.tabReady();
	
	// 검색 연동
	$('.search').on('click', function(){

		var searchParam = $('.srchT').domJson();
		gridElement = plus.makeGrid('.gridElement', gridColumn, plus.makeAjax('/plusadmin/ajax/aigo/commentaryList', searchParam, 'resultList'), {pageLength : 10, attr : '속성'});
	});
	
	// 수정 팝업
	$('.gridElement tbody').on('click','.clickkbox',function () {
		
		var rowData = gridElement.row( $(this).closest('tr') ).data();
		var info = gridElement.page.info();
		var $form = $("#wrapEdit");


		rowData['cmtrText'] = rowData['cmtrText']||'';

		console.log(rowData);
		plus.frontPage.popup($form, rowData, 'EDIT');
		$("input[name='searchTextPopup']").prop("readonly", true);
		
		goSearch('EDIT');
		
	});
	
	// 등록/수정팝업 검색
	$(".searchEditPopup").on('click', function(){
		if(!$.trim($("input[name='searchTextPopup']").val())){
			alert('문항 코드는 필수입니다.');
			return false;
		}
		$('#wrapEdit').data()
		goSearch('NEW');
	});
	
	function goSearch(mode){


		var searchParam = {
			qstKey : $("input[name='searchTextPopup']").val()
		};
		$.call('/plusadmin/ajax/aigo/searchQuestionInfo', searchParam, function(r){
			delete r['useYn'];
			delete r['cmtrId'];
			delete r['subId'];
			delete r['levId'];
			delete r['acvId'];
			plus.event.formAfter($('#wrapEdit'), r, mode);
		});
	}
	
	// 컨텐츠 타입 설정
	$("input:radio[name='cmtrType']").on('change', function(){
		var $this = $(this);
		$("select[name='uploadType']").val('path').trigger('change');
		if($this.val() == 'T'){
			$('.contentsText').show();
			$('.contentsMedia').hide();
		}else{
			$('.contentsMedia').show();
			$('.contentsText').hide();
		}
	});

	
	// 업로드 방법 선택
	$("select[name='uploadType']").on('change', function(){
		var $this = $(this);
		if($this.val() == 'path'){
			$('.typePath').show();
			$('.typeUpload').hide();
		}else{
			$('.typeUpload').show();
			$('.typePath').hide();
		}
	});
	
	// 등록 팝업
	$('.btnReg').click(function(){
		var $form = $('#wrapEdit');
		var param  = $form.domJson();
		$form.find("input[name='searchTextPopup']").prop("readonly", false);
		$form.find("input[name='searchTextPopup']").val("");
		$.each(param, function(k,v){
			param[k] = '';
		})
		param['cmtrText'] = '';
		param['useYn'] = 'y';
		param['cmtrText'] = '';
		param['cmtrType'] = 'I';
		goSearch('NEW');
		plus.frontPage.popup($('.commentaryEditPopup').find('form'), param,'NEW');
	});
	
	// 수정/저장
	$('.btnSubmit').unbind('click').click(function(){

		var xstr= '';
		$.each($('.clearx'),function(k,v){
			xstr+=$.trim($(this).text())
		});
		if(!xstr){
			Swal.fire(
						'[과목,레벨,단원분류,성취기준준]항목은 필수입니다.',
						'',
						'error'
				)
				return false;
		}
		var editorId = $('.html-editor').attr('id');
			oEditors.getById[editorId].exec("UPDATE_CONTENTS_FIELD", []);
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