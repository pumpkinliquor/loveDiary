<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<%@ include file="/WEB-INF/pages/views/front/common/sns.jsp"%>
<script type="text/javascript" src="https://appleid.cdn-apple.com/appleauth/static/jsapi/appleid/1/en_US/appleid.auth.js"></script>
<body>
	<div class="wrapper bg_2">
		<header class="header">
			<a href="#!" class="btn_allmenu">전체메뉴</a>
			<h1>회원가입</h1>
			<p class="top_txt">Aigo 계정을 만들고<br>좀 더 정밀한 진단을 받아보세요.</p>
		</header>
		<div class="container join join-email">
			<section class="contents">
				<ul class="join_icon_div">
					<li><a href="#!" class="ico_j1" onclick="fnJoinSns('kakao');"><span>카카오톡 ID로 만들기</span></a></li>
					<li><a href="#!" class="ico_j2" onclick="fnJoinSns('facebook');"><span>페이스북 ID로 만들기</span></a></li>
					<li><div id="naverIdLogin"><a href="#!" class="ico_j3" onclick="fnJoinSns('naver');"><span>네이버 ID로 만들기</span></a></div></li>
					<li><a href="#!" class="ico_j4" onclick="fnJoinSns('apple');">
						<span>
						<button id="sign-in-with-apple-button"> 애플 ID로 만들기 </button>
				        <script type="text/javascript">
				            AppleID.auth.init({
				                clientId : 'com.aigo.sns.service',
				                scope : 'email name',
				                redirectURI : 'http://aigo.portalsns.com:8080/redirect',
				                state : 'origin:web',
				                usePopup : false
				            });

				            var buttonElement = document.getElementById('sign-in-with-apple-button');
				            buttonElement.addEventListener('click', () => {
				                AppleID.auth.signIn();
				            });
				        </script>
						</span>
					</a></li>
					<li><a href="#!" class="ico_j5" onclick="goJoinEmail();"><span>이메일로 만들기</span></a></li>
				</ul>

				<div class="txt_btm">
					<p>이미 가입하셨나요?</p>
					<a href="#!" onclick="goLoginPage();">로그인 후 맞춤문제를 확인하세요</a>
				</div>
			</section>
		</div><!-- //container -->
	</div>
</body>
<script type="text/javascript">

qSelect();

$(function(){

	//Listen for authorization success
	document.addEventListener('AppleIDSignInOnSuccess', (data) => {
		console.log('sucess');
		//handle successful response
	});
	//Listen for authorization failures
	document.addEventListener('AppleIDSignInOnFailure', (error) => {
		console.log('fail');
		//handle error.
	});

});

// 이메일 회원가입
function goJoinEmail(){
	location.href = '/front/join/joinEmail';
}

function apple(){
	AppleID.auth.init({
                clientId : 'com.aigo.example',
		                scope : 'email name',
		                redirectURI : 'http://aigo.portalsns.com/',
		                state : 'origin:web',
		                usePopup : false
            });
}

</script>