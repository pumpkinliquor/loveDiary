package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

@Controller
public class FrontTermsController extends CoreController {
	private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );
	
	/**
	 * @ClassName	: FrontTermsController.java
	 * @Method		: termsOfService
	 * @Date		: 2021. 1. 15. 
	 * @author		: dev.yklee
	 * @Description	: 이용약관 View Page
	 */
	@RequestMapping(value = { "/front/terms/termsOfService", "/front/terms/termsOfService/{lan}"})
	public String termsOfService (HttpServletRequest request
							, HttpServletResponse response
							, @PathVariable Map<String, String> pathVariables
							, ModelMap model
							, Locale localeParam ) throws Exception {
		
		//path 설정
		String path = "/front/terms/termsOfService";
		return path;
		
	}
	
	/**
	 * @ClassName	: FrontTermsController.java
	 * @Method		: privacyPolicy
	 * @Date		: 2021. 1. 15. 
	 * @author		: dev.yklee
	 * @Description	: 개인정보처리방침 View Page
	 */
	@RequestMapping(value = { "/front/terms/privacyPolicy", "/front/terms/privacyPolicy/{lan}"})
	public String privacyPolicy (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		//path 설정
		String path = "/front/terms/privacyPolicy";
		return path;
		
	}
	
	/**
	 * @ClassName	: FrontTermsController.java
	 * @Method		: marketing
	 * @Date		: 2021. 1. 15. 
	 * @author		: dev.yklee
	 * @Description	: 마케팅활용동의 View Page
	 */
	@RequestMapping(value = { "/front/terms/marketing", "/front/terms/marketing/{lan}"})
	public String marketing (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		//path 설정
		String path = "/front/terms/marketing";
		return path;
		
	}
	
}