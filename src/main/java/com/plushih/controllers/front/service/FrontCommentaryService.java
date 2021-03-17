package com.plushih.controllers.front.service;

import java.util.Map;

import com.plushih.entities.AigoCommentaryEntity;

public interface FrontCommentaryService {
	
	/**
	 * @ClassName	: FrontCommentaryService.java
	 * @Method		: selectCommentaryDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학스목표 상세
	 */
	public Map<String, Object> selectCommentaryDetail(Map<String, Object> commentaryMap) throws Exception;
	
	/**
	 * @ClassName	: FrontCommentaryService.java
	 * @Method		: selectCommentaryInfo
	 * @Date		: 2021. 2. 16. 
	 * @author		: dev.yklee
	 * @Description	: 해설 컨텐츠 조회
	 */
	public Map<String, Object> selectCommentaryInfo(AigoCommentaryEntity commentaryMap) throws Exception;
	
}
