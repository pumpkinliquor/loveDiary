package com.plushih.controllers.ajax;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontLearnService;
import com.plushih.entities.AigoLearnEntity;
import com.plushih.entities.AigoQuestionAnswerEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserMemberEntity;

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
@RequestMapping("front/ajax/learn")
public class AjaxLearnController extends CoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AjaxLearnController.class );
	
	@Autowired
	FrontLearnService frontLearnService;
	
	
	/**
	 * @ClassName	: AjaxLearnController.java
	 * @Method		: sendAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 문제풀이 답변 저장 (공통)
	 */
	@ResponseBody
	@RequestMapping(value = {"/sendAnswer", "/sendAnswer/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity sendAnswer(HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, AigoQuestionAnswerEntity aigoQuestionAnswerEntity
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		// 사용자 key 세팅
		aigoQuestionAnswerEntity.setMemId(LoginSession.getSeq(request.getSession()));
		
		// 사용자 답안 제출 처리
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontLearnService.insertAnswer(aigoQuestionAnswerEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
	
	/**
	 * @ClassName	: AjaxLearnController.java
	 * @Method		: sendAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이 답변 저장
	 */
	@ResponseBody
	@RequestMapping(value = {"/sendAnswerLearn", "/sendAnswerLearn/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity sendAnswerLearn(HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, AigoQuestionAnswerEntity aigoQuestionAnswerEntity
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		// 사용자 key 세팅
		aigoQuestionAnswerEntity.setMemId(LoginSession.getSeq(request.getSession()));
		
		// 사용자 답안 제출 처리
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontLearnService.insertAnswerLearn(aigoQuestionAnswerEntity, request));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
	
	/**
	 * @ClassName	: AjaxLearnController.java
	 * @Method		: checkLearnProgress
	 * @Date		: 2021. 2. 1. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	@ResponseBody
	@RequestMapping(value = {"/checkLearnProgress", "/checkLearnProgress/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity checkLearnProgress(HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, AigoLearnEntity aigoLearnEntity
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		// 사용자 key 세팅
		aigoLearnEntity.setMemId(LoginSession.getSeq(request.getSession()));
		
		// 사용자 학습을 어디까지 진행했는지 확인한다.
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontLearnService.selectLearnProgressInfo(aigoLearnEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
	
	/**
	 * @ClassName	: AjaxLearnController.java
	 * @Method		: levelUp
	 * @Date		: 2021. 2. 12. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 레벨업
	 */
	@ResponseBody
	@RequestMapping(value = {"/levelUp", "/levelUp/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public CommonResultEntity levelUp(HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, @RequestParam Map<String, Object> paramMap
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontLearnService.updateUserLevel(request, paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return res;
	}
}