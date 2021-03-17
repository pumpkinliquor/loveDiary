package com.plushih.controllers.ajax;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.controllers.front.service.FrontQuestionAnswerService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/ajax/question")
public class AjaxAigoQuestionAnswerController extends CoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoQuestionAnswerController.class );
	
	@Autowired
	FrontQuestionAnswerService frontQuestionAnswerService;
	
	
	/**
	 * @ClassName	: AjaxAigoQuestionController.java
	 * @Method		: saveAnswer
	 * @Date		: 2021. 1. 22. 
	 * @author		: dev.yklee
	 * @Description	: 사용자의 문제풀이 답안 저장 (공통)
	 */
	@ResponseBody
	@RequestMapping(value = {"/saveAnswer", "/saveAnswer/{lan}"}, method = {RequestMethod.POST})
	public CommonResultEntity saveAnswer(HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, AigoQuestionAnswerEntity aigoQuestionAnswerEntity
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		// 사용자의 문제풀이 답안 저장
		CommonResultEntity res = new CommonResultEntity();
		res.setResultCode(frontQuestionAnswerService.insertQuestionAnswer(request, aigoQuestionAnswerEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
}