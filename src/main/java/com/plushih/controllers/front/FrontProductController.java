package com.plushih.controllers.front;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plushih.common.ci.CoreController;
import com.plushih.controllers.front.service.FrontProductService;
import com.plushih.services.front.SiteFileUploadService;

@Controller
@RequestMapping("front/product")
public class FrontProductController extends CoreController {
    
    @Autowired
    FrontProductService frontProductService;
    @Autowired
    SiteFileUploadService siteFileUploadService;
    
    /**
     * @ClassName	: FrontProductController.java
     * @Method		: info
     * @Date		: 2021. 3. 11. 
     * @author		: dev.yklee
     * @Description	: 이용권 > 구매 가능한 이용권 정보
     */
    @RequestMapping(value = { "/info","/info/{lan}"})
    public String info (HttpServletRequest request
    		, HttpServletResponse response
    		, ModelMap model
    		, @PathVariable Map<String, String> pathVariables
    		, Locale localeParam ) throws Exception {
    	
    	String path = "/front/product/info";
    	return path;
    }
    
    /**
     * @ClassName	: FrontProductController.java
     * @Method		: detail
     * @Date		: 2021. 3. 11. 
     * @author		: dev.yklee
     * @Description	: 이용권 > 이용권 상세정보
     */
    @RequestMapping(value = { "/detail","/detail/{lan}"})
    public String detail (HttpServletRequest request
    		, HttpServletResponse response
    		, ModelMap model
    		, @PathVariable Map<String, String> pathVariables
    		, Locale localeParam ) throws Exception {
    	
    	String path = "/front/product/detail";
    	return path;
    }
    
}
