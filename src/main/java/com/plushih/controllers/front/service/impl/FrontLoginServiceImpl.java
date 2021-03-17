package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.HashUtils;
import com.plushih.common.utils.NumberUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.UserMemberEntity;

@SuppressWarnings("unchecked")
@Service("frontLoginService")
public class FrontLoginServiceImpl implements FrontLoginService {
	
	@Autowired
	private CommonDao commonDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @ClassName	: LoginServiceImpl.java
	 * @Method		: loginProc
	 * @Date		: 2020. 12. 31. 
	 * @author		: dev.yklee
	 * @Description	: 로그인 처리
	 */
	@Override
	public UserMemberEntity loginProc(HttpServletRequest request, HttpServletResponse response, UserMemberEntity userMemberEntity) throws Exception{
		
		UserMemberEntity returnUser = new UserMemberEntity();
		UserMemberEntity checkUserInfo = new UserMemberEntity();
		
		Map<String, Object> historyMap = new HashMap<String, Object>();
		historyMap.put("mllSuccess", Default.NO);
		
		String loginId = StringUtils.nvl(userMemberEntity.getMemUserid(), "");
		String loginPw = StringUtils.nvl(userMemberEntity.getMemPassword(), "");
		String loginPwEnc = HashUtils.encryptSHA256(StringUtils.nvl(userMemberEntity.getMemPassword(), ""));
		userMemberEntity.setMemPasswordEnc(loginPwEnc);
		
		/** 1. ID가 NULL인 경우 */
		if("".equals(loginId)){
			// ERROR(아이디 필요)
			returnUser.setLoginStateCode(Code.Login.ID_INVALID);
		}else if("".equals(loginPw)){
			/** 2. PWD가 NULL인 경우 */
			// ERROR(비밀번호 필요)
			returnUser.setLoginStateCode(Code.Login.PW_INVALID);
		}else{
			/** 3. ID, PWD 모두 NULL이 아닌경우 */
			// 파라미터 세팅 후 입력받은 값으로 사용자 정보 조회 */
			checkUserInfo = (UserMemberEntity) commonDao.selectOne("FrontLoginDAO.selectLoginUserInfo", userMemberEntity);
			
			if(checkUserInfo != null){
				// 비밀번호가 틀린 경우
				if(!StringUtils.nvl(checkUserInfo.getMemPassword(), "").equals(StringUtils.nvl(userMemberEntity.getMemPasswordEnc(),""))){
					returnUser.setLoginStateCode(Code.Login.PW_INCORRECT);
				}else{
					/** 회원정보 Sesssion에 저장 */
					returnUser.setMemUserid(loginId);
					
					HttpSession session = request.getSession(true);
					session.setAttribute(LoginSession.ID, checkUserInfo.getMemUserid());
					session.setAttribute(LoginSession.SEQ, checkUserInfo.getMemId());
					session.setAttribute(LoginSession.NICK_NAME, checkUserInfo.getMemNickname());
					session.setAttribute(LoginSession.IP, request.getRemoteAddr());
					session.setAttribute(LoginSession.SUBJECT_ID, checkUserInfo.getMemSubId());
					session.setAttribute(LoginSession.EMAIL, checkUserInfo.getMemEmail());
//					session.setAttribute(LoginSession.LEVEL, checkUserInfo.getMemLevel());
					session.setAttribute(LoginSession.LEVEL, 4);	// 임시 세팅
					
					String tempId = "0";
					String tempClass = "0";
					int tempGrade = 0;
					
					if(!StringUtils.isEmpty(session.getAttribute(LoginSession.TEMP_ID))) {
						tempId = String.valueOf(session.getAttribute(LoginSession.TEMP_ID));
					}
					if(!StringUtils.isEmpty(session.getAttribute(LoginSession.TEMP_CLASS))) {
						tempClass = String.valueOf(session.getAttribute(LoginSession.TEMP_CLASS));
					}
					if(!StringUtils.isEmpty(session.getAttribute(LoginSession.TEMP_GRADE))) {
						tempGrade = NumberUtils.stringToInt(String.valueOf(session.getAttribute(LoginSession.TEMP_GRADE))) ;
					}
					
					// 회원 Seq Set
					userMemberEntity.setMemId(checkUserInfo.getMemId());
					userMemberEntity.setMemTempId(checkUserInfo.getMemTempId());
					userMemberEntity.setMemGrade(checkUserInfo.getMemGrade());
					
					// 사전진단 선택 등급별 그룹핑 (모의진단 문제 출제 그룹)
					if (Code.Aigo.DEFAULT_TEMP_GRADE != userMemberEntity.getMemGrade())  {
						if (userMemberEntity.getMemGrade() == Code.Aigo.GRADE_1) {
							userMemberEntity.setMemFurGroup(Code.Aigo.CON_GROUP_5);
						} else if (userMemberEntity.getMemGrade() == Code.Aigo.GRADE_2) {
							userMemberEntity.setMemFurGroup(Code.Aigo.CON_GROUP_4);
						} else if (userMemberEntity.getMemGrade() == Code.Aigo.GRADE_3) {
							userMemberEntity.setMemFurGroup(Code.Aigo.CON_GROUP_3);
						} else if (userMemberEntity.getMemGrade() == Code.Aigo.GRADE_4) {
							userMemberEntity.setMemFurGroup(Code.Aigo.CON_GROUP_2);
						} else {
							userMemberEntity.setMemFurGroup(Code.Aigo.CON_GROUP_1);
						}
					}
					// 사전진단 및 모의진단 이력 조회
					checkUserInfo = (UserMemberEntity) commonDao.selectOne("FrontLoginDAO.checkPreTestYn", userMemberEntity);
					
					
					// 1. DB 내 사전진단 참여기록이 없는 사용자
					if(Code.Login.DEFAULT_TEMP_ID.equals(userMemberEntity.getMemTempId()) ) {
						// 1-1. 세션에 참여한 기록이 있을 경우 - 세션 정보로 DB 업데이트 필수
						// 랜딩 페이지 : 모의진단 시작 페이지
						if(!Code.Login.DEFAULT_TEMP_ID.equals(tempId)) {
							returnUser.setLoginStateCode(Code.Login.SUCCESS_CON);			// 로그인 결과코드 세팅
							userMemberEntity.setMemTempId(tempId);
							userMemberEntity.setMemClass(tempClass);
							userMemberEntity.setMemGrade(tempGrade);
							commonDao.update("FrontLoginDAO.updatePreTestInfo", userMemberEntity);
							
						// 1-2. 세션에 참여한 기록이 없을 경우
						// 랜딩 페이지 : 사전진단 페이지
						} else {
							returnUser.setLoginStateCode(Code.Login.SUCCESS_FUR);			// 로그인 결과코드 세팅
						}
					// 2. DB에 사전진단 참여기록이 있음
					} else {
						session.setAttribute(LoginSession.TEMP_ID, checkUserInfo.getMemTempId());
						session.setAttribute(LoginSession.TEMP_CLASS, checkUserInfo.getMemClass());
						session.setAttribute(LoginSession.TEMP_GRADE, checkUserInfo.getMemGrade());
						
						// 2-1. 모의진단을 완료한 사용자
						// 랜딩페이지 : 학습 홈
						if(Default.YES.equals(checkUserInfo.getMemConYn())) {
							returnUser.setLoginStateCode(Code.Login.SUCCESS_LEARN);			// 로그인 결과코드 세팅
						
						// 2-2. 모의진단 진행하지 않은 사용자
						// 랜딩페이지 : 모의진단 시작 페이지
						} else {
							returnUser.setLoginStateCode(Code.Login.SUCCESS_CON);			// 로그인 결과코드 세팅
						}
					}
					
					historyMap.put("mllSuccess", Default.YES);
					// 회원정보 세션 set
				}
				
				// 로그인 이력 등록
				historyMap.put("memId", checkUserInfo.getMemId());
				historyMap.put("mllUserid", userMemberEntity.getMemUserid());
				historyMap.put("mllIp", request.getRemoteAddr());
				historyMap.put("mllReason", returnUser.getLoginStateCode());
				historyMap.put("mllUseragent", request.getHeader("user-agent"));	
				historyMap.put("mllUrl", StringUtils.nvl(String.valueOf(request.getRequestURL()), Default.Site.SERVER_DOMAIN));
				historyMap.put("mllReferer", request.getHeader("referer"));
				commonDao.insert("FrontLoginDAO.insertLoginHistory", historyMap);
				
			}else{
				returnUser.setLoginStateCode(Code.Login.ID_NULL);
			}
		}
		
		return returnUser;
	}
	
	/**
	 * @ClassName	: LoginServiceImpl.java
	 * @Method		: sendAuthcode
	 * @Date		: 2020. 12. 31. 
	 * @author		: dev.yklee
	 * @Description	: 이메일로 인증코드 전송
	 */
	@Override
	public String sendAuthcode(HttpServletRequest request, UserMemberEntity user) throws Exception{
		
		String resultCode = "";
		
		String memUserid = StringUtils.nvl(user.getMemUserid(), "");
		
		if("".equals(memUserid)) {
			resultCode = Code.Result.FAIL_01;				// 이메일 아이디가 입력되지 않음
		} else {
			UserMemberEntity checkUserInfo = (UserMemberEntity) commonDao.selectOne("FrontLoginDAO.selectLoginUserInfo", user);
			if(checkUserInfo == null){
				resultCode = Code.Result.FAIL_02;			// 존재하지 않는 계정
			} else {
				HashMap<String, Object> sendEmailMap = new HashMap<String, Object>();
				int authCode = (int) NumberUtils.getRandomNumber(6, 6, 999999);
				sendEmailMap.put("memId", checkUserInfo.getMemId());
				sendEmailMap.put("memUserid", checkUserInfo.getMemUserid());
				sendEmailMap.put("maeKey", authCode);
				sendEmailMap.put("maeType", 0);
				
				if( commonDao.insert("FrontLoginDAO.insertAuthCode", sendEmailMap) < 1){
					resultCode = Code.Result.FAIL_03;		// 인증번호 생성에 실패
				} else {
					// 이메일 발송 처리 로직 추가한 후 처리결과에 따라 SUCC 코드 세팅
					resultCode = Code.Result.SUCC;
				}
			}
		}
		return resultCode;
	}
	
	/**
	 * @ClassName	: LoginServiceImpl.java
	 * @Method		: checkAuthcode
	 * @Date		: 2020. 12. 31. 
	 * @author		: dev.yklee
	 * @Description	: 인증번호 유효성 체크 및 사용 처리
	 */
	@Override
	public String checkAuthCode(HttpServletRequest request, Map<String, Object> paramMap) throws Exception{
		
		String resultCode = "";
		
		if("".equals(StringUtils.nvl(String.valueOf(paramMap.get("memUserid")), ""))) {
			resultCode = Code.Result.FAIL_01;				// 이메일 아이디가 입력되지 않음
		} else {
			if("".equals(StringUtils.nvl(String.valueOf(paramMap.get("maeKey")), ""))){
				resultCode = Code.Result.FAIL_02;			// 인증번호를 입력하지 않음
			} else {
				HashMap<String, Object> authCodeMap = (HashMap<String, Object>) commonDao.selectOne("FrontLoginDAO.selectAuthCode", paramMap);
				if(authCodeMap == null) {
					resultCode = Code.Result.FAIL_03;		// 유효하지 않은 인증번호
				} else {
					// 1. 인증번호 사용 처리
					paramMap.put("memId", StringUtils.nvl(String.valueOf(authCodeMap.get("memId")),""));
					if(commonDao.update("FrontLoginDAO.updateAuthCode", paramMap) > 0) {
						resultCode = Code.Result.SUCC;
						// 2. 발급 후 사용하지 않은 인증번호 만료 처리
						commonDao.update("FrontLoginDAO.updateUnusedAuthCode", paramMap);
					} else {
						resultCode = Code.Result.FAIL_04;	// 처리중 오류 발생
					}
				}
			}
		}
		
		return resultCode;
	}
	
	/**
	 * @ClassName	: LoginServiceImpl.java
	 * @Method		: updatePassword
	 * @Date		: 2020. 12. 31. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	public String updatePassword (HttpServletRequest request, UserMemberEntity user) throws Exception {
		
		HttpSession session = request.getSession(true);
		
		user.setMemUserid(LoginSession.getLoginId(session));
		
		String resultCode = "";
		
		if("".equals(StringUtils.nvl(user.getMemUserid(), ""))) {
			resultCode = Code.Result.FAIL_01;					// 이메일 아이디가 입력되지 않음
		} else {
			if("".equals(StringUtils.nvl(user.getMemPassword(), ""))) {
				resultCode = Code.Result.FAIL_02;				// 비밀번호가 입력되지 않음
			} else {
				user.setMemPasswordEnc(HashUtils.encryptSHA256(user.getMemPassword()));
				if(commonDao.update("FrontLoginDAO.updatePassword", user) > 0) {
					resultCode = Code.Result.SUCC;
				} else {
					resultCode = Code.Result.FAIL_03;			// 비밀번호 수정처리 실패
				}
			}
		}
		
		return resultCode;
	}
	
}
