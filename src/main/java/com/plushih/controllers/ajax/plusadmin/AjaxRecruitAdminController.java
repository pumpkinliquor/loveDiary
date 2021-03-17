package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.RecruitEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.RecruitService;
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
@RequestMapping("plusadmin/ajax/recruit")
public class AjaxRecruitAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxRecruitAdminController.class );


    @Autowired
    private RecruitService recruitService;

    @Autowired
    private SiteFileUploadService siteFileUploadService;
    @Autowired
    private CommonService commonService;

    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/recruitList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        commonResultEntity = recruitService.getRecruitList(db);


        logEnd(fullName);
        return commonResultEntity;
    }

    /**
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/recruitDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "delrecruitSeqs", defaultValue = "") String delrecruitSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(delrecruitSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(delrecruitSeqs)) {

            String[] stri = delrecruitSeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"recruit");
            }
            db.where_in("rc_seq",delrecruitSeqs.split(","));
            db.from("plus_recruit");
            commonService.setDelete(db);


            db.flag= plusQueryBuilder.inType.DELETE;
            commonResultEntity= db.getOut();

        } else {
            db.flag= plusQueryBuilder.inType.ERR;
            commonResultEntity= db.getOut();
        }

        logEnd(fullName);
        return commonResultEntity;
    }

    /**
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/recruitExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@ModelAttribute RecruitEntity recruitEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

//        Cache cache = new Cache();
//        String rules = cache.get_cache("recruit.bbs");
//
//        Debug.log("===rules==="+rules);

        Debug.log(fileList);

        if(!StringUtils.isEmpty(fileList)){
            if(fileList.length()>10){
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
        Integer insertID = recruitService.setRecruitExcute(db,recruitEntity);
        //commonResultEntity.setResultList(businessInfoList);
        Debug.log("Debug.log(db.flag);+++++++++"+db.flag);

        MultiValueMap<String, MultipartFile> fileuploads = request.getMultiFileMap();
        Iterator<String> iterator = fileuploads.keySet().iterator();

        while(iterator.hasNext()) {

            String key = iterator.next();
            LinkedList<MultipartFile> df = (LinkedList<MultipartFile>) fileuploads.get(key);

            MultipartFile fileInfo = (MultipartFile) df.getFirst();
            if (fileInfo.getSize() > 0) {
                siteFileUploadService.uploadFile(fileInfo, 0, "/recruit", "RECRUIT", insertID, key);
            }
        }
        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
    }



}