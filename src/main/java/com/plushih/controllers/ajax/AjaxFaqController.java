package com.plushih.controllers.ajax;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontFaqService;
import com.plushih.controllers.front.service.FrontReportService;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.controllers.front.service.FrontWeekService;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/ajax/faq")
public class AjaxFaqController extends CoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AjaxFaqController.class );
	
	@Autowired
	private FrontFaqService frontFaqService;
	
	@Autowired
	private CommonService cmmService;
	
	@Autowired
	private FrontUserService frontUserService;
	
	
	/**
	 * @ClassName	: AjaxReviewController.java
	 * @Method		: sendAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	@ResponseBody
	@RequestMapping(value = {"/list", "/list/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity list(@RequestParam Map<String,Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());
		String levId = LoginSession.getLoginLevel(request.getSession());
		
		// 필요한 데이터 세팅
		paramMap.put("memId", NumberUtils.stringToInt(memId));
		paramMap.put("optionalSubId", NumberUtils.stringToInt(subId));
		paramMap.put("levId", NumberUtils.stringToInt(levId));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontFaqService.selectFaqList(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
	
	/**
	 * @ClassName	: AjaxReviewController.java
	 * @Method		: sendAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	@ResponseBody
	@RequestMapping(value = {"/srchList", "/srchList/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity srchList(@RequestParam Map<String,Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());
		String levId = LoginSession.getLoginLevel(request.getSession());
		
		// 필요한 데이터 세팅
		paramMap.put("memId", NumberUtils.stringToInt(memId));
		paramMap.put("optionalSubId", NumberUtils.stringToInt(subId));
		paramMap.put("levId", NumberUtils.stringToInt(levId));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontFaqService.selectSrchList(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
	
}