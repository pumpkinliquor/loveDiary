package com.plushih.controllers.front;

import com.plushih.common.ci.CoreController;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


@Controller
@RequestMapping("front/review")
public class FrontReviewController extends CoreController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);
	
	
	/**
	 * @ClassName	: FrontReviewController.java
	 * @Method		: list
	 * @Date		: 2021. 1. 26. 
	 * @author		: dev.yklee
	 * @Description	: 다시풀기 > 문제 리스트
	 */
	@RequestMapping(value = {"/list", "/list/{lan}"})
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		return request.getRequestURI().toLowerCase();
	}
	
	/**
	 * @ClassName	: FrontReviewController.java
	 * @Method		: question
	 * @Date		: 2021. 1. 26. 
	 * @author		: dev.yklee
	 * @Description	: 다시풀기 > 문제 페이지
	 */
	@RequestMapping(value = {"/question", "/question/{lan}"})
	public String question(HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		return request.getRequestURI().toLowerCase();
	}
	
	/**
	 * @ClassName	: FrontReviewController.java
	 * @Method		: concept
	 * @Date		: 2021. 1. 26. 
	 * @author		: dev.yklee
	 * @Description	: 다시풀기 > 문제상세> 개념보기 페이지
	 */
	@RequestMapping(value = {"/concept", "/concept/{lan}"})
	public String concept(HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		return request.getRequestURI().toLowerCase();
	}
	
	/**
	 * @ClassName	: FrontReviewController.java
	 * @Method		: commentary
	 * @Date		: 2021. 1. 26. 
	 * @author		: dev.yklee
	 * @Description	: 다시풀기 > 문제상세 > 해설보기 페이지
	 */
	@RequestMapping(value = {"/commentary", "/commentary/{lan}"})
	public String commentary(HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		return request.getRequestURI().toLowerCase();
	}
	
}