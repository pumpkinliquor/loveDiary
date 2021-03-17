package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.controllers.front.service.FrontAchieveService;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoAchievementEntity;
import com.plushih.entities.AigoSubjectEntity;
import com.plushih.entities.AigoTendencyEntity;

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
			
			// 성취도 체크는 기출문제, 확인문제, 주간평가 문제들로만 체크한다.
			int[] arrCategories			= {Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK, Code.Aigo.CATEGORY_WEEKLY};
			int[] arrCategoriesSub	= {Code.Aigo.CATEGORY_PREV_BASIC, Code.Aigo.CATEGORY_CHECK_BASIC, Code.Aigo.CATEGORY_WEEKLY_BASIC};
			
			aigoAchievementEntity.setArrCategories(arrCategories);
			aigoAchievementEntity.setArrCategoriesSub(arrCategoriesSub);
			
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
			
			// 사용자 선택과목명 Set
			AigoSubjectEntity subjectEntity = new AigoSubjectEntity();
			subjectEntity.setSubId(aigoAchievementEntity.getOptionalSubId());
			subjectEntity.setUseYn(Default.YES_LOWER);
			resultMap.put("mathOptionalInfo", commonDao.selectOne("FrontSubjectDAO.selectSubjectInfo", subjectEntity));
			
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
			AigoTendencyEntity aigoTendencyEntity = new AigoTendencyEntity();
			aigoTendencyEntity.setAcvId(aigoAchievementEntity.getAcvId());
			resultMap.put("detail", commonDao.selectList("FrontTendencyDAO.selectTendencyDetail", aigoTendencyEntity));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
}
