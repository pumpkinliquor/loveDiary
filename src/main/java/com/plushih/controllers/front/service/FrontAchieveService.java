package com.plushih.controllers.front.service;

import java.util.Map;

import com.plushih.entities.AigoAchievementEntity;
import com.plushih.entities.AigoConceptEntity;

public interface FrontAchieveService {
	
	/**
	 * @ClassName	: FrontAchieveService.java
	 * @Method		: selectAchieveList
	 * @Date		: 2021. 1. 24. 
	 * @author		: dev.yklee
	 * @Description	: 성취기준 리스트
	 */
	public Map<String, Object> selectAchieveList(AigoAchievementEntity aigoAchievementEntity) throws Exception;

	/**	
	 * @ClassName	: FrontAchieveService.java
	 * @Method		: selectAchieveDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학스목표 상세
	 */
	public Map<String, Object> selectAchieveDetailList(AigoAchievementEntity aigoAchievementEntity) throws Exception;
	
}
