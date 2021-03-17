package com.plushih.controllers.front.service;

import java.util.Map;

public interface FrontNoticeService {
	
	/**
	 * @ClassName	: FrontNoticeService.java
	 * @Method		: selectNoticeList
	 * @Date		: 2021. 1. 24. 
	 * @author		: dev.yklee
	 * @Description	: 성취기준 리스트
	 */
	public Map<String, Object> selectNoticeList(Map<String, Object> paramMap) throws Exception;

	/**	
	 * @ClassName	: FrontNoticeService.java
	 * @Method		: selectNoticeDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학스목표 상세
	 */
	public Map<String, Object> selectNoticeDetail(Map<String, Object> paramMap) throws Exception;
	
}
