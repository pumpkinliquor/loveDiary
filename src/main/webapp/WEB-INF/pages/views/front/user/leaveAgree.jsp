<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<c:set var="summary" value="${resultMap.resultData }" />

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>탈퇴신청</h1>
		</header>
		<div class="container leave">
			<form id="form" name="form" method="post">
				<input type="hidden" name="leaveCode" value="1" />
				<input type="hidden" name="leaveReason" value="" />
				<div class="contents scroll_y">
					<p class="txt1">회원님은 <em><fmt:formatDate value="${summary.startDate }" pattern="yyyy-MM-dd" /></em>부터 AIGo와 함께하고 있으며 탈퇴 시 <em><fmt:formatNumber value="${summary.qstCnt }" pattern="#,###" />개</em>의 문제의 풀이 이력과 <em><fmt:formatNumber value="${summary.reportCnt }" pattern="#,###" />개</em>의 리포트 결과가 영원히 사라집니다.</p>
					<p class="txt2">그래도 탈퇴 하시겠습니까?</p>
					<ul class="leave_reason">
						<li class="active"><button type="button" onclick="setLeaveCode(1, this);">더 이상 AIGo에서 공부하고 싶지 않아요.</button></li>
						<li><button type="button" onclick="setLeaveCode(2, this);">다른 더 좋은 수학 학습앱이 있어요.</button></li>
						<li><button type="button" onclick="setLeaveCode(3, this);">탈퇴 후 재가입 하고 싶어요.</button></li>
						<li><button type="button" onclick="setLeaveCode(4, this);">기타사유</button></li>
					</ul> 
					<div class="chkbox_div">
						<input type="checkbox" id="chk01" class="ipt_chk" name="leave_agree">
						<label class="lable_chk" for="chk01">모든 데이터를 삭제하고 AIGo를 탈퇴하는 것에 동의합니다.</label>
					</div>
					<button type="button" class="btn btn_st6" disabled onclick="fnLeave();">탈퇴하기</button>
				</div>
			</form>
		</div><!-- //container -->
	</div>
</body>



<script type="text/javascript">

leaveBtn();

var $form = $("#form");

$(document).ready(function(){
	
    $('.ipt_chk').click(function(){
	    if($("input[name='leave_agree']:checkbox").prop('checked')){
	        $('.btn_st6').attr("disabled", false);
	    }else{
		$('.btn_st6').attr("disabled", true);
	    }
	});
    
	$form.find("input[name='leaveReason']").val($('.leave_reason .active button').text());
	
});

function setLeaveCode(num, obj){
	$form.find("input[name='leaveCode']").val(num);
	$form.find("input[name='leaveReason']").val($(obj).text());
}

function fnLeave(){
    console.log("leaved");
}

</script>