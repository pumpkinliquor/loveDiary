package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
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
public class FrontFursController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );

    /**
     * about us/vision
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/front/furs/main", "/front/furs/main/{lan}"})
    public String userMain (HttpServletRequest request
    					  , HttpServletResponse response
    					  , @PathVariable Map<String, String> pathVariables
    					  , ModelMap model
    					  , Locale localeParam ) throws Exception {

    	
    	//path 설정
		String path = "furs/main";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    	
    }
    
    /**
     * about us/samilHistory
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/front/furs/step02","/front/furs/step02/{lan}"})
    public String samilHistory (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Locale localeParam ) throws Exception {
    	

    	//path 설정
		String path = "furs/step02";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    	
    }
    
    /**
     * about us/design
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/front/furs/step03","/front/furs/step03/{lan}"})
    public String design (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Locale localeParam ) throws Exception {
    	

    	//path 설정
		String path = "furs/step03";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }
    
    
    /**
     * about us/ethical
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/front/furs/step04","/front/furs/step04/{lan}"})
    public String ethical (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Locale localeParam ) throws Exception {
    	

    	//path 설정
		String path = "furs/step04";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    	
    }
    
    /**
     * about us/info
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/front/furs/step05","/front/furs/step05/{lan}"})
    public String info (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Locale localeParam ) throws Exception {

    	//path 설정
		String path = "furs/step05";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }
	 /**
     * about us/info
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/front/furs/step06","/front/furs/step06/{lan}"})
    public String step06 (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Locale localeParam ) throws Exception {

    	//path 설정
		String path = "furs/step06";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }

     /**
     * furs/question
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/front/furs/question","/front/furs/question/{lan}"})
    public String question (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Locale localeParam ) throws Exception {

    	//path 설정
		String path = "furs/question";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }
}