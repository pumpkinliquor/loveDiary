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
import com.plushih.controllers.front.service.FrontCommentaryService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.AigoCommentaryEntity;
import com.plushih.entities.CommonResultEntity;


@Controller
@RequestMapping("front/commentary")
public class FrontCommentaryController extends CoreController {

	@Autowired
	private FrontCommentaryService frontCommentaryService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);

	/**
	 * 회원관리
	 * 
	 * @param model
	 * @param localeParam
	 * @return
	 */
	@RequestMapping(value = {"/commentaryDetail"})
	public String commentaryDetail(@RequestParam Map<String, Object> commentaryMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, AigoCommentaryEntity aigoCommentaryEntity, Locale localeParam) throws Exception {
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontCommentaryService.selectCommentaryDetail(commentaryMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		return "/front/commentary/commentaryDetail";
	}

}