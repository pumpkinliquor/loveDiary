package com.plushih.controllers.front.service.impl;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.plushih.controllers.front.service.FrontCommentaryService;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoCommentaryEntity;
import com.plushih.services.ci.CommonService;

@Service("frontCommentaryService")
public class FrontCommentaryServiceImpl implements FrontCommentaryService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	FrontLoginService frontLoginService;
	
	@Autowired
	CommonService commonService;
	
	/**
	 * @ClassName	: FrontAchieveServiceImpl.java
	 * @Method		: selectAchieveDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학습목표 상세
	 */
	@Override
	public Map<String, Object> selectCommentaryDetail(Map<String, Object> commentaryMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			// 학습목표 상세 내용
			Map<String, Object> tempMap = new HashMap<>();
			Map<String, Object> cptNamesMap = new HashMap<>();
			
			tempMap = commonDao.selectOne("FrontCommentaryDAO.selectCommentaryDetail", commentaryMap);
			
			if(tempMap.get("allCptId") != null) {
				String allCptId[] = tempMap.get("allCptId").toString().split(",");
				Arrays.sort(allCptId);
				commentaryMap.put("allCptId", allCptId);
				cptNamesMap = commonDao.selectOne("FrontCommentaryDAO.selectCptNames", commentaryMap);
				//정렬해서 새롭게 세팅
				tempMap.put("all_cpt_id", cptNamesMap.get("allCptId"));
				tempMap.put("all_acv_id", cptNamesMap.get("allAcvId"));
				tempMap.put("all_cpt_name", cptNamesMap.get("allCptName"));
			}
			
			commentaryMap.put("type", "COMMENTARY");
			commentaryMap.put("seq", tempMap.get("cmtrId"));
			AigoCommentaryEntity vo = new AigoCommentaryEntity();
			vo.setCmtrId((Integer) tempMap.get("cmtrId"));
			
			resultMap.put("detail", tempMap);
			resultMap.put("cmtrInfo", commonDao.selectOne("FrontCommentaryDAO.selectCommentaryInfo", vo));
			resultMap.put("fileList", commonDao.selectList("Common.cmmFileList", commentaryMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

	/**
	 * @ClassName	: FrontCommentaryServiceImpl.java
	 * @Method		: selectCommentaryInfo
	 * @Date		: 2021. 2. 16. 
	 * @author		: dev.yklee
	 * @Description	: 해설 컨텐츠 조회
	 */
	@Override
	public Map<String, Object> selectCommentaryInfo(AigoCommentaryEntity commentaryMap) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			// 해설 컨텐츠 조회
			resultMap.put("commentary", commonDao.selectOne("FrontCommentaryDAO.selectCommentaryInfo", commentaryMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}
