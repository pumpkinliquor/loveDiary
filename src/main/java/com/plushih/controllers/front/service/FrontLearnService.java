package com.plushih.controllers.front.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.plushih.entities.AigoLearnEntity;
import com.plushih.entities.AigoQuestionAnswerEntity;

public interface FrontLearnService {
	
	/**
	 * @ClassName	: FrontLearnService.java
	 * @Method		: presentQuestions
	 * @Date		: 2021. 1. 27. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이 > 기출문제,확인문제
	 */
	public Map<String, Object> learning(HttpServletRequest request, AigoLearnEntity aigoLearnEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontLearnService.java
	 * @Method		: insertAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이 > 사용자 답안 제출처리
	 */
	public String insertAnswer(AigoQuestionAnswerEntity aigoQuestionAnswerEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontLearnService.java
	 * @Method		: selectAchieveDetail
	 * @Date		: 2021. 1. 25. 
	 * @author		: dev.yklee
	 * @Description	: 출제경향 상세
	 */
	public Map<String, Object> selectHome(Map<String, Object> param) throws Exception;
	
}
