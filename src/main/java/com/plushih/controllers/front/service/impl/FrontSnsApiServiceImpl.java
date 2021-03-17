package com.plushih.controllers.front.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontSnsApiService;
import com.plushih.entities.UserMemberEntity;


/**
 * @PackageName	: com.aigo.math.mo.api.service.impl
 * @ClassName	: SnsApiServiceImpl.java
 * @Date		: 2020. 12. 29. 
 * @author		: dev.yklee
 * @Description	: SNS Api 관련 서비스
 */
@Service("snsApiService")
public class FrontSnsApiServiceImpl implements FrontSnsApiService {
	
	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: getKakaoUserInfo
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: Kakao Api를 통한 사용자 정보 조회
	 */
	@Override
	public UserMemberEntity getKakaoUserInfo(String code, UserMemberEntity userMemberEntity, String serviceType) {
		userMemberEntity = this.getUserInfoByKakaoApi(this.getKakaoAccessToken(code, serviceType), userMemberEntity);
		return userMemberEntity;
	}

	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: getFacebookUserInfo
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: Facebook Api를 통한 사용자 정보 조회
	 */
	@Override
	public UserMemberEntity getFacebookUserInfo(String code, UserMemberEntity userMemberEntity, String serviceType) {
		userMemberEntity = this.getUserInfoByFacebookApi(this.getFacebookAccessToken(code, serviceType), userMemberEntity);
		return userMemberEntity;
	}

	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: getNaverUserInfo
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: Naver Api를 통한 사용자 정보 조회
	 */
	@Override
	public UserMemberEntity getNaverUserInfo(String code, UserMemberEntity userMemberEntity, String serviceType) {
		userMemberEntity = this.getUserInfoByNaverApi(this.getNaverAccessToken(code, serviceType), userMemberEntity);
		return userMemberEntity;
	}
	
	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: getKakaoAccessToken
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: Kakao Api > 사용자 정보조회 Api사용을 위한 액세스 토큰 생성
	 */
	public String getKakaoAccessToken(String authorizeCode, String serviceType) {
		
		String returnJsonStr	= "";		// 결과 전문
		String accessToken		= "";		// Api를 통해 리턴받을 토큰
			
		// POST요청에 필요한 파라미터를 스트림을 통해 전송
		StringBuilder sb = new StringBuilder();
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=" + Default.Sns.KAKAO_REST_API_KEY);			// 발급받은 Kakao REST Api key
		sb.append("&code=" + authorizeCode);
		if("join".equals(serviceType)){
			sb.append("&redirect_uri=" + Default.Site.SERVER_DOMAIN + Default.Sns.JOIN_PROC_URL);			// 서비스 타입이 회원가입일 경우
		}else {
			sb.append("&redirect_uri=" + Default.Site.SERVER_DOMAIN + Default.Sns.LOGIN_PROC_URL);			// 서비스 타입이 로그인일 경우
		}
		sb.append("?type="+Default.Sns.KAKAO);
		String parameter = sb.toString();
		
		// 통신결과 리턴
		returnJsonStr = this.commonURLConnection(Default.Sns.KAKAO_GET_TOKEN_API_URL, parameter, Default.Http.REQUEST_METHOD_POST, "");
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(returnJsonStr);
		accessToken = element.getAsJsonObject().get("access_token").getAsString();
			
		return accessToken;
	}
	
	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: getUserInfoByKakaoApi
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: Kakao Api를 통한 회원정보 조회
	 */
	public UserMemberEntity getUserInfoByKakaoApi(String accessToken, UserMemberEntity userMemberEntity) {
		
		String returnJsonStr = this.commonURLConnection(Default.Sns.KAKAO_GET_USERINFO_API_URL, "", Default.Http.REQUEST_METHOD_GET, accessToken);
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(returnJsonStr);
		
		JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		JsonObject kakaoUserAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
		JsonObject profile = kakaoUserAccount.getAsJsonObject().get("profile").getAsJsonObject();
		
		String userUniqueKakaoId = element.getAsJsonObject().get("id").getAsString();														// 필수
		String email = kakaoUserAccount.getAsJsonObject().get("email").getAsString();													// 필수
		// 필수값이 아닌 항목들 처리
		String nickname = "";
		if(!StringUtils.isEmpty(properties.getAsJsonObject().get("nickname"))) {
			nickname = StringUtils.nvl(String.valueOf(properties.getAsJsonObject().get("nickname").getAsString()), email);
		} else {
			nickname = email;
		}
		String profileImageUrl = "";
		if(!StringUtils.isEmpty(profile.getAsJsonObject().get("profile_image_url"))) {
			profileImageUrl = StringUtils.nvl(String.valueOf(profile.getAsJsonObject().get("profile_image_url").getAsString()), "noimage");
		} else {
			profileImageUrl = "noimage";
		}
		
		// 결과 항목들 Set
		userMemberEntity.setMemUserid(userUniqueKakaoId);
		userMemberEntity.setMemEmail(email);
		userMemberEntity.setMemNickname(nickname);
		userMemberEntity.setMemPhoto(profileImageUrl);
		
		return userMemberEntity;
	}
	
	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: getNaverAccessToken
	 * @Date		: 2020. 12. 30. 
	 * @author		: dev.yklee
	 * @throws Exception 
	 * @Description	: Naver Api > 사용자 정보조회 Api사용을 위한 액세스 토큰 생성
	 */
	public String getNaverAccessToken(String authorizeCode, String serviceType) {
		
		String returnJsonStr	= "";			// 결과 전문
		String accessToken		= "";			// Api를 통해 리턴받을 토큰
		String redirectUri		= "";			// Parameter로 전송할 리다이렉트 URI
		String encRedirectUri		= "";		// Parameter로 전송할 리다이렉트 URI
		
		// POST요청에 필요한 파라미터를 스트림을 통해 전송
		StringBuilder sb = new StringBuilder();
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=" + Default.Sns.NAVER_CLIENT_ID_KEY);			// 발급받은 Naver Api key
		sb.append("&client_secret=" + Default.Sns.NAVER_CLIENT_SECRET_KEY);	// 발급받은 Naver Secret Api key
		sb.append("&code=" + authorizeCode);
		sb.append("&redirect_uri=");
		if("join".equals(serviceType)){
			redirectUri = Default.Site.SERVER_DOMAIN + Default.Sns.JOIN_PROC_URL;	// 서비스 타입이 회원가입일 경우
		}else {
			redirectUri = Default.Site.SERVER_DOMAIN + Default.Sns.LOGIN_PROC_URL;	// 서비스 타입이 로그인일 경우
		}
		redirectUri += "?type="+Default.Sns.NAVER;
		try {
			encRedirectUri = URLEncoder.encode(redirectUri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			encRedirectUri = redirectUri;
		}
		sb.append(encRedirectUri);			// 서비스 타입이 로그인일 경우
		String parameter = sb.toString();
		
		// 통신결과 리턴
		returnJsonStr = this.commonURLConnection(Default.Sns.NAVER_GET_TOKEN_API_URL, parameter, Default.Http.REQUEST_METHOD_GET, "");
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(returnJsonStr);
		accessToken = element.getAsJsonObject().get("access_token").getAsString();
		
		return accessToken;
	}
	
	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: getUserInfoByNaverApi
	 * @Date		: 2020. 12. 30. 
	 * @author		: dev.yklee
	 * @Description	: Naver Api를 통한 회원정보 조회
	 */
	public UserMemberEntity getUserInfoByNaverApi(String accessToken, UserMemberEntity userMemberEntity) {
		
		String returnJsonStr = this.commonURLConnection(Default.Sns.NAVER_GET_USERINFO_API_URL, "", Default.Http.REQUEST_METHOD_GET, accessToken);
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(returnJsonStr);
		
		JsonObject naverUserAccount = element.getAsJsonObject().get("response").getAsJsonObject();
		
		String userUniqueNaverId = naverUserAccount.getAsJsonObject().get("id").getAsString();											// 필수
		String email = naverUserAccount.getAsJsonObject().get("email").getAsString();													// 필수
		// 필수값이 아닌 항목들 처리
		String nickname = "";
		if(!StringUtils.isEmpty(naverUserAccount.getAsJsonObject().get("nickname"))) {
			nickname = StringUtils.nvl(String.valueOf(naverUserAccount.getAsJsonObject().get("nickname").getAsString()), email);
		} else {
			nickname = email;
		}
		String profileImage = "";
		if(!StringUtils.isEmpty(naverUserAccount.getAsJsonObject().get("profile_image"))) {
			profileImage = StringUtils.nvl(String.valueOf(naverUserAccount.getAsJsonObject().get("profile_image").getAsString()), "noimage");
		} else {
			profileImage = "noimage";
		}
		
		// 결과 항목들 Set
		userMemberEntity.setMemUserid(userUniqueNaverId);
		userMemberEntity.setMemEmail(email);
		userMemberEntity.setMemNickname(nickname);
		userMemberEntity.setMemPhoto(profileImage);
		
		return userMemberEntity;
	}
	
	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: getFacebookAccessToken
	 * @Date		: 2020. 12. 30. 
	 * @author		: dev.yklee
	 * @Description	: Facebook Api > 사용자 정보조회 Api사용을 위한 액세스 토큰 생성
	 */
	public String getFacebookAccessToken(String authorizeCode, String serviceType) {
		
		String returnJsonStr	= "";			// 결과 전문
		String accessToken		= "";			// Api를 통해 리턴받을 토큰
		String redirectUri		= "";			// Parameter로 전송할 리다이렉트 URI
		String encRedirectUri		= "";		// Parameter로 전송할 리다이렉트 URI
		
		// POST요청에 필요한 파라미터를 스트림을 통해 전송
		StringBuilder sb = new StringBuilder();
		sb.append("&client_id=" + Default.Sns.FACEBOOK_KEY);					// 발급받은 Facebook Api key
		sb.append("&client_secret=" + Default.Sns.FACEBOOK_SECRET_CODE_KEY);	// 발급받은 Facebook Secret Api key
		sb.append("&code=" + authorizeCode);
		sb.append("&redirect_uri=");
		if("join".equals(serviceType)){
			redirectUri = Default.Site.SERVER_DOMAIN + Default.Sns.JOIN_PROC_URL;	// 서비스 타입이 회원가입일 경우
		}else {
			redirectUri = Default.Site.SERVER_DOMAIN + Default.Sns.LOGIN_PROC_URL;	// 서비스 타입이 로그인일 경우
		}
		redirectUri += "?type="+Default.Sns.FACEBOOK;
		try {
			encRedirectUri = URLEncoder.encode(redirectUri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			encRedirectUri = redirectUri;
		}
		sb.append(encRedirectUri);			// 서비스 타입이 로그인일 경우
		String parameter = sb.toString();
		
		// 통신결과 리턴
		returnJsonStr = this.commonURLConnection(Default.Sns.FACEBOOK_GET_TOKEN_API_URL, parameter, Default.Http.REQUEST_METHOD_GET, "");
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(returnJsonStr);
		accessToken = element.getAsJsonObject().get("access_token").getAsString();
		
		return accessToken;
	}
	
	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: getUserInfoByFacebookApi
	 * @Date		: 2020. 12. 30. 
	 * @author		: dev.yklee
	 * @Description	: Facebook Api를 통한 회원정보 조회
	 */
	public UserMemberEntity getUserInfoByFacebookApi(String accessToken, UserMemberEntity userMemberEntity) {
		
		// Facebook의 경우 accessToken 값과 필요 항목을 파라미터에 세팅해서 요청하도록 되어있다.
		// POST요청에 필요한 파라미터를 스트림을 통해 전송
		StringBuilder sb = new StringBuilder();
		sb.append("&access_token=" + accessToken);			// 발급받은 Facebook accessToken
		sb.append("&fields=id,name,email,picture");			// 필요한 항목 scope 설정
		String parameter = sb.toString();
				
		String returnJsonStr = this.commonURLConnection(Default.Sns.FACEBOOK_GET_USERINFO_API_URL, parameter, Default.Http.REQUEST_METHOD_GET, "");
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(returnJsonStr);
		
		String userUniqueFacebookId = element.getAsJsonObject().get("id").getAsString();											// 필수
		String email = element.getAsJsonObject().get("email").getAsString();													// 필수
		
		String nickname = "";
		if(!StringUtils.isEmpty(element.getAsJsonObject().get("name").getAsString())) {
			nickname = StringUtils.nvl(String.valueOf(element.getAsJsonObject().get("name").getAsString()), email);				// 필수아님
		} else {
			nickname = email;
		}
		JsonObject picture = element.getAsJsonObject().get("picture").getAsJsonObject();
		JsonObject data = picture.getAsJsonObject().get("data").getAsJsonObject();
		
		String profileImage = "";
		if(!StringUtils.isEmpty(data.getAsJsonObject().get("url").getAsString())) {
			profileImage = StringUtils.nvl(String.valueOf(data.getAsJsonObject().get("url").getAsString()),"noimage");	// 필수아님
		} else {
			profileImage = "noimage";
		}
		
		
		// 결과 항목들 Set
		userMemberEntity.setMemUserid(userUniqueFacebookId);
		userMemberEntity.setMemEmail(email);
		userMemberEntity.setMemNickname(nickname);
		userMemberEntity.setMemPhoto(profileImage);
		
		return userMemberEntity;
	}
	
	/**
	 * @ClassName	: SnsApiServiceImpl.java
	 * @Method		: commonURLConnection
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: URLConnection을 위한 메소드
	 */
	public String commonURLConnection(String targetUrl, String parameter, String method, String accessToken) {
		
		String responseStr = "";
		
		try {
			
			URL url = new URL(targetUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod(method);
			
			// 액세스 토큰을 이용해서 요청할 때
			if(!"".equals(accessToken)) {
				conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			}
			conn.setDoOutput(true);
			
			// 전송할 Parameter가 있을 경우 // 액세스 토큰을 요청할 때
			// POST요청에 필요한 파라미터를 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			if(!"".equals(parameter)) {
				bw.write(parameter);
				bw.flush();
			}
			
			// 결과 코드가 200 리턴 시 성공
			int responseCode = conn.getResponseCode();
			
			BufferedReader br = null;
			
			if(Default.Http.HTTP_STATUS_CODE_200 == responseCode) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			}else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			}
			
			// JSON타입의 Response 메세지 파싱
			String inputLine = "";
			StringBuffer result = new StringBuffer();
			
			while ((inputLine = br.readLine()) != null) {
				result.append(inputLine);
			}
			
			responseStr = result.toString();
			
			br.close();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseStr;
	}
}