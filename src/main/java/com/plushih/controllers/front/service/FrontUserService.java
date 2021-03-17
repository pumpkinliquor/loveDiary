package com.plushih.controllers.front.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserMemberEntity;

public interface FrontUserService {

	/**
	 * @ClassName	: selectUserInfo.java
	 * @Method		: insertMember
	 * @Date		: 2021. 01. 19.
	 * @author		: dev.khko
	 * @Description	: 회원정보 조회
	 */
	public Map<String, Object> selectUserInfo(HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception;

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: selectUserInfo
	 * @Date		: 2021. 1. 20.
	 * @author		: dev.khko
	 * @Description	: 회원 정보 수정
	 */
	public String updateUserInfo(MultipartHttpServletRequest request) throws Exception;

	/**
	 * @ClassName	: FrontUserService.java
	 * @Method		: updateUserInfoTempId
	 * @Date		: 2021. 1. 21.
	 * @author		: dev.yklee
	 * @Description	: 회원정보와 사전진단 매핑 처리
	 */
	public int updateUserInfoTempId(UserMemberEntity userMemberEntity) throws Exception;

	/**
	 * @ClassName	: LoginServiceImpl.java
	 * @Method		: sendSmsAuthcode
	 * @Date		: 2021. 1. 21.
	 * @author		: dev.khko
	 * @Description	: 문자 인증코드 전송
	 */
	public String sendSmsAuthcode(HttpServletRequest request, Map<String, Object> user) throws Exception;

	/**
	 * @ClassName	: LoginServiceImpl.java
	 * @Method		: checkSmsAuthCode
	 * @Date		: 2021. 1. 21.
	 * @author		: dev.khko
	 * @Description	: 인증번호 유효성 체크 및 사용 처리
	 */
	public String checkSmsAuthCode(HttpServletRequest request, Map<String, Object> paramMap) throws Exception;

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: selectLevelCode
	 * @Date		: 2021. 1. 22.
	 * @author		: dev.khko
	 * @Description	: 인증번호 유효성 체크 및 사용 처리
	 */
	public List<Map<String, String>> selectLevelCode(HttpServletRequest request) throws Exception;

	/**
	 *
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: selectLevelCode
	 * @Date		: 2021. 1. 22.
	 * @author		: dev.khko
	 * @Description	: 과목 목록 조회
	 */
	public List<Map<String, String>> selectSubCode(HttpServletRequest request) throws Exception;

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: selectSettingInfo
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 설정 정보 조회
	 */
	public Map<String, Object> selectSettingInfo(HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception;

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: updateUserInfoTempId
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 설정 정보 저장
	 */
	public CommonResultEntity updateSettingInfo(HttpServletRequest request, UserMemberEntity userMemberEntity, CommonResultEntity commonResultEntity) throws Exception;

}
