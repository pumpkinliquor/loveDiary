package com.plushih.controllers.front.service;

import java.util.HashMap;

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

	/**
	 * @ClassName	: FrontQuestionAnswerServiceImpl.java
	 * @Method		: selectQuestionRetryInfo
	 * @Date		: 2021. 1. 29.
	 * @author		: dev.khko
	 * @Description	: 다시풀기 상세 정보 조회
	 */
	public HashMap<String, Object> selectQuestionRetryInfo(HashMap<String, String> requestParams) throws Exception;

}
