package com.plushih.controllers.front.service;

import java.util.Map;

import com.plushih.entities.CommonResultEntity;

public interface FrontAlarmService {
	
	/**
	 * @ClassName	: FrontAlarmService.java
	 * @Method		: selectAlarmDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학스목표 상세
	 */
	public Map<String, Object> selectAlarmList(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: updateUserInfoTempId
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 설정 정보 저장
	 */
	public CommonResultEntity readAlarm(Map<String, Object> paramMap, CommonResultEntity commonResultEntity) throws Exception;
	
	public Map<String, Object> notReadAlarmCnt(Map<String, Object> paramMap)throws Exception;
	
}
