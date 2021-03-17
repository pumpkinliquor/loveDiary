package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontPromotionService;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.daos.CommonDao;

@Service("frontPromotionService")
public class FrontPromotionServiceImpl implements FrontPromotionService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	FrontLoginService frontLoginService;
	
	/**
	 * @ClassName	: MemberJoinServiceImpl.java
	 * @Method		: insertMember
	 * @Date		: 2020. 12. 29.
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > 등록
	 */
	public Map<String, Object> selectPromotionList(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			resultMap.put("promotionList", commonDao.selectList("FrontPromotionDAO.selectPromotionList", paramMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontPromotionServiceImpl.java
	 * @Method		: selectPromotionDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학습목표 상세
	 */
	@Override
	public Map<String, Object> selectPromotionDetail(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("promotionDetail", commonDao.selectOne("FrontPromotionDAO.selectPromotionList", paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
}
