package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.*;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.AigoCommentaryService;
import com.plushih.services.front.AigoQuestionService;
import com.plushih.services.front.SiteFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.*;

/**
 * hsk3807
 */
@Controller
@RequestMapping("plusadmin/ajax/aigo")
public class AjaxAigoCommentaryAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoCommentaryAdminController.class );



    @Autowired
    private SiteFileUploadService siteFileUploadService;
    @Autowired
    private CommonService commonService;

    @Autowired
    private AigoQuestionService aigoQuestionService;
    
    @Autowired
    private AigoCommentaryService aigoCommentaryService;

    
    
    /**
     * @ClassName	: AjaxAigoQuestionAdminController.java
     * @Method		: memberList
     * @Date		: 2021. 1. 10. 
     * @author		: dev.yklee
     * @Description	: 해설관리 리스트
     */
    @ResponseBody
	@RequestMapping(value = "/commentaryList", method = {RequestMethod.POST})
	public CommonResultEntity commentaryList (HttpServletRequest request, HttpServletResponse response, Locale localeParam, AigoCommentaryEntity aigoCommentaryEntity) throws Exception {
		
		String fullName = getFunction();
		logStart(fullName);
		
		String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		plusActiveRecord dbEntity = new plusActiveRecord(functionName, request);
		
		// 쿼리 호출
		List<AigoCommentaryEntity> dataList = aigoCommentaryService.getCommentaryList(dbEntity, aigoCommentaryEntity);
		
		// 결과값 Set
		CommonResultEntity res = new CommonResultEntity();
		
		try {
			int cnount = commonService.getCount(dbEntity);
			res.setDraw(dbEntity.draw);
			res.setRecordsTotal(cnount);
			res.setRecordsFiltered(cnount);
			res.setResultList(dataList);
			Debug.log(dataList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logEnd(fullName);
		return res;
	}
    
    /**
     * @ClassName	: AjaxAigoQuestionAdminController.java
     * @Method		: searchQuestionInfo
     * @Date		: 2021. 1. 10. 
     * @author		: dev.yklee
     * @Description	: 문항 정보 조회
     */
    @ResponseBody
	@RequestMapping(value = "/searchQuestionInfo", method = {RequestMethod.POST})
    public AigoQuestionEntity searchQuestionInfo (HttpServletRequest request, HttpServletResponse response, Locale localeParam, AigoQuestionEntity aigoQuestionEntity) throws Exception {
    	
    	String fullName = getFunction();
		logStart(fullName);
    	
		String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		plusActiveRecord dbEntity = new plusActiveRecord(functionName, request);
		
		AigoQuestionEntity questionData = aigoQuestionService.getQuestionInfo(dbEntity, aigoQuestionEntity);
		
		logEnd(fullName);
		return questionData;
    }
    
    
    /**
     * @ClassName	: AjaxAigoQuestionAdminController.java
     * @Method		: commentaryExcute
     * @Date		: 2021. 1. 10. 
     * @author		: dev.yklee
     * @Description	: 해설 등록/수정 처리
     */
    @RequestMapping(value =  "/commentaryExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity commentaryExcute (@ModelAttribute AigoCommentaryEntity aigoCommentaryEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
    	
        String fullName = getFunction();
        logStart(fullName);
        Debug.log(fileList);
        
        if(!StringUtils.isEmpty(fileList)){
        	if(fileList.length() > 10){
        		Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<SiteAttachFileEntity>>(){}.getType();
                List<SiteAttachFileEntity> fileArrayList =gson.fromJson(fileList,listType);
                Debug.log(fileArrayList);
                for( SiteAttachFileEntity bbsAttachFileEntity:fileArrayList){
                    if(!StringUtils.isEmpty(bbsAttachFileEntity.getDelSafSeq())){
                        siteFileUploadService.deleteFile(bbsAttachFileEntity.getDelSafSeq());
                    }
                }
            }
        }
        
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        db.from("cb_aigo_commentary");
        Integer insertID = aigoCommentaryService.setCommentaryExcute(db, aigoCommentaryEntity);

         MultiValueMap<String, MultipartFile> fileuploads = request.getMultiFileMap();
        Iterator<String> iterator = fileuploads.keySet().iterator();

        while(iterator.hasNext()) {

            String key = iterator.next();
            LinkedList<MultipartFile> df = (LinkedList<MultipartFile>) fileuploads.get(key);

            MultipartFile fileInfo = (MultipartFile) df.getFirst();
            if (fileInfo.getSize() > 0) {
                siteFileUploadService.uploadFile(fileInfo, 0, "/CMTR", "CMTR", insertID, key);
            }
        }
        
        Debug.log("Debug.log(db.flag);++asdfa+++++++"+db.flag);
        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
	}
}