package com.plushih.controllers.front;


import java.util.List;
import java.util.Locale;
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
import com.plushih.controllers.front.service.FrontOurBusinessService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.services.front.FileUploadService;

@Controller
@RequestMapping("front/ourBusiness")
public class FrontOurBusinessController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );

    @Autowired
    FrontOurBusinessService frontOurBusinessService;
    @Autowired
    private FileUploadService fileUploadService;
    
    
    /**
     * cdmo
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/cdmo","/cdmo/{lan}"})
    public String cdmo (HttpServletRequest request
    					, HttpServletResponse response
    					, @PathVariable Map<String, String> pathVariables
    					, ModelMap model
    					, Locale localeParam ) throws Exception {
        
      	//path 설정
		String path = "ourBusiness/cdmo";
    	path = pathToLangFront(path,pathVariables,model);
      	return path;

    }
    /**
     * rd
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/rd","/rd/{lan}"})
    public String rd (HttpServletRequest request
    				, HttpServletResponse response
    				, @PathVariable Map<String, String> pathVariables
    				, ModelMap model
    				, PlusBbsVO plusBbsVO
    				, Locale localeParam ) throws Exception {
    	
    	//페이징
    	plusBbsVO.setListLength(10); //list 출력 개수
    	int currentPage = plusBbsVO.getStartIdx() / plusBbsVO.getListLength() + 1; 
    	
    	//R&D
    	plusBbsVO.setBbBbs("RD");
    	List<Map<String,Object>> rndList = frontOurBusinessService.selectRndListList(plusBbsVO); //리스트
    	
    	for(Map<String,Object> row:rndList){

			Map<String,Object> fileRows  = fileUploadService.getBbsAttachFiles(plusBbsVO.getBbBbs(),String.valueOf(row.get("bbSeq")));
			row.put("fileMap",fileRows);
			for (Map.Entry<String, Object> entry : fileRows.entrySet()) {
				String key = entry.getKey();
				Map<String,Object> fileRow = (Map<String,Object>)entry.getValue();
				row.put(key,String.valueOf(fileRow.get("bafSeq")));
			}
		}
    	
    	int rndListCnt = frontOurBusinessService.selectRndListCnt(plusBbsVO);
    	
    	model.addAttribute("vo"					, plusBbsVO);
    	model.addAttribute("rndList"			, rndList);
    	model.addAttribute("listCnt"			, rndListCnt);
    	model.addAttribute("startIdx"			, plusBbsVO.getStartIdx()); //리스트 시작 index
    	model.addAttribute("listLength"			, plusBbsVO.getListLength()); //리스트 개수
    	model.addAttribute("currentPage"		, currentPage); //현재 페이지
    	
    	//path 설정
		String path = "ourBusiness/rd";
    	path = pathToLangFront(path,pathVariables,model);
      	return path;

    }
    /**
     * global
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/global","/global/{lan}"})
    public String businessInfoList (HttpServletRequest request
    							  , HttpServletResponse response
    							  , @PathVariable Map<String, String> pathVariables
    							  , ModelMap model
    							  , Locale localeParam ) throws Exception {
    	//path 설정
		String path = "ourBusiness/global";
    	path = pathToLangFront(path,pathVariables,model);
      	return path;
    }
    
    

}