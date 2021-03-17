package com.plushih.controllers.front.service;

import java.util.Map;

import com.plushih.entities.AigoTendencyEntity;

public interface FrontTendencyService {
	
	/**
	 * @ClassName	: FrontTendencyService.java
	 * @Method		: selectAchieveDetail
	 * @Date		: 2021. 1. 25. 
	 * @author		: dev.yklee
	 * @Description	: 출제경향 상세
	 */
	public Map<String, Object> selectTendencyDetail(AigoTendencyEntity aigoTendencyEntity) throws Exception;
	
}
