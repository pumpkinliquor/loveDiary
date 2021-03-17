package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.LoginSession;
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
public class FrontCsrController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );


    /**
     * csr
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/front/csr","/front/csr/{lan}"})
    public String userMain (HttpServletRequest request
    					  , HttpServletResponse response
    					  , @PathVariable Map<String, String> pathVariables
    					  , ModelMap model
    					  , Locale localeParam ) throws Exception {

        //return "/front/csr";
        //return String.format("/front/%s/csr/csr", LoginSession.getLanguage(request.getSession()));

		//path 설정
		String path = "csr/csr";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }

}