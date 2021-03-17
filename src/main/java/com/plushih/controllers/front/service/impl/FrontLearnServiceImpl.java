package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontLearnService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoLearnEntity;
import com.plushih.entities.AigoQuestionAnswerEntity;

@Service("frontLearnService")
public class FrontLearnServiceImpl implements FrontLearnService {
	
	@Autowired
	private CommonDao commonDao;
	
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
		AigoLearnEntity questionMap		= null;
		
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
			
			//	CASE 1. 확인문제를 풀면서 오답을 제출했을 시 유사문제 제출로직 필요								//	원래 풀고있던 기본문제의 정보를 가지고 있어야 함
			if(Code.Aigo.QST_TYPE_CHECK.equals(aigoLearnEntity.getCurrentTestType())) {					//	확인문제를 풀고 있었는지 체크
			// 	CASE 1-1. 확인문제 > 기본문제 풀이중인 경우
				if(Code.Aigo.QST_SUB_TYPE_BASIC.equals(aigoLearnEntity.getCurrentTestTypeSub()) ) {		//	확인문제의 기본문제 풀이 중
			//		CASE 1-1-1. 확인문제 > 기본문제 > 오답을 냈을 경우
					if(Default.NO_LOWER.equals(aigoLearnEntity.getUserPassYn())) {
						aigoLearnEntity.setSubAcaId(Code.Aigo.CATEGORY_CHECK_SIMILAR);
			//			* 사용자가 유사문제를 모두 푼 상태인지 체크한다.
						checkMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionInfo", aigoLearnEntity);
			// 			CASE 1-1-1-1. 확인문제 > 더이상 풀 유사문제가 없는 경우 > 기본문제 출제 쿼리 실행
						if (checkMap.getTotalQstCnt() <= checkMap.getUserAnsCnt()) {
							questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
						}else {
			// 			CASE 1-1-1-2. 확인문제 > 풀 유사문제가 남은 경우 > 유사문제 출제 쿼리 실행
							questionMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionList", aigoLearnEntity);
						}
					}else {
			//		CASE 1-1-2. 확인문제 > 기본문제 > 정답을 냈을 경우
						questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
					}
			// 	CASE 1-2. 확인문제 > 유사문제 풀이 중인 경우
				}else if(Code.Aigo.QST_SUB_TYPE_SIMILAR.equals(aigoLearnEntity.getCurrentTestTypeSub()) ) {
			// 		CASE 1-2-1. 확인문제 > 유사문제 > 오답을 냈을 경우
					if(Default.NO_LOWER.equals(aigoLearnEntity.getUserPassYn())) {
						aigoLearnEntity.setSubAcaId(Code.Aigo.CATEGORY_CHECK_SIMILAR_SUB);
			//			* 사용자가 유사2문제를 모두 푼 상태인지 체크한다.
						checkMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionInfo", aigoLearnEntity);
			//			CASE 1-2-1-1. 확인문제 > 더이상 풀 유사2문제가 없는 경우 > 기본문제 출제 쿼리 실행
						if (checkMap.getTotalQstCnt() <= checkMap.getUserAnsCnt()) {
							questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
						}else {
			// 			CASE 1-2-1-2. 확인문제 > 풀 유사문제가 남은 경우 > 유사문제 출제 쿼리 실행
							questionMap = commonDao.selectOne("FrontLearnDAO.selectSubQuestionList", aigoLearnEntity);
						}
					}else {
			// 		CASE 1-2-2. 확인문제 > 유사문제 > 정답을 냈을 경우
						questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
					}
					 // 유사2문제 출제 쿼리 실행
				}
			// 	CASE 2. 기출문제 문제 풀이 시 별도의 처리 없음
			}else{
				questionMap = commonDao.selectOne("FrontLearnDAO.selectBasicQuestionList", aigoLearnEntity);
			}
			
			
			// 실제 사용자가 풀고있는 문제에 대한 정보가 없을 시 1로 세팅
			if(StringUtils.isEmpty(aigoLearnEntity.getCurrentQuestionViewNumber())) {
				questionMap.setCurrentQuestionViewNumber(1);
			}else {
				questionMap.setCurrentQuestionViewNumber(aigoLearnEntity.getCurrentQuestionViewNumber()+1);
			}
			questionMap.setCurrentQuestionNumber(questionMap.getRownum());
			
			resultMap.put("question", questionMap);
			
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
	public String insertAnswer(AigoQuestionAnswerEntity aigoQuestionAnswerEntity) throws Exception {
		
		String resultCode = Code.Result.FAIL_99;
		
		try {
			
			aigoQuestionAnswerEntity.setsCnt(0);
			aigoQuestionAnswerEntity.setfCnt(0);
			
			// 사용여부 설정
			aigoQuestionAnswerEntity.setUseYn(Default.YES_LOWER);
			
			// 1. 사용자 학습풀이정보 테이블 insert
			if(commonDao.insert("FrontQuestionAnswerDAO.insertQuestionAnswerInfo", aigoQuestionAnswerEntity) > 0) {
			// 	2. 사용자 학습풀이 답안 테이블 insert
				if(commonDao.insert("FrontQuestionAnswerDAO.insertQuestionAnswer", aigoQuestionAnswerEntity) > 0) {
			//		3. 사용자 학습풀이 답안 이력 테이블 insert
					if(commonDao.insert("FrontQuestionAnswerDAO.insertQuestionAnswerHistory", aigoQuestionAnswerEntity) > 0) {
			//			4. 사용자 학습풀이정보 테이블에 정답/오답 카운트 update
			//			정/오답에 따른 Info 카운트 세팅
						if(Default.YES_LOWER.equals(aigoQuestionAnswerEntity.getPassYn())) {
							aigoQuestionAnswerEntity.setsCnt(1);
							aigoQuestionAnswerEntity.setfCnt(0);
						}else {
							aigoQuestionAnswerEntity.setsCnt(0);
							aigoQuestionAnswerEntity.setfCnt(1);
						}
						if(commonDao.update("FrontQuestionAnswerDAO.updateQuestionAnswerInfo", aigoQuestionAnswerEntity) > 0) {
							resultCode = Code.Result.SUCC;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultCode;
	}
	
	
	/**
	 * @ClassName	: FrontLearnServiceImpl.java
	 * @Method		: selectLearnDetail
	 * @Date		: 2021. 1. 25. 
	 * @author		: dev.yklee
	 * @Description	: 출제경향 상세
	 */
	@Override
	public Map<String, Object> selectHome(Map<String, Object> param) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			// 학습목표 상세 내용
			param.put("subId", Code.Aigo.MATH_I);
			
			// 결과 Set
			resultMap.put("cnts", commonDao.selectOne("FrontLearnDAO.selectHomeCnt", param));
			resultMap.put("passY", commonDao.selectList("FrontLearnDAO.selectPassYList", param));
			resultMap.put("passN", commonDao.selectList("FrontLearnDAO.selectPassNList", param));
			resultMap.put("great", commonDao.selectList("FrontLearnDAO.selectGreatList", param));
			resultMap.put("unitList", commonDao.selectList("FrontLearnDAO.selectUnitTestList", param));
			param.put("sort", "d");
			resultMap.put("allPassY", commonDao.selectList("FrontLearnDAO.selectAllPassList", param));
			param.put("sort", "a");
			resultMap.put("allPassN", commonDao.selectList("FrontLearnDAO.selectAllPassList", param));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}
