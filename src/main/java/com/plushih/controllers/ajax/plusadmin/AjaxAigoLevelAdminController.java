package com.plushih.controllers.ajax.plusadmin;


import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.AigoLevelEntity;
import com.plushih.entities.AigoSubjectEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.AigoLevelService;
import com.plushih.services.front.SiteFileUploadService;
import com.plushih.services.front.AigoSubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * hsk3807
 */
@Controller
@RequestMapping("plusadmin/ajax/aigo")
public class AjaxAigoLevelAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoLevelAdminController.class );


    @Autowired
    private AigoLevelService levelService;

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
    @RequestMapping(value =  "/levelList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        //commonResultEntity = levelService.getlevelList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        db.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id");
        db.from("cb_aigo_level bb");
        db.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        db.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");

        if(!StringUtils.isEmpty(db.input.getSearchString())){
            db.like("lev_name",db.input.getSearchString());
        }
        db.order("bb.reg_sysdate","desc");

        CommonResultEntity res = new CommonResultEntity();
        List<AigoSubjectEntity> dataList = null;
        try {
            dataHashList  = commonService.getList(db);

            int cnount = commonService.getCount(db);
            res.setDraw(db.draw);
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
    @RequestMapping(value =  "/levelNameCheck", method = {RequestMethod.POST})
    public @ResponseBody String levelCheck (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = subjectService.getsubjectList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id");
        dbEntity.from("cb_aigo_level bb");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");


        if(!StringUtils.isEmpty(dbEntity.input.get_post("levName"))){
            dbEntity.where("lev_name",dbEntity.input.get_post("levName"));
        }
        dbEntity.not("lev_id",dbEntity.input.get_post("levId"));
        //dbEntity.order("reg_date","desc");


        String res = "";
        List<AigoSubjectEntity> dataList = null;
        try {

            int cnount = commonService.getCount(dbEntity);
            if (cnount > 0) {
                res = "레벨명이 중복됩니다.";
            } else {

                res = "true";
            }


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
    @RequestMapping(value =  "/levelValueCheck", method = {RequestMethod.POST})
    public @ResponseBody String levelValueCheck (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = subjectService.getsubjectList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id");
        dbEntity.from("cb_aigo_level bb");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");


        if(!StringUtils.isEmpty(dbEntity.input.get_post("levValue"))){
            dbEntity.where("lev_name",dbEntity.input.get_post("levValue"));
        }
        dbEntity.not("lev_id",dbEntity.input.get_post("levId"));
        //dbEntity.order("reg_date","desc");


        String res = "";
        List<AigoSubjectEntity> dataList = null;
        try {

            int cnount = commonService.getCount(dbEntity);
            if (cnount > 0) {
                res = "레벨이 중복됩니다.";
            } else {
                res = "true";
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
    @RequestMapping(value =  "/levelDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "dellevelSeqs", defaultValue = "") String dellevelSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(dellevelSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(dellevelSeqs)) {

            String[] stri = dellevelSeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"level");
            }
            db.where_in("lev_id",dellevelSeqs.split(","));
            db.from("cb_aigo_level");
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
    @RequestMapping(value =  "/levelExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@ModelAttribute AigoLevelEntity aigoLevelEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);


        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();
        plusActiveRecord db =new plusActiveRecord(fullName,request);
        db.from("cb_aigo_level");
        try {
            commonService._INDB(db);
            db.checkInsertUpdate();

            Debug.log("db.flag.=="+db.flag);
            if(db.flag.equals(plusQueryBuilder.queryType.INSERT)){
                commonService.setInsert(db);

                db.where("lev_id",String.valueOf(db.insert_id));
                db.add("lev_key","LEV"+StringUtils.zeroFill(String.valueOf(db.insert_id),5));
                commonService.setUpdate(db);
                //Debug.log((new Gson()).toJson(db));
            }
            else if(db.flag.equals(plusQueryBuilder.queryType.UPDATE)){
                //db.insert_id = aigoSubjectEntity.getSubId();//bbsEntity.getBbSeq();
                db.add("lev_key","LEV"+StringUtils.zeroFill(String.valueOf(aigoLevelEntity.getLevId()),5));
                commonService.setUpdate(db);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Debug.log("FLAG = "+db.flag+" ::::insert_id=="+db.insert_id+"::::::::::::");

        logEnd(fullName);
        return db.getOut();
    }



}