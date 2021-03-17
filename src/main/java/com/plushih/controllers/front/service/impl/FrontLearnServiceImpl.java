package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.utils.NumberUtils;
import com.plushih.common.utils.StringUtils;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontLearnService;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoCommentaryEntity;
import com.plushih.entities.AigoLearnEntity;
import com.plushih.entities.AigoLearnHistoryEntity;
import com.plushih.entities.AigoNotionEntity;
import com.plushih.entities.AigoQuestionAnswerEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.entities.UserLevelHistoryEntity;
import com.plushih.entities.UserMemberEntity;

@Service("frontLearnService")
public class FrontLearnServiceImpl implements FrontLearnService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private FrontUserService frontUserService;
	
	/**
	 * @ClassName	: FrontLearnServiceImpl.java
	 * @Method		: presentQuestions
	 * @Date		: 2021. 1. 27. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이(기출문제 ,확인문제)
	 */
	@Override
	public Map<String, Object> learning(HttpServletRequest request, AigoLearnEntity aigoLearnEntity) throws Exception {
		
		Map<String, Object> resultMap	= new HashMap<>();
		AigoLearnEntity questionMap		= new AigoLearnEntity();
		
		// 사용자 key 세팅
		aigoLearnEntity.setMemId(LoginSession.getSeq(request.getSession()));
		// 문제내용 이미지를 가져올 코드 세팅
		aigoLearnEntity.setFileCategory(Default.FileBbs.QUESTION);
		
		try {
			
			/////////////////////////////////// 출제할 문제 조회 ///////////////////////////////////
			// STEP 1. 출제하기 위한 조건 세팅
			// 출제할 문제 조회를 위한 필수 조건
			// 1. 사용자 고유 Key값 (session)
			// 2. 과목 Key값	(parameter)
			// 3. 레벨 Key값 (session)
			// 4. 성취기준 Key값 	(parameter)
			// 5. 문제분류 카테고리 Key값과 하위 카테고리 Key값 (Code.java)
			//		Code.Aigo.CATEGORY_PREV					// 문제분류		: 기출문제
			// 		Code.Aigo.CATEGORY_CHECK				// 문제분류		: 확인문제
			// 		Code.Aigo.CATEGORY_PREV_BASIC			// 2차문제분류	: 기출문제 > 기본문제
			// 		Code.Aigo.CATEGORY_CHECK_BASIC			// 2차문제분류	: 확인문제 > 기본문제
			// 		Code.Aigo.CATEGORY_CHECK_SIMILAR		// 2차문제분류	: 확인문제 > 유사문제
			// 		Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB	// 2차문제분류	: 확인문제 > 유사2문제
			// 6. 문제파일의 타입구분값 (Default.java)
			//		Default.FileBbs.QUESTION (QST)
			int[] arrCategories			= {Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK};	// 기출문제와 확인문제는 함께 출제된다.
			int[] arrExceptCategories	= {Code.Aigo.CATEGORY_CHECK_SIMILAR, Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB};
			aigoLearnEntity.setArrCategories(arrCategories);
			aigoLearnEntity.setArrExceptCategories(arrExceptCategories);
			
			AigoLearnEntity checkMap = new AigoLearnEntity();
			
			//	CASE 1. 확인문제를 풀면서 오답을 제출했을 시 유사문제 제출로직 필요
			if(Code.Aigo.QST_TYPE_CHECK.equals(aigoLearnEntity.getCurrentTestType())) {					//	확인문제를 풀고 있었는지 체크
			// 	CASE 1-1. 확인문제 > 기본문제 풀이해야할 경우
				if(Code.Aigo.QST_SUB_TYPE_BASIC.equals(aigoLearnEntity.getCurrentTestTypeSub()) ) {		//	확인문제의 기본문제 풀이 중
					questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
			// 	CASE 1-2. 확인문제 > 유사문제 풀이해야 할 경우
				}else if(Code.Aigo.QST_SUB_TYPE_SIMILAR.equals(aigoLearnEntity.getCurrentTestTypeSub()) ) {
					aigoLearnEntity.setSubAcaId(Code.Aigo.CATEGORY_CHECK_SIMILAR);
					//	* 사용자가 유사문제를 모두 푼 상태인지 체크한다.
					checkMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionInfo", aigoLearnEntity);
					//	CASE 1-2-1. 확인문제 > 더이상 풀 유사문제가 없는 경우 > 기본문제 출제 쿼리 실행
					if (checkMap.getTotalQstCnt() <= checkMap.getUserAnsCnt()) {
						questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
					}else {
					//	CASE 1-2-2. 확인문제 > 풀 유사문제가 남은 경우 > 유사문제 출제 쿼리 실행
						questionMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionList", aigoLearnEntity);
					}
		//		CASE 1-3. 확인문제 > 유사2문제 풀이해야 할 경우
				}else if(Code.Aigo.QST_SUB_TYPE_SIMILAR_SUB.equals(aigoLearnEntity.getCurrentTestTypeSub()) ) {
					aigoLearnEntity.setSubAcaId(Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB);
					//	* 사용자가 유사2문제를 모두 푼 상태인지 체크한다.
					checkMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionInfo", aigoLearnEntity);
					//	CASE 1-3-1. 확인문제 > 더이상 풀 유사2문제가 없는 경우 > 기본문제 출제 쿼리 실행
					if (checkMap.getTotalQstCnt() <= checkMap.getUserAnsCnt()) {
						questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
					}else {
					//	CASE 1-3-2. 확인문제 > 풀 유사문제가 남은 경우 > 유사문제 출제 쿼리 실행
						questionMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionList", aigoLearnEntity);
					}
				}
			// 	CASE 2. 기출문제 문제 풀이 시 별도의 처리 없음
			}else{
				questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
			}
			
			/////////////////////////////////// 넘버링 및 다음출제문제 번호 세팅 ///////////////////////////////////
			// 실제 사용자가 풀고있는 문제에 대한 정보가 없을 시 1로 세팅
			if((StringUtils.isEmpty(aigoLearnEntity.getCurrentQuestionViewNumber()) || aigoLearnEntity.getCurrentQuestionViewNumber() == 0 )) {
				questionMap.setCurrentQuestionViewNumber(NumberUtils.stringToInt(StringUtils.nvl(questionMap.getRownum(), "1")));
			}else {
				questionMap.setCurrentQuestionViewNumber(aigoLearnEntity.getCurrentQuestionViewNumber()+1);
			}
			questionMap.setCurrentQuestionNumber(questionMap.getRownum());
			
			// 문제분류 카테고리 값 세팅
			if(Code.Aigo.CATEGORY_PREV == questionMap.getAcaId()) {
				questionMap.setCurrentTestType(Code.Aigo.QST_TYPE_PREV);
				if(Code.Aigo.CATEGORY_PREV_BASIC == questionMap.getSubAcaId()) {
					questionMap.setCurrentTestTypeSub(Code.Aigo.QST_SUB_TYPE_BASIC);
				}
			}else if(Code.Aigo.CATEGORY_CHECK == questionMap.getAcaId()) {
				questionMap.setCurrentTestType(Code.Aigo.QST_TYPE_CHECK);
				if(Code.Aigo.CATEGORY_CHECK_BASIC == questionMap.getSubAcaId()) {
					questionMap.setCurrentTestTypeSub(Code.Aigo.QST_SUB_TYPE_BASIC);
				}else if(Code.Aigo.CATEGORY_CHECK_SIMILAR == questionMap.getSubAcaId()) {
					questionMap.setCurrentTestTypeSub(Code.Aigo.QST_SUB_TYPE_SIMILAR);
				}else if(Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB == questionMap.getSubAcaId()) {
					questionMap.setCurrentTestTypeSub(Code.Aigo.QST_SUB_TYPE_SIMILAR_SUB);
				}
			}
			
			resultMap.put("question", questionMap);		// 결과 Map에 세팅
			
			// 문제 컨텐츠 파일 조회
			SiteAttachFileEntity fileEntity = new SiteAttachFileEntity();
			fileEntity.setSeq(questionMap.getQstId());
			fileEntity.setSafBbs(Default.FileBbs.QUESTION);
			resultMap.put("questionFileList", commonDao.selectList("FrontCommonDAO.selectCommonStieFileList", fileEntity));
			
			/////////////////////////////////// 출제할 문제에 대한 관련데이터 조회 ///////////////////////////////////
			// 1. 관련 개념 조회
			if(!"".equals(StringUtils.nvl(questionMap.getQstRelNotData(), ""))) {				
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(questionMap.getQstRelNotData());
				JSONArray concept = (JSONArray) obj;
				resultMap.put("concept", concept);
			}
			
			// 2. 해설 조회
			AigoCommentaryEntity aigoCommentaryEntity = new AigoCommentaryEntity();
			aigoCommentaryEntity.setCmtrId(questionMap.getCmtrId());
			resultMap.put("commentaryInfo", commonDao.selectOne("FrontCommentaryDAO.selectCommentaryInfo", aigoCommentaryEntity));		// 결과 Map에 세팅
			
			// 3. 개념컨텐츠 조회
			AigoNotionEntity aigoNotionEntity = new AigoNotionEntity();
			aigoNotionEntity.setSubId(questionMap.getSubId());
			aigoNotionEntity.setAcvId(questionMap.getAcvId());
			aigoNotionEntity.setQstKey(questionMap.getQstKey());
			resultMap.put("notionInfo", commonDao.selectList("FrontConceptDAO.selectNotionInfo", aigoNotionEntity));		// 결과 Map에 세팅
			
			// * 하단 학습 진행도 그래프 표시를 위한 데이터 조회
			// 문제리스트 카테고리 범위			: 기출문제, 확인문제
			// 학습 진행도 체크 카테고리 범위	: 기출문제, 확인문제, 주간평가 문제 → 카테고리 다시 세팅해주어야 함
			arrCategories		= new int[]{Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK, Code.Aigo.CATEGORY_WEEKLY};	// 기출문제,확인문제,주간평가문제 조회
			arrExceptCategories	= new int[]{Code.Aigo.CATEGORY_CHECK_SIMILAR, Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB, Code.Aigo.CATEGORY_WEEKLY_SIMILAR}; // 유사문제들은 모든 진도 체크범위에서 제외된다.
			aigoLearnEntity.setArrCategories(arrCategories);
			aigoLearnEntity.setArrExceptCategories(arrExceptCategories);
			resultMap.put("learnProgressInfo", commonDao.selectList("FrontLearnDAO.selectLearnProgressInfo", aigoLearnEntity));		// 결과 Map에 세팅
			
			/////////////////////////////////// 사용자 성취도 관련 데이터 조회 ///////////////////////////////////
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("memId", LoginSession.getSeq(request.getSession()));
			resultMap.put("userAchieveData", frontUserService.selectLearnAchieveData(paramMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontLearnServiceImpl.java
	 * @Method		: weeklyTest
	 * @Date		: 2021. 1. 31. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이 > 주간평가
	 */
	@Override
	public Map<String, Object> weeklyTest(HttpServletRequest request, AigoLearnEntity aigoLearnEntity) throws Exception {
		
		Map<String, Object> resultMap	= new HashMap<>();
		AigoLearnEntity questionMap		= new AigoLearnEntity();
		
		// 사용자 key 세팅
		aigoLearnEntity.setMemId(LoginSession.getSeq(request.getSession()));
		// 문제내용 이미지를 가져올 코드 세팅
		aigoLearnEntity.setFileCategory(Default.FileBbs.QUESTION);
		
		try {
			
			/////////////////////////////////// 출제할 문제 조회 ///////////////////////////////////
			// STEP 1. 출제하기 위한 조건 세팅
			// 출제할 문제 조회를 위한 필수 조건
			// 1. 사용자 고유 Key값 (session)
			// 2. 과목 Key값	(parameter)
			// 3. 레벨 Key값 (session)
			// 4. 성취기준 Key값 	(parameter)
			// 5. 문제분류 카테고리 Key값과 하위 카테고리 Key값 (Code.java)
			//		Code.Aigo.CATEGORY_WEEKLY				// 문제분류		: 주간평가
			// 		Code.Aigo.CATEGORY_WEEKLY_BASIC			// 2차문제분류	: 주간평가 > 기본문제
			// 		Code.Aigo.CATEGORY_WEEKLY_SIMILAR		// 2차문제분류	: 주간평가 > 유사문제
			// 6. 문제파일의 타입구분값 (Default.java)
			//		Default.FileBbs.QUESTION (QST)
			int[] arrCategories			= {Code.Aigo.CATEGORY_WEEKLY};			// 조회할 문제분류 카테고리
			int[] arrExceptCategories	= {Code.Aigo.CATEGORY_WEEKLY_SIMILAR};	// 조회에서 제외할 문제분류 카테고리
			aigoLearnEntity.setArrCategories(arrCategories);
			aigoLearnEntity.setArrExceptCategories(arrExceptCategories);
			
			AigoLearnEntity checkMap = new AigoLearnEntity();
			
			//	CASE 1. 주간평가 진행 시 오답을 제출했을 때 유사문제 제출로직 필요
			// 	CASE 1-1. 주간평가 > 기본문제 풀이해야할 경우
			if(Code.Aigo.QST_SUB_TYPE_BASIC.equals(aigoLearnEntity.getCurrentTestTypeSub()) ) {		//	주간평가 기본문제 풀이 중
				questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
				// 	CASE 1-2. 주간평가 > 유사문제 풀이해야 할 경우
			}else if(Code.Aigo.QST_SUB_TYPE_SIMILAR.equals(aigoLearnEntity.getCurrentTestTypeSub()) ) {
				aigoLearnEntity.setSubAcaId(Code.Aigo.CATEGORY_WEEKLY_SIMILAR);
				//	* 사용자가 유사문제를 모두 푼 상태인지 체크한다.
				checkMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionInfo", aigoLearnEntity);
				//	CASE 1-2-1. 주간평가 > 더이상 풀 유사문제가 없는 경우 > 기본문제 출제 쿼리 실행
				if (checkMap.getTotalQstCnt() <= checkMap.getUserAnsCnt()) {
					questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
				}else {
				//	CASE 1-2-2. 주간평가 > 풀 유사문제가 남은 경우 > 유사문제 출제 쿼리 실행
					questionMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionList", aigoLearnEntity);
				}
			}
			
			/////////////////////////////////// 넘버링 및 다음출제문제 번호 세팅 ///////////////////////////////////
			// 실제 사용자가 풀고있는 문제에 대한 정보가 없을 시 1로 세팅
			if((StringUtils.isEmpty(aigoLearnEntity.getCurrentQuestionViewNumber()) || aigoLearnEntity.getCurrentQuestionViewNumber() == 0 )) {
				questionMap.setCurrentQuestionViewNumber(NumberUtils.stringToInt(StringUtils.nvl(questionMap.getRownum(), "1")));
			}else {
				questionMap.setCurrentQuestionViewNumber(aigoLearnEntity.getCurrentQuestionViewNumber()+1);
			}
			questionMap.setCurrentQuestionNumber(questionMap.getRownum());
			
			// 문제분류 카테고리 값 세팅
			if(Code.Aigo.CATEGORY_WEEKLY == questionMap.getAcaId()) {
				questionMap.setCurrentTestType(Code.Aigo.QST_TYPE_WEEKLY);
				if(Code.Aigo.CATEGORY_WEEKLY_BASIC == questionMap.getSubAcaId()) {
					questionMap.setCurrentTestTypeSub(Code.Aigo.QST_SUB_TYPE_BASIC);
				}else if(Code.Aigo.CATEGORY_WEEKLY_SIMILAR == questionMap.getSubAcaId()) {
					questionMap.setCurrentTestTypeSub(Code.Aigo.QST_SUB_TYPE_SIMILAR);
				}
			}
			
			resultMap.put("question", questionMap);		// 결과 Map에 세팅
			
			// 문제 컨텐츠 파일 조회
			SiteAttachFileEntity fileEntity = new SiteAttachFileEntity();
			fileEntity.setSeq(questionMap.getQstId());
			fileEntity.setSafBbs(Default.FileBbs.QUESTION);
			resultMap.put("questionFileList", commonDao.selectList("FrontCommonDAO.selectCommonStieFileList", fileEntity));
			
			/////////////////////////////////// 출제할 문제에 대한 관련데이터 조회 ///////////////////////////////////
			// 1. 관련 개념 조회
			if(!"".equals(StringUtils.nvl(questionMap.getQstRelNotData(), ""))) {				
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(questionMap.getQstRelNotData());
				JSONArray concept = (JSONArray) obj;
				resultMap.put("concept", concept);
			}
			
			// 2. 해설 조회
			AigoCommentaryEntity aigoCommentaryEntity = new AigoCommentaryEntity();
			aigoCommentaryEntity.setCmtrId(questionMap.getCmtrId());
			resultMap.put("commentaryInfo", commonDao.selectOne("FrontCommentaryDAO.selectCommentaryInfo", aigoCommentaryEntity));		// 결과 Map에 세팅
			
			// 3. 개념컨텐츠 조회
			AigoNotionEntity aigoNotionEntity = new AigoNotionEntity();
			aigoNotionEntity.setSubId(questionMap.getSubId());
			aigoNotionEntity.setAcvId(questionMap.getAcvId());
			resultMap.put("notionInfo", commonDao.selectList("FrontConceptDAO.selectNotionInfo", aigoNotionEntity));		// 결과 Map에 세팅
			
			// * 하단 학습 진행도 그래프 표시를 위한 데이터 조회
			// 문제리스트 카테고리 범위			: 주간평가
			// 학습 진행도 체크 카테고리 범위	: 기출문제, 확인문제, 주간평가 문제 → 카테고리 다시 세팅해주어야 함
			arrCategories		= new int[]{Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK, Code.Aigo.CATEGORY_WEEKLY};	// 기출문제,확인문제,주간평가문제 조회
			arrExceptCategories	= new int[]{Code.Aigo.CATEGORY_CHECK_SIMILAR, Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB, Code.Aigo.CATEGORY_WEEKLY_SIMILAR}; // 유사문제들은 모든 진도 체크범위에서 제외된다.
			aigoLearnEntity.setArrCategories(arrCategories);
			aigoLearnEntity.setArrExceptCategories(arrExceptCategories);
			resultMap.put("learnProgressInfo", commonDao.selectList("FrontLearnDAO.selectLearnProgressInfo", aigoLearnEntity));		// 결과 Map에 세팅
			
			/////////////////////////////////// 사용자 성취도 관련 데이터 조회 ///////////////////////////////////
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("memId", LoginSession.getSeq(request.getSession()));
			resultMap.put("userAchieveData", frontUserService.selectLearnAchieveData(paramMap));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontLearnServiceImpl.java
	 * @Method		: levelTest
	 * @Date		: 2021. 1. 31. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 문제풀이 > 레벨평가
	 */
	public Map<String, Object> levelTest(HttpServletRequest request, AigoLearnEntity aigoLearnEntity) throws Exception {
		
		Map<String, Object> resultMap	= new HashMap<>();
		AigoLearnEntity questionMap		= new AigoLearnEntity();
		
		// 사용자 key 세팅
		aigoLearnEntity.setMemId(LoginSession.getSeq(request.getSession()));
		// 문제내용 이미지를 가져올 코드 세팅
		aigoLearnEntity.setFileCategory(Default.FileBbs.QUESTION);
		
		try {
		
			/////////////////////////////////// 출제할 문제 조회 ///////////////////////////////////
			// STEP 1. 출제하기 위한 조건 세팅
			// 출제할 문제 조회를 위한 필수 조건
			// 1. 사용자 고유 Key값 (session)
			// 2. 과목 Key값	(parameter)
			// 3. 레벨 Key값 (session)
			// 4. 성취기준 Key값 	(parameter)
			// 5. 문제분류 카테고리 Key값과 하위 카테고리 Key값 (Code.java)
			//		Code.Aigo.CATEGORY_LEVEL				// 문제분류		: 레벨평가
			// 6. 문제파일의 타입구분값 (Default.java)
			//		Default.FileBbs.QUESTION (QST)
			int[] arrCategories			= {Code.Aigo.CATEGORY_LEVEL};			// 조회할 문제분류 카테고리
			aigoLearnEntity.setArrCategories(arrCategories);
			
			// 	CASE 1. 레벨평가 > 문제 조회 / 레벨평가는 2차 문제분류가 없다.
			questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
			
			/////////////////////////////////// 넘버링 및 다음출제문제 번호 세팅 ///////////////////////////////////
			// 실제 사용자가 풀고있는 문제에 대한 정보가 없을 시 1로 세팅
			if((StringUtils.isEmpty(aigoLearnEntity.getCurrentQuestionViewNumber()) || aigoLearnEntity.getCurrentQuestionViewNumber() == 0 )) {
				questionMap.setCurrentQuestionViewNumber(NumberUtils.stringToInt(StringUtils.nvl(questionMap.getRownum(), "1")));
			}else {
				questionMap.setCurrentQuestionViewNumber(aigoLearnEntity.getCurrentQuestionViewNumber()+1);
			}
			questionMap.setCurrentQuestionNumber(questionMap.getRownum());
			
			// 문제분류 카테고리 값 세팅
			if(Code.Aigo.CATEGORY_LEVEL == questionMap.getAcaId()) {
				questionMap.setCurrentTestType(Code.Aigo.QST_TYPE_LEVEL);
			}
			
			resultMap.put("question", questionMap);		// 결과 Map에 세팅
			
			// 문제 컨텐츠 파일 조회
			SiteAttachFileEntity fileEntity = new SiteAttachFileEntity();
			fileEntity.setSeq(questionMap.getQstId());
			fileEntity.setSafBbs(Default.FileBbs.QUESTION);
			resultMap.put("questionFileList", commonDao.selectList("FrontCommonDAO.selectCommonStieFileList", fileEntity));
			
			/////////////////////////////////// 출제할 문제에 대한 관련데이터 조회 ///////////////////////////////////
			// 1. 관련 개념 조회
			if(!"".equals(StringUtils.nvl(questionMap.getQstRelNotData(), ""))) {				
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(questionMap.getQstRelNotData());
				JSONArray concept = (JSONArray) obj;
				resultMap.put("concept", concept);
			}
			
			// 2. 해설 조회
			AigoCommentaryEntity aigoCommentaryEntity = new AigoCommentaryEntity();
			aigoCommentaryEntity.setCmtrId(questionMap.getCmtrId());
			resultMap.put("commentaryInfo", commonDao.selectOne("FrontCommentaryDAO.selectCommentaryInfo", aigoCommentaryEntity));		// 결과 Map에 세팅
			
			// 3. 개념컨텐츠 조회
			AigoNotionEntity aigoNotionEntity = new AigoNotionEntity();
			aigoNotionEntity.setSubId(questionMap.getSubId());
			aigoNotionEntity.setAcvId(questionMap.getAcvId());
			resultMap.put("notionInfo", commonDao.selectList("FrontConceptDAO.selectNotionInfo", aigoNotionEntity));		// 결과 Map에 세팅
			
			// * 하단 학습 진행도 그래프 표시를 위한 데이터 조회
			// 문제리스트 카테고리 범위			: 레벨평가
			// 평가 진행도 체크 카테고리 범위	: 레벨평가 기본문제
			arrCategories = new int[]{Code.Aigo.CATEGORY_LEVEL};
			aigoLearnEntity.setArrCategories(arrCategories);
			resultMap.put("learnProgressInfo", commonDao.selectList("FrontLearnDAO.selectLearnProgressInfo", aigoLearnEntity));		// 결과 Map에 세팅
			
			/////////////////////////////////// 사용자 성취도 관련 데이터 조회 ///////////////////////////////////
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("memId", LoginSession.getSeq(request.getSession()));
			resultMap.put("userAchieveData", frontUserService.selectLearnAchieveData(paramMap));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontLearnServiceImpl.java
	 * @Method		: insertAnswer
	 * @Date		: 2021. 1. 28. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 답안 제출
	 */
	@Override
	public Map<String, Object> insertAnswer(AigoQuestionAnswerEntity aigoQuestionAnswerEntity) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String resultCode	= Code.Result.FAIL_99;
		
		try {
			
			aigoQuestionAnswerEntity.setsCnt(0);
			aigoQuestionAnswerEntity.setfCnt(0);
			
			// 사용여부 설정
			aigoQuestionAnswerEntity.setUseYn(Default.YES_LOWER);
			
			///////////////////////////////////////////////////////// 정답여부 체크
			Map<String, Object> answerMap = commonDao.selectOne("FrontQuestionAnswerDAO.selectQuestionInfo", aigoQuestionAnswerEntity);
			if(!StringUtils.isEmpty(answerMap)) {
				
				if(StringUtils.nvl(String.valueOf(answerMap.get("qstValue")), "").equals(aigoQuestionAnswerEntity.getAnsValue())) {
					aigoQuestionAnswerEntity.setPassYn(Default.YES_LOWER);
				}else {
					aigoQuestionAnswerEntity.setPassYn(Default.NO_LOWER);
				}
				
			///////////////////////////////////////////////////////// 사용자 답안 DB 저장
				// 1. 사용자 학습풀이정보 테이블 insert
				if(commonDao.insert("FrontQuestionAnswerDAO.insertQuestionAnswerInfo", aigoQuestionAnswerEntity) > 0) {
					// 	2. 사용자 학습풀이 답안 테이블 insert
					if(commonDao.insert("FrontQuestionAnswerDAO.insertQuestionAnswer", aigoQuestionAnswerEntity) > 0) {
						//	3. 사용자 학습풀이 답안 이력 테이블 insert
						if(commonDao.insert("FrontQuestionAnswerDAO.insertQuestionAnswerHistory", aigoQuestionAnswerEntity) > 0) {
							//	4. 사용자 학습풀이정보 테이블에 정답/오답 카운트 update
							//	   정/오답에 따른 Info 카운트 세팅
							if(Default.YES_LOWER.equals(aigoQuestionAnswerEntity.getPassYn())) {
								aigoQuestionAnswerEntity.setsCnt(1);
								aigoQuestionAnswerEntity.setfCnt(0);
							}else {
								aigoQuestionAnswerEntity.setsCnt(0);
								aigoQuestionAnswerEntity.setfCnt(1);
							}
							if(commonDao.update("FrontQuestionAnswerDAO.updateQuestionAnswerInfo", aigoQuestionAnswerEntity) > 0) {
								resultCode = Code.Result.SUCC_01;
							}else {
								resultCode = Code.Result.FAIL_01;
							}
						}else {
							resultCode = Code.Result.FAIL_02;
						}
					}else {
						resultCode = Code.Result.FAIL_03;
					}
				}else {
					resultCode = Code.Result.FAIL_04;
				}
			}else {
				resultCode = Code.Result.FAIL_05;
			}
			
			resultMap.put(Default.ResultValue.RESULT_STATUS, aigoQuestionAnswerEntity.getPassYn());
			resultMap.put(Default.ResultValue.RESULT_CODE, resultCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontLearnServiceImpl.java
	 * @Method		: insertAnswerLearn
	 * @Date		: 2021. 2. 1. 
	 * @author		: dev.yklee
	 * @Description	: 문제풀이 > 학습
	 */
	@Override
	public Map<String, Object> insertAnswerLearn(AigoQuestionAnswerEntity aigoQuestionAnswerEntity, HttpServletRequest request) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String resultCode	= Code.Result.FAIL_99;
		
		try {
			
			resultMap = this.insertAnswer(aigoQuestionAnswerEntity);
			
			///////////////////////////////////////////////////////// 문제를 틀렸을 때 풀이 로직에 맞는 출제분류를 세팅 : 기출문제, 레벨평가일 때는 따로 처리하지 않음
			String pageType		= Code.Aigo.QST_TYPE_PREV;
			String pageTypeSub	= Code.Aigo.QST_SUB_TYPE_BASIC;
			if(Default.NO_LOWER.equals(aigoQuestionAnswerEntity.getPassYn())) {
				if(Code.Aigo.QST_TYPE_CHECK.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
					pageType = Code.Aigo.QST_TYPE_CHECK;
					if(Code.Aigo.QST_SUB_TYPE_BASIC.equals(aigoQuestionAnswerEntity.getCurrentTestTypeSub())) {
						pageTypeSub = Code.Aigo.QST_SUB_TYPE_SIMILAR;
					} else if(Code.Aigo.QST_SUB_TYPE_SIMILAR.equals(aigoQuestionAnswerEntity.getCurrentTestTypeSub())) {
						pageTypeSub = Code.Aigo.QST_SUB_TYPE_SIMILAR_SUB;
					} else if(Code.Aigo.QST_SUB_TYPE_SIMILAR_SUB.equals(aigoQuestionAnswerEntity.getCurrentTestTypeSub())) {
						pageTypeSub = Code.Aigo.QST_SUB_TYPE_BASIC;
					}
				}else if(Code.Aigo.QST_TYPE_WEEKLY.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
					pageType = Code.Aigo.QST_TYPE_WEEKLY;
					if(Code.Aigo.QST_SUB_TYPE_BASIC.equals(aigoQuestionAnswerEntity.getCurrentTestTypeSub())) {
						pageTypeSub = Code.Aigo.QST_SUB_TYPE_SIMILAR;
					} else if(Code.Aigo.QST_SUB_TYPE_SIMILAR.equals(aigoQuestionAnswerEntity.getCurrentTestTypeSub())) {
						pageTypeSub = Code.Aigo.QST_SUB_TYPE_BASIC;
					}
				}else if(Code.Aigo.QST_TYPE_LEVEL.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
					pageType = Code.Aigo.QST_TYPE_LEVEL;
					pageTypeSub = Code.Aigo.QST_SUB_TYPE_BASIC;
				}else if(Code.Aigo.QST_TYPE_PREV.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
					pageType = Code.Aigo.QST_TYPE_PREV;
					pageTypeSub = Code.Aigo.QST_SUB_TYPE_BASIC;
				}
			}else {
				if(Code.Aigo.QST_TYPE_CHECK.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
					pageType = Code.Aigo.QST_TYPE_CHECK;
					if(Code.Aigo.QST_SUB_TYPE_SIMILAR.equals(aigoQuestionAnswerEntity.getCurrentTestTypeSub())
							||Code.Aigo.QST_SUB_TYPE_SIMILAR_SUB.equals(aigoQuestionAnswerEntity.getCurrentTestTypeSub()) ) {
						pageTypeSub = Code.Aigo.QST_SUB_TYPE_BASIC;
					}
				} else if(Code.Aigo.QST_TYPE_WEEKLY.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
					pageType = Code.Aigo.QST_TYPE_WEEKLY;
					if(Code.Aigo.QST_SUB_TYPE_SIMILAR.equals(aigoQuestionAnswerEntity.getCurrentTestTypeSub())){
						pageTypeSub = Code.Aigo.QST_SUB_TYPE_BASIC;
					}
				} else if(Code.Aigo.QST_TYPE_LEVEL.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
					pageType = Code.Aigo.QST_TYPE_LEVEL;
					pageTypeSub = Code.Aigo.QST_SUB_TYPE_BASIC;
				} else if(Code.Aigo.QST_TYPE_PREV.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
					pageType = Code.Aigo.QST_TYPE_PREV;
					pageTypeSub = Code.Aigo.QST_SUB_TYPE_BASIC;
				}
			}
			
			///////////////////////////////////////////////////////// 해당 성취기준의 문제를 모두 풀었는지 체크한다.
			AigoLearnEntity aigoLearnEntity = new AigoLearnEntity();
			
			if(Code.Aigo.QST_TYPE_PREV.equals(aigoQuestionAnswerEntity.getCurrentTestType()) || Code.Aigo.QST_TYPE_CHECK.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
				int[] arrCategories			= {Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK};	// 기출문제와 확인문제 문제분류 값
				int[] arrExceptCategories	= {Code.Aigo.CATEGORY_CHECK_SIMILAR, Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB};	// 유사문제는 풀이 여부 체크하지 않는다. 안 풀어도 됨.
				aigoLearnEntity.setArrCategories(arrCategories);
				aigoLearnEntity.setArrExceptCategories(arrExceptCategories);
			}else if(Code.Aigo.QST_TYPE_WEEKLY.equals(aigoQuestionAnswerEntity.getCurrentTestType())){
				int[] arrCategories			= {Code.Aigo.CATEGORY_WEEKLY};	// 주간평가 문제 문제분류 값
				int[] arrExceptCategories	= {Code.Aigo.CATEGORY_WEEKLY_SIMILAR};	// 유사문제는 풀이 여부 체크하지 않는다. 안 풀어도 됨.
				aigoLearnEntity.setArrCategories(arrCategories);
				aigoLearnEntity.setArrExceptCategories(arrExceptCategories);
			}else if(Code.Aigo.QST_TYPE_LEVEL.equals(aigoQuestionAnswerEntity.getCurrentTestType())){
				int[] arrCategories			= {Code.Aigo.CATEGORY_LEVEL};	// 레벨평가 문제 문제분류 값
				aigoLearnEntity.setArrCategories(arrCategories);
			}
			aigoLearnEntity.setSubId(aigoQuestionAnswerEntity.getSubId());
			aigoLearnEntity.setAcvId(aigoQuestionAnswerEntity.getAcvId());
			aigoLearnEntity.setLevId(aigoQuestionAnswerEntity.getLevId());
			aigoLearnEntity.setMemId(aigoQuestionAnswerEntity.getMemId());
			AigoLearnEntity checkLearnFinishMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionInfo", aigoLearnEntity);
			if(checkLearnFinishMap.getTotalQstCnt() == checkLearnFinishMap.getUserAnsCnt()) {
				resultCode = Code.Result.SUCC_03;	// 문제풀이 모두 완료!
				// 평가 이력 테이블에 이력 저장
				AigoLearnHistoryEntity learnHistoryEntity = new AigoLearnHistoryEntity();
				learnHistoryEntity.setThiTestType(aigoQuestionAnswerEntity.getCurrentTestType());
				if(!StringUtils.isEmpty(aigoQuestionAnswerEntity.getSubId())) {
					learnHistoryEntity.setSubId(aigoQuestionAnswerEntity.getSubId());
				}else {
					learnHistoryEntity.setSubId(0);
				}
				if(!StringUtils.isEmpty(aigoQuestionAnswerEntity.getAcvId())) {
					learnHistoryEntity.setAcvId(aigoQuestionAnswerEntity.getAcvId());
				}else {
					learnHistoryEntity.setAcvId(0);
				}
				if(Code.Aigo.QST_TYPE_PREV.equals(aigoQuestionAnswerEntity.getCurrentTestType())) {
					learnHistoryEntity.setAcaId(Code.Aigo.CATEGORY_PREV);
				}else if(Code.Aigo.QST_TYPE_CHECK.equals(aigoQuestionAnswerEntity.getCurrentTestType())){
					learnHistoryEntity.setAcaId(Code.Aigo.CATEGORY_CHECK);
				}else if(Code.Aigo.QST_TYPE_WEEKLY.equals(aigoQuestionAnswerEntity.getCurrentTestType())){
					learnHistoryEntity.setAcaId(Code.Aigo.CATEGORY_WEEKLY);
				}else if(Code.Aigo.QST_TYPE_LEVEL.equals(aigoQuestionAnswerEntity.getCurrentTestType())){
					learnHistoryEntity.setAcaId(Code.Aigo.CATEGORY_LEVEL);
				}
				learnHistoryEntity.setLevId(aigoQuestionAnswerEntity.getLevId());
				learnHistoryEntity.setMemId(aigoQuestionAnswerEntity.getMemId());
				
				if(commonDao.insert("FrontLearnDAO.insertLearnHistory", learnHistoryEntity) > 0) {
					// 레벨평가 이력정보 Set
					AigoLearnHistoryEntity learnHistoryMap = new AigoLearnHistoryEntity();
					learnHistoryMap.setMemId(StringUtils.nvl(learnHistoryEntity.getMemId(), "0"));
					learnHistoryMap.setThiTestType(Code.Aigo.QST_TYPE_LEVEL);
					List<AigoLearnHistoryEntity> levelTestHistory = commonDao.selectList("FrontLearnDAO.selectLearnHistory", learnHistoryMap);
					request.getSession().setAttribute("levelTestHistory", levelTestHistory);
				}
			}
			
			resultMap.put(Default.ResultValue.RESULT_STATUS, aigoQuestionAnswerEntity.getPassYn());
			resultMap.put(Default.ResultValue.RESULT_CODE, resultCode);
			resultMap.put(Default.ResultValue.PAGETYPE, pageType);
			resultMap.put(Default.ResultValue.PAGETYPE_SUB, pageTypeSub);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	
	/**
	 * @ClassName	: FrontLearnServiceImpl.java
	 * @Method		: selectLearnDetail
	 * @Date		: 2021. 1. 25. 
	 * @author		: dev.yklee
	 * @Description	: 학습 > 홈 데이터 조회
	 */
	@Override
	public Map<String, Object> selectHome(HttpServletRequest request, Map<String, Object> param) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			// 학습목표 상세 내용
			param.put("subId", Code.Aigo.MATH_I);
			
			/* 해당 사용자의 성취도(문제풀이 이력) */
			resultMap.put("rate", commonDao.selectOne("FrontLearnDAO.selectMyRatePer", param));
			/* 해당 사용자의 기본 문제풀이 정보 */
			resultMap.put("cnts", commonDao.selectOne("FrontLearnDAO.selectHomeCnt", param));
			
			/* 해당 사용자의 문제풀이 이력이 1개 이상 일 때 */
			if((Long) ((Map) resultMap.get("cnts")).get("testCnt") > 0) {
				/* 해당 사용자의 맞춘문제도 다시보자 */
				param.put("sort", "d");
				param.put("passYn", "y");
				resultMap.put("passY", commonDao.selectList("FrontLearnDAO.selectPassTestList", param));
				/* 해당 사용자의 틀린문제도 다시풀자 */
				param.put("sort", "a");
				param.put("passYn", "n");
				resultMap.put("passN", commonDao.selectList("FrontLearnDAO.selectPassTestList", param));
			} else {
				/* 모든 사용자의 대다수가 맞춘 문제 탑 3 */
				param.put("sort", "d");
				resultMap.put("allPassY", commonDao.selectList("FrontLearnDAO.selectAllPassList", param));
				/* 모든 사용자의 대다수가 틀린 문제 탑 3 */
				param.put("sort", "a");
				resultMap.put("allPassN", commonDao.selectList("FrontLearnDAO.selectAllPassList", param));
			}
			
			/* 해당 사용자의 문제풀이 이력이 5개 이상 일 때 */
			if((Long) ((Map) resultMap.get("cnts")).get("testCnt") > 4) {
				/* 해당 사용자가 가장 잘하는 단원 리스트 */
				resultMap.put("great", commonDao.selectList("FrontLearnDAO.selectGreatList", param));
			} else {
				/* 해당 사용자가 취약한 단원 리스트 */
				resultMap.put("unitList", commonDao.selectList("FrontLearnDAO.selectUnitTestList", param));
			}
			
			resultMap.put("userAchieveData", frontUserService.selectLearnAchieveData(param));
			
			AigoLearnEntity checkParamMap = new AigoLearnEntity();
			AigoLearnEntity checkLevelTestMap = new AigoLearnEntity();
			AigoLearnEntity resultLearningMap = new AigoLearnEntity();
			
			checkParamMap.setMemId(LoginSession.getSeq(request.getSession()));
			checkParamMap.setLevId(NumberUtils.stringToInt(LoginSession.getLoginLevel(request.getSession())));
			checkParamMap.setLevId(NumberUtils.stringToInt(LoginSession.getLoginLevel(request.getSession())));
			
			// 1. 레벨평가 진행 조건 체크
			// 레벨평가를 이미 진행했는지 확인
			// 검색할 과목 설정 : 기본과목(수I,수II) + 선택과목(기하/확률과통계/미분과적분 중 선택한 과목)
			checkParamMap.setArrSubjects(new int[] {Code.Aigo.MATH_I, Code.Aigo.MATH_II, NumberUtils.stringToInt(LoginSession.getLoginSubjectId(request.getSession())) });
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
			 AigoLearnEntity checkLearningMap = commonDao.selectOne("FrontLearnDAO.selectRecentlyLearningInfo", checkParamMap);
			 
			if(!StringUtils.isEmpty(checkLearningMap)) {
				
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
				resultMap.put("learningHistoryYn", Default.YES_LOWER);
			}else {
				resultMap.put("learningHistoryYn", Default.NO_LOWER);
			}
			
			resultMap.put("learning", resultLearningMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

	/**
	 * @ClassName	: FrontLearnServiceImpl.java
	 * @Method		: selectLearnProgressInfo
	 * @Date		: 2021. 2. 1. 
	 * @author		: dev.yklee
	 * @Description	: 학습 및 평가 진행여부 확인
	 */
	@Override
	public Map<String, Object> selectLearnProgressInfo(AigoLearnEntity aigoLearnEntity) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String resultCode	= Code.Result.FAIL_99;
		AigoLearnEntity checkMap = new AigoLearnEntity();
		
		try {
			
			int[] arrCategories			= {Code.Aigo.CATEGORY_PREV, Code.Aigo.CATEGORY_CHECK};	// 기출문제와 확인문제 문제분류 값
			int[] arrExceptCategories	= {Code.Aigo.CATEGORY_CHECK_SIMILAR, Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB};	// 유사문제는 풀이 여부 체크하지 않는다. 안 풀어도 됨.
			aigoLearnEntity.setArrCategories(arrCategories);
			aigoLearnEntity.setArrExceptCategories(arrExceptCategories);
			
			// 1. 기출문제, 확인평가 모두 풀었는지 확인
			checkMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionInfo", aigoLearnEntity);
			if(checkMap.getTotalQstCnt() <= checkMap.getUserAnsCnt()) {
				arrCategories			= new int[] {Code.Aigo.CATEGORY_WEEKLY};			// 주간평가 문제분류 값
				arrExceptCategories		= new int[] {Code.Aigo.CATEGORY_WEEKLY_SIMILAR};	// 유사문제는 풀이 여부 체크하지 않는다. 안 풀어도 됨.
				aigoLearnEntity.setArrCategories(arrCategories);
				aigoLearnEntity.setArrExceptCategories(arrExceptCategories);
				checkMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionInfo", aigoLearnEntity);
				
				// 2. 주간평가를 진행 했는지 확인
				if(checkMap.getTotalQstCnt() <= checkMap.getUserAnsCnt()) {
					
					resultCode = Code.Result.SUCC_01;		// 주간평가 모두 진행 // 주간 리포트로 이동
					
				}else {
					resultCode = Code.Result.SUCC_02;		// 기출문제 풀이, 확인문제 풀이 모두 진행 // 주간평가 문제페이지로 이동
				}
			}else {
				resultCode = Code.Result.SUCC_03;			// 기출문제, 확인문제 풀이 해야함 // 문제풀이 페이지로 이동
			}
			
			resultMap.put(Default.ResultValue.RESULT_CODE, resultCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

	/**
	 * @ClassName	: FrontLearnServiceImpl.java
	 * @Method		: updateUserLevel
	 * @Date		: 2021. 2. 12. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 레벨업
	 */
	@Override
	public Map<String, Object> updateUserLevel(HttpServletRequest request, Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		String resultCode = Code.Result.FAIL_99;
		
		try {
			
			int reportLevel = NumberUtils.stringToInt(StringUtils.nvl(paramMap.get("levId"), "0"));									// 레벨평가 리포트의 레벨
			int currentLevel = NumberUtils.stringToInt(StringUtils.nvl(LoginSession.getLoginLevel(request.getSession()), "0"));		// 현재 레벨
			int memId = NumberUtils.stringToInt(StringUtils.nvl(LoginSession.getSeq(request.getSession()), "0"));					// 사용자 ID
			// 현재 레벨에 맞는 레벨평가 리포트에서 학습을 완료했을 경우 level up
			if(currentLevel != 0 && reportLevel != 0 && (currentLevel == reportLevel)) {
				UserMemberEntity userEntity = new UserMemberEntity();
				userEntity.setMemLevel(currentLevel+1);
				userEntity.setMemId(memId);
				if(commonDao.update("FrontUserDAO.updateUserLevel", userEntity) > 0) {
					UserLevelHistoryEntity userLevelHistory = new UserLevelHistoryEntity();
					userLevelHistory.setMemId(memId);
					userLevelHistory.setMlhFrom(currentLevel);
					userLevelHistory.setMlhTo(currentLevel+1);
					userLevelHistory.setMlhReason("levelTest");
					userLevelHistory.setMlhIp(request.getRemoteAddr());
					commonDao.update("FrontUserDAO.insertUserLevelHistory", userLevelHistory);
					resultCode = Code.Result.SUCC;		// 레벨업 완료
					request.getSession().setAttribute("loginSessionLevel", currentLevel+1);
					
				}else {
					resultCode = Code.Result.FAIL_01;	// 사용자 레벨 업데이트에 실패했을 경우
				}
			}else {
				resultCode = Code.Result.FAIL_02;		// 레벨업 조건이 아닌 경우
			}
			
			resultMap.put(Default.ResultValue.RESULT_CODE, resultCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}
