<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<%@ include file="/WEB-INF/pages/views/front/common/sns.jsp"%>

<body class="join_body">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>로그인</h1>
		</header>
		<div class="container join">
			<section class="contents">
				<form id="form" name="form" method="post">
					<div class="member_join_cont">
						<div class="member_ipt_div">
							<h2>이메일</h2>
							<div class="ipt_div">
								<input type="text" name="memUserid" value="" maxlength="100"/>
							</div>
							<p class="er_txt emailErrorArea"><!-- 올바른 형식의 이메일을 입력하세요. --></p> 
						</div> 
						<div class="member_ipt_div">
							<h2>비밀번호</h2>
							<div class="ipt_div">
								<input type="password" name="memPassword" value="" maxlength="30">
								<a href="#" class="ico_pw_view disabled">비밀번호 보기</a>
							</div>
							<p class="er_tx passwordErrorArea"><!-- 영문, 숫자, 특수문자 중 2종류 조합하여 10자리 이상으로 입력하세요. --></p> 
						</div> 
						<button type="button" class="btn btn_join" onclick="fnLogin();">로그인</button> 
						<div class="login_btm_div">
							<div class="fL"><span class="ico_find"></span><a href="#!" onclick="goFindAccount();">아이디 / 비밀번호 찾기</a></div>
							<div class="fR"><span class="ico_join"></span><a href="#!" onclick="goJoin();">회원가입</a></div>
						</div>
						<div class="login_btn_div">
							<a href="#!" onclick="fnLoginSns('kakao');" class="btn btn_kakao"><span>카카오로그인</span></a>
							<a href="#!" onclick="fnLoginSns('facebook');" class="btn btn_facebook"><span>페이스북로그인</span></a>
							<a href="#!" onclick="fnLoginSns('naver');" class="btn btn_naver"><span>네이버로그인</span></a>
							<a href="#!" onclick="fnLoginSns('apple');" class=""><span>apple ID 로그인</span></a>
						</div>
					</div><!-- //member_ipt_cont -->
				</form> 
			</section>
		</div><!-- //container -->
	</div>
</body>

<script type="text/javascript">

//qSelect();
chkAll();

var $form = $("#form");

$(document).ready(function(){
	
	// 비밀번호 보기 활성화/비활성화
	$('.ico_pw_view').on('click', function(){
		var $this = $(this);
		var $memPassword = $("input[name='memPassword']");
		if($this.hasClass('disabled')){
			$this.removeClass('disabled');
			$memPassword.attr("type", "text");
		}else{
			$this.addClass('disabled');
			$memPassword.attr("type", "password");
		}
	});
	
});

// 아이디/비밀번호 찾기
function goFindAccount(){
	window.location.href = "/front/login/findAccount";
}

// 회원가입 페이지 이동
function goJoin(){
	window.location.href = "/front/join";
}

// 로그인
function fnLogin(){
	
	var param = $form.domJson();
	
	// 이메일 체크
	if(checkEmail( $("input[name='memUserid']").val(), 'emailErrorArea' )){
		//$form.attr("action","/front/login/loginProc").submit();
		$.call("/front/ajax/login/loginProc", param, function(returnJson){
			var $emailErrorArea = $('.emailErrorArea');
			var $passwordArea = $('.passwordErrorArea');
			var errorMsg = '';
			if(returnJson.resultCode == '91'){
				window.location.href = '/front/main';
			} else if (returnJson.resultCode == '92') {
				window.location.href = '/front/pre/main';
			} else if (returnJson.resultCode == '93') {
				window.location.href = '/front/learn/home';
			} else if (returnJson.resultCode == '10' || returnJson.resultCode == '20') {
				errorMsg = '이메일과 비밀번호는 필수 항목입니다.';
			} else if (returnJson.resultCode == '30') {
				errorMsg = '등록된 이메일이 아닙니다.회원가입을 통해 계정을 만드세요.';
			} else if (returnJson.resultCode == '40') {
				errorMsg = '이메일 또는 비밀번호가 올바르지 않습니다.';
			} else {
				errorMsg = '';
				alert('시스템 오류입니다.\n관리자에게 문의하세요.');
				return;
			}
			$emailErrorArea.text(errorMsg);
		});
	}
}

</script>