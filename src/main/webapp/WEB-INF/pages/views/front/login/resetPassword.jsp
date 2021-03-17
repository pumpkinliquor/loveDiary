<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<body class="join_body">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>비밀번호 재설정</h1>
		</header>
		<div class="container join">
			<section class="contents">
				<form id="form" name="form" method="post">
					<input type="hidden" name="accessKey" value="<c:out value="${paramMap.accessKey}" />" />
					<input type="hidden" name="memUserid" value="<c:out value="${paramMap.memUserid}" />" />
					
					<div class="member_join_cont">
						<div class="member_ipt_div">
							<h2>비밀번호</h2>
							<div class="ipt_div">
								<input type="password" name="memPassword" value="">
								<a href="#" class="ico_pw_view disabled" style="display:none;">비밀번호 보기</a>
							</div>
							<p class="er_txt passwordErrorArea"><!-- 영문, 숫자, 특수문자 중 2종류 조합하여 10자리 이상으로 입력하세요. --></p> 
						</div> 
						<div class="member_ipt_div">
							<h2>비밀번호 확인 </h2>
							<input type="password" name="comparePassword" value="">
							<p class="er_txt passwordErrorCompareArea"><!-- 비밀번호가 일치하지 않슷빈다 --></p>
						</div>
						<button type="button" class="btn btn_join" onclick="fnUpdatePassword();">확인</button> 
					</div><!-- //member_ipt_cont -->
				</form> 
			</section>
		</div><!-- //container -->
	</div>
</body>

<script type="text/javascript">

qSelect();
chkAll();

var $form = $("#form");

$(document).ready(function(){

	if($("input[name='accessKey']").val() == ''){
		alert('비정상적인 접근입니다.');
		window.location.href = '/front/login';
	}

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
	
	// 비밀번호 입력 시 유효성 검사
	$("input[name='memPassword'], input[name='comparePassword']").on('keyup', function(){
		checkPassword( $("input[name='memPassword']").val(), $("input[name='comparePassword']").val(), '' );
		if($(this).val() == ""){
			$(".passwordErrorArea").text("");
			$(".passwordErrorCompareArea").text("");
		}
	});
	
});

$(document).on("keyup", "[name=memPassword]", function() {
	if($(this).val() != "") {
		$(this).siblings("a").css("display", "");
	} else {
		$(this).siblings("a").css("display", "none");
	}
});

// 로그인
function fnUpdatePassword(){
	
	var param = $form.domJson();
	if( checkPassword( param.memPassword, param.comparePassword, 'passwordErrorArea' ) ){
		$.call("/front/ajax/login/updatePassword", param, function(returnJson){
			var json = returnJson;
			var $passwordErrorArea = $('.passwordErrorArea');
			if(json.resultCode == 'S00'){
				$passwordErrorArea.text('');
				commonModalPopup('변경된 비밀번호로 로그인 해주세요.');
				$(".popupConfirm").click({}, goLogin);
			}else{
				if(json.resultCode == 'F01'){
					commonModalPopup('계정정보가 누락되었습니다.<br/>처음부터 다시 진행해주세요.');
					$(".popupConfirm").click({}, goFindAccount);
				}else if(json.resultCode == 'F02'){
					$passwordErrorArea.text('비밀번호를 입력해주세요.');
					return;
				}else if(json.resultCode == 'F03'){
					commonModalPopup('비밀번호 변경 중 오류가 발생하였습니다.<br/>관리자에게 문의하세요.');
					return;
				}else{
					commonModalPopup('시스템 오류입니다.<br/>관리자에게 문의하세요.');
					return;
				}
			}
		});
	}
}

function goLogin(){
	window.location.href = '/front/login';
}

function goFindAccount(){
	window.location.href = '/front/login/findAccount';
}

</script>