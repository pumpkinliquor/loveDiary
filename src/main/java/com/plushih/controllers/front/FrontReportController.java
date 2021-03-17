package com.plushih.controllers.front;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plushih.controllers.front.service.FrontReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontWeekService;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;


@Controller
@RequestMapping("front/report")
public class FrontReportController extends CoreController {

	@Autowired
	private FrontWeekService frontWeekService;
	
	@Autowired
	private FrontReportService frontReportService;
	
	@Autowired
	private CommonService cmmService;

	private static final Logger LOGGER = LoggerFactory.getLogger(FrontReportController.class);


	/**
	 * 종합리포트
	 *
	 * @param model
	 * @param localeParam
	 * @return
	 */
	@RequestMapping(value = {"/totalReport"})
	public String totalReport(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {

		String memId = LoginSession.getSeq(request.getSession());

		paramMap.put("memId", memId);

		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontReportService.selectTotalReport(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute("userInfo", cmmService.userInfo(paramMap));
		return "/front/report/totalReport";
	}

	/**
	 * 종합리포트
	 *
	 * @param model
	 * @param localeParam
	 * @return
	 */
	@RequestMapping(value = {"/totalReportWeek"})
	public String totalReportWeek(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {

		String memId = LoginSession.getSeq(request.getSession());

		paramMap.put("memId", memId);

		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontReportService.selectTotalWeekReport(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		return "/front/report/totalReportWeek";
	}
	/**
	 * 종합리포트
	 *
	 * @param model
	 * @param localeParam
	 * @return
	 */
	@RequestMapping(value = {"/totalReportLevel"})
	public String totalReportLevel(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		paramMap.put("memId", LoginSession.getSeq(request.getSession()));
		paramMap.put("optionalSub", NumberUtils.stringToInt(LoginSession.getLoginSubjectId(request.getSession())));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontReportService.selectTotalLevelReport(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		return "/front/report/totalReportLevel";
	}
	/**
	 * 주간리포트
	 * 
	 * @param model
	 * @param localeParam
	 * @return
	 */
	@RequestMapping(value = {"/weekReport"})
	public String weekList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		String memId = LoginSession.getSeq(request.getSession());
		
		paramMap.put("memId", memId);
		paramMap.put("userSubId", LoginSession.getLoginSubjectId(request.getSession()));
		paramMap.put("levId", LoginSession.getLoginLevel(request.getSession()));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontWeekService.selectWeekReport(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute("userInfo", cmmService.userInfo(paramMap));
		
		return "/front/report/weekReport";
	}
	
	/**
	 * @ClassName	: FrontReportController.java
	 * @Method		: levelReport
	 * @Date		: 2021. 2. 8. 
	 * @author		: dev.yklee
	 * @Description	: 레벨평가 리포트
	 */
	@RequestMapping(value = {"/levelReport"})
	public String levelReport(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		paramMap.put("memId", LoginSession.getSeq(request.getSession()));
		paramMap.put("optionalSub", NumberUtils.stringToInt(LoginSession.getLoginSubjectId(request.getSession()))); 
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontReportService.selectLevelReportInfo(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute(Default.ResultValue.REQUEST_PARAMETER_MAP, paramMap);
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("userInfo", cmmService.userInfo(paramMap));
		
		return "/front/report/levelReport";
	}
	
	
}