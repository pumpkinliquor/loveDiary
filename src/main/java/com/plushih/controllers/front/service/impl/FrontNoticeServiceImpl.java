package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontNoticeService;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.daos.CommonDao;

@Service("frontNoticeService")
public class FrontNoticeServiceImpl implements FrontNoticeService {
	
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
	public Map<String, Object> selectNoticeList(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			resultMap.put("noticeList", commonDao.selectList("FrontNoticeDAO.selectNoticeList", paramMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontNoticeServiceImpl.java
	 * @Method		: selectNoticeDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학습목표 상세
	 */
	@Override
	public Map<String, Object> selectNoticeDetail(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			
			resultMap.put("noticeDetail", commonDao.selectOne("FrontNoticeDAO.selectNoticeList", paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
}
