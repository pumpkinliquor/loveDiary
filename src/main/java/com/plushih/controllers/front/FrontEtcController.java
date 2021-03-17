package com.plushih.controllers.front;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plushih.common.ci.CoreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontEtcService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.totaladmin.AdminBusinessController;

@Controller
@RequestMapping("front/etc")
public class FrontEtcController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );

    @Autowired
    FrontEtcService frontEtcService;

    /**
     * faq
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/faq","/faq/{lan}"})
    public String faq (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, PlusBbsVO plusBbsVO
    		, Map<String,Object> map
    		) throws Exception {
    	
    	
    	//FAQ
    	plusBbsVO.setBbBbs("FAQ");
    	List<Map<String,Object>> faqList = frontEtcService.selectFaqList(plusBbsVO); //리스트
    	
    	model.addAttribute("faqList", faqList);
    	
    	//path 설정
		String path = "/front/etc/faq";
    	path = pathToLangFront(path,pathVariables,model,request);
      	String lan="";
      	if(pathVariables.containsKey("lan")) {
      		lan = pathVariables.get("lan"); 
      		if("EN".equals(lan)) {
      			path = "redirect:/front/etc/faq/KR";
      		}
      	}
    	
    	
    	
    	return path;
    }
    
    /**
     * privacyInfo
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/privacyInfo","/privacyInfo/{lan}"})
    public String privacyInfo (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Map<String,Object> map
    		) throws Exception {
    	

        //path 설정
		String path = "/front/etc/privacyInfo";
    	path = pathToLangFront(path,pathVariables,model,request);
      	return path;
    	
    	
    }
    
    /**
     * contactUs
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/contactUs","/contactUs/{lan}"})
    public String contactUs (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Map<String,Object> map
    		) throws Exception {
    	

    	//path 설정
		String path = "/front/etc/contactUs";
    	path = pathToLangFront(path,pathVariables,model,request);
      	return path;
    }
    
    
    /**
     * emailCollect
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/emailCollect","/emailCollect/{lan}"})
    public String emailCollect (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Map<String,Object> map
    		) throws Exception {

    	//path 설정
		String path = "/front/etc/emailCollect";
    	path = pathToLangFront(path,pathVariables,model,request);
      	return path;
    	
    }
    
    /**
     * legalNoti
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/legalNoti","/legalNoti/{lan}"})
    public String legalNoti (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Map<String,Object> map
    		) throws Exception {

    	//path 설정
		String path = "/front/etc/legalNoti";
    	path = pathToLangFront(path,pathVariables,model,request);
      	return path;
    }
    
    

}