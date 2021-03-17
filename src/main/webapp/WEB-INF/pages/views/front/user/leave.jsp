<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>탈퇴신청</h1>
		</header>
		<div class="container leave">
			<div class="contents">
				<form id="form" name="form" method="post">
				<h2>계정 확인</h2>
				
				<c:choose>
				<%-- kakao 계정 --%>
				<c:when test="${loginSessionJoinChannel eq 'kakao' }">
					<div class="login_btn_div"><a href="#" class="btn btn_kakao"><span>카카오 계정 확인</span></a></div>
					<div class="fin_div checkResultArea" style="display: none;"><p>카카오 계정 확인 완료</p></div>
				</c:when>
				<%-- facebook 계정 --%>
				<c:when test="${loginSessionJoinChannel eq 'facebook' }">
					<div class="login_btn_div"><a href="#" class="btn btn_facebook"><span>페이스북 계정 확인</span></a></div>
					<div class="fin_div checkResultArea" style="display: none;"><p>페이스북 계정 확인 완료</p></div>
				</c:when>
				<%-- naver 계정 --%>
				<c:when test="${loginSessionJoinChannel eq 'naver' }">
					<div class="login_btn_div"><a href="#" class="btn btn btn_naver"><span>네이버 계정 확인</span></a></div>
					<div class="fin_div checkResultArea" style="display: none;"><p>네이버 계정 확인 완료</p></div>
				</c:when>
				<%-- apple 계정 --%>
				<c:when test="${loginSessionJoinChannel eq 'apple' }">
					<div class="login_btn_div"><a href="#" class="btn btn_apple"><span>Apple 계정 확인</span></a></div>
					<div class="fin_div checkResultArea" style="display: none;"><p>Apple 계정 확인 완료</p></div>
				</c:when>
				<%-- 이메일 계정 --%>
				<c:when test="${loginSessionJoinChannel eq 'email' }">
					<div class="member_ipt_div">
						<div class="ipt_div ipt_edit_div">
							<input type="password" name="memPassword" value="" />
							<button type="button" class="btn_gray_s" onclick="fnCheckAccount();">확인</button>
						</div>
						<p class="er_txt passwordErrorArea"></p>
					</div> 
					<div class="fin_div checkResultArea" style="display: none;"><p>계정 확인 완료</p></div>
				</c:when>
				</c:choose>
				
				<div class="btm_btn_div2"><a href="#!" class="next disabled btnNext"><span>다음</span></a></div>
				</form>
				
			</div>
		</div><!-- //container -->
	</div>
</body>

<script type="text/javascript">

modalClose();

var $form = $("#form");

$(document).ready(function(){
	
});

// 회원정보 확인
function fnCheckAccount(){
    
    var param = $form.domJson();
	var errorMsg = '';
    
	if($("input[name='']").val() == ""){
	    $passwordArea.text("비밀번호를 정확하게 입력 해 주세요.");
	}else{
	    $.call("/front/ajax/user/checkAccount", param, function(returnJson){
			var $checkResultArea = $('.checkResultArea');
			var $passwordArea = $('.passwordErrorArea');
			$checkResultArea.hide();
			if(returnJson.resultCode == 'S00'){
			    var $btnNext = $('.btnNext');
			    $btnNext.attr('onclick','goNextStep()');
			    $btnNext.removeClass('disabled');
			    $checkResultArea.show();
			} else if (returnJson.resultCode == 'F01') {
				errorMsg = '등록된 이메일이 아닙니다.회원가입을 통해 계정을 만드세요.';
			} else if (returnJson.resultCode == 'F02') {
				errorMsg = '이메일 또는 비밀번호가 올바르지 않습니다.';
			} else {
				errorMsg = '';
				alert('시스템 오류입니다.\n관리자에게 문의하세요.');
				return;
			}
			
			$passwordArea.text(errorMsg);
		});
	}
    
}

// 탈퇴 다음단계 이동
function goNextStep(){
	$form.attr("action", "/front/user/leaveAgree").submit();
}


</script>