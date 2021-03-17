package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/user")
public class FrontUserController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );

    /**
	 * @ClassName	: FrontUserController.java
	 * @Method		: userEdit
	 * @Date		: 2021. 1. 18. 
	 * @author		: dev.khko
	 * @Description	: 회원정보 조회 및 수정 View
	 */
	@RequestMapping(value = {"/userEdit"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String userEdit (HttpServletRequest request
							, HttpServletResponse response
							, @PathVariable Map<String, String> pathVariables
							, ModelMap model
							, Locale localeParam ) throws Exception {
		return "/front/user/userEdit";
	}
	
	/**
	 * @ClassName	: FrontUserController.java
	 * @Method		: alarm
	 * @Date		: 2021. 1. 22. 
	 * @author		: dev.khko
	 * @Description	: 알람 조회 및 확인 View
	 */
	@RequestMapping(value = {"/alarm"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String alarm (HttpServletRequest request
							, HttpServletResponse response
							, @PathVariable Map<String, String> pathVariables
							, ModelMap model
							, Locale localeParam ) throws Exception {
		return "/front/user/alarm";
	}
	
	/**
	 * @ClassName	: FrontUserController.java
	 * @Method		: menuLayer
	 * @Date		: 2021. 1. 22. 
	 * @author		: dev.khko
	 * @Description	: 알람 조회 및 확인 View
	 */
	@RequestMapping(value = {"/menuLayer"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String menuLayer (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, Locale localeParam ) throws Exception {
		return "/front/user/menuLayer";
	}
	
	/**
	 * @ClassName	: FrontUserController.java
	 * @Method		: setting
	 * @Date		: 2021. 1. 25. 
	 * @author		: dev.khko
	 * @Description	: 설정
	 */
	@RequestMapping(value = {"/setting"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String setting (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, Locale localeParam ) throws Exception {
		return "/front/user/setting";
	}

}