package com.plushih.controllers.front.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontFaqService;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.daos.CommonDao;

@Service("frontFaqService")
public class FrontFaqServiceImpl implements FrontFaqService {
	
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
	public Map<String, Object> selectFaqList(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			System.out.println("PARAM >>>> " + paramMap);
			
			resultMap.put("faqList", commonDao.selectList("FrontFaqDAO.selectFaqList", paramMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: MemberJoinServiceImpl.java
	 * @Method		: insertMember
	 * @Date		: 2020. 12. 29.
	 * @author		: dev.yklee
	 * @Description	: 회원가입 > 등록
	 */
	public Map<String, Object> selectSrchList(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			
			System.out.println("PARAM >>>> " + paramMap);
			
			resultMap.put("srchList", commonDao.selectList("FrontFaqDAO.selectSrchList", paramMap));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * @ClassName	: FrontFaqServiceImpl.java
	 * @Method		: selectFaqDetailList
	 * @Date		: 2021. 1. 25.
	 * @author		: dev.khko
	 * @Description	: 학습목표 상세
	 */
	@Override
	public Map<String, Object> selectFaqDetail(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("faqDetail", commonDao.selectOne("FrontFaqDAO.selectFaqList", paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
}
