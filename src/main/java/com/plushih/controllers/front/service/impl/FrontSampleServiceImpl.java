package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontSampleService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.SampleEntity;

@Service("frontSampleService")
public class FrontSampleServiceImpl implements FrontSampleService {
	
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * @ClassName	: FrontSampleServiceImpl.java
	 * @Method		: selectSampleList
	 * @Date		: 2021. 1. 27. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	@Override
	public List<SampleEntity> selectSampleList(SampleEntity sampleEntity) throws Exception {
		return commonDao.selectList("FrontSampleDAO.selectSampleList", sampleEntity);
	}

	/**
	 * @ClassName	: FrontSampleServiceImpl.java
	 * @Method		: selectSampleListCount
	 * @Date		: 2021. 1. 27. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	@Override
	public int selectSampleListCount(SampleEntity sampleEntity) throws Exception {
		return commonDao.selectOne("FrontSampleDAO.selectSampleListCount", sampleEntity);
	}

	/**
	 * @ClassName	: FrontSampleServiceImpl.java
	 * @Method		: selectSampleInfo
	 * @Date		: 2021. 1. 27. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	@Override
	public Map<String, Object> selectSampleInfo(HttpServletRequest request, SampleEntity sampleEntity) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		return resultMap;
	}

}
