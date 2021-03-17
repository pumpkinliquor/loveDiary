package com.plushih.controllers.front;

import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontTendencyService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.AigoTendencyEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;
import java.util.Map;


@Controller
@RequestMapping("front/tendency")
public class FrontTendencyController extends CoreController {

	@Autowired
	private FrontTendencyService frontTendencyService;

	@Autowired
	private CommonService cmmService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);




	/**
	 * @ClassName : FrontTendencyController.java
	 * @Method : detail
	 * @Date : 2021. 1. 25.
	 * @author : dev.khko
	 * @Description : 학습목표 상세
	 */
	@RequestMapping(value = {"/detail", "/detail/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String detail(@RequestParam Map<String, Object> reviewMap, HttpServletRequest request, HttpServletResponse response, @PathVariable Map<String, String> pathVariables, ModelMap model, AigoTendencyEntity aigoTendencyEntity, Locale localeParam) throws Exception {

		String memId = LoginSession.getSeq(request.getSession());

		reviewMap.put("memId", memId);
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontTendencyService.selectTendencyDetail(aigoTendencyEntity));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		model.addAttribute("userInfo", cmmService.userInfo(reviewMap));

		return "/front/tendency/detail";
	}

}