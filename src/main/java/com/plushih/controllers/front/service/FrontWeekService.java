package com.plushih.controllers.front.service;

import java.util.Map;

public interface FrontWeekService {
	
	/**
	 * @ClassName	: FrontWeekService.java
	 * @Method		: selectWeekDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학스목표 상세
	 */
	public Map<String, Object> selectWeekReport(Map<String, Object> paramMap) throws Exception;

	public int[] selectThisDayCnt(Map<String, Object> paramMap) throws Exception;
}
