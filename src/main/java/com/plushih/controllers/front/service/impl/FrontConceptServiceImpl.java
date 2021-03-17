package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontConceptService;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoConceptEntity;

@Service("frontConceptService")
public class FrontConceptServiceImpl implements FrontConceptService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	FrontLoginService frontLoginService;
	
	/**
	 * @ClassName	: FrontAchieveServiceImpl.java
	 * @Method		: selectAchieveDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학습목표 상세
	 */
	@Override
	public Map<String, Object> selectConceptDetailList(AigoConceptEntity aigoConceptEntity) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			// 학습목표 상세 내용
			resultMap.put("detailList", commonDao.selectList("FrontConceptDAO.selectConceptDetailList", aigoConceptEntity));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}
