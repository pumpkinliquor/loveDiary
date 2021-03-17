package com.plushih.controllers.front.service;

import com.plushih.entities.UserMemberEntity;

/**
 * @PackageName	: com.aigo.math.mo.api.service
 * @ClassName	: SnsApiService.java
 * @Date		: 2020. 12. 29. 
 * @author		: dev.yklee
 * @Description	: SNS Api 관련 서비스
 */
public interface FrontSnsApiService {
	
	
	/**
	 * @ClassName	: SnsApiService.java
	 * @Method		: getKakaoUserInfo
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: Kakao Api를 통한 사용자 정보 조회
	 */
	public UserMemberEntity getKakaoUserInfo(String code, UserMemberEntity userMemberEntity, String serviceType);
	
	/**
	 * @ClassName	: SnsApiService.java
	 * @Method		: getFacebookUserInfo
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: Facebook Api를 통한 사용자 정보 조회
	 */
	public UserMemberEntity getFacebookUserInfo(String code, UserMemberEntity userMemberEntity, String serviceType);
	
	/**
	 * @ClassName	: SnsApiService.java
	 * @Method		: getNaverUserInfo
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: Naver Api를 통한 사용자 정보 조회
	 */
	public UserMemberEntity getNaverUserInfo(String code, UserMemberEntity userMemberEntity, String serviceType);
	
}