package com.plushih.controllers.front.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.NumberUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.controllers.front.service.FrontReportService;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoLearnEntity;
import com.plushih.entities.AigoLearnHistoryEntity;

@Service("FrontReportService")
public class FrontReportServiceImpl implements FrontReportService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	FrontLoginService frontLoginService;
	
	@Autowired
	private FrontUserService frontUserService;


	/**
	 * @ClassName	: FrontAchieveServiceImpl.java
	 * @Method		: selectAchieveDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학습목표 상세
	 */
	@Override
	public Map<String, Object> selectTotalReport(Map<String, Object> paramMap) throws Exception {

		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> keyInfo = new ArrayList<Map<String, Object>>();
		String[] _sortFilter = null;

		try {

//			keyInfo = commonDao.selectList("FrontWeekDAO.selectAcvList", paramMap);
//			paramMap.put("acvId", keyInfo.get(0).get("acvId"));
			paramMap.put("thiTestType", "level");
//			resultMap.put("totalReport", commonDao.selectOne("FrontWeekDAO.selectTotalReport", paramMap));
			resultMap.put("totalReportTmp", commonDao.selectList("FrontWeekDAO.selectTotalReportTmp", paramMap));
			resultMap.put("totalDurChart", commonDao.selectOne("FrontWeekDAO.selectChartDurTime", paramMap));
			resultMap.put("totalChartMonth", commonDao.selectOne("FrontWeekDAO.selectChartMonth", paramMap));
			resultMap.put("totalChartOneMonth", commonDao.selectOne("FrontWeekDAO.selectChartOneMonth", paramMap));
			resultMap.put("totalChartWeek", commonDao.selectOne("FrontWeekDAO.selectChartWeek", paramMap));
			resultMap.put("totalDayOfWeek", commonDao.selectOne("FrontWeekDAO.selectDayOfWeek", paramMap));
			resultMap.put("totalClockOfDay", commonDao.selectOne("FrontWeekDAO.selectClockOfDay", paramMap));
			resultMap.put("allTestCnt", commonDao.selectOne("FrontWeekDAO.selectAllTestCnt", paramMap));
			paramMap.put("sort", "ut");
			resultMap.put("userUnitTrue", commonDao.selectList("FrontWeekDAO.selectTotalUnitList", paramMap));
			paramMap.put("sort", "at");
			resultMap.put("allUnitTrue", commonDao.selectList("FrontWeekDAO.selectTotalUnitList", paramMap));
			paramMap.put("sort", "uf");
			resultMap.put("userUnitFalse", commonDao.selectList("FrontWeekDAO.selectTotalUnitList", paramMap));
			paramMap.put("sort", "af");
			resultMap.put("allUnitFalse", commonDao.selectList("FrontWeekDAO.selectTotalUnitList", paramMap));
			resultMap.put("userAchieveData", frontUserService.selectLearnAchieveData(paramMap));
			List<Map<String, Object>> acvKeyInfo = commonDao.selectList("FrontWeekDAO.selectAcvList", paramMap);
			List<AigoLearnHistoryEntity> levelList = commonDao.selectList("FrontLearnDAO.selectLearnHistory", paramMap);
			resultMap.put("weekCnt", acvKeyInfo.size());
			resultMap.put("levCnt", levelList.size());
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
	public Map<String, Object> selectTotalWeekReport(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		List<Map<String, Object>> keyInfo = new ArrayList<Map<String, Object>>();
		String[] _sortFilter = null;
		
		try {
			keyInfo = commonDao.selectList("FrontWeekDAO.selectAcvList", paramMap);
			
			if("".equals((String) paramMap.get("acvId")) || (String) paramMap.get("acvId") == null) {
				paramMap.put("acvId", keyInfo.get(0).get("acvId"));
			}
			
			resultMap.put("acvList", keyInfo);
			resultMap.put("weekAcvList", commonDao.selectList("FrontWeekDAO.selectWeekAcvList", paramMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontReportServiceImpl.java
	 * @Method		: selectLevelReportInfo
	 * @Date		: 2021. 2. 9. 
	 * @author		: dev.yklee
	 * @Description	: 리포트 > 레벨평가 리포트 정보 조회
	 */
	@Override
	public Map<String, Object> selectLevelReportInfo(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			// 필요한 공통 데이터 Set
			String memId = StringUtils.nvl(paramMap.get("memId"), "0");
			int levId = NumberUtils.stringToInt(StringUtils.nvl(paramMap.get("levId"), "0"));
			
			// 레벨평가 이력 조회
			AigoLearnHistoryEntity learnHistoryMap = new AigoLearnHistoryEntity();
			learnHistoryMap.setMemId(memId);
			learnHistoryMap.setThiTestType(Code.Aigo.QST_TYPE_LEVEL);
			learnHistoryMap.setOrderType(Default.OrderBy.DESC);
			List<AigoLearnHistoryEntity> levelList = commonDao.selectList("FrontLearnDAO.selectLearnHistory", learnHistoryMap);
			if(levId == 0) {
				levId = levelList.get(0).getLevId();
				paramMap.put("levId", levId);
			}
			
			AigoLearnEntity learnMap = new AigoLearnEntity();
			learnMap.setLevId(levId);
			learnMap.setMemId(memId);
			learnMap.setUserPassYn(Default.YES_LOWER);	// 정답을 맞춘 항목만 조회한다.
			learnMap.setArrSubjects(new int[] {Code.Aigo.MATH_I, Code.Aigo.MATH_II, (Integer)(paramMap.get("optionalSub"))});
			
			//	주간평가 결과
			learnMap.setArrCategories(new int[] {Code.Aigo.CATEGORY_WEEKLY});
			learnMap.setArrSubCategories(new int[] {Code.Aigo.CATEGORY_WEEKLY_BASIC});
			AigoLearnEntity weeklyMap = commonDao.selectOne("FrontLearnDAO.selectAllQuestionCountInfo", learnMap);
			
			//	레벨평가 결과
			learnMap.setArrCategories(new int[] {Code.Aigo.CATEGORY_LEVEL});
			learnMap.setArrSubCategories(null);
			AigoLearnEntity levelMap = commonDao.selectOne("FrontLearnDAO.selectAllQuestionCountInfo", learnMap);
			
			//	보충학습 이력
			learnMap.setArrCategories(new int[] {Code.Aigo.CATEGORY_AFTER});
			learnMap.setArrSubCategories(null);
			learnMap.setUserPassYn(null);	// 정답 여부와 관계없이 학습한 이력 조회
			AigoLearnEntity afterMap = commonDao.selectOne("FrontLearnDAO.selectAllQuestionCountInfo", learnMap);
			
			resultMap.put("levReportDtl", commonDao.selectOne("FrontReportDAO.selectLevelReportDtl", learnMap));
			resultMap.put("levReportList", commonDao.selectList("FrontReportDAO.selectLevelReportList", learnMap));
			resultMap.put("levelList", levelList);
			resultMap.put("weeklyMap", weeklyMap);
			resultMap.put("levelMap", levelMap);
			resultMap.put("afterMap", afterMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontReportServiceImpl.java
	 * @Method		: selectLevelReport
	 * @Date		: 2021. 2. 9. 
	 * @author		: dev.yklee
	 * @Description	: 리포트 > 레벨평가 리포트 > 리스트
	 */
	@Override
	public List<Map<String, Object>> selectLevelReport(Map<String, Object> paramMap) throws Exception {
		paramMap.put("acaId", Code.Aigo.CATEGORY_LEVEL);
		paramMap.put("orderType", StringUtils.nvl(String.valueOf(paramMap.get("orderType")), Default.OrderBy.ASC));
		return commonDao.selectList("FrontReportDAO.selectLevelReportList", paramMap);
	}
	
	/**
	 * @ClassName	: FrontReportServiceImpl.java
	 * @Method		: selectTotalLevelReport
	 * @Date		: 2021. 2. 24. 
	 * @author		: dev.yklee
	 * @Description	: AI 종합리포트 > 레벨평가 상세 문항분석
	 */
	@Override
	public Map<String, Object> selectTotalLevelReport(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			// 필요한 공통 데이터 Set
			String memId = StringUtils.nvl(paramMap.get("memId"), "0");
			
			// 레벨평가 이력 조회
			AigoLearnHistoryEntity learnHistoryMap = new AigoLearnHistoryEntity();
			learnHistoryMap.setMemId(memId);
			learnHistoryMap.setThiTestType(Code.Aigo.QST_TYPE_LEVEL);
			learnHistoryMap.setOrderType(Default.OrderBy.ASC);
			List<AigoLearnHistoryEntity> levelList = commonDao.selectList("FrontReportDAO.selectTotalLevelInfo", learnHistoryMap);
			
			if("".equals((String) paramMap.get("levId")) || (String) paramMap.get("levId") == null) {
				paramMap.put("levId", levelList.get(0).getLevId());
			}
			
			AigoLearnEntity learnMap = new AigoLearnEntity();
			paramMap.put("userPassYn", Default.YES_LOWER);	// 정답을 맞춘 항목만 조회한다.
			paramMap.put("arrSubjects", new int[] {Code.Aigo.MATH_I, Code.Aigo.MATH_II, (Integer)(paramMap.get("optionalSub"))});
			
			// 레벨평가 결과
			paramMap.put("arrCategories", new int[] {Code.Aigo.CATEGORY_LEVEL});
			paramMap.put("arrSubCategories", null);
			AigoLearnEntity levelMap = commonDao.selectOne("FrontLearnDAO.selectAllQuestionCountInfo", paramMap);
			
			resultMap.put("levelMap", levelMap);
			resultMap.put("levelList", levelList);
			resultMap.put("levReportList", commonDao.selectList("FrontReportDAO.selectLevelReportList", paramMap));
			resultMap.put("levId", paramMap.get("levId"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
}
