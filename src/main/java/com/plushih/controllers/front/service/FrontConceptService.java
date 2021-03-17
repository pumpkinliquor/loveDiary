package com.plushih.controllers.front.service;

import java.util.Map;

import com.plushih.entities.AigoAchievementEntity;
import com.plushih.entities.AigoConceptEntity;

public interface FrontConceptService {
	
	/**
	 * @ClassName	: FrontConceptService.java
	 * @Method		: selectConceptDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학스목표 상세
	 */
	public Map<String, Object> selectConceptDetailList(AigoConceptEntity aigoConceptEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontConceptService.java
	 * @Method		: selectConceptDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학스목표 상세
	 */
	public Map<String, Object> selectConceptDetail(Map<String, Object> aigoConceptEntity) throws Exception;
	
}
