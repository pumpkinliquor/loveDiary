<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>회원집계</h2>
<table class="srchT">
	<colgroup>
		<col width="18%">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th>기간</th>
			<td>
			 	<form>
					<div class="date_div">
						<input type="date" class="ipt_date" id="sdate" name="sdate" value="" /><span>~</span>
						<input type="date" class="ipt_date" id="edate" name="edate" value="" />
					</div>
					<button type="button" class="btn btn-st1 btn_srch btnSearch">검색</button>
				</form>
			</td>
		</tr>
		<tr class="EDIT">
			<th>사용자</th>
			<td><span class="totalUserCount">-</span></td>
		</tr>
		<tr class="EDIT">
			<th>신규설치자</th>
			<td><span class="totalInsCount">-</span></td>
		</tr>
		<tr class="EDIT">
			<th>가입자</th>
			<td><span class="totalJoinCount">-</span></td>
		</tr>
	</tbody>
</table>
<table id="dataT" class="table table-bordered table-hover dataTable dtr-inline gridElement">
	<colgroup>
		<col width="25%">
		<col width="25%">
		<col width="25%">
		<col width="25%">
	</colgroup>
	<thead>
		<tr>
			<th>일자</th>
			<th>사용자</th>
			<th>설치자</th>
			<th>가입자</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<script>

var gridElement = null, gridColumn = [];

$(document).ready(function(){
	
	/* 날짜값 세팅 */
	var sDate = new Date();
	var eDate = new Date();
	sDate.setMonth(sDate.getMonth()-1);

	console.log(sDate.format('yyyy-MM-dd'));
	$("#sdate").val(sDate.format('yyyy-MM-dd'));
	$("#edate").val(eDate.format('yyyy-MM-dd'));
	
	/* 코드 사용 */
	$.call('/ajax/codeList',{codes:''},function(r){
		$.extend(plus.codes,r.codes);
	});
	$.each(plus.codes,function(k,v){
		$('.code.'+k).addCodeItem(v);
		$('.codex.'+k).addCodeItem(v,true)
	});
	
	/* tab 생성후 초기이벤트*/
	plus.event.tabAfter = function(pageContentLast, rowData, mode){
		// 유효성검사 할 항목 정의
		var rules = {
			sDate:{required:true}
			, edate:{required:true}
		};
		pageContentLast.data({rules:rules});
		var tableElement = pageContentLast.find('table');
		plus.event.formAfter(pageContentLast, rowData, mode);
	};
	
	// 리스트 내 row 선택 이벤트
	plus.event.clickbox = function(d, t, r){
		var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
		return div.prop('outerHTML');
	}
	$('.btnSearch').click(function(){
		var searchParam = $('.srchT').domJson();
		gridElement = plus.makeGrid('.gridElement', gridColumn, plus.makeAjax('/plusadmin/ajax/member/memberUsedDataList', searchParam, 'resultList'), {pageLength : 50, attr : '속성'});
	});
	
	var searchParam = $('.srchT').domJson();

	$.call('/plusadmin/ajax/member/memberUsedDataInfo', searchParam, function(r){
		//plus.event.formAfter($('.srchT'), r.resultValue, 'EDIT');
		$('.totalUserCount').html(r.resultValue['totalUserCount']);
		$('.totalInsCount').html(r.resultValue['totalInsCount']);
		$('.totalJoinCount').html(r.resultValue['totalJoinCount']);
	});
	
	gridColumn.push({'data':'basedate',		'title':'일자' });
	gridColumn.push({'data':'userCount',	'title':'사용자'});
	gridColumn.push({'data':'insCount',		'title':'설치자'});
	gridColumn.push({'data':'joinCount',	'title':'가입자'});
	gridElement = plus.makeGrid('.gridElement', gridColumn, plus.makeAjax('/plusadmin/ajax/member/memberUsedDataList', searchParam, 'resultList'), {pageLength : 50, attr : '속성'});
	
});

</script>