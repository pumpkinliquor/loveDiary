package com.plushih.controllers.front;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.Charset;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontNoticeService;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;


@Controller
@RequestMapping("front/notice")
public class FrontNoticeController extends CoreController {
	
	@Autowired
	private FrontNoticeService frontNoticeService;
	
	@Autowired
	private CommonService cmmService;
	
	@Autowired
	private FrontUserService frontUserService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminBusinessController.class);


	/**
	 * @ClassName : FrontNoticeController.java
	 * @Method : list
	 * @Date : 2021. 1. 25.
	 * @author : dev.yklee
	 * @Description : 성취기준 리스트
	 */
	@RequestMapping(value = {"/list", "/list/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String list(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, @PathVariable Map<String, String> pathVariables, ModelMap model, Locale localeParam) throws Exception {
		
		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());
		String levId = LoginSession.getLoginLevel(request.getSession());
		
		// 필요한 데이터 세팅
		paramMap.put("memId", NumberUtils.stringToInt(memId));
		paramMap.put("optionalSubId", NumberUtils.stringToInt(subId));
		paramMap.put("levId", NumberUtils.stringToInt(levId));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontNoticeService.selectNoticeList(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);
		
		String path =  "/front/notice/list";
		path = pathToLangFront(path,pathVariables,model,request);
    	return path;
	}
	
	/**
	 * @ClassName : FrontNoticeController.java
	 * @Method : detail
	 * @Date : 2021. 1. 25.
	 * @author : dev.khko
	 * @Description : 학습목표 상세
	 */
	@RequestMapping(value = {"/detail", "/detail/{lan}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String detail(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, @PathVariable Map<String, String> pathVariables, ModelMap model, Locale localeParam) throws Exception {

		String memId = LoginSession.getSeq(request.getSession());
		String subId = LoginSession.getLoginSubjectId(request.getSession());
		String levId = LoginSession.getLoginLevel(request.getSession());
		
		// 필요한 데이터 세팅
		paramMap.put("memId", NumberUtils.stringToInt(memId));
		paramMap.put("optionalSubId", NumberUtils.stringToInt(subId));
		paramMap.put("levId", NumberUtils.stringToInt(levId));
		
		// 필요한 데이터 조회
		CommonResultEntity res = new CommonResultEntity();
		res.setResultData(frontNoticeService.selectNoticeDetail(paramMap));
		model.addAttribute(Default.ResultValue.RESPONSE_RESULT_MAP, res);

		String path = "/front/notice/detail";
		path = pathToLangFront(path,pathVariables,model,request);
    	return path;
	}
	
	/**
	 * 첨부파일 다운로드
	 * @param siteId
	 * @param menuId
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({"/fileDown", "/fileDown/{lan}"})
	public String downloadFlowDocFile(@RequestParam Map<String, Object> fileMap, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception{	
		
		model.addAttribute("vo", fileMap);

		if (fileMap.get("bafFile") == null || "".equals(fileMap.get("bafFile"))) {
			model.addAttribute("script", "history.back();");
		}
		
		String fileNm = fileMap.get("bafFile").toString().trim();
		String downFileName = "/aigoupload/NOTICE" + "/" + fileNm;
		String oriFileNm = fileMap.get("bafOrFile").toString().trim();
		oriFileNm = new String(oriFileNm.getBytes("8859_1"), "UTF-8");
		
		System.out.println("다운로드 경로 >>> " + downFileName + "\n파일 명 >>> " + oriFileNm);
		
		File file = new File(downFileName);

		if (!file.exists()||!file.isFile()) {
			model.addAttribute("script", "history.back();");
		}
		
		model.addAttribute("downloadFile", file);
		model.addAttribute("orgFileName", oriFileNm);
		
		return "downloadView";
	}

}