package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.controllers.front.service.FrontAchieveService;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoAchievementEntity;
import com.plushih.entities.AigoConceptEntity;

@Service("frontAchieveService")
public class FrontAchieveServiceImpl implements FrontAchieveService {
	
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
	public Map<String, Object> selectAchieveList(AigoAchievementEntity aigoAchievementEntity) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			// 수학 I - 기본과목 리스트
			aigoAchievementEntity.setSubId(Code.Aigo.MATH_I);
			List<AigoAchievementEntity> mathI = commonDao.selectList("FrontAchieveDAO.selectAchieveList", aigoAchievementEntity);
			
			// 수학 II - 기본과목 리스트
			aigoAchievementEntity.setSubId(Code.Aigo.MATH_II);
			List<AigoAchievementEntity> mathII = commonDao.selectList("FrontAchieveDAO.selectAchieveList", aigoAchievementEntity);
			
			// 사용자 선택과목 리스트
			aigoAchievementEntity.setSubId(aigoAchievementEntity.getOptionalSubId());
			List<AigoAchievementEntity> mathOptional = commonDao.selectList("FrontAchieveDAO.selectAchieveList", aigoAchievementEntity);
			
			// 결과 Set
			resultMap.put("mathI", mathI);
			resultMap.put("mathII", mathII);
			resultMap.put("mathOptional", mathOptional);
			resultMap.put("lv", aigoAchievementEntity.getLevId());
			
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
	public Map<String, Object> selectAchieveDetailList(AigoAchievementEntity aigoAchievementEntity) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			// 학습목표 상세 내용
			aigoAchievementEntity.setSubId(Code.Aigo.MATH_I);
			AigoAchievementEntity detail = commonDao.selectOne("FrontAchieveDAO.selectAchieveDetail", aigoAchievementEntity);
			
			// 결과 Set
			resultMap.put("detail", detail);
			resultMap.put("lv", aigoAchievementEntity.getLevId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
}
