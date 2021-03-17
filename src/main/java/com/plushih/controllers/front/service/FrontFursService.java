package com.plushih.controllers.front.service;

import java.util.Map;

public interface FrontFursService {
	
	/**
	 * @ClassName	: FrontFursService.java
	 * @Method		: selectContentsAnalysisInfo
	 * @Date		: 2021. 3. 3. 
	 * @author		: dev.yklee
	 * @Description	: 모의진단 > 진단결과 > 내용영역별 분석 차트데이터 조회
	 */
	public Map<String, Object> selectContentsAnalysisInfo(Map<String, Object> paramMap) throws Exception;
}
