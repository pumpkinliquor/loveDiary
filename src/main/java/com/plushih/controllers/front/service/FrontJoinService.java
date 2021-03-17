package com.plushih.controllers.front.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.plushih.entities.UserMemberEntity;

public interface FrontJoinService {
	
	/**
	 * @ClassName	: MemberJoinService.java
	 * @Method		: selectMemberJoinCheck
	 * @Date		: 2020. 12. 30. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > 회원가입 여부 체크
	 */
	public int selectMemberJoinCheck(UserMemberEntity userMemberEntity) throws Exception;
	
	/**
	 * @ClassName	: MemberJoinService.java
	 * @Method		: selectCheckIdCount
	 * @Date		: 2020. 12. 30. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > 이메일아이디 중복체크
	 */
	public int selectCheckIdCount(UserMemberEntity userMemberEntity) throws Exception;
	
	/**
	 * @ClassName	: MemberJoinService.java
	 * @Method		: insertMember
	 * @Date		: 2020. 12. 29. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > 등록
	 */
	public String insertMember(HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception;

	public boolean emailCheck(Map<String, Object> emailCheck) throws Exception;

}
