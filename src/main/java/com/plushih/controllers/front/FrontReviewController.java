package com.plushih.controllers.front;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
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
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontConceptService;
import com.plushih.controllers.front.service.FrontQuestionAnswerService;
import com.plushih.controllers.front.service.FrontReviewService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.AigoConceptEntity;
import com.plushih.entities.AigoNotionEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;


@Controller
@RequestMapping("front/review")
public class FrontReviewController extends CoreController {
	@Autowired
	private FrontReviewService frontReviewService;
	
	@Autowired
	private FrontConceptService frontConceptService;
	
	@Autowired
	private FrontQuestionAnswerService frontQuestionAnswerService;
	
	@Autowired
    private CommonService commonService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);

	/**
	 * @ClassName	: FrontReviewController.java
	 * @Method		: list
	 * @Date		: 2021. 1. 26.
	 * @author		: dev.yklee
	 * @Description	: 다시풀기 > 문제 리스트
	 */
	@RequestMapping(value = {"/list", "/list/{lan}"})
	public String list(@RequestParam Map<String,Object> reviewMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		String memId = LoginSession.getSeq(request.getSession());
		
		String levIdStr = (String) reviewMap.get("levIdStr");
		String subIdStr = (String) reviewMap.get("subIdStr");
		String unitIdStr = (String) reviewMap.get("unitIdStr");
		
		reviewMap.put("memId", memId);
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontReviewService.selectReviewList(reviewMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute("userInfo", commonService.userInfo(reviewMap));
		reviewMap.put("levIdStr", levIdStr);
		reviewMap.put("subIdStr", subIdStr);
		reviewMap.put("unitIdStr", unitIdStr);
		model.addAttribute("dp", reviewMap);
		
		return "/front/review/list";
	}
	/**
	 * @ClassName	: FrontReviewController.java
	 * @Method		: question
	 * @Date		: 2021. 1. 26.
	 * @author		: dev.yklee
	 * @Description	: 다시풀기 > 문제 페이지
	 */
	@RequestMapping(value = {"/question", "/question/{lan}"})
	public String question(HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam, @RequestParam Map<String,Object> paramMap) throws Exception {
		String memId = LoginSession.getSeq(request.getSession());
		String qstId = StringUtils.nvl(paramMap.get("qstId"), "0") ;
		String cptId = (String) paramMap.get("cptId");
		String notId = (String) paramMap.get("notId");

		HashMap<String, String> requestParam = new HashMap<String, String>();
		requestParam.put("qstId", StringUtils.replaceInt(qstId,"QST"));
		requestParam.put("cptId", StringUtils.replaceInt(cptId,"CPT"));
		requestParam.put("notId", StringUtils.replaceInt(notId,"CPT"));
		requestParam.put("memId", memId);

		HashMap<String, Object> responseParam = frontQuestionAnswerService.selectQuestionRetryInfo(requestParam);
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, responseParam);
		model.addAttribute(Default.ResultValue.REQUEST_PARAMETER_MAP, paramMap);

		return request.getRequestURI().toLowerCase();
	}
	
	/**
	 * @ClassName	: FrontReviewController.java
	 * @Method		: question
	 * @Date		: 2021. 1. 26.
	 * @author		: dev.yklee
	 * @Description	: 다시풀기 > 문제 페이지
	 */
	@RequestMapping(value = {"/notion", "/notion/{lan}"})
	public String concept(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, AigoConceptEntity aigoConceptEntity, ModelMap model, Locale localeParam) throws Exception {
		
		String memId = LoginSession.getSeq(request.getSession());
		
		Map<String, Object> resultMap = new HashedMap();
		
		resultMap.put("resultDataConcept", frontConceptService.selectConceptDetail(paramMap));
		
		paramMap.put("memId", memId);
		paramMap.put("type", "NOTION");
		paramMap.put("seq", ((List<AigoNotionEntity>)((HashMap) resultMap.get("resultDataConcept")).get("notionInfo")).get(0).getNotId());
		
		resultMap.put("resultDataFile", commonService.cmmFileList(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, resultMap);
		return "/front/review/notionDetail";
	}

}