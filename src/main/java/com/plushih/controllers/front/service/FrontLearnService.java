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
	 * @Method		: weeklyTest
	 * @Date		: 2021. 1. 31. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이 > 주간평가
	 */
	public Map<String, Object> weeklyTest(HttpServletRequest request, AigoLearnEntity aigoLearnEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontLearnService.java
	 * @Method		: levelTest
	 * @Date		: 2021. 1. 31. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이 > 레벨평가
	 */
	public Map<String, Object> levelTest(HttpServletRequest request, AigoLearnEntity aigoLearnEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontLearnService.java
	 * @Method		: insertAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이 > 사용자 답안 제출처리 (공통)
	 */
	public Map<String, Object> insertAnswer(AigoQuestionAnswerEntity aigoQuestionAnswerEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontLearnService.java
	 * @Method		: insertAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이 > 사용자 답안 제출처리 (기출문제/확인학습/주간평가/레벨평가)
	 */
	public Map<String, Object> insertAnswerLearn(AigoQuestionAnswerEntity aigoQuestionAnswerEntity, HttpServletRequest request) throws Exception;
	
	/**
	 * @ClassName	: FrontLearnService.java
	 * @Method		: selectAchieveDetail
	 * @Date		: 2021. 1. 25. 
	 * @author		: dev.yklee
	 * @Description	: 출제경향 상세
	 */
	public Map<String, Object> selectHome(HttpServletRequest request, Map<String, Object> param) throws Exception;
	
	/**
	 * @ClassName	: FrontLearnService.java
	 * @Method		: selectLearnProgressInfo
	 * @Date		: 2021. 2. 1. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	public Map<String, Object> selectLearnProgressInfo(AigoLearnEntity aigoLearnEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontLearnService.java
	 * @Method		: updateUserLevel
	 * @Date		: 2021. 2. 12. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 레벨업
	 */
	public Map<String, Object> updateUserLevel(HttpServletRequest request, Map<String, Object> paramMap) throws Exception;
	
}
