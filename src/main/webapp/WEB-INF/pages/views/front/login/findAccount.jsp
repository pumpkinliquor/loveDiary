<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/views/front/common/sns.jsp"%>

<body class="join_body">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>비밀번호 찾기</h1>
		</header>
		<div class="container join">
			<section class="contents">
				<form id="form" name="form" method="post">
					<input type="hidden" name="accessKey" value="" />
					<div class="member_join_cont">
						<p class="txt emailInputArea">
							회원 가입 시 사용한 이메일로 비밀번호 재설정<br>인증코드가 전송됩니다.
						</p>
						<p class="txt codeInputArea" style="display: none;">
							<em>jeehyun@123.com</em><br>으로 비밀번호 재설정 인증코드가 발송되었습니다.
						</p>
						<div class="member_ipt_div emailInputArea">
							<h2>이메일</h2>
							<div class="ipt_div">
								<input type="text" name="memUserid" id="memUserid" value="" />
							</div>
							<p class="er_txt emailErrorArea"></p> 
						</div>
						<button type="button" class="btn btn_join emailInputArea" onclick="fnSendAuthcode();" >확인</button> 
						
						<div class="member_ipt_div codeInputArea" style="display: none;">
							<h2>인증코드를 입력하세요.</h2>
							<div class="ipt_div">
								<input type="text" name="maeKey" value="" class="fwm">
							</div>
							<p class="er_txt codeErrorArea"><!-- 올바른 형식의 코드를 입력하세요. --></p> 
						</div> 
						<button type="button" class="btn btn_join codeInputArea" onclick="fnCheckAuthCode();" style="display: none;">확인</button> 
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

$(function(){
	$('#memUserid').on('keyup', function(){
		checkEmail( $("input[name='memUserid']").val(), 'emailErrorArea' );
	});
});

// 인증코드 전송
function fnSendAuthcode(){

	var param = $form.domJson();
	
	if(checkEmail( param.memUserid, 'emailErrorArea' )){
		$.call("/front/ajax/login/sendAuthcode", param, function(returnJson){
			var json = returnJson;
			var $emailErrorArea = $('.emailErrorArea');
			var errorMsg = '';
			if(json.resultCode == 'S00'){
				// 하단 인증코드 입력영역 show
				$('.emailInputArea').hide();
				$('.codeInputArea').show();
				// 이메일 세팅
				$('.txt.codeInputArea em').text(param.memUserid);
			}else if(json.resultCode == 'F01'){
				errorMsg = '올바른 형식의 이메일을 입력하세요.';
			}else if(json.resultCode == 'F02'){
				errorMsg = '존재하지 않는 계정입니다.';
			}else if(json.resultCode == 'F03'){
				alert('인증번호 생성에 실패하였습니다.');
			}
			$emailErrorArea.text(errorMsg);
		});
	}
}

// 입력 인증코드 확인
function fnCheckAuthCode(){
	
	var param = $form.domJson();
	
	$.call("/front/ajax/login/checkAuthCode", param, function(returnJson){
		var json = returnJson.resultData;
		var $codeErrorArea = $('.codeErrorArea');
		var errorMsg = '';
		if(returnJson.resultCode == 'S00'){
			// 올바른 인증번호일 경우 비밀번호 재설정 페이지로 이동
			$form.find("input[name='memUserid']").val(json.memUserid);
			$form.find("input[name='accessKey']").val(json.accessKey);
			$form.attr("action","/front/login/resetPassword").submit();
		}else{
			if(returnJson.resultCode == 'F02'){
				errorMsg = '인증번호를 입력해주세요.';
			}else if(returnJson.resultCode == 'F03'){
				errorMsg = '유효하지 않은 인증번호입니다.';
				$codeErrorArea.text('');
			}else if(returnJson.resultCode == 'F04'){
				errorMsg = '';
				alert('인증번호 처리 중 오류가 발생하였습니다.\n관리자에게 문의하세요.');
				return;
			}else{
				errorMsg = '';
				alert('시스템 오류입니다.\n관리자에게 문의하세요.');
				return;
			}
		}
		$codeErrorArea.text(errorMsg);
	});
}

</script>