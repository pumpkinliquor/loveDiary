package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.*;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.BbsAttachFileEntity;
import com.plushih.entities.BbsEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.BbsService;
import com.plushih.services.front.FileUploadService;
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
@RequestMapping("plusadmin/ajax/bbs")
public class AjaxBbsAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxBbsAdminController.class );

    @Autowired
    private BbsService bbsService;

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private CommonService commonService;

    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/bbsList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);


        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);


        commonResultEntity = bbsService.getBbsList(db);

//        LOGGER.info("##########ㅣ햬ㅒ햬햬헇햬########");


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
    @RequestMapping(value =  "/bbsDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "delBbsSeqs", defaultValue = "") String delBbsSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(delBbsSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(delBbsSeqs)) {

            String[] stri = delBbsSeqs.split(",");
            for(String bbSeq :stri){
                fileUploadService.deleteFile(bbSeq,"BB");
            }
            db.where_in("bb_seq",delBbsSeqs.split(","));
            db.from("plus_bbs");
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
    @RequestMapping(value =  "/bbsExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@ModelAttribute BbsEntity bbsEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

//        Cache cache = new Cache();
//        String rules = cache.get_cache("Bbs.bbs");
//
//        Debug.log("===rules==="+rules);

        Debug.log(fileList);

        if(!StringUtils.isEmpty(fileList)){
            if(fileList.length()>10){
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<BbsAttachFileEntity>>(){}.getType();
                List<BbsAttachFileEntity> fileArrayList =gson.fromJson(fileList,listType);
                Debug.log(fileArrayList);
                for( BbsAttachFileEntity bbsAttachFileEntity:fileArrayList){
                    if(!StringUtils.isEmpty(bbsAttachFileEntity.getDelBafSeq())){
                        fileUploadService.deleteFile(bbsAttachFileEntity.getDelBafSeq());
                    }
                }
            }
        }

        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        Integer insertID = bbsService.setBbsExcute(db,bbsEntity);
        //commonResultEntity.setResultList(businessInfoList);
        Debug.log("Debug.log(db.flag);+++++++++"+db.flag);

        MultiValueMap<String, MultipartFile> fileuploads = request.getMultiFileMap();
        Iterator<String> iterator = fileuploads.keySet().iterator();

        while(iterator.hasNext()) {

            String key = iterator.next();
            LinkedList<MultipartFile> df = (LinkedList<MultipartFile>) fileuploads.get(key);

            MultipartFile fileInfo = (MultipartFile) df.getFirst();
            if (fileInfo.getSize() > 0) {
                fileUploadService.uploadFile(fileInfo, 0, "/"+bbsEntity.getBbBbs(), bbsEntity.getBbBbs(), insertID, key);
            }
        }
        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
    }



}