package com.plushih.controllers.front.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
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
			List<Map<String, Object>> getMap = (List) commonDao.selectList("FrontConceptDAO.selectConceptDetailList", aigoConceptEntity);
			
			MapUtils.debugPrint(System.out, "getMap", getMap.get(0));
			
			// 학습목표 상세 내용
			resultMap.put("detailList", getMap);
			resultMap.put("notionInfo", commonDao.selectList("FrontConceptDAO.selectNotionDetailInfo", getMap.get(0)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontAchieveServiceImpl.java
	 * @Method		: selectAchieveDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학습목표 상세
	 */
	@Override
	public Map<String, Object> selectConceptDetail(Map<String, Object> aigoConceptEntity) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			// 학습목표 상세 내용
			Map<String, Object> tempMap = new HashMap<>();
			Map<String, Object> cptNamesMap = new HashMap<>();
			
			tempMap = commonDao.selectOne("FrontConceptDAO.fromReviewConceptDetail", aigoConceptEntity);
			
			if(tempMap.get("allCptId") != null) {
				String allCptId[] = tempMap.get("allCptId").toString().split(",");
				Arrays.sort(allCptId);
				aigoConceptEntity.put("allCptId", allCptId);
				cptNamesMap = commonDao.selectOne("FrontCommentaryDAO.selectCptNames", aigoConceptEntity);
				//정렬해서 새롭게 세팅
				tempMap.put("all_cpt_id", cptNamesMap.get("allCptId"));
				tempMap.put("all_acv_id", cptNamesMap.get("allAcvId"));
				tempMap.put("all_cpt_name", cptNamesMap.get("allCptName"));
				tempMap.put("not_id", (allCptId != null) ? allCptId[0] : "");
			}
			
			MapUtils.debugPrint(System.out, "getMap", tempMap);
			
			resultMap.put("detail", tempMap);
			resultMap.put("notionInfo", commonDao.selectList("FrontConceptDAO.selectNotionDetailInfo", tempMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}
