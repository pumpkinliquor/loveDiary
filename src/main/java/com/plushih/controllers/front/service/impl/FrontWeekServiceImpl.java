package com.plushih.controllers.front.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.controllers.front.service.FrontWeekService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoLearnEntity;

@Service("frontWeekService")
public class FrontWeekServiceImpl implements FrontWeekService {
	
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
	public Map<String, Object> selectWeekReport(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> acvKeyInfo = new ArrayList<Map<String, Object>>();
		Map<String, Object> keyInfo = new HashMap<>();
		String[] _sortFilter = null;
		
		try {
			acvKeyInfo = commonDao.selectList("FrontWeekDAO.selectAcvList", paramMap);
			
			if("".equals((String) paramMap.get("acvId")) || (String) paramMap.get("acvId") == null) {
				paramMap.put("acvId", acvKeyInfo.get(0).get("acvId"));
			}
			
			keyInfo = commonDao.selectOne("FrontWeekDAO.selectAcvInfo", paramMap);
			
			paramMap.put("acvId", keyInfo.get("acvId"));
			
			// 성취도 체크는 기출문제, 확인문제, 주간평가 문제들로만 체크한다.
			int[] arrCategories			= {Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK, Code.Aigo.CATEGORY_WEEKLY};
			int[] arrCategoriesSub	= {Code.Aigo.CATEGORY_PREV_BASIC, Code.Aigo.CATEGORY_CHECK_BASIC, Code.Aigo.CATEGORY_WEEKLY_BASIC};
			paramMap.put("arrCategories", arrCategories);
			paramMap.put("arrCategoriesSub", arrCategoriesSub);
			
			if(MapUtils.isNotEmpty(paramMap)) {
				if(!StringUtils.isEmpty(paramMap.get("sortFilter"))) {
					_sortFilter = paramMap.get("sortFilter").toString().split(",");
					paramMap.put("sortFilter", _sortFilter);
				}
			}
			
			// 마지막으로 푼 문제의 성취기준 정보
			resultMap.put("acvList", acvKeyInfo);
			resultMap.put("acvInfo", keyInfo);
			resultMap.put("weekAcvList", commonDao.selectList("FrontWeekDAO.selectWeekAcvList", paramMap));
			resultMap.put("rivalInfo", commonDao.selectOne("FrontWeekDAO.selectRivalInfo", paramMap));
			resultMap.put("runtimeInfo", commonDao.selectOne("FrontWeekDAO.selectRuntimeInfo", paramMap));
			resultMap.put("rivalPerInfo", commonDao.selectOne("FrontWeekDAO.selectPerInfo", paramMap));
			resultMap.put("notAcvInfo", commonDao.selectOne("FrontWeekDAO.selectNotTestAcvInfo", paramMap));
			
			AigoLearnEntity checkParamMap = new AigoLearnEntity();
			AigoLearnEntity checkLevelTestMap = new AigoLearnEntity();
			AigoLearnEntity checkLearningMap = new AigoLearnEntity();
			AigoLearnEntity resultLearningMap = new AigoLearnEntity();
			
			checkParamMap.setMemId((String) paramMap.get("memId"));
			checkParamMap.setLevId(NumberUtils.stringToInt((String) paramMap.get("levId")));
			
			// 1. 레벨평가 진행 조건 체크
			// 레벨평가를 이미 진행했는지 확인
			// 검색할 과목 설정 : 기본과목(수I,수II) + 선택과목(기하/확률과통계/미분과적분 중 선택한 과목)
			checkParamMap.setArrSubjects(new int[] {Code.Aigo.MATH_I, Code.Aigo.MATH_II, NumberUtils.stringToInt((String) paramMap.get("userSubId")) });
			checkParamMap.setArrCategories(new int[] {Code.Aigo.CATEGORY_LEVEL});
			checkLevelTestMap = commonDao.selectOne("FrontLearnDAO.selectAllQuestionCountInfo", checkParamMap);
			if(checkLevelTestMap.getTotalQstCnt() <= checkLevelTestMap.getUserAnsCnt()) {		// 사용자가 레벨평가 문제를 모두 풀었을 경우
				resultMap.put("levelTestYn", Default.NO_LOWER);
			}else if(checkLevelTestMap.getTotalQstCnt() > checkLevelTestMap.getUserAnsCnt() && checkLevelTestMap.getUserAnsCnt() > 0) {		// 사용자가 레벨평가를 진행하다가 벗어낫을 경우
				resultMap.put("levelTestYn", Default.YES_LOWER);
			}else if(checkLevelTestMap.getUserAnsCnt() == 0) {	// 레벨평가를 안 받았을 경우
				//	- 진행 조건 : 기출/확인/주간평가 문제 모두 풀어야 함. 유사/유사2 문제는 체크 안 함
				//	검색 카테고리 설정
				checkParamMap.setArrCategories(new int[] {Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK, Code.Aigo.CATEGORY_WEEKLY});
				checkParamMap.setArrExceptCategories(new int[] {Code.Aigo.CATEGORY_CHECK_SIMILAR, Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB, Code.Aigo.CATEGORY_WEEKLY_SIMILAR});
				checkLevelTestMap = commonDao.selectOne("FrontLearnDAO.selectAllQuestionCountInfo", checkParamMap);
				if(checkLevelTestMap.getTotalQstCnt() <= checkLevelTestMap.getUserAnsCnt()) {		// 사용자가 모든 성취기준의 기출+확인+주간평가 문제를 모두 풀었을 경우
					resultMap.put("levelTestYn", Default.YES_LOWER);
				} else{
					resultMap.put("levelTestYn", Default.NO_LOWER);
				}
			}
			
			// 2. 진행중이었던 학습이 있는지 조회
			checkLearningMap = commonDao.selectOne("FrontLearnDAO.selectRecentlyLearningInfo", checkParamMap);
			checkParamMap.setSubId(checkLearningMap.getSubId());
			checkParamMap.setLevId(checkLearningMap.getLevId());
			checkParamMap.setAcvId(checkLearningMap.getAcvId());
			resultLearningMap.setSubId(checkLearningMap.getSubId());
			resultLearningMap.setLevId(checkLearningMap.getLevId());
			resultLearningMap.setAcvId(checkLearningMap.getAcvId());
			
			if( Code.Aigo.CATEGORY_LEVEL != checkLearningMap.getAcaId() ) {
				if( Code.Aigo.CATEGORY_WEEKLY == checkLearningMap.getAcaId() ) {		// 마지막 푼 문제 : 주간평가
					checkParamMap.setArrCategories(new int[] {Code.Aigo.CATEGORY_WEEKLY});
					checkParamMap.setArrExceptCategories(new int[] {Code.Aigo.CATEGORY_WEEKLY_SIMILAR});
					resultLearningMap.setCurrentTestType(Code.Aigo.QST_TYPE_WEEKLY);
					resultLearningMap.setCurrentTestTypeSub(Code.Aigo.QST_SUB_TYPE_BASIC);
				}else if( (Code.Aigo.CATEGORY_PREV == checkLearningMap.getAcaId() || Code.Aigo.CATEGORY_CHECK == checkLearningMap.getAcaId() ) ) {		// 마지막 푼 문제 : 기출+확인
					checkParamMap.setArrCategories(new int[] {Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK});
					checkParamMap.setArrExceptCategories(new int[] {Code.Aigo.CATEGORY_CHECK_SIMILAR, Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB});
					resultLearningMap.setCurrentTestType(Code.Aigo.QST_TYPE_PREV);
					resultLearningMap.setCurrentTestTypeSub(Code.Aigo.QST_SUB_TYPE_BASIC);
				}
				checkLearningMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionInfo",checkParamMap);		// 진행중이었던 학습의 푼 문제 수 조회
				if(checkLearningMap.getTotalQstCnt() <= checkLearningMap.getUserAnsCnt()) {		// 최근 학습한 성취기준의 문제를 다 풀었을 경우
					resultMap.put("recentlyLearningYn", Default.NO_LOWER);						// 이미 다 풀어서 풀던 문제페이지로 이동 안해도 됨
				}else {
					resultMap.put("recentlyLearningYn", Default.YES_LOWER);	// 문제를 아직 다 안 풀었으므로 이동
				}
			}else{
				resultMap.put("recentlyLearningYn", Default.NO_LOWER);			// 레벨평가 진행중이었을 경우 :'n'
			}
			
			resultMap.put("learning", resultLearningMap);
			
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
	public int[] selectThisDayCnt(Map<String, Object> paramMap) throws Exception {
	
		int[] thisDayCnt = new int[4];
		
		thisDayCnt[0] = commonDao.selectOne("FrontWeekDAO.selectWeekReportCnt", paramMap);
		thisDayCnt[1] = commonDao.selectOne("FrontWeekDAO.selectThisWeekDayCnt", paramMap);
		thisDayCnt[2] = commonDao.selectOne("FrontWeekDAO.selectTotalReportCnt", paramMap);
		thisDayCnt[3] = commonDao.selectOne("FrontWeekDAO.selectThisTotalDayCnt", paramMap);
		
		return thisDayCnt;
	}
	
	
}
