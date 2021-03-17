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
								<a href="#" class="ico_pw_view disabled">비밀번호 보기</a>
								<!-- <a href="#" class="ico_pw_view disabled">비밀번호 보기</a> 비활성상태일 때-->
							</div>
							<p class="er_txt passwordErrorArea"><!-- 영문, 숫자, 특수문자 중 2종류 조합하여 10자리 이상으로 입력하세요. --></p> 
						</div> 
						<div class="member_ipt_div">
							<h2>비밀번호 확인 </h2>
							<input type="password" name="comparePassword" value="">
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
				alert('변경된 비밀번호로 로그인 해주세요.');
				window.location.href = '/front/login';
			}else{
				if(json.resultCode == 'F01'){
					alert('계정정보가 누락되었습니다.');
					return;
				}else if(json.resultCode == 'F02'){
					$passwordErrorArea.text('비밀번호를 입력해주세요.');
					return;
				}else if(json.resultCode == 'F03'){
					alert('비밀번호 변경 중 오류가 발생하였습니다.\n관리자에게 문의하세요.');
					return;
				}else{
					alert('시스템 오류입니다.\n관리자에게 문의하세요.');
					return;
				}
			}
		});
	}
}

</script>