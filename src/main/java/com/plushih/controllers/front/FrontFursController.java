package com.plushih.controllers.front;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontFursService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.UserLevelHistoryEntity;
import com.plushih.services.ci.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class FrontFursController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );

    @Autowired
    private FrontFursService frontFursService;
    
    @Autowired
    private CommonService commonService;
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
		String path = "/front/furs/main";
    	path = pathToLangFront(path,pathVariables,model,request);
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
		String path = "/front/furs/step02";
    	path = pathToLangFront(path,pathVariables,model,request);
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
		String path = "/front/furs/step03";
    	path = pathToLangFront(path,pathVariables,model,request);
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
		String path = "/front/furs/step04";
    	path = pathToLangFront(path,pathVariables,model,request);
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
		String path = "/front/furs/step05";
    	path = pathToLangFront(path,pathVariables,model,request);
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
		String path = "/front/furs/step06";
    	path = pathToLangFront(path,pathVariables,model,request);
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
		String path = "/front/furs/question";
    	path = pathToLangFront(path,pathVariables,model,request);
    	return path;
    }
     /**
     * furs/question
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/front/furs/complete","/front/furs/complete/{lan}"})
    public String complete (HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable Map<String, String> pathVariables
    		, ModelMap model
    		, Locale localeParam ) throws Exception {
		String functionName = getFunction();
    	plusActiveRecord db =new plusActiveRecord(functionName,request);
    	String myqstlist = db.input.get_post("myqstlist");
    	if(!StringUtils.isEmpty(myqstlist)){
        	if(myqstlist.length() > 10){
        		Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Map<String,String>>>(){}.getType();
                List<Map<String,String>> myqstArrayList =gson.fromJson(myqstlist,listType);
                Debug.log(myqstArrayList);

                try{
					String MEM_KEY = LoginSession.getSeq(request.getSession());
					String TEMP_ID = LoginSession.getTempId(request.getSession());

					if (StringUtils.isEmpty(TEMP_ID)) {
						model.addAttribute("errMsg","사전 질문 후 진행해주세요.");
						String path = "/front/furs/complete";
						path = pathToLangFront(path, pathVariables, model,request);
						return path;
					}

					db.from("cb_aigo_temp_info");
					db.add("temp_qst",db.input.get_post("grade"));
					db.add("ai_complit",db.input.get_post("grade"));
					db.where("temp_id",TEMP_ID);
					commonService.setUpdate(db);

					db.clearWhere();
					db.clearQuery();
					db.from("cb_member");
					db.add("mem_level",db.input.get_post("grade"));



					db.where("mem_id",MEM_KEY);
					commonService.setUpdate(db);

					db.clearQuery();
					db.clearWhere();
					try {

						db.from("cb_member_level_history");
						db.add("mem_id",MEM_KEY);
						db.add("mlh_from",db.input.get_post("grade"));
						db.add("mlh_to",db.input.get_post("grade"));
						db.add("mlh_reason","levelTest");
						db.add("mlh_datetime",db.getYmdHis());
						db.add("mlh_ip",request.getRemoteAddr());
						commonService.setInsert(db);
					} catch(Exception ex){
						ex.printStackTrace();;
					}
					/*
					UserLevelHistoryEntity userLevelHistory = new UserLevelHistoryEntity();
					userLevelHistory.setMemIMEM_KEY);
					userLevelHistory.setMlhFrom(Integer.parseInt(db.input.get_post("grade")) );
					userLevelHistory.setMlhTo(Integer.parseInt(db.input.get_post("grade")));
					userLevelHistory.setMlhReason("levelTest");
					userLevelHistory.setMlhIp(request.getRemoteAddr());
					commonService.setupdate("FrontUserDAO.insertUserLevelHistory", userLevelHistory);
					*/
					db.clearWhere();
					db.clearQuery();


					for( Map<String,String> map:myqstArrayList){
//                    if(!StringUtils.isEmpty(bbsAttachFileEntity.getDelSafSeq())){
//                        siteFileUploadService.deleteFile(bbsAttachFileEntity.getDelSafSeq());
						//commonService.

						db.from("cb_aigo_temp_qst");
						db.add("qst_id",map.get("qstId"));
						db.add("qst_key",map.get("qstKey"));
						db.add("qst_info",map.get("qstInfo"));
						db.add("tqst_value",map.get("qstValue"));
						db.add("tqst_result",map.get("oxQustValue"));
						db.add("use_yn","y");
						db.add("in_time",map.get("inTime"));
						db.add("out_time",map.get("outTime"));
						db.add("temp_id",TEMP_ID);
						db.add("mem_id",MEM_KEY);
						db.add("reg_mem_id",MEM_KEY);
						db.add("udt_mem_id",MEM_KEY);

						db.add("reg_date",db.getYmdHis());
						db.add("udt_date",db.getYmdHis());
						commonService.setInsert(db);
//                    }

					}
				} catch(Exception e){
					model.addAttribute("errMsg","사전 질문 후 진행해주세요.");
					String path = "/front/furs/complete";
					path = pathToLangFront(path, pathVariables, model,request);
					return path;
				}
            }
        }
    	
    	// 내용영역별 분석차트 관련 데이터 조회
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("memId", LoginSession.getSeq(request.getSession()));
    	paramMap.put("tempId", LoginSession.getTempId(request.getSession()));
    	paramMap.put("levId", db.input.get_post("grade"));
    	request.getSession().setAttribute(LoginSession.LEVEL, paramMap.get("levId"));
    	model.addAttribute("analysisInfo", frontFursService.selectContentsAnalysisInfo(paramMap));
    	
    	//path 설정
		String path = "/front/furs/complete";
    	path = pathToLangFront(path,pathVariables,model,request);
    	return path;
    }
}