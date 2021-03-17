package com.plushih.controllers.front.service.impl;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontQuestionAnswerService;
import com.plushih.daos.CommonDao;
import com.plushih.entities.AigoQuestionAnswerEntity;
import com.plushih.entities.SiteAttachFileEntity;

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

	/**
	 * @ClassName	: FrontQuestionAnswerServiceImpl.java
	 * @Method		: selectQuestionRetryInfo
	 * @Date		: 2021. 1. 29.
	 * @author		: dev.khko
	 * @Description	: 사용자의 문제풀이 답안 저장 (공통)
	 */
	@Override
	public HashMap<String, Object> selectQuestionRetryInfo(HashMap<String, String> requestParams) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		HashMap<String, Object> questionMap = commonDao.selectOne("FrontQuestionAnswerDAO.selectQuestionRetryInfo", requestParams);
		if(questionMap.get("cpt_id_str") != null) {
			String allCptId[] = questionMap.get("cpt_id_str").toString().split(",");
			questionMap.put("cpt_id", (allCptId != null) ? allCptId[0] : "");
		}
		if(questionMap.get("cpt_id_str") != null) {
			String allNotId[] = questionMap.get("qst_rel_not_id").toString().split(",");
			questionMap.put("not_id", (allNotId != null) ? allNotId[0] : "");
		}
		resultMap.put("question", questionMap);
		// 문제 컨텐츠 파일 조회
		SiteAttachFileEntity fileEntity = new SiteAttachFileEntity();
		fileEntity.setSeq(NumberUtils.stringToInt(StringUtils.nvl(questionMap.get("qst_id"), "0")));
		fileEntity.setSafBbs(Default.FileBbs.QUESTION);
		resultMap.put("questionFileList", commonDao.selectList("FrontCommonDAO.selectCommonStieFileList", fileEntity));
		
		return resultMap;
	}


}
