package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.controllers.front.service.FrontMainService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.services.front.SiteFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class FrontMainController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );

    @Autowired
    FrontMainService frontMainService;
    @Autowired
    SiteFileUploadService siteFileUploadService;

    /**
     * 유저 메인
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/main","/front/main","","/main/{lan}"})
    public String userMain (HttpServletRequest request
    					  , HttpServletResponse response
    					  , @PathVariable Map<String, String> pathVariables
    					  , ModelMap model
    					  , Locale localeParam ) throws Exception {
		Integer lgSeq = 1;
		HashMap<String,Object> param = new HashMap<String, Object>();

    	//path 설정
    	return "/main";

    }

}