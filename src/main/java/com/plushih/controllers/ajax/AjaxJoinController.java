package com.plushih.controllers.ajax;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.controllers.front.service.FrontJoinService;
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

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/ajax/join")
public class AjaxJoinController extends CoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AjaxJoinController.class );
	
	@Autowired
	FrontJoinService frontJoinService;
	
	
	/**
	 * @ClassName	: FrontJoinController.java
	 * @Method		: joinProc
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 처리
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
		userMemberEntity.setMemJoinChannel(Code.Join.CHANNEL_EMAIL);
		
		// 결과 세팅
		CommonResultEntity res = new CommonResultEntity();
		res.setResultCode(frontJoinService.insertMember(request, userMemberEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
	
	
	/**
	 * @ClassName	: FrontJoinController.java
	 * @Method		: joinProc
	 * @Date		: 2021. 1. 14. 
	 * @author		: dev.yklee
	 * @Description	: 회원가입 처리
	 */
	@ResponseBody
	@RequestMapping(value = {"/emailCheck", "/emailCheck/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public boolean emailCheck(HttpServletRequest request
			, @RequestParam Map<String, Object> emailCheck
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, UserMemberEntity userMemberEntity
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		boolean flag = true;
		
		// 결과 세팅
		flag = frontJoinService.emailCheck(emailCheck);
		
		return flag;
	}
}