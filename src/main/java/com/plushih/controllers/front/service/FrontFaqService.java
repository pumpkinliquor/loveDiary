package com.plushih.controllers.front.service;

import java.util.Map;

public interface FrontFaqService {
	
	/**
	 * @ClassName	: FrontFaqService.java
	 * @Method		: selectFaqList
	 * @Date		: 2021. 1. 24. 
	 * @author		: dev.yklee
	 * @Description	: 성취기준 리스트
	 */
	public Map<String, Object> selectFaqList(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @ClassName	: FrontFaqService.java
	 * @Method		: selectFaqList
	 * @Date		: 2021. 1. 24. 
	 * @author		: dev.yklee
	 * @Description	: 성취기준 리스트
	 */
	public Map<String, Object> selectSrchList(Map<String, Object> paramMap) throws Exception;

	/**	
	 * @ClassName	: FrontFaqService.java
	 * @Method		: selectFaqDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학스목표 상세
	 */
	public Map<String, Object> selectFaqDetail(Map<String, Object> paramMap) throws Exception;
	
}
