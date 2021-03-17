package com.plushih.controllers.front.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plushih.entities.UserMemberEntity;

public interface FrontLoginService {
	
	/**
	 * @ClassName	: FrontLoginService.java
	 * @Method		: loginProc
	 * @Date		: 2020. 12. 30. 
	 * @author		: dev.yklee
	 * @Description	: 로그인 처리
	 */
	public UserMemberEntity loginProc (HttpServletRequest request, HttpServletResponse response, UserMemberEntity userMemberEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontLoginService.java
	 * @Method		: sendAuthcode
	 * @Date		: 2020. 12. 31. 
	 * @author		: dev.yklee
	 * @Description	: 이메일로 인증코드 전송
	 */
	public String sendAuthcode (HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception;
	
	
	/**
	 * @ClassName	: FrontLoginService.java
	 * @Method		: checkAuthCode
	 * @Date		: 2020. 12. 31. 
	 * @author		: dev.yklee
	 * @Description	: 인증번호 유효성 체크 및 사용 처리
	 */
	public String checkAuthCode (HttpServletRequest request, Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @ClassName	: FrontLoginService.java
	 * @Method		: updatePassword
	 * @Date		: 2020. 12. 31. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 비밀번호 수정
	 */
	public String updatePassword (HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception;
	
}
