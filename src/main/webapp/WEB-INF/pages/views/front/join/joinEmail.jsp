<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/views/front/common/sns.jsp"%>
<body class="join_body">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>이메일로 가입</h1>
		</header>
		<div class="container join">
			<section class="contents joinArea">
				<form id="form" name="form" method="post">
					<div class="member_join_cont">
						<div class="member_ipt_div">
							<h2>이메일</h2>
							<div class="ipt_div">
								<input type="text" name="memUserid" value="">
							</div>
							<p class="er_txt emailErrorArea"></p> 
						</div> 
						<div class="member_ipt_div">
							<h2>비밀번호</h2>
							<div class="ipt_div">
								<input type="password" name="memPassword" value="" />
								<a href="#" class="ico_pw_view disabled tab_cont" style="display:none;">비밀번호 보기</a>
							</div>
							<p class="er_txt passwordErrorArea"></p> 
						</div> 
						<div class="member_ipt_div">
							<h2>비밀번호 확인 </h2>
							<input type="password" name="comparePassword" value="">
							<p class="er_txt passwordErrorCompareArea"></p> 
						</div>
						<div class="member_agree_div">
							<div class="all_chk">
								<p>이용약관 및 개인정보 처리방침에 동의 해주세요.</p>
								<div class="chkbox_div">
									<input type="checkbox" id="checkAll" class="ipt_chk" onclick="chkAll();">
									<label class="lable_chk" for="checkAll"></label>
								</div>
							</div>
							<p>만 14세 이상이며, 이용약관 및 개인정보 처리방침, 마케팅 &middot; 프로모션 정보 수신(선택)에 모두 동의합니다. </p>
							<ul>
								<li>
									<a href="/front/terms/termsOfService">이용약관 (필수)</a>
									<div class="chkbox_div">
										<input type="checkbox" id="chk01" class="ipt_chk chkItem" name="mtaTermsOfService" value="Y" />
										<label class="lable_chk" for="chk01"></label>
									</div>
								</li>
								<li>
									<a href="/front/terms/privacyPolicy">개인정보처리방침 (필수)</a>
									<div class="chkbox_div">
										<input type="checkbox" id="chk02" class="ipt_chk chkItem" name="mtaPrivacyPolicy" value="Y" />
										<label class="lable_chk" for="chk02"></label>
									</div>
								</li>
								<li>
									<a href="/front/terms/marketing">마케팅 &middot; 프로모션 정보 수신(선택)</a>
									<div class="chkbox_div">
										<input type="checkbox" id="chk03" class="ipt_chk chkItem" name="mtaMarketing" value="Y" />
										<label class="lable_chk" for="chk03"></label>
									</div>
								</li>
							</ul>
						</div>
						<button type="button" class="btn btn_join" onclick="fnJoin();">회원가입</button> 
					</div><!-- //member_ipt_cont -->
				</form> 
			</section>
			
		</div><!-- //container -->
	</div>
</body>
<form id="emailFrm" name="emailFrm" method="post">
	<input type="hidden" id="emailCheck" name="emailCheck"/>
</form>
<script type="text/javascript">

qSelect();
chkAll();

var $form = $("#form");
var emailFlag = false;
$(function(){
	
	// 비밀번호 보기 활성화/비활성화
	$('.ico_pw_view').on('click', function(){
		var $this = $(this);
		$('.ico_pw_view').removeClass("tab_cont");
		var $memPassword = $("input[name='memPassword']");
		if($this.hasClass('disabled')){
			$this.removeClass('disabled');
			$memPassword.attr("type", "text");
		}else{
			$this.addClass('disabled');
			$memPassword.attr("type", "password");
		}
		$("input[name='memPassword']").focus();
		$('.ico_pw_view').removeClass("tab_cont");
	});
	
	$("input[name='memPassword']").focus(function(){
		$('.ico_pw_view').removeClass("tab_cont");
	}).blur(function(){
		setTimeout(function(){
			$('.ico_pw_view').addClass("tab_cont")
		},200)
	});
	
	// 이메일 입력 시 유효성 검사
	$("input[name='memUserid']").on('keyup', function(){
		$("input[name=emailCheck]").val($(this).val());
		var param = $("[name=emailFrm]").domJson();
		$.call("/front/ajax/join/emailCheck", param, function(returnJson){
			if(!returnJson) {
				$('.emailErrorArea').text('이미 가입하신 이메일이 있습니다.');
			} else {
				checkEmail( $("input[name='memUserid']").val(), 'emailErrorArea' );
			}
			emailFlag = returnJson;
		});
	});
	
	// 비밀번호 입력 시 유효성 검사
	$("input[name='memPassword'], input[name='comparePassword']").on('keyup', function(){
		checkPassword( $("input[name='memPassword']").val(), $("input[name='comparePassword']").val(), '' );
		if($(this).val() == ""){
			$(".passwordErrorArea").text("");
			$(".passwordErrorCompareArea").text("");
		}
	});
	
	$('#chk01, #chk02, #chk03').on('click', function() {
		if($('.chkItem:checked').length == 3 ){
			$('#checkAll').prop('checked', true);
		}else{
			$('#checkAll').prop('checked', false);
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

// 회원가입
function fnJoin(){
	if (!emailFlag) {
		$('.emailErrorArea').text('이미 가입하신 이메일이 있습니다.');
		return false;
	}
	var eFlag = 0;
	$("[id^=chk0]").not("[id=chk03]").each(function(i, e) {
		if($(e).is(":checked")) {
			$(e).closest("li").find("a").css("color", "");
		} else {
			$(e).closest("li").find("a").css("color", "#e40101");
			eFlag++;
		}
	});
	if(eFlag > 0) {
		$("div[class=all_chk]").find("p").css("color", "#e40101");
		$("div[class=all_chk]").find("p").focus();
		return false;
	} else {
		$("div[class=all_chk]").find("p").css("color", "");
	}
	
	var param = $form.domJson();
	
	// 이메일 체크
	if(checkEmail( param.memUserid, 'emailErrorArea' )){
		if( checkPassword( param.memPassword, param.comparePassword, 'passwordErrorArea' ) ){
			$.call("/front/ajax/join/joinProc", param, function(returnJson){
				if( param.mtaTermsOfService == 'Y' && param.mtaPrivacyPolicy == 'Y' ){
					var $emailErrorArea = $('.emailErrorArea');
					var $passwordArea = $('.passwordErrorArea');
					var errorMsg = '';
					if(returnJson.resultCode == 'S00'){
						commonModalPopup('Aigo 회원가입이 완료되었습니다.');
						$(".popupConfirm").click({}, goMain);
					} else if (returnJson.resultCode == 'F01') {
						$('.emailErrorArea').text('이미 가입하신 이메일이 있습니다.');
					} else if (returnJson.resultCode == 'F03') {
						errorMsg = '사용하실 비밀번호를 입력해주세요.';
					} else if (returnJson.resultCode == 'F04') {
						errorMsg = '올바른 형식의 이메일을 입력하세요.';
					} else {
						errorMsg = '';
						commonModalPopup('시스템 오류입니다.<br/>관리자에게 문의하세요.');
						return;
					}
					$emailErrorArea.text(errorMsg);
				}
			});
		}
	}
}

function goMain(){
	window.location.href = '/front/furs/main';
}

</script>