package com.plushih.controllers.front;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontAchieveService;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.AigoAchievementEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;


@Controller
@RequestMapping("front/achieve")
public class FrontAchieveController extends CoreController {
	
	@Autowired
	private FrontAchieveService frontAchieveService;
	
	@Autowired
	private CommonService cmmService;
	
	@Autowired
	private FrontUserService frontUserService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);


	/**
	 * @ClassName : FrontAchieveController.java
	 * @Method : list
	 * @Date : 2021. 1. 25.
	 * @author : dev.yklee
	 * @Description : 성취기준 리스트
	 */
	@RequestMapping(value = {"/list", "/list/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String list(HttpServletRequest request, HttpServletResponse response, @PathVariable Map<String, String> pathVariables, ModelMap model, AigoAchievementEntity aigoAchievementEntity, Locale localeParam) throws Exception {
		
		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());
		String levId = LoginSession.getLoginLevel(request.getSession());
		
		// 필요한 데이터 세팅
		aigoAchievementEntity.setMemId(NumberUtils.stringToInt(memId));
		aigoAchievementEntity.setOptionalSubId(NumberUtils.stringToInt(subId));
		aigoAchievementEntity.setLevId(NumberUtils.stringToInt(levId));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontAchieveService.selectAchieveList(aigoAchievementEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute("userInfo", cmmService.userInfo(aigoAchievementEntity));
		
		String path =  "/front/achieve/list";
		path = pathToLangFront(path,pathVariables,model,request);
    	return path;
	}
	
	/**
	 * @ClassName : FrontAchieveController.java
	 * @Method : detail
	 * @Date : 2021. 1. 25.
	 * @author : dev.khko
	 * @Description : 학습목표 상세
	 */
	@RequestMapping(value = {"/detail", "/detail/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String detail(HttpServletRequest request, HttpServletResponse response, @PathVariable Map<String, String> pathVariables, ModelMap model, AigoAchievementEntity aigoAchievementEntity, Locale localeParam) throws Exception {

		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());
		String levId = LoginSession.getLoginLevel(request.getSession());
		
		// 필요한 데이터 세팅
		aigoAchievementEntity.setMemId(NumberUtils.stringToInt(memId));
		aigoAchievementEntity.setOptionalSubId(NumberUtils.stringToInt(subId));
		aigoAchievementEntity.setLevId(NumberUtils.stringToInt(levId));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontAchieveService.selectAchieveDetailList(aigoAchievementEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute("userInfo", cmmService.userInfo(aigoAchievementEntity));

		String path = "/front/achieve/detail";
		path = pathToLangFront(path,pathVariables,model,request);
    	return path;
	}

}