<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>접속이력</h2>
<table class="srchT">
	<colgroup>
		<col width="18%">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th>회원계정</th>
			<td><input type="text" name="memUserid" id="memUserid" class="ipt1"></td>
		</tr>
		<tr>
			<th>조회기간</th>
			<td>
				<div class="date_div">
					<span class="ipt_dates"><input type="date" class="ipt_date" id="sdate" name="sdate" value="" /></span>
					<span class="ipt_dates"><input type="date" class="ipt_date" id="edate" name="edate" value="" /></span>
				</div>
				<button type="button" class="btn btn-st1 btn_srch btnSearch">검색</button>
			</td>
		</tr>
	</tbody>
</table>
<table class="tbl-st2" id="gridElement">
	<colgroup>
		<col width="7%">
		<col width="10%">
		<col width="*">
		<col width="*">
		<col width="10%">
		<col width="10%">
		<col width="10%">
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>구분</th>
			<th>실행일지</th>
			<th>종료일지</th>
			<th>실행시간(분)</th>
			<th>접속기기</th>
			<th>초기화</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<script>

var gridElement = null, gridColumn = [];

function memReset(memId){
	if(confirm('해당 계정을 초기화 하시겠습니까?')){

	}
}
$(document).ready(function(){

	/* 날짜값 세팅 */
	var sDate = new Date();
	var eDate = new Date();
	sDate.setMonth(sDate.getMonth()-1);

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
	plus.event.tabAfter=function(pageContentLast, rowData, mode){
		// 유효성검사 할 항목 정의
		var rules = {
			sDate:{required:true}
			, edate:{required:true}
		};
		pageContentLast.data({rules:rules});
		var tableElement =pageContentLast.find('table');
		plus.event.formAfter(pageContentLast,rowData,mode);
		plus.event.bbsfile(rowData);
	};

	// 리스트 내 row 선택 이벤트
	plus.event.clickbox = function(d, t, r){
		var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
		return div.prop('outerHTML');
	}
	$('.btnSearch').click(function(){
		var searchParam = $('.srchT').domJson();
		gridElement = plus.makeGrid('#gridElement', gridColumn, plus.makeAjax('/plusadmin/ajax/member/memberLoginLogList', searchParam, 'resultList'), {pageLength : 10, attr : '속성'});
	});
	var searchParam = $('.srchT').domJson();

	gridColumn.push({'data':'joinCount',		'title':'번호',render:plus.renderer.rrownum });
	gridColumn.push({'data':'mllReason',	'title':'구분'});
	gridColumn.push({'data':'mllDatetime',	'title':'실행일시',render:plus.renderer.datetime });
	gridColumn.push({'data':'mllDatetime',	'title':'종료일시',render:plus.renderer.datetime });
	gridColumn.push({'data':'joinCount',	'title':'실행시간(분)',render:function(){return '1';}});
	gridColumn.push({'data':'mllUseragent',	'title':'접속기기',render:plus.renderer.os });
	gridColumn.push({'data':'memId',	'title':'초기화',render:function(r){return '<a href="javascript:memReset('+(r)+');">초기화</a>';}});
	gridElement = plus.makeGrid('#gridElement', gridColumn, plus.makeAjax('/plusadmin/ajax/member/memberLoginLogList', searchParam, 'resultList'), {pageLength : 10, attr : '속성'});

});

</script>