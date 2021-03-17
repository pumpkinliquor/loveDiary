package com.plushih.controllers.front;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontLearnService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.AigoLearnEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;


@Controller
@RequestMapping("front/learn")
public class FrontLearnController extends CoreController {
	
	@Autowired
	private FrontLearnService frontLearnService;
	
	@Autowired
	private CommonService cmmService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);
	
	/**
	 * @ClassName	: FrontLearnController.java
	 * @Method		: home
	 * @Date		: 2021. 1. 27. 
	 * @author		: hwang
	 * @Description	: 학습 > 홈
	 */
	@RequestMapping(value = {"/home", "/home/{lan}"})
	public String home(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());

		// 필요한 데이터 세팅
		// 레벨 임의 세팅 - 나중에 모의진단 결과 레벨 세팅해주어야 함
		param.put("levId", LoginSession.getLoginLevel(request.getSession()));
		param.put("memId", memId);
		param.put("optionalSubId", subId);
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		
		res.setResultData(frontLearnService.selectHome(request, param));
		
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute("userInfo", cmmService.userInfo(param));
		
		return request.getRequestURI().toLowerCase();
	}
	
	/**
	 * @ClassName	: FrontLearnController.java
	 * @Method		: question
	 * @Date		: 2021. 1. 26. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 기출문제 / 확인문제
	 */
	@RequestMapping(value = {"/question", "/question/{lan}"})
	public String question(HttpServletRequest request, HttpServletResponse response, ModelMap model, AigoLearnEntity aigoLearnEntity, Locale localeParam) throws Exception {
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontLearnService.learning(request, aigoLearnEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute(Default.ResultValue.REQUEST_PARAMETER_MAP, aigoLearnEntity);
		
		return request.getRequestURI().toLowerCase();
	}
	
	/**
	 * @ClassName	: FrontLearnController.java
	 * @Method		: weeklyTest
	 * @Date		: 2021. 1. 31. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 주간평가
	 */
	@RequestMapping(value = {"/weeklyTest", "/weeklyTest/{lan}"})
	public String weeklyTest(HttpServletRequest request, HttpServletResponse response, ModelMap model, AigoLearnEntity aigoLearnEntity, Locale localeParam) throws Exception {
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontLearnService.weeklyTest(request, aigoLearnEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute(Default.ResultValue.REQUEST_PARAMETER_MAP, aigoLearnEntity);
		
		return "/front/learn/weeklyTest";
	}
	
	/**
	 * @ClassName	: FrontLearnController.java
	 * @Method		: levelTest
	 * @Date		: 2021. 1. 31. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 레벨평가
	 */
	@RequestMapping(value = {"/levelTest", "/levelTest/{lan}"})
	public String levelTest(HttpServletRequest request, HttpServletResponse response, ModelMap model, AigoLearnEntity aigoLearnEntity, Locale localeParam) throws Exception {
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontLearnService.levelTest(request, aigoLearnEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute(Default.ResultValue.REQUEST_PARAMETER_MAP, aigoLearnEntity);
		
		return "/front/learn/levelTest";
	}
	
	/**
	 * @ClassName	: AjaxLearnController.java
	 * @Method		: exit
	 * @Date		: 2021. 2. 18. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 학습종료
	 */
	@RequestMapping(value = {"/exit", "/exit/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String exit(HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		request.getSession().setAttribute(LoginSession.LEARNING_STATUS, Default.NO_LOWER);
		
		return "redirect:/front/learn/home";
	}
}