package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontAlarmService;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.CommonResultEntity;

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
@RequestMapping("front/user")
public class FrontUserController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );
    
    @Autowired
    private FrontAlarmService frontAlarmService;

    @Autowired
	private FrontUserService frontUserService;
    
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
	public String alarm (@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		// 필요한 데이터 조회
		String memId = LoginSession.getSeq(request.getSession());
		
		paramMap.put("memId", memId);
		
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontAlarmService.selectAlarmList(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
				
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
	
	/**
	 * @ClassName	: FrontUserController.java
	 * @Method		: leave
	 * @Date		: 2021. 3. 12. 
	 * @author		: dev.yklee
	 * @Description	: 회원탈퇴
	 */
	@RequestMapping(value = {"/leave","/leave/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String leave (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, @RequestParam Map<String, Object> paramMap
			, Locale localeParam ) throws Exception {
		
		model.addAttribute(Default.ResultValue.REQUEST_PARAMETER_MAP, paramMap);
		String path =  "/front/user/leave";
		path = pathToLangFront(path,pathVariables,model,request);
		return path;
	}
	
	/**
	 * @ClassName	: FrontUserController.java
	 * @Method		: leaveAgree
	 * @Date		: 2021. 3. 12. 
	 * @author		: dev.yklee
	 * @Description	: 회원탈퇴 사유 입력
	 */
	@RequestMapping(value = {"/leaveAgree","/leaveAgree/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String leaveAgree (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, @RequestParam Map<String, Object> paramMap
			, Locale localeParam ) throws Exception {
		
		model.addAttribute(Default.ResultValue.REQUEST_PARAMETER_MAP, paramMap);
		
		paramMap.put("memId", (NumberUtils.stringToInt(LoginSession.getSeq(request.getSession()))));
		
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontUserService.selectUserLearnSummary(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		String path =  "/front/user/leaveAgree";
		path = pathToLangFront(path,pathVariables,model,request);
		return path;
	}

}