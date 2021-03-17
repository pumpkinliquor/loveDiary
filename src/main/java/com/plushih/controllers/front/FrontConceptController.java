package com.plushih.controllers.front;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
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
import com.plushih.controllers.front.service.FrontConceptService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.AigoConceptEntity;
import com.plushih.entities.AigoNotionEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;


@Controller
@RequestMapping("front/concept")
public class FrontConceptController extends CoreController {

	@Autowired
	private FrontConceptService frontConceptService;
	@Autowired
    private CommonService commonService;
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);



	/**
	 * 회원관리
	 * 
	 * @param model
	 * @param localeParam
	 * @return
	 */
	@RequestMapping(value = {"/conceptList"})
	public String conceptList(HttpServletRequest request, HttpServletResponse response, ModelMap model, AigoConceptEntity aigoConceptEntity, Locale localeParam) throws Exception {

		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontConceptService.selectConceptDetailList(aigoConceptEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		return "/front/concept/conceptList";
	}




	/**
	 * 회원관리
	 * 
	 * @param model
	 * @param localeParam
	 * @return
	 */
	@RequestMapping(value = {"/conceptDetail"})
	public String conceptDetail(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, AigoConceptEntity aigoConceptEntity, Locale localeParam) throws Exception {
		
		String memId = LoginSession.getSeq(request.getSession());
		
		Map<String, Object> resultMap = new HashedMap();
		// 필요한 데이터 조회
		resultMap.put("resultDataConcept", frontConceptService.selectConceptDetailList(aigoConceptEntity));
		paramMap.put("memId", memId);
		paramMap.put("type", "NOTION");
		paramMap.put("seq", ((List<AigoNotionEntity>)((HashMap) resultMap.get("resultDataConcept")).get("notionInfo")).get(0).getNotId());
		resultMap.put("resultDataFile", commonService.cmmFileList(paramMap));
		
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, resultMap);
		return "/front/concept/conceptDetail";
	}

}