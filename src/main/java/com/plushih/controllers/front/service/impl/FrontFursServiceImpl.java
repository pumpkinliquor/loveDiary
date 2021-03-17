package com.plushih.controllers.front.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontFursService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoEvaluationEntity;
import com.plushih.entities.CodeMasterEntity;
import com.plushih.services.CodeService;

@Service("frontFursService")
public class FrontFursServiceImpl implements FrontFursService {
	
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * @ClassName	: FrontFursServiceImpl.java
	 * @Method		: selectContentsAnalysisInfo
	 * @Date		: 2021. 3. 3. 
	 * @author		: dev.yklee
	 * @Description	: 모의진단 > 진단결과 > 내용영역별 분석 차트데이터 조회
	 */
	@Override
	public Map<String, Object> selectContentsAnalysisInfo(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// 내용영역 코드 리스트 조회
		CodeMasterEntity code = new CodeMasterEntity();
		code.setCmGubun(Code.Gubun.QST_ANALYSIS_CON);
		List<CodeMasterEntity> itemList = commonDao.selectList("FrontCommonDAO.selectCommonCodeList", code);
		String[] arrItem = new String[itemList.size()];
		int index = 0;
		for( CodeMasterEntity item : itemList) {
			arrItem[index] = item.getCmName();
			index ++;
		}
		
		// 데이터 조회
		paramMap.put("cmGubun", Code.Gubun.QST_ANALYSIS_CON);
		List<Map<String, String>> analysisChartDataList = commonDao.selectList("FrontFursDAO.selectAnalysisChartDataList", paramMap);
		resultMap.put("chartDataList", analysisChartDataList);
		String[] arrItemData = new String[analysisChartDataList.size()];
		String[] arrTotalData = new String[analysisChartDataList.size()];
		String[] arrUserData = new String[analysisChartDataList.size()];
		int chartIndex = 0;
		for(Map<String, String> data : analysisChartDataList) {
			arrItemData[chartIndex] = "'"+String.valueOf(data.get("cmName"))+"'";
			arrTotalData[chartIndex] = String.valueOf(data.get("totalScore"));
			arrUserData[chartIndex] = String.valueOf(data.get("userScore"));
			chartIndex++;
		}
		// 차트 script에 직접 set
		resultMap.put("arrItemData", Arrays.toString(arrItemData));
		resultMap.put("arrTotalData", Arrays.toString(arrTotalData));
		resultMap.put("arrUserData", Arrays.toString(arrUserData));
		
		// 진단 총평
		AigoEvaluationEntity evaluationInfo = commonDao.selectOne("FrontFursDAO.selectEvaluationInfo", paramMap);
		resultMap.put("evaluationInfo", evaluationInfo);
		
		
		// 항목 리스트 - 배열로 Set
		resultMap.put("arrItem", arrItem);
		
		return resultMap;
	}
	
}
