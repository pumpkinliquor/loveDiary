package com.plushih.controllers.front;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plushih.common.constant.LoginSession;
import com.plushih.services.front.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.controllers.front.service.FrontStoryService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.front.BbsService;

@Controller
@RequestMapping("front/story")
public class FrontStoryController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );


    @Autowired
    private BbsService bbsService;

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private FrontStoryService frontStoryService;
    /**
     * pressRelease
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/pressRelease","/pressRelease/{lan}"})
    public String pressRelease (HttpServletRequest request
    						, HttpServletResponse response
    						, @PathVariable Map<String, String> pathVariables
    						, ModelMap model
    						, PlusBbsVO plusBbsVO
    						, Locale localeParam ) throws Exception {

    	//페이징
    	plusBbsVO.setListLength(10); //list 출력 개수
    	int currentPage = plusBbsVO.getStartIdx() / plusBbsVO.getListLength() + 1; 
    	
    	//채용 FAQ
    	plusBbsVO.setBbBbs("PR");
    	List<Map<String,Object>> pressReleaseList = frontStoryService.selectProductNewsList(plusBbsVO); //리스트
    	int pressReleaseCnt = frontStoryService.selectProductNewsCnt(plusBbsVO);
    	
    	model.addAttribute("vo"					, plusBbsVO);
    	model.addAttribute("pressReleaseList"	, pressReleaseList);
    	model.addAttribute("listCnt"			, pressReleaseCnt);
    	model.addAttribute("startIdx"			, plusBbsVO.getStartIdx()); //리스트 시작 index
    	model.addAttribute("listLength"			, plusBbsVO.getListLength()); //리스트 개수
    	model.addAttribute("currentPage"		, currentPage); //현재 페이지

    	//path 설정
		String path = "story/pressRelease";
    	path = pathToLangFront(path,pathVariables,model);
      	String lan="";
      	if(pathVariables.containsKey("lan")) {
      		lan = pathVariables.get("lan"); 
      		if("EN".equals(lan)) {
      			path = "redirect:/front/story/pressRelease/KR";
      		}
      	}
    	
        return path;

    }
    
    /**
     * pressReleaseDetail
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/pressReleaseDetail","/pressReleaseDetail/{lan}"})
    public String pressReleaseDetail (@RequestParam Map<String, String> param
    								, HttpServletRequest request
    								, HttpServletResponse response
    								, @PathVariable Map<String, String> pathVariables
    								, ModelMap model
    								, PlusBbsVO plusBbsVO
    								, Locale localeParam ) throws Exception {

        
    	plusBbsVO.setBbBbs("PR");
    	PlusBbsVO pressReleaseDetail = frontStoryService.selectPressReleaseDetail(plusBbsVO);
    	
    	if(pressReleaseDetail != null) {
    		Map<String,Object> fileRows  = fileUploadService.getBbsAttachFiles(plusBbsVO.getBbBbs(),pressReleaseDetail.getBbSeq());
    		pressReleaseDetail.setFileMap(fileRows);
    	}
    	
    	PlusBbsVO prev 	= frontStoryService.selectPressReleaseDetailPrevDetail(plusBbsVO);
    	PlusBbsVO next 	= frontStoryService.selectPressReleaseDetailNextDetail(plusBbsVO);
    	
    	
    	model.addAttribute("pressReleaseDetail"		, pressReleaseDetail);
    	model.addAttribute("prev"		, prev);
    	model.addAttribute("next"		, next);
    	
    	
    	//path 설정
		String path = "story/pressReleaseDetail";
    	path = pathToLangFront(path,pathVariables,model);
      	String lan="";
      	if(pathVariables.containsKey("lan")) {
      		lan = pathVariables.get("lan"); 
      		if("EN".equals(lan)) {
      			path = "redirect:/front/story/pressRelease/KR";
      		}
      	}
    	
        return path;
    }
    
    /**
     * brodAd
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/brodAd","/brodAd/{lan}"})
    public String brodAddr (HttpServletRequest request
    					  , HttpServletResponse response
    					  , @PathVariable Map<String, String> pathVariables
    					  , ModelMap model
    					  , PlusBbsVO plusBbsVO
    					  , Locale localeParam ) throws Exception {
    	
    	//페이징
    	plusBbsVO.setListLength(6); //list 출력 개수
    	int currentPage = plusBbsVO.getStartIdx() / plusBbsVO.getListLength() + 1; 
    	
    	//보도 자료
    	plusBbsVO.setBbBbs("BA");
    	List<Map<String,Object>> brodAd = frontStoryService.selectAdList(plusBbsVO); //리스트
		for(Map<String,Object> row:brodAd){

			Map<String,Object> fileRows  = fileUploadService.getBbsAttachFiles(plusBbsVO.getBbBbs(),String.valueOf(row.get("bbSeq")));
			row.put("fileMap",fileRows);
			for (Map.Entry<String, Object> entry : fileRows.entrySet()) {
				String key = entry.getKey();
				Map<String,Object> fileRow = (Map<String,Object>)entry.getValue();
				row.put(key,String.valueOf(fileRow.get("bafSeq")));
			}

		}
    	int brodAdCnt = frontStoryService.selectAdCnt(plusBbsVO);
    	
    	model.addAttribute("bordAdList"		, brodAd);
    	model.addAttribute("listCnt"		, brodAdCnt);
    	model.addAttribute("startIdx"		, plusBbsVO.getStartIdx()); //리스트 시작 index
    	model.addAttribute("listLength"		, plusBbsVO.getListLength()); //리스트 개수
    	model.addAttribute("currentPage"	, currentPage); //현재 페이지
    	
    	//path 설정
		String path = "story/brodAddr";
    	path = pathToLangFront(path,pathVariables,model);
      	String lan="";
      	if(pathVariables.containsKey("lan")) {
      		lan = pathVariables.get("lan"); 
      		if("EN".equals(lan)) {
      			path = "redirect:/front/story/brodAd/KR";
      		}
      	}
    	
        return path;
    }

    /**
     * brodAdDetail
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/brodAdDetail","/brodAdDetail/{lan}"})
    public String brodAddrDetail (HttpServletRequest request
    							, HttpServletResponse response
    							, @PathVariable Map<String, String> pathVariables
    							, ModelMap model
    							, PlusBbsVO plusBbsVO
    							, Locale localeParam ) throws Exception {
    	
    	//보도 자료
    	plusBbsVO.setBbBbs("BA");
    	PlusBbsVO adDetail = frontStoryService.selectAdDetail(plusBbsVO);
    	if(adDetail != null) {
    		Map<String,Object> fileRows  = fileUploadService.getBbsAttachFiles(plusBbsVO.getBbBbs(),String.valueOf(adDetail.getBbSeq()));
    		model.addAttribute("fileMap"		, fileRows);
    		for (Map.Entry<String, Object> entry : fileRows.entrySet()) {
    			String key = entry.getKey();
    			Map<String,Object> fileRow = (Map<String,Object>)entry.getValue();
    			model.addAttribute(key		, String.valueOf(fileRow.get("bafSeq")));
    		}
    	}

    	model.addAttribute("adDetail"		, adDetail);
    	//path 설정
		String path = "story/brodAddrDetail";
    	path = pathToLangFront(path,pathVariables,model);
      	String lan="";
      	if(pathVariables.containsKey("lan")) {
      		lan = pathVariables.get("lan"); 
      		if("EN".equals(lan)) {
      			path = "redirect:/front/story/brodAd/KR";
      		}
      	}
    	return path;
    }
    
    /**
     * printAd
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/printAd","/printAd/{lan}"})
    public String printAddr (HttpServletRequest request
    					   , HttpServletResponse response
    					   , @PathVariable Map<String, String> pathVariables
    					   , ModelMap model
    					   , PlusBbsVO plusBbsVO
    					   , Locale localeParam ) throws Exception {
    	//페이징
    	plusBbsVO.setListLength(3); //list 출력 개수
    	int currentPage = plusBbsVO.getStartIdx() / plusBbsVO.getListLength() + 1; 
    	
    	//보도 자료
    	plusBbsVO.setBbBbs("PA");
    	List<Map<String,Object>> printAd = frontStoryService.selectAdList(plusBbsVO); //리스트
		for(Map<String,Object> row:printAd){

			Map<String,Object> fileRows  = fileUploadService.getBbsAttachFiles(plusBbsVO.getBbBbs(),String.valueOf(row.get("bbSeq")));
			row.put("fileMap",fileRows);
			for (Map.Entry<String, Object> entry : fileRows.entrySet()) {
				String key = entry.getKey();
				Map<String,Object> fileRow = (Map<String,Object>)entry.getValue();
				row.put(key,String.valueOf(fileRow.get("bafSeq")));
			}

		}
    	int printAdCnt = frontStoryService.selectAdCnt(plusBbsVO);
    	
    	model.addAttribute("printAdList"		, printAd);
    	model.addAttribute("listCnt"		, printAdCnt);
    	model.addAttribute("startIdx"		, plusBbsVO.getStartIdx()); //리스트 시작 index
    	model.addAttribute("listLength"		, plusBbsVO.getListLength()); //리스트 개수
    	model.addAttribute("currentPage"	, currentPage); //현재 페이지
    	
    	//path 설정
		String path = "story/printAddr";
    	path = pathToLangFront(path,pathVariables,model);
      	String lan="";
      	if(pathVariables.containsKey("lan")) {
      		lan = pathVariables.get("lan"); 
      		if("EN".equals(lan)) {
      			path = "redirect:/front/story/printAd/KR";
      		}
      	}
    	return path;
    }
    
    /**
     * printAdDetail
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/printAdDetail","printAdDetail/{lan}"})
    public String printAddrDetail (HttpServletRequest request
    							 , HttpServletResponse response
    							 , @PathVariable Map<String, String> pathVariables
    							 , ModelMap model
    							 , PlusBbsVO plusBbsVO
    							 , Locale localeParam ) throws Exception {
    	plusBbsVO.setBbBbs("PA");
    	PlusBbsVO adDetail = frontStoryService.selectAdDetail(plusBbsVO);
    	if(adDetail != null) {
    		Map<String,Object> fileRows  = fileUploadService.getBbsAttachFiles(plusBbsVO.getBbBbs(),String.valueOf(adDetail.getBbSeq()));
    		model.addAttribute("fileMap"		, fileRows);
    		for (Map.Entry<String, Object> entry : fileRows.entrySet()) {
    			String key = entry.getKey();
    			Map<String,Object> fileRow = (Map<String,Object>)entry.getValue();
    			model.addAttribute(key		, String.valueOf(fileRow.get("bafSeq")));
    		}
    	}

    	
    	model.addAttribute("adDetail"		, adDetail);
    	
    	//path 설정
		String path = "story/printAddrDetail";
    	path = pathToLangFront(path,pathVariables,model);
      	String lan="";
      	if(pathVariables.containsKey("lan")) {
      		lan = pathVariables.get("lan"); 
      		if("EN".equals(lan)) {
      			path = "redirect:/front/story/printAd/KR";
      		}
      	}


    	return path;
    }
    
    
    

}