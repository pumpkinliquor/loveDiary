package com.plushih.controllers.front.service;

import javax.servlet.http.HttpServletRequest;

import com.plushih.entities.AigoQuestionAnswerEntity;

public interface FrontQuestionAnswerService {
	
	/**
	 * @ClassName	: FrontQuestionService.java
	 * @Method		: insertQuestionAnswer
	 * @Date		: 2021. 1. 22. 
	 * @author		: dev.yklee
	 * @Description	: 사용자의 문제풀이 답안 저장
	 */
	public String insertQuestionAnswer(HttpServletRequest request, AigoQuestionAnswerEntity aigoQuestionAnswerEntity) throws Exception;

}
