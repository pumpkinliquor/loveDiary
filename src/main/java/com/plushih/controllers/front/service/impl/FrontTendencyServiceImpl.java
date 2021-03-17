package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.controllers.front.service.FrontTendencyService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoTendencyEntity;

@Service("frontTendencyService")
public class FrontTendencyServiceImpl implements FrontTendencyService {
	
	@Autowired
	private CommonDao commonDao;
	
	
	/**
	 * @ClassName	: FrontTendencyServiceImpl.java
	 * @Method		: selectTendencyDetail
	 * @Date		: 2021. 1. 25. 
	 * @author		: dev.yklee
	 * @Description	: 출제경향 상세
	 */
	@Override
	public Map<String, Object> selectTendencyDetail(AigoTendencyEntity aigoTendencyEntity) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			// 학습목표 상세 내용
			aigoTendencyEntity.setSubId(Code.Aigo.MATH_I);
			
			// 결과 Set
			resultMap.put("detail", commonDao.selectOne("FrontTendencyDAO.selectTendencyDetail", aigoTendencyEntity));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}
