package com.plushih.controllers.ajax;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontReportService;
import com.plushih.controllers.front.service.FrontWeekService;
import com.plushih.entities.CommonResultEntity;

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
@RequestMapping("front/ajax/report")
public class AjaxReportController extends CoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AjaxReportController.class );
	
	@Autowired
	FrontWeekService frontWeekService;
	
	@Autowired
	FrontReportService frontReportService;
	
	
	/**
	 * @ClassName	: AjaxReviewController.java
	 * @Method		: sendAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	@ResponseBody
	@RequestMapping(value = {"/weekList", "/weekList/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity list(@RequestParam Map<String,Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		String memId = LoginSession.getSeq(request.getSession());
		
		paramMap.put("memId", memId);
		paramMap.put("userSubId", LoginSession.getLoginSubjectId(request.getSession()));
		paramMap.put("levId", LoginSession.getLoginLevel(request.getSession()));
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontWeekService.selectWeekReport(paramMap));
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
	@RequestMapping(value = {"/totalWeekList", "/totalWeekList/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity totalWeekList(@RequestParam Map<String,Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		String memId = LoginSession.getSeq(request.getSession());
		
		paramMap.put("memId", memId);
		paramMap.put("userSubId", LoginSession.getLoginSubjectId(request.getSession()));
		paramMap.put("levId", LoginSession.getLoginLevel(request.getSession()));
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontReportService.selectTotalWeekReport(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
	
	/**
	 * @ClassName	: AjaxReportController.java
	 * @Method		: levelReportList
	 * @Date		: 2021. 2. 9. 
	 * @author		: dev.yklee
	 * @Description	: 리포트 > 레벨평가 리스트 조회
	 */
	@ResponseBody
	@RequestMapping(value = {"/levelReportList", "/levelReportList/{lan}"}, method = {RequestMethod.POST})
	public CommonResultEntity levelReportList(@RequestParam Map<String,Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		paramMap.put("memId", LoginSession.getSeq(request.getSession()));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultList(frontReportService.selectLevelReport(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
	
	/**
	 * @ClassName	: AjaxReportController.java
	 * @Method		: levelReportList
	 * @Date		: 2021. 2. 9. 
	 * @author		: dev.yklee
	 * @Description	: 전체메뉴 / 업데이트 여부
	 */
	@ResponseBody
	@RequestMapping(value = {"/thisDayCnt", "/thisDayCnt/{lan}"}, method = {RequestMethod.POST})
	public int[] thisDayCnt(@RequestParam Map<String,Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		paramMap.put("memId", LoginSession.getSeq(request.getSession()));
		
		return frontWeekService.selectThisDayCnt(paramMap);
	}
}