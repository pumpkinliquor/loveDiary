package com.plushih.controllers.ajax;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontReviewService;
import com.plushih.entities.AigoQuestionAnswerEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/ajax/review")
public class AjaxReviewController extends CoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AjaxReviewController.class );
	
	@Autowired
	FrontReviewService frontReviewService;
	
	
	/**
	 * @ClassName	: AjaxReviewController.java
	 * @Method		: sendAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	@ResponseBody
	@RequestMapping(value = {"/list", "/list/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity list(@RequestParam Map<String,Object> aigoAchievementEntity, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		String memId = LoginSession.getSeq(request.getSession());
		
		aigoAchievementEntity.put("memId", memId);
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontReviewService.selectReviewList(aigoAchievementEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
}