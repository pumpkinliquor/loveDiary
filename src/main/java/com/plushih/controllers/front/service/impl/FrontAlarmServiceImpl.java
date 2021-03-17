package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontAlarmService;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.CommonResultEntity;

@Service("frontAlarmService")
public class FrontAlarmServiceImpl implements FrontAlarmService {
	
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
	public Map<String, Object> selectAlarmList(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			// 학습목표 상세 내용
			resultMap.put("arList", commonDao.selectList("FrontAlarmDAO.selectAlarmList", paramMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: readAlarm
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 설정 정보 저장
	 */
	@Override
	public CommonResultEntity readAlarm(Map<String, Object> paramMap, CommonResultEntity commonResultEntity) throws Exception {
		if(commonDao.update("FrontAlarmDAO.readAlarm", paramMap) > 0) {
			commonResultEntity.setResultCode(Code.Result.SUCC);
		}
		else commonResultEntity.setResultCode(Code.Result.FAIL_01);
		
		return commonResultEntity;
	}
	
	/**
	 * @ClassName	: FrontUserServiceImpl.java
	 * @Method		: readAlarm
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 설정 정보 저장
	 */
	@Override
	public Map<String, Object> notReadAlarmCnt(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			// 학습목표 상세 내용
			resultMap.put("notReadCnt", commonDao.selectOne("FrontAlarmDAO.notReadAlarmCnt", paramMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return resultMap;
	}
	

}
