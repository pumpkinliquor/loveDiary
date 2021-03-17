package com.plushih.controllers.ajax;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.*;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * hsk3807
 */
@Controller
@RequestMapping("front/ajax/bbs")
public class AjaxBbsController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxBbsController.class );

    @Autowired
    private BbsService bbsService;
    @Autowired
    private CommonService commonService;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/bbsList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity noticeList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        db.where("bb_open","Y");
        commonResultEntity =  bbsService.getBbsList(db);
        commonResultEntity.getResultList();

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
    public @ResponseBody CommonResultEntity bbsExcute (BbsEntity bbsEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(fileList);

        if(!StringUtils.isEmpty(fileList)){
            if(fileList.length()>10){
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<BbsAttachFileEntity>>(){}.getType();
                List<BbsAttachFileEntity> fileArrayList =gson.fromJson(fileList,listType);
                Debug.log(fileArrayList);
            }
        }



        Cache cache = new Cache();
        String rules = cache.get_cache("Bbs.bbs");


        Debug.log("===rules==="+rules);
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
            MultipartFile df = (MultipartFile) fileuploads.get(key);

            if (df.getSize() > 0) {
                fileUploadService.uploadFile(df, 0, "/BB", "BB", insertID, key);
            }
        }

        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
    }



}