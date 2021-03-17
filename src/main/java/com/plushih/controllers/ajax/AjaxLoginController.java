package com.plushih.controllers.ajax;


import com.ibm.icu.text.SimpleDateFormat;
import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.HashUtils;
import com.plushih.controllers.front.service.FrontLoginService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/ajax/login")
public class AjaxLoginController extends CoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AjaxLoginController.class );
	
	@Autowired
	FrontLoginService frontLoginService;
	
	/**
	 * @ClassName	: AjaxLoginController.java
	 * @Method		: sendAuthcode
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 아이디/비밀번호 찾기 > 이메일로 인증코드 전송
	 */
	@ResponseBody
	@RequestMapping(value = {"/sendAuthcode","/sendAuthcode/{lan}"}, method = {RequestMethod.POST})
	public CommonResultEntity sendAuthcode (HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception {
		
		CommonResultEntity res = new CommonResultEntity();
		// 인증번호 생성 및 메일 발송
		String resultCode = frontLoginService.sendAuthcode(request, userMemberEntity);
		res.setResultCode(resultCode);
		
		return res;
	}
	
	/**
	 * @ClassName	: AjaxLoginController.java
	 * @Method		: checkAuthCode
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 인증번호 유효성 체크 및 사용 처리
	 */
	@ResponseBody
	@RequestMapping(value = {"/checkAuthCode","/checkAuthCode/{lan}"}, method = {RequestMethod.POST})
	public CommonResultEntity checkAuthCode (HttpServletRequest request, @RequestParam Map<String, Object> paramMap) throws Exception {
		
		CommonResultEntity res = new CommonResultEntity();
		Map<String, Object> authMap = new HashMap<>();
		
		// 인증번호 유효성 체크 및 사용 처리
		String resultCode = frontLoginService.checkAuthCode(request, paramMap);
		res.setResultCode(resultCode);
		
		// 비밀번호 재설정 페이지로의 비정상인 접근 제어
		// 인증번호로 이메일 인증 성공 후에만 이동가능
		if(Code.Result.SUCC.equals(resultCode)) {
			authMap.put(Default.ResultValue.VIEW_ACCESS_KEY, HashUtils.encryptSHA256(new SimpleDateFormat().format(new Date())));
			authMap.put("memUserid", String.valueOf(paramMap.get("memUserid")));
		}
		res.setResultData(authMap);
		
		return res;
	}
	
	/**
	 * @ClassName	: AjaxLoginController.java
	 * @Method		: updatePassword
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 비밀번호 수정
	 */
	@ResponseBody
	@RequestMapping(value = {"/updatePassword","/updatePassword/{lan}"}, method = {RequestMethod.POST})
	public CommonResultEntity updatePassword (HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception {
		
		CommonResultEntity res = new CommonResultEntity();
		
		String resultCode = frontLoginService.updatePassword(request, userMemberEntity);
		res.setResultCode(resultCode);
		
		return res;
	}
	
	/**
	 * @ClassName	: FrontLoginController.java
	 * @Method		: loginProc
	 * @Date		: 2021. 1. 13. 
	 * @author		: dev.yklee
	 * @Description	: 로그인 처리
	 */
	@ResponseBody
	@RequestMapping(value = {"/loginProc","/loginProc/{lan}"}, method = {RequestMethod.POST})
	public CommonResultEntity loginProc (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, UserMemberEntity userMemberEntity
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		CommonResultEntity res = new CommonResultEntity();
		UserMemberEntity loginUser = frontLoginService.loginProc(request, response, userMemberEntity);
		res.setResultCode(String.valueOf(loginUser.getLoginStateCode()));
		
		return res;
	}
}