package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontJoinService;
import com.plushih.controllers.front.service.FrontMainService;
import com.plushih.controllers.front.service.FrontSnsApiService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserMemberEntity;
import com.plushih.services.front.SiteFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/join")
public class FrontJoinController extends CoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );
	
	@Autowired
	FrontMainService frontMainService;
	
	@Autowired
	FrontSnsApiService	frontSnsApiService;
	
	@Autowired
	FrontJoinService	frontJoinService;
	
	@Autowired
	SiteFileUploadService siteFileUploadService;
	
	/**
	 * @ClassName	: FrontLoginController.java
	 * @Method		: login
	 * @Date		: 2021. 1. 13. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 관련 처리
	 */
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public String join (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, Locale localeParam ) throws Exception {
		return "/front/join";
	}
	
	/**
	 * @ClassName	: FrontJoinController.java
	 * @Method		: joinEmail
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 이메일 회원가입 View
	 */
	@RequestMapping(value = {"/joinEmail"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String joinEmail (HttpServletRequest request
							, HttpServletResponse response
							, @PathVariable Map<String, String> pathVariables
							, ModelMap model
							, Locale localeParam ) throws Exception {
		return "/front/join/joinEmail";
	}
	
	/**
	 * @ClassName	: FrontJoinController.java
	 * @Method		: joinProc
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > 이메일로 회원가입
	 */
	@ResponseBody
	@RequestMapping(value = {"/joinProc", "/joinProc/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity joinProc(HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, UserMemberEntity userMemberEntity
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		userMemberEntity.setMemEmail(userMemberEntity.getMemUserid());
		
		// 회원가입 처리
		CommonResultEntity res = new CommonResultEntity();
		res.setResultCode(frontJoinService.insertMember(request, userMemberEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
	
	/**
	 * @ClassName	: FrontJoinController.java
	 * @Method		: joinProcSns
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > SNS 회원가입 처리
	 */
	@RequestMapping(value = {"/joinProcSns", "/joinProcSns/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String joinProcSns(HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		UserMemberEntity joinUser = new UserMemberEntity();			// 회원가입 처리할 사용자 정보
		
		String sysType = StringUtils.nvl(String.valueOf(paramMap.get("type")), "");			// 요청 SNS 타입
		String code = StringUtils.nvl(String.valueOf(paramMap.get("code")), "");
		
		// 1. Kakao로 로그인 한 사용자
		if(Default.Sns.KAKAO.equals(sysType)) {
			joinUser = frontSnsApiService.getKakaoUserInfo(code, joinUser, "join");
		// 2. Facebook으로 로그인 한 사용자
		} else if (Default.Sns.FACEBOOK.equals(sysType)) {
			joinUser = frontSnsApiService.getFacebookUserInfo(code, joinUser, "join");
		// 3. Naver로 로그인 한 사용자
		} else if (Default.Sns.NAVER.equals(sysType)) {
			joinUser = frontSnsApiService.getNaverUserInfo(code, joinUser, "join");
		}
		
		// 회원등록 시 필수정보
		joinUser.setMemPassword(Code.Login.DEFAULT_PASSWORD);	// SNS 회원가입의 경우 비밀번호를 입력받지 않음
		
		// 약관동의 필수정보
		joinUser.setMtaTermsOfService("Y");			// 이용약관 동의
		joinUser.setMtaPrivacyPolicy("Y");			// 개인정보 처리방침 동의
		joinUser.setMtaMarketing("N");				// 마케팅 활용 비동의
		
		// 회원가입 처리
		CommonResultEntity res = new CommonResultEntity();
		res.setResultCode(frontJoinService.insertMember(request, joinUser));
		
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return "/front/join/joinProcSns";
	}

}