package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.plushih.common.constant.Code;
import com.plushih.controllers.front.service.FrontReviewService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoConceptEntity;

@Service("frontReviewService")
public class FrontReviewServiceImpl implements FrontReviewService {
	
	@Autowired
	private CommonDao commonDao;
	
	
	/**
	 * @ClassName	: FrontReviewServiceImpl.java
	 * @Method		: selectReviewDetail
	 * @Date		: 2021. 1. 25. 
	 * @author		: dev.yklee
	 * @Description	: 출제경향 상세
	 */
	@Override
	public Map<String, Object> selectReviewList(Map<String, Object> reviewMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		String[] levIdStr = null;
		String[] subIdStr = null;
		String[] unitIdStr = null;
		try {
			// 성취도 체크는 기출문제, 확인문제, 주간평가 문제들로만 체크한다.
			int[] arrCategories			= {Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK, Code.Aigo.CATEGORY_WEEKLY};
			int[] arrCategoriesSub	= {Code.Aigo.CATEGORY_PREV_BASIC, Code.Aigo.CATEGORY_CHECK_BASIC, Code.Aigo.CATEGORY_WEEKLY_BASIC};
			
			reviewMap.put("arrCategories", arrCategories);
			reviewMap.put("arrCategoriesSub", arrCategoriesSub);
			
			// 학습목표 상세 내용
			reviewMap.put("subId", Code.Aigo.MATH_I);
			
			if(MapUtils.isNotEmpty(reviewMap)) {
				if(!StringUtils.isEmpty(reviewMap.get("levIdStr"))) {
					levIdStr = reviewMap.get("levIdStr").toString().split(",");
					reviewMap.put("levIdStr", levIdStr);
					resultMap.put("levIdStr", levIdStr);
				}
				if(!StringUtils.isEmpty(reviewMap.get("subIdStr"))) {
					subIdStr = reviewMap.get("subIdStr").toString().split(",");
					reviewMap.put("subIdStr", subIdStr);
					resultMap.put("subIdStr", levIdStr);
				}
				if(!StringUtils.isEmpty(reviewMap.get("unitIdStr"))) {
					unitIdStr = reviewMap.get("unitIdStr").toString().split(",");
					reviewMap.put("unitIdStr", unitIdStr);
					resultMap.put("unitIdStr", levIdStr);
				}
			}
			
			// 결과 Set
			resultMap.put("list", commonDao.selectList("FrontReviewDAO.selectReviewList", reviewMap));
			resultMap.put("fList", commonDao.selectList("FrontReviewDAO.selectFilterList", reviewMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}
