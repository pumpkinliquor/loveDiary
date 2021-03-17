package com.plushih.controllers.front.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.plushih.entities.SampleEntity;
import com.plushih.entities.UserMemberEntity;

public interface FrontSampleService {
	
	/**
	 * @ClassName	: FrontSampleService.java
	 * @Method		: selectSampleList
	 * @Date		: 2021. 1. 27. 
	 * @author		: dev.yklee
	 * @Description	: 샘플 > 리스트
	 */
	public List<SampleEntity> selectSampleList(SampleEntity sampleEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontSampleService.java
	 * @Method		: selectSampleListCount
	 * @Date		: 2021. 1. 27. 
	 * @author		: dev.yklee
	 * @Description	: 샘플 > 리스트 카운트
	 */
	public int selectSampleListCount(SampleEntity sampleEntity) throws Exception;
	
	/**
	 * @ClassName	: FrontSampleService.java
	 * @Method		: selectSampleInfo
	 * @Date		: 2021. 1. 27. 
	 * @author		: dev.yklee
	 * @Description	: 샘플 > 상세
	 */
	public Map<String, Object> selectSampleInfo(HttpServletRequest request, SampleEntity sampleEntity) throws Exception;

}
