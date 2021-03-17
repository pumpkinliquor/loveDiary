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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontPromotionService;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;


@Controller
@RequestMapping("front/promotion")
public class FrontPromotionController extends CoreController {
	
	@Autowired
	private FrontPromotionService frontPromotionService;
	
	@Autowired
	private CommonService cmmService;
	
	@Autowired
	private FrontUserService frontUserService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);


	/**
	 * @ClassName : FrontPromotionController.java
	 * @Method : list
	 * @Date : 2021. 1. 25.
	 * @author : dev.yklee
	 * @Description : 성취기준 리스트
	 */
	@RequestMapping(value = {"/list", "/list/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, @PathVariable Map<String, String> pathVariables, ModelMap model, Locale localeParam) throws Exception {
		
		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());
		String levId = LoginSession.getLoginLevel(request.getSession());
		
		// 필요한 데이터 세팅
		paramMap.put("memId", NumberUtils.stringToInt(memId));
		paramMap.put("optionalSubId", NumberUtils.stringToInt(subId));
		paramMap.put("levId", NumberUtils.stringToInt(levId));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontPromotionService.selectPromotionList(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		String path =  "/front/promotion/list";
		path = pathToLangFront(path,pathVariables,model,request);
    	return path;
	}
	
	/**
	 * @ClassName : FrontPromotionController.java
	 * @Method : detail
	 * @Date : 2021. 1. 25.
	 * @author : dev.khko
	 * @Description : 학습목표 상세
	 */
	@RequestMapping(value = {"/detail", "/detail/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String detail(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, @PathVariable Map<String, String> pathVariables, ModelMap model, Locale localeParam) throws Exception {

		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());
		String levId = LoginSession.getLoginLevel(request.getSession());
		
		// 필요한 데이터 세팅
		paramMap.put("memId", NumberUtils.stringToInt(memId));
		paramMap.put("optionalSubId", NumberUtils.stringToInt(subId));
		paramMap.put("levId", NumberUtils.stringToInt(levId));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontPromotionService.selectPromotionDetail(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);

		String path = "/front/promotion/detail";
		path = pathToLangFront(path,pathVariables,model,request);
    	return path;
	}

}