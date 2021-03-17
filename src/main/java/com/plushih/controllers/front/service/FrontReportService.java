package com.plushih.controllers.front.service;

import java.util.List;
import java.util.Map;

public interface FrontReportService {
	
	/**
	 * @ClassName	: FrontWeekService.java
	 * @Method		: selectWeekDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: AI 종합리포트 > 상세
	 */
	public Map<String, Object> selectTotalReport(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @ClassName	: FrontWeekService.java
	 * @Method		: selectWeekDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.jwhwang
	 * @Description	: AI 종합리포트 > 주간평가 상세 문항분석
	 */
	public Map<String, Object> selectTotalWeekReport(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @ClassName	: FrontReportService.java
	 * @Method		: selectTotalLevelReport
	 * @Date		: 2021. 2. 24. 
	 * @author		: dev.yklee
	 * @Description	: AI 종합리포트 > 레벨평가 상세 문항분석
	 */
	public Map<String, Object> selectTotalLevelReport(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @ClassName	: FrontReportService.java
	 * @Method		: selectLevelReportInfo
	 * @Date		: 2021. 2. 9. 
	 * @author		: dev.yklee
	 * @Description	: 리포트 > 레벨평가 리포트 정보 조회
	 */
	public Map<String, Object> selectLevelReportInfo(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @ClassName	: FrontReportService.java
	 * @Method		: selectLevelReport
	 * @Date		: 2021. 2. 9. 
	 * @author		: dev.yklee
	 * @Description	: 리포트 > 레벨평가 리포트 > 리스트
	 */
	public List<Map<String, Object>> selectLevelReport(Map<String, Object> paramMap) throws Exception;
	
}
