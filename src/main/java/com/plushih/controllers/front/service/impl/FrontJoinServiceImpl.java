package com.plushih.controllers.front.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.HashUtils;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontJoinService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.UserMemberEntity;

@Service("frontJoinService")
public class FrontJoinServiceImpl implements FrontJoinService {
	
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * @ClassName	: MemberJoinServiceImpl.java
	 * @Method		: selectMemberJoinCheck
	 * @Date		: 2020. 12. 30. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > 회원가입 여부 체크
	 */
	public int selectMemberJoinCheck(UserMemberEntity userMemberEntity) throws Exception {
		return commonDao.selectOne("FrontJoinDAO.selectMemberJoinCheck", userMemberEntity);
	}
	
	/**
	 * @ClassName	: MemberJoinServiceImpl.java
	 * @Method		: selectCheckIdCount
	 * @Date		: 2020. 12. 30. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > 이메일아이디 중복체크
	 */
	public int selectCheckIdCount (UserMemberEntity userMemberEntity) throws Exception {
		return commonDao.selectOne("FrontJoinDAO.selectCheckIdCount", userMemberEntity);
	}
	
	/**
	 * @ClassName	: MemberJoinServiceImpl.java
	 * @Method		: insertMember
	 * @Date		: 2020. 12. 29.
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > 등록
	 */
	public String insertMember(HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception {
		
		userMemberEntity.setMemIsAdmin(Code.Login.FRONT_USER);		// 사용자 : 0, 관리자 : 1
		userMemberEntity.setMemRegisterIp(request.getRemoteAddr());	// IP정보 세팅
		
		int dbResult = 0;
		String resultCode = "";
		
		try {
			// 1. ID 누락 체크
			if(!"".equals(String.valueOf(userMemberEntity.getMemUserid())) ) {
				// 2. 비밀번호 누락 체크
				if(!"".equals(String.valueOf(userMemberEntity.getMemPassword())) ) {
					// 3. ID 중복 체크
					int idCheckCnt = commonDao.selectOne("FrontJoinDAO.selectCheckIdCount", userMemberEntity);
					
					if(idCheckCnt > 0) {
						resultCode = Code.Result.FAIL_01;
						return resultCode;
					}
					
					// 비밀번호 암호화
					userMemberEntity.setMemPasswordEnc(HashUtils.encryptSHA256(userMemberEntity.getMemPassword()));
					
					// 사전진단 정보 Set
					String tempId = LoginSession.getTempId(request.getSession());
					String tempClass = LoginSession.getTempClass(request.getSession());
					String tempGrade = LoginSession.getTempGrade(request.getSession());
					String tempSubject = LoginSession.getTempSubject(request.getSession());
					userMemberEntity.setMemTempId(tempId);
					userMemberEntity.setMemClass(tempClass);
					userMemberEntity.setMemGrade(NumberUtils.stringToInt(tempGrade));
					userMemberEntity.setMemSubId(NumberUtils.stringToInt(tempSubject));
					
					// 회원 테이블 등록
					dbResult = commonDao.insert("FrontJoinDAO.insertMember", userMemberEntity);
					
					// 회원가입 후 바로 로그인 처리
					HttpSession session = request.getSession();
					session.setAttribute(LoginSession.SEQ, userMemberEntity.getMemId());
					session.setAttribute(LoginSession.ID, userMemberEntity.getMemUserid());
					session.setAttribute(LoginSession.NICK_NAME, userMemberEntity.getMemNickname());
					session.setAttribute(LoginSession.EMAIL, userMemberEntity.getMemEmail());
					session.setAttribute(LoginSession.TEMP_ID, userMemberEntity.getMemTempId());
					session.setAttribute(LoginSession.TEMP_CLASS, userMemberEntity.getMemClass());
					session.setAttribute(LoginSession.TEMP_GRADE, userMemberEntity.getMemGrade());
					session.setAttribute(LoginSession.TEMP_SUBJECT, userMemberEntity.getMemSubId());
					session.setAttribute(LoginSession.SUBJECT_ID, userMemberEntity.getMemSubId());
					
					if(dbResult > 0) {
						commonDao.insert("FrontJoinDAO.insertAgreeInfo", userMemberEntity);
						commonDao.insert("FrontJoinDAO.updateTempInfo", userMemberEntity);

						resultCode = Code.Result.SUCC;
					} else {
						resultCode = Code.Result.FAIL_02; 
					}
				}else {
					resultCode = Code.Result.FAIL_03;
				}
			}else {
				resultCode = Code.Result.FAIL_04;
			}
		} catch(Exception e) {  
			e.printStackTrace();
			resultCode = Code.Result.FAIL_99;
			return resultCode;
		}
		return resultCode;
	}
	
	public boolean emailCheck (Map<String, Object> emailCheck) throws Exception {
		int emailCnt = commonDao.selectOne("FrontJoinDAO.selectEmailCnt", emailCheck);
		
		if(emailCnt <= 0) {
			return true;
		} else {
			return false;
		}
	}

}
