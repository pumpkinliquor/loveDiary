package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.controllers.front.service.FrontSnsApiService;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserMemberEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/login")
public class FrontLoginController extends CoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( FrontLoginController.class );
	
	@Autowired
	FrontLoginService frontLoginService;
	
	@Autowired
	FrontSnsApiService frontSnsApiService;
	
	/**
	 * @ClassName	: FrontLoginController.java
	 * @Method		: login
	 * @Date		: 2021. 1. 13. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 로그인 View
	 */
	@RequestMapping(value = {""}, method = {RequestMethod.GET, RequestMethod.POST})
	public String login (HttpServletRequest request
							, HttpServletResponse response
							, @PathVariable Map<String, String> pathVariables
							, ModelMap model
							, Locale localeParam ) throws Exception {
		return "/front/login";
	}
	
	/**
	 * @ClassName	: FrontLoginController.java
	 * @Method		: findAccount
	 * @Date		: 2021. 1. 13. 
	 * @author		: dev.yklee
	 * @Description	: 아이디/비밀번호 찾기 View
	 */
	@RequestMapping(value = {"/findAccount", "/findAccount/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String findAccount (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, Locale localeParam ) throws Exception {
		return "/front/login/findAccount";
	}
	
	/**
	 * @ClassName	: FrontLoginController.java
	 * @Method		: resetPassword
	 * @Date		: 2021. 1. 13. 
	 * @author		: dev.yklee
	 * @Description	: 비밀번호 재설정 View 페이지
	 */
	@RequestMapping(value = {"/resetPassword","/resetPassword/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String resetPassword (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, @RequestParam Map<String, Object> paramMap
			, Locale localeParam ) throws Exception {
		
		model.addAttribute(Default.ResultValue.REQUEST_PARAMETER_MAP, paramMap);
		return "/front/login/resetPassword";
	}
	
	/**
	 * @ClassName	: FrontLoginController.java
	 * @Method		: loginProc
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 로그인 처리 (SNS)
	 */
	@RequestMapping(value = {"/loginProcSns","/loginProcSns/{lan}"}, method = {RequestMethod.POST, RequestMethod.GET})
	public String loginProcSns (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		UserMemberEntity loginUser = new UserMemberEntity();			// 회원가입 처리할 사용자 정보
		
		String sysType = StringUtils.nvl(String.valueOf(paramMap.get("type")), "");			// 요청 SNS 타입
		String code = StringUtils.nvl(String.valueOf(paramMap.get("code")), "");
		
		// 1. Kakao로 로그인 한 사용자
		if(Default.Sns.KAKAO.equals(sysType)) {
			loginUser = frontSnsApiService.getKakaoUserInfo(code, loginUser, "login");
		// 2. Facebook으로 로그인 한 사용자
		} else if (Default.Sns.FACEBOOK.equals(sysType)) {
			loginUser = frontSnsApiService.getFacebookUserInfo(code, loginUser, "login");
		// 3. Naver로 로그인 한 사용자
		} else if (Default.Sns.NAVER.equals(sysType)) {
			loginUser = frontSnsApiService.getNaverUserInfo(code, loginUser, "login");
		}
		
		// Default 비밀번호 세팅
		loginUser.setMemPassword(Code.Login.DEFAULT_PASSWORD);
		loginUser = frontLoginService.loginProc(request, response, loginUser);
		
		// 로그인 실행 후 결과 세팅
		CommonResultEntity res = new CommonResultEntity();
		res.setResultCode(String.valueOf(loginUser.getLoginStateCode()));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
;		
		return "/front/login/loginProcSns";
	}
	

}