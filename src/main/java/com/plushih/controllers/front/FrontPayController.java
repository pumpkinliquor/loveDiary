package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/pay")
public class FrontPayController extends CoreController {
	private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );
	
	/**
	 * @ClassName	: FrontPayController.java
	 * @Method		: history
	 * @Date		: 2021. 3. 11. 
	 * @author		: dev.yklee
	 * @Description	: 구매 > 구매이력
	 */
	@RequestMapping(value = { "/history", "/history/{lan}"})
	public String history (HttpServletRequest request
			, HttpServletResponse response
			, @PathVariable Map<String, String> pathVariables
			, ModelMap model
			, Locale localeParam ) throws Exception {
		
		//path 설정
		String path = "/front/pay/history";
		return path;
		
	}
	
	/**
	 * @ClassName	: FrontPayController.java
	 * @Method		: result
	 * @Date		: 2021. 3. 11. 
	 * @author		: dev.yklee
	 * @Description	: 구매 > 결제 결과페이지
	 */
	@RequestMapping(value = { "/result", "/result/{lan}"})
	public String result (HttpServletRequest request
							, HttpServletResponse response
							, @PathVariable Map<String, String> pathVariables
							, ModelMap model
							, Locale localeParam ) throws Exception {
		
		//path 설정
		String path = "/front/pay/result";
		return path;
		
	}
	
}