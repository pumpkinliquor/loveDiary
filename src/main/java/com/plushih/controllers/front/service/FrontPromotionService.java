package com.plushih.controllers.front.service;

import java.util.Map;

public interface FrontPromotionService {
	
	/**
	 * @ClassName	: FrontPromotionService.java
	 * @Method		: selectPromotionList
	 * @Date		: 2021. 1. 24. 
	 * @author		: dev.yklee
	 * @Description	: 성취기준 리스트
	 */
	public Map<String, Object> selectPromotionList(Map<String, Object> paramMap) throws Exception;

	/**	
	 * @ClassName	: FrontPromotionService.java
	 * @Method		: selectPromotionDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학스목표 상세
	 */
	public Map<String, Object> selectPromotionDetail(Map<String, Object> paramMap) throws Exception;
	
}
