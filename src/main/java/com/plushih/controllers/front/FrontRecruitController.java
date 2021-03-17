package com.plushih.controllers.front;


import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.icu.util.Calendar;
import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.front.service.FrontRecruitService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.front.vo.RecruitVO;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.services.front.FileUploadService;
import com.plushih.services.front.SiteFileUploadService;



@Controller
@RequestMapping("front/recruit")
public class FrontRecruitController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );

    @Autowired
    private FrontRecruitService frontRecruitService;
    @Autowired
    SiteFileUploadService siteFileUploadService;
    @Autowired
    private FileUploadService fileUploadService;
    /**
     * recruit
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/recruit","/recruit/{lan}"})
    public String recruit (HttpServletRequest request
    					, HttpServletResponse response
    					, @PathVariable Map<String, String> pathVariables
    					, ModelMap model
    					, RecruitVO recruitVO 
    					, Locale localeParam ) throws Exception {
    	
    	//채용 목록 리스트
    	List<Map<String,Object>> recruitList = frontRecruitService.selectRecruitList(recruitVO); //리스트
    	
    	//현재 채용 건수
    	int currRecruitCnt = frontRecruitService.selectCurrRecruitCnt();
    	
    	SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddhhmmss");
    	Calendar time = Calendar.getInstance();
    	String today = format.format(time.getTime());
    	
    	model.addAttribute("recruitList"		, recruitList);
    	model.addAttribute("currRecruitCnt"		, currRecruitCnt);
    	model.addAttribute("today"				, today);
        
    	//path 설정
		String path = "recruit/recruit";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;

    }
    
    /**
     * hrCare
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/hrCare","/hrCare/{lan}"})
    public String hrCare (HttpServletRequest request
    					, HttpServletResponse response
    					, @PathVariable Map<String, String> pathVariables
    					, ModelMap model
    					, Locale localeParam ) throws Exception {
    	model.addAttribute("bgGb", "hrCare");


    	//path 설정
		String path = "recruit/hrCare";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }
    
    /**
     * workCare
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/workCare","/workCare/{lan}"})
    public String workCare (HttpServletRequest request
    					, HttpServletResponse response
    					, @PathVariable Map<String, String> pathVariables
    					, ModelMap model
    					, Locale localeParam ) throws Exception {
    	model.addAttribute("bgGb", "workCare");

    	//path 설정
		String path = "recruit/workCare";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }

    /**
     * recruitNotice
     * @param model
     * @param localeParam
     * @return
     */

    @RequestMapping(value = {"/recruitNotice","/recruitNotice/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String recruitNotice (HttpServletRequest request
    						  , HttpServletResponse response
    						  , @PathVariable Map<String, String> pathVariables
    						  , ModelMap model
    						  , RecruitVO recruitVO 
    						  , @RequestParam Map<String, Object> paramMap
    						  , Locale localeParam ) throws Exception {
    	model.addAttribute("bgGb", "workCare");
    	
    	//페이징
    	recruitVO.setListLength(10); //list 출력 개수
    	int currentPage = recruitVO.getStartIdx() / recruitVO.getListLength() + 1;
    	
    	//채용전체리스트
    	List<Map<String,Object>> recruitAllList = frontRecruitService.selectRecruitAllList(recruitVO); //리스트
    	int recruitAllCnt = frontRecruitService.selectRecruitAllCnt(recruitVO);
    	
    	model.addAttribute("recruitAllList"	, recruitAllList);
    	model.addAttribute("listCnt"		, recruitAllCnt);
    	model.addAttribute("startIdx"		, recruitVO.getStartIdx()); //리스트 시작 index
    	model.addAttribute("listLength"		, recruitVO.getListLength()); //리스트 개수
    	model.addAttribute("currentPage"	, currentPage); //현재 페이지
    	model.addAttribute("vo"				, recruitVO);

    	//path 설정
    	String path = "recruit/recruitNotice";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }
    
    /**
     * recruitNoticeDetail
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/recruitNoticeDetail","recruitNoticeDetail/{lan}"})
    public String recruitNoticeDetail (HttpServletRequest request
    								, HttpServletResponse response
    						        , @PathVariable Map<String, String> pathVariables
    								, ModelMap model
    								, RecruitVO recruitVO 
    								, Locale localeParam ) throws Exception {
    	model.addAttribute("bgGb", "workCare");
    	
    	RecruitVO recruitDetail = frontRecruitService.selectrecRuitNoticeDetail(recruitVO);
    	
    	Map<String,Object> fileRows  = siteFileUploadService.getSiteAttachFiles("RECRUIT",String.valueOf(recruitDetail.getRcSeq()));
		model.addAttribute("fileMap"		, fileRows);
    	
    	
    	RecruitVO prev 	= frontRecruitService.selectrecRuitNoticePrevDetail(recruitVO);
    	RecruitVO next 	= frontRecruitService.selectrecRuitNoticeNextDetail(recruitVO);
    	
    	SimpleDateFormat format = new SimpleDateFormat ( "yyyyMMddhhmmss");
    	Calendar time = Calendar.getInstance();
    	String today = format.format(time.getTime());
    	
    	model.addAttribute("today"				, today);
    	model.addAttribute("recruitDetail", recruitDetail);
    	model.addAttribute("prev", prev);
    	model.addAttribute("next", next);
    	
    	//path 설정
    	String path = "recruit/recruitNoticeDetail";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }
    
    /**
     * recruitFaq
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/recruitFaq","/recruitFaq/{lan}"})
    public String recruitFaq (HttpServletRequest request
    						, HttpServletResponse response
    						, @PathVariable Map<String, String> pathVariables
    						, ModelMap model
    						, PlusBbsVO plusBbsVO
    						, Locale localeParam ) throws Exception {
    	model.addAttribute("bgGb", "workCare");
    	
    	//페이징
    	plusBbsVO.setListLength(10); //list 출력 개수
    	int currentPage = plusBbsVO.getStartIdx() / plusBbsVO.getListLength() + 1; 
    	
    	//채용 FAQ
    	plusBbsVO.setBbBbs("RF");
    	List<Map<String,Object>> recruitFaqList = frontRecruitService.selectRecruitFaqList(plusBbsVO); //리스트
    	int recruitFaqCnt = frontRecruitService.selectRecruitFaqCnt(plusBbsVO);
    	
    	model.addAttribute("vo"				, plusBbsVO);
    	model.addAttribute("recruitFaqList"	, recruitFaqList);
    	model.addAttribute("listCnt"		, recruitFaqCnt);
    	model.addAttribute("startIdx"		, plusBbsVO.getStartIdx()); //리스트 시작 index
    	model.addAttribute("listLength"		, plusBbsVO.getListLength()); //리스트 개수
    	model.addAttribute("currentPage"	, currentPage); //현재 페이지
    	
    	//path 설정
    	String path = "recruit/recruitFaq";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
    }
    
    
    

}