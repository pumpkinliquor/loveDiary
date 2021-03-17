package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserMemberEntity;
import com.plushih.services.front.SiteFileUploadService;

@Service("frontUserService")
public class FrontUserServiceImpl implements FrontUserService {

	@Autowired
	private CommonDao commonDao;

	@Autowired
    private SiteFileUploadService siteFileUploadService;

	@Autowired
	private FrontLoginService frontLoginService;

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: selectUserInfo
	 * @Date		: 2021. 1. 20.
	 * @author		: dev.khko
	 * @Description	: 회원 정보 조회
	 */
	@Override
	public Map<String, Object> selectUserInfo(HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception {
		HttpSession session = request.getSession(true);
		userMemberEntity.setMemUserid(LoginSession.getLoginId(session));

		return commonDao.selectOne("FrontUserDAO.selectUserInfo", userMemberEntity);
	}

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: selectUserInfo
	 * @Date		: 2021. 1. 20.
	 * @author		: dev.khko
	 * @Description	: 회원 정보 수정
	 */
	@Override
	public String updateUserInfo(MultipartHttpServletRequest request) throws Exception {
		UserMemberEntity userMemberEntity = new UserMemberEntity();
		HttpSession session = request.getSession(true);

		userMemberEntity.setMemUserid(LoginSession.getLoginId(session));
		userMemberEntity.setMemNickname(request.getParameter("memNickname"));
		userMemberEntity.setMemPassword(request.getParameter("memPassword"));
		userMemberEntity.setMemPasswordEnc(request.getParameter("memPasswordEnc"));
		userMemberEntity.setMemIcon(request.getParameter("memIcon"));
		userMemberEntity.setMemPhone(request.getParameter("memPhone"));
		userMemberEntity.setMemClass(request.getParameter("memClass"));
		userMemberEntity.setMemSubId(Integer.parseInt(request.getParameter("memSubId")));



		String resultCode = "";

		try {
			//아이콘 수정
			MultiValueMap<String, MultipartFile> fileuploads = request.getMultiFileMap();
			Iterator<String> iterator = fileuploads.keySet().iterator();

			while(iterator.hasNext()) {

				String key = iterator.next();
				LinkedList<MultipartFile> df = (LinkedList<MultipartFile>) fileuploads.get(key);

				MultipartFile fileInfo = (MultipartFile) df.getFirst();
				if (fileInfo.getSize() > 0) {
					int iconSeq = commonDao.selectOne("FrontUserDAO.selectFileSeq");
					siteFileUploadService.uploadImage(fileInfo, 0, "/USER", "USER", iconSeq);
					userMemberEntity.setMemIcon(iconSeq+"");
					commonDao.update("FrontUserDAO.updateIcon", userMemberEntity);
				}
			}

			//비밀번호 수정
			if(userMemberEntity.getMemPassword().length() > 0 && userMemberEntity.getMemPasswordEnc().length() > 0 ) frontLoginService.updatePassword(request, userMemberEntity);

			//닉네임, 휴대폰번호, 학년, 선택과 과목
			if(userMemberEntity.getMemNickname().length() > 0
			|| userMemberEntity.getMemPhone().length() > 0
			|| userMemberEntity.getMemClass().length() > 0
			|| userMemberEntity.getMemSubId() > 0
			)
			commonDao.update("FrontUserDAO.updateUserInfo", userMemberEntity);

			resultCode = Code.Result.SUCC;
		}catch (Exception e) {
			resultCode = Code.Result.FAIL_99;
		}

		return resultCode;
	}


	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: sendSmsAuthcode
	 * @Date		: 2021. 1. 21.
	 * @author		: dev.khko
	 * @Description	: 문자 인증코드 전송
	 */
	@Override
	public String sendSmsAuthcode(HttpServletRequest request, Map<String, Object> user) throws Exception{

		String resultCode = "";

		String memUserid = StringUtils.nvl(user.get("memUserid").toString(), "");

		if("".equals(memUserid)) {
			resultCode = Code.Result.FAIL_01;				// 이메일 아이디가 입력되지 않음
		} else {
			UserMemberEntity checkUserInfo = (UserMemberEntity) commonDao.selectOne("FrontLoginDAO.selectLoginUserInfo", user);
			if(checkUserInfo == null){
				resultCode = Code.Result.FAIL_02;			// 존재하지 않는 계정
			} else {
				HashMap<String, Object> sendSmsMap = new HashMap<String, Object>();
				int authCode = (int) NumberUtils.getRandomNumber(6, 6, 999999);
				sendSmsMap.put("memId", checkUserInfo.getMemId());
				sendSmsMap.put("memUserid", checkUserInfo.getMemUserid());
				sendSmsMap.put("masKey", authCode);
				sendSmsMap.put("masType", 0);

				if( commonDao.insert("FrontUserDAO.insertSmsAuthCode", sendSmsMap) < 1){
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
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: checkSmsAuthCode
	 * @Date		: 2021. 1. 21.
	 * @author		: dev.khko
	 * @Description	: 인증번호 유효성 체크 및 사용 처리
	 */
	@Override
	public String checkSmsAuthCode(HttpServletRequest request, Map<String, Object> paramMap) throws Exception{

		String resultCode = "";

		if("".equals(StringUtils.nvl(String.valueOf(paramMap.get("memUserid")), ""))) {
			resultCode = Code.Result.FAIL_01;				// 이메일 아이디가 입력되지 않음
		} else {
			if("".equals(StringUtils.nvl(String.valueOf(paramMap.get("masKey")), ""))){
				resultCode = Code.Result.FAIL_02;			// 인증번호를 입력하지 않음
			} else {
				HashMap<String, Object> authCodeMap = (HashMap<String, Object>) commonDao.selectOne("FrontUserDAO.selectSmsAuthCode", paramMap);
				if(authCodeMap == null) {
					resultCode = Code.Result.FAIL_03;		// 유효하지 않은 인증번호
				} else {
					// 1. 인증번호 사용 처리
					paramMap.put("memId", StringUtils.nvl(String.valueOf(authCodeMap.get("memId")),""));
					if(commonDao.update("FrontUserDAO.updateSmsAuthCode", paramMap) > 0) {
						resultCode = Code.Result.SUCC;
						// 2. 발급 후 사용하지 않은 인증번호 만료 처리
						commonDao.update("FrontUserDAO.updateUnusedSmsAuthCode", paramMap);
					} else {
						resultCode = Code.Result.FAIL_04;	// 처리중 오류 발생
					}
				}
			}
		}

		return resultCode;
	}

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: selectLevelCode
	 * @Date		: 2021. 1. 22.
	 * @author		: dev.khko
	 * @Description	: 학년 목록 조회
	 */
	@Override
	public List<Map<String, String>> selectLevelCode(HttpServletRequest request) throws Exception {
		return commonDao.selectList("FrontUserDAO.selectLevelCode");
	}

	/**
	 *
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: selectLevelCode
	 * @Date		: 2021. 1. 22.
	 * @author		: dev.khko
	 * @Description	: 과목 목록 조회
	 */
	@Override
	public List<Map<String, String>> selectSubCode(HttpServletRequest request) throws Exception {
		return commonDao.selectList("FrontUserDAO.selectSubCode");
	}

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: updateUserInfoTempId
	 * @Date		: 2021. 1. 21.
	 * @author		: dev.yklee
	 * @Description	: 회원정보와 사전진단 매핑 처리
	 */
	@Override
	public int updateUserInfoTempId(UserMemberEntity userMemberEntity) throws Exception {
		return commonDao.update("FrontUserDAO.updateUserInfoTempId", userMemberEntity);
	}

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: selectSettingInfo
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 설정 정보 조회
	 */
	@Override
	public Map<String, Object> selectSettingInfo(HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception {
		HttpSession session = request.getSession(true);
		userMemberEntity.setMemUserid(LoginSession.getLoginId(session));

		return commonDao.selectOne("FrontUserDAO.selectSettingInfo", userMemberEntity);
	}

	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: updateUserInfoTempId
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 설정 정보 저장
	 */
	@Override
	public CommonResultEntity updateSettingInfo(HttpServletRequest request, UserMemberEntity userMemberEntity, CommonResultEntity commonResultEntity) throws Exception {
		HttpSession session = request.getSession(true);
		userMemberEntity.setMemUserid(LoginSession.getLoginId(session));
		userMemberEntity.setMemId(Integer.parseInt(LoginSession.getSeq(session)));
		if(commonDao.update("FrontUserDAO.updateSettingInfo", userMemberEntity) > 0) {
			commonDao.insert("FrontUserDAO.insertSettingLog", userMemberEntity);
			commonResultEntity.setResultCode(Code.Result.SUCC);
		}
		else commonResultEntity.setResultCode(Code.Result.FAIL_01);
		return commonResultEntity;
	}
}
