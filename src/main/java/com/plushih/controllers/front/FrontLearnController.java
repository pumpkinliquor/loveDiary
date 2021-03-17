package com.plushih.controllers.front;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontLearnService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.AigoLearnEntity;
import com.plushih.entities.CommonResultEntity;


@Controller
@RequestMapping("front/learn")
public class FrontLearnController extends CoreController {
	
	@Autowired
	private FrontLearnService frontLearnService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);
	
	/**
	 * @ClassName	: FrontLearnController.java
	 * @Method		: home
	 * @Date		: 2021. 1. 27. 
	 * @author		: hwang
	 * @Description	: 학습 > 홈
	 */
	@RequestMapping(value = {"/home", "/home/{lan}"})
	public String home(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());

		// 필요한 데이터 세팅
		// 레벨 임의 세팅 - 나중에 모의진단 결과 레벨 세팅해주어야 함
		param.put("levId", 3); // 임의로 3으로 설정
		param.put("memId", NumberUtils.stringToInt(memId));
		param.put("optionalSubId", NumberUtils.stringToInt(subId));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		
		res.setResultData(frontLearnService.selectHome(param));
		
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute("cmm", param);
		
		return request.getRequestURI().toLowerCase();
	}
	
	/**
	 * @ClassName	: FrontLearnController.java
	 * @Method		: question
	 * @Date		: 2021. 1. 26. 
	 * @author		: dev.yklee
	 * @Description	: 문제풀이 페이지
	 */
	@RequestMapping(value = {"/question", "/question/{lan}"})
	public String question(HttpServletRequest request, HttpServletResponse response, ModelMap model, AigoLearnEntity aigoLearnEntity, Locale localeParam) throws Exception {
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontLearnService.learning(request, aigoLearnEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		return request.getRequestURI().toLowerCase();
	}
	

}