package com.plushih.controllers.front.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontQuestionAnswerService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoQuestionAnswerEntity;

@Service("frontQuestionService")
public class FrontQuestionAnswerServiceImpl implements FrontQuestionAnswerService {
	
	@Autowired
	private CommonDao commonDao;

	/**
	 * @ClassName	: FrontQuestionServiceImpl.java
	 * @Method		: insertQuestionAnswer
	 * @Date		: 2021. 1. 22. 
	 * @author		: dev.yklee
	 * @Description	: 사용자의 문제풀이 답안 저장 (공통)
	 */
	@Override
	public String insertQuestionAnswer(HttpServletRequest request, AigoQuestionAnswerEntity aigoQuestionAnswerEntity) throws Exception {
		
		String resultCode = Code.Result.FAIL_99;
		
		// 사용자 memId Set
		aigoQuestionAnswerEntity.setMemId(LoginSession.getSeq(request.getSession()));
		
		return resultCode;
	}
	

}
