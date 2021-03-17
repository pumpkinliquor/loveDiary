package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.AigoConceptEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.AigoConceptService;
import com.plushih.services.front.AigoConceptService;
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
public class AjaxAigoConceptAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoConceptAdminController.class );


    @Autowired
    private AigoConceptService conceptService;

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
    @RequestMapping(value =  "/conceptList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = conceptService.getconceptList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id,sub.sub_name,acv.acv_name");
        dbEntity.from("cb_aigo_concept bb");
        dbEntity.join("cb_aigo_subject sub","bb.sub_id = sub.sub_id","left");
        dbEntity.join("cb_aigo_achievement acv","bb.acv_id = acv.acv_id","left");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");


        if(!StringUtils.isEmpty(dbEntity.input.get_post("searchString"))){
            dbEntity.like("bb.cpt_name",dbEntity.input.get_post("searchString"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("subId"))){
            dbEntity.where("bb.sub_id",dbEntity.input.get_post("subId"));
//            dbEntity.like("bb.sub_id",dbEntity.input.get_post("subId"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("acvId"))){
        	dbEntity.where("bb.acv_id",dbEntity.input.get_post("acvId"));
//            dbEntity.like("bb.acv_id",dbEntity.input.get_post("acvId"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("useYn"))){
        	dbEntity.where("bb.use_yn",dbEntity.input.get_post("useYn"));
//            dbEntity.like("bb.use_yn",dbEntity.input.get_post("useYn"));
        }
        //dbEntity.order("reg_date","desc");
        dbEntity.order("bb.cpt_id desc");

        CommonResultEntity res = new CommonResultEntity();
        List<AigoConceptEntity> dataList = null;
        try {
            dataHashList  = commonService.getList(dbEntity);

            int cnount = commonService.getCount(dbEntity);
            res.setDraw(dbEntity.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);

            
            Debug.log(dataHashList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logEnd(fullName);
        return res;
    }

    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/cptCheck", method = {RequestMethod.POST})
    public @ResponseBody String acvCheck (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = subjectService.getsubjectList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id,sub.sub_name,acv.acv_name");
        dbEntity.from("cb_aigo_concept bb");
        dbEntity.join("cb_aigo_subject sub","bb.sub_id = sub.sub_id","left");
        dbEntity.join("cb_aigo_achievement acv","bb.acv_id = acv.acv_id","left");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");


        if(!StringUtils.isEmpty(dbEntity.input.get_post("cptName"))){
            dbEntity.where("bb.cpt_name",dbEntity.input.get_post("cptName"));
        }
        dbEntity.where("bb.sub_id",dbEntity.input.get_post("subId"));
        dbEntity.where("bb.acv_id",dbEntity.input.get_post("acvId"));

        dbEntity.not("cpt_id",dbEntity.input.get_post("cptId"));
        //dbEntity.order("reg_date","desc");


        String res = "";
        try {

            int cnount = commonService.getCount(dbEntity);
            if (cnount > 0) {
                res = "개념요소명이 중복입니다.";
            } else {
//                dbEntity.clearWhere();
//
//                dbEntity.where("bb.sub_id", dbEntity.input.get_post("subId"));
//                dbEntity.where("bb.acv_id", dbEntity.input.get_post("acvId"));
//
//                dbEntity.not("cpt_id", dbEntity.input.get_post("cptId"));
//                cnount = commonService.getCount(dbEntity);
//                if (cnount > 0) {
//                	System.out.println(cnount + " <<<<>>>> " + "여기 ?");
//                    res = "개념요소가 중복입니다.";
//                } else {

                    res = "true";
//                }
            }


            Debug.log(dataHashList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logEnd(fullName);
        return res;
    }

    /**
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/conceptDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "delconceptSeqs", defaultValue = "") String delconceptSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(delconceptSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(delconceptSeqs)) {

            String[] stri = delconceptSeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"concept");
            }
            db.where_in("cpt_id",delconceptSeqs.split(","));
            db.from("cb_aigo_concept");
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
    @RequestMapping(value =  "/conceptExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@ModelAttribute AigoConceptEntity aigoConceptEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

//        Cache cache = new Cache();
//        String rules = cache.get_cache("concept.bbs");
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
        String functionName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        plusActiveRecord db = new plusActiveRecord(functionName, request);
        Integer insertID = conceptService.setConceptExcute(db, aigoConceptEntity);
        //commonResultEntity.setResultList(businessInfoList);
        Debug.log("Debug.log(db.flag);+++++++++" + db.flag);

        MultiValueMap<String, MultipartFile> fileuploads = request.getMultiFileMap();
        Iterator<String> iterator = fileuploads.keySet().iterator();

        while (iterator.hasNext()) {

            String key = iterator.next();
            LinkedList<MultipartFile> df = (LinkedList<MultipartFile>) fileuploads.get(key);

            MultipartFile fileInfo = (MultipartFile) df.getFirst();
            if (fileInfo.getSize() > 0) {
                siteFileUploadService.uploadFile(fileInfo, 0, "/CPT", "CPT", insertID, key);
            }
        }


        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
    }



}