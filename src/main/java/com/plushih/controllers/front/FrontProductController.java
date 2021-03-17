package com.plushih.controllers.front;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.plushih.common.ci.CoreController;
import com.plushih.controllers.front.service.FrontProductService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.front.vo.ProductVO;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.services.front.SiteFileUploadService;

@Controller
@RequestMapping("front/product")
public class FrontProductController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );

    @Autowired
    FrontProductService frontProductService;
    @Autowired
    SiteFileUploadService siteFileUploadService;
    /**
     * searchProduct
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/searchProduct","/searchProduct/{lan}"})
    public String searchProduct (HttpServletRequest request
    						   , HttpServletResponse response
    						   , @PathVariable Map<String, String> pathVariables
    						   , ModelMap model
    						   , Map<String,Object> map
    						   , ProductVO productVO
    						   ) throws Exception {
    	
    	//페이징
    	productVO.setListLength(16); //list 출력 개수
    	int currentPage = productVO.getStartIdx() / productVO.getListLength() + 1;

    	productVO.setLgSeq("1");
    	if(pathVariables.containsKey("lan")) {
    		String lan = pathVariables.get("lan");
    		if("KR".equals(lan)) {
    			productVO.setLgSeq("1");
    		}else if ("EN".equals(lan)) {
    			productVO.setLgSeq("2");
    		}
    	}
    	
    	//제품 리스트
    	List<Map<String,Object>> productList = frontProductService.selectProductList(productVO);
    	for(Map<String,Object> row:productList){

			Map<String,Object> fileRows  = siteFileUploadService.getSiteAttachFiles("PRODUCT",String.valueOf(row.get("pdSeq")));
			row.put("fileMap",fileRows);
			for (Map.Entry<String, Object> entry : fileRows.entrySet()) {
				String key = entry.getKey();
				Map<String,Object> fileRow = (Map<String,Object>)entry.getValue();
				row.put(key,String.valueOf(fileRow.get("safSeq")));
			}

		}

    	int productCnt = frontProductService.selectProductCnt(productVO);
    	
    	model.addAttribute("productList"	, productList);
    	model.addAttribute("listCnt"		, productCnt);
    	model.addAttribute("startIdx"		, productVO.getStartIdx()); //리스트 시작 index
    	model.addAttribute("listLength"		, productVO.getListLength()); //리스트 개수
    	model.addAttribute("currentPage"	, currentPage); //현재 페이지
    	model.addAttribute("vo"				, productVO);
    	
        //path 설정
		String path = "product/searchProduct";
    	path = pathToLangFront(path,pathVariables,model);
      	return path;
    }
    
    
    @RequestMapping(value = { "/searchProductGubun","/searchProductGubun/{lan}"})
    public @ResponseBody Map<String, Object> searchProductGubun(HttpServletRequest request
								   , HttpServletResponse response
								   , @PathVariable Map<String, String> pathVariables
								   , ModelMap model
								   , Map<String,Object> map
								   , ProductVO productVO)  throws Exception {
		//ModelAndView mav=new ModelAndView("jsonView");
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		//페이징
    	productVO.setListLength(16); //list 출력 개수
		
		//제품 리스트
    	List<Map<String,Object>> productList = frontProductService.selectProductList(productVO);
    	for(Map<String,Object> row:productList){

			Map<String,Object> fileRows  = siteFileUploadService.getSiteAttachFiles("PRODUCT",String.valueOf(row.get("pdSeq")));
			row.put("fileMap",fileRows);
			for (Map.Entry<String, Object> entry : fileRows.entrySet()) {
				String key = entry.getKey();
				Map<String,Object> fileRow = (Map<String,Object>)entry.getValue();
				row.put(key,String.valueOf(fileRow.get("safSeq")));
			}

		}

    	int productCnt = frontProductService.selectProductCnt(productVO);
    	
    	retMap.put("productList", productList);
    	retMap.put("listCnt"		, productCnt);
    	retMap.put("startIdx"		, productVO.getStartIdx()); //리스트 시작 index
    	retMap.put("listLength"		, productVO.getListLength()); //리스트 개수
    	retMap.put("currentPage"	, 1); //현재 페이지
		
		
		return retMap;
    }
    
    
    
    /**
     * productNews1
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/productNews1","/productNews1/{lan}"})
    public String productNews1 (HttpServletRequest request
    						, HttpServletResponse response
    						, ModelMap model
    						, @PathVariable Map<String, String> pathVariables
    						, PlusBbsVO plusBbsVO
    						, Locale localeParam ) throws Exception {
    	//페이징
    	plusBbsVO.setListLength(10); //list 출력 개수
    	int currentPage = plusBbsVO.getStartIdx() / plusBbsVO.getListLength() + 1; 
    	
    	//채용 FAQ
    	plusBbsVO.setBbBbs("PN");
    	List<Map<String,Object>> productNewsList = frontProductService.selectProductNewsList(plusBbsVO); //리스트
    	int productNewsCnt = frontProductService.selectProductNewsCnt(plusBbsVO);
    	
    	model.addAttribute("vo"					, plusBbsVO);
    	model.addAttribute("productNewsList"	, productNewsList);
    	model.addAttribute("listCnt"			, productNewsCnt);
    	model.addAttribute("startIdx"			, plusBbsVO.getStartIdx()); //리스트 시작 index
    	model.addAttribute("listLength"			, plusBbsVO.getListLength()); //리스트 개수
    	model.addAttribute("currentPage"		, currentPage); //현재 페이지
    	
    	
    	//path 설정
		String path = "product/productNews1";
    	path = pathToLangFront(path,pathVariables,model);
    	String lan="";
    	if(pathVariables.containsKey("lan")) {
    		lan = pathVariables.get("lan"); 
    		if("KR".equals(lan)) {
    			path = "/front/KR/product/productNews1";
    		}else if ("EN".equals(lan)) {
    			path = "redirect:/front/product/searchProduct/EN";
    		}
    	}
    	
        return path;
    }
    /**
     * productNews2
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/productNewsDetail","/productNewsDetail/{lan}"})
    public String productNews2 (HttpServletRequest request
    						, HttpServletResponse response
							, @PathVariable Map<String, String> pathVariables
    						, ModelMap model
    						, PlusBbsVO plusBbsVO
    						, Locale localeParam ) throws Exception {

    	plusBbsVO.setBbBbs("PN");
    	PlusBbsVO productNewsDetail = frontProductService.selectProductNewsDetail(plusBbsVO);
    	PlusBbsVO prev 	= frontProductService.selectProductNewsPrevDetail(plusBbsVO);
    	PlusBbsVO next 	= frontProductService.selectProductNewsNextDetail(plusBbsVO);
    	
    	
    	model.addAttribute("productNewsDetail"		, productNewsDetail);
    	model.addAttribute("prev"		, prev);
    	model.addAttribute("next"		, next);

    	String path = "product/productNews2";
    	path = pathToLangFront(path,pathVariables,model);
    	return path;
        //return "/front/KR/product/productNews2";
    }
}
    

		

    	
    
    

