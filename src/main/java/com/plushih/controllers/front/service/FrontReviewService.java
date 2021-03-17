package com.plushih.controllers.front.service;

import java.util.Map;

public interface FrontReviewService {
	
	/**
	 * @ClassName	: FrontReviewService.java
	 * @Method		: selectAchieveDetail
	 * @Date		: 2021. 1. 25. 
	 * @author		: dev.yklee
	 * @Description	: 출제경향 상세
	 */
	public Map<String, Object> selectReviewList(Map<String, Object> reviewMap) throws Exception;
	
}
