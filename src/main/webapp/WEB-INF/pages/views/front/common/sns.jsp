<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<%-- SNS SDK Include START --%>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ko_KR/sdk.js"></script>
<script src="/assets/js/module/sdk/min/kakao.min.js"></script>
<script src="/assets/js/module/sdk/min/naveridlogin_js_sdk_2.0.0.min.js"></script>
<%-- SNS SDK Include END --%>
<script>
	
	$(function(){
		$.call('/front/ajax/sns/init', {}, function(result) {
			var snsInfo = result.resultValue;
			sns.serverDomain	= snsInfo.serverDomain;
			sns.loginReturnUrl	= snsInfo.loginReturnUrl;
			sns.joinReturnUrl	= snsInfo.joinReturnUrl;
			sns.kakao			= snsInfo.kakao;
			sns.facebook		= snsInfo.facebook;
			sns.naver			= snsInfo.naver;
			sns.kakaoKey		= snsInfo.kakaoKey;
			sns.facebookKey		= snsInfo.facebookKey;
			sns.naverKey		= snsInfo.naverKey;
			sns.facebookAuthUrl	= snsInfo.facebookAuthUrl;
			sns.naverAuthUrl	= snsInfo.naverAuthUrl;
		});
	});
	
	var sns = {
		serverDomain	: '',
		loginReturnUrl	: '',
		joinReturnUrl	: '',
		kakao			: '',
		facebook		: '',
		naver			: '',
		kakaoKey		: '',
		facebookKey		: '',
		naverKey		: '',
		facebookAuthUrl	: '',
		naverAuthUrl	: ''
	}
	
	/** KAKAO */
	var paramKakaoApi = {
		redirectUri	: '',						// 인가 코드를 받을 URI
		state		: '',						// 인가 코드 요청과 응답 과정에서 유지할 수 있는 파라미터	
		scope		: 'profile,account_email',	// 추가 동의 받을 항목 Scope 설정
		throughTalk	: true						// 간편 로그인 사용 여부 (Default: true)
	};
	
	// SNS 가입
	function fnJoinSns(type){
		if(type == 'kakao'){
			paramKakaoApi.redirectUri = sns.serverDomain + sns.joinReturnUrl + '?type=' + sns.kakao;
			Kakao.init(sns.kakaoKey);
			Kakao.Auth.authorize(paramKakaoApi);
		} else if(type == 'facebook'){
			// 현재 페이지 이동을 위해 URL을 만들어준다.
			var facebookApiUrl = '';
			facebookApiUrl += sns.facebookAuthUrl+'?';
			facebookApiUrl += 'response_type=code';
			facebookApiUrl += '&display=popup';
			facebookApiUrl += '&app_id='+sns.facebookKey;
			facebookApiUrl += '&redirect_uri='+sns.serverDomain + sns.joinReturnUrl + '?type=' + sns.facebook;
			facebookApiUrl += '&scope=public_profile, email';
			location.href = facebookApiUrl;
		} else if(type == 'naver'){
			var encodeReturnUri = encodeURIComponent(sns.serverDomain + sns.joinReturnUrl + '?type=' + sns.naver);
			var naverApiAuthUrl = '';
			naverApiAuthUrl += sns.naverAuthUrl;
			naverApiAuthUrl += '?client_id='+sns.naverKey;
			naverApiAuthUrl += '&response_type=code';
			naverApiAuthUrl += '&redirect_url='+encodeReturnUri;
			location.href = naverApiAuthUrl;
		}
	}
	
	// SNS 로그인
	function fnLoginSns(type){
		if(type == 'kakao'){
			paramKakaoApi.redirectUri = sns.serverDomain + sns.loginReturnUrl + '?type=' + sns.kakao;
			Kakao.init(sns.kakaoKey);
			Kakao.Auth.authorize(paramKakaoApi);
		} else if(type == 'facebook'){
			// 현재 페이지 이동을 위해 URL을 만들어준다.
			var facebookApiUrl = '';
			facebookApiUrl += sns.facebookAuthUrl+'?';
			facebookApiUrl += 'response_type=code';
			facebookApiUrl += '&display=popup';
			facebookApiUrl += '&app_id='+sns.facebookKey;
			facebookApiUrl += '&redirect_uri='+sns.serverDomain + sns.loginReturnUrl + '?type=' + sns.facebook;
			facebookApiUrl += '&scope=public_profile,email';
			location.href = facebookApiUrl;
		} else if(type == 'naver'){ 
			var encodeReturnUri = encodeURIComponent(sns.serverDomain + sns.loginReturnUrl + '?type=' + sns.naver);
			var naverApiAuthUrl = '';
			naverApiAuthUrl += sns.naverAuthUrl;
			naverApiAuthUrl += '?client_id='+sns.naverKey;
			naverApiAuthUrl += '&response_type=code';
			naverApiAuthUrl += '&redirect_url='+encodeReturnUri;
			location.href = naverApiAuthUrl;
		}
	}

</script>
