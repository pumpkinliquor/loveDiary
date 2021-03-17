package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.AigoCategoryEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.AigoCategoryService;
import com.plushih.services.front.SiteFileUploadService;
import com.plushih.services.front.AigoCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * hsk3807
 */
@Controller
@RequestMapping("plusadmin/ajax/aigo")
public class AjaxAigoCategoryAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoCategoryAdminController.class );


    @Autowired
    private AigoCategoryService categoryService;

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
    @RequestMapping(value =  "/categoryList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity categoryList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = categoryService.getcategoryList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id,sub.aca_id sub_aca_id,sub.aca_name sub_aca_name,sub.aca_key sub_aca_key");
        dbEntity.from("cb_aigo_category bb");
        dbEntity.join("cb_aigo_category sub","sub.parent_aca_id = bb.aca_id and sub.parent_aca_id !=0 ","left");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");


        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("aca_name",dbEntity.input.getSearchString());
        }
        //dbEntity.order("reg_date","desc");
        dbEntity.order("bb.aca_id desc");

        CommonResultEntity res = new CommonResultEntity();
        List<AigoCategoryEntity> dataList = null;
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
    @RequestMapping(value =  "/categoryCheck", method = {RequestMethod.POST})
    public @ResponseBody String categoryCheck (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = categoryService.getcategoryList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id,sub.aca_id sub_aca_id,sub.aca_name sub_aca_name");
        dbEntity.from("cb_aigo_category bb");
        dbEntity.join("cb_aigo_category sub","sub.parent_aca_id = bb.aca_id and sub.parent_aca_id !=0 ","left");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");

        dbEntity.not("bb.aca_id",dbEntity.input.get_post("acaId"));
        if(!StringUtils.isEmpty(dbEntity.input.get_post("acaName"))){
            dbEntity.like("bb.aca_name",dbEntity.input.get_post("acaName"));
        }



        //dbEntity.order("reg_date","desc");
        dbEntity.order("bb.aca_id desc");

        String res = "";
        try {

            int cnount = commonService.getCount(dbEntity);
            if(cnount>0){
               res = "분류명이 중복됩니다.";
            } else {
                Boolean checkCount=false;
                if(!StringUtils.isEmpty(dbEntity.input.get_post("subData"))) {
                    String subData = dbEntity.input.get_post("subData");

                    if(subData.length()>10){

                        Gson gson = new Gson();
                        Type listType = new TypeToken<ArrayList<Map<String,String>>>(){}.getType();
                        List<Map<String,String>> categoryList =gson.fromJson(subData,listType);

                        for(Map<String,String> row : categoryList){

                            dbEntity.clearWhere();

                            if(row.get("subAcaId")==null){
                                row.put("subAcaId","0");
                            }
                            dbEntity.not("bb.aca_id",row.get("subAcaId"));
                            if(!StringUtils.isEmpty(row.get("subAcaName"))){
                                dbEntity.like("bb.aca_name",row.get("subAcaName"));
                            }
                            cnount = commonService.getCount(dbEntity);
                            if(cnount>0){
                                checkCount =true;
                                res = "2차분류명 ("+ row.get("subAcaName")+")이 중복됩니다.";
                            }

                        }
                    }

                }
                if(checkCount==true){

                } else {

                    res = "true";
                }
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
    @RequestMapping(value =  "/categorySubList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity categorySubList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = categoryService.getcategoryList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id");
        dbEntity.from("cb_aigo_category bb");
        dbEntity.where("parent_aca_id");

        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");



        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("aca_name",dbEntity.input.getSearchString());
        }
        //dbEntity.order("reg_date","desc");
        dbEntity.order("bb.aca_id desc");

        CommonResultEntity res = new CommonResultEntity();
        List<AigoCategoryEntity> dataList = null;
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
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/categoryDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "delcategorySeqs", defaultValue = "") String delcategorySeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(delcategorySeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(delcategorySeqs)) {

            String[] stri = delcategorySeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"category");
            }
            db.where_in("aca_id",delcategorySeqs.split(","));
            db.from("cb_aigo_category");
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
    @RequestMapping(value =  "/categoryExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@RequestParam(required = false, value = "dataJson", defaultValue = "[]") String dataJson, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        AigoCategoryEntity aigoCategoryEntity = new AigoCategoryEntity();


//        Cache cache = new Cache();
//        String rules = cache.get_cache("category.bbs");
//
//        Debug.log("===rules==="+rules);

        Debug.log(dataJson);



        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        aigoCategoryEntity.setAcaId(Integer.parseInt(String.valueOf(db.input.get_post("acaId"))));
        Integer insertID = categoryService.setCategoryExcute(db,aigoCategoryEntity);





        if(!StringUtils.isEmpty(dataJson)){
            if(dataJson.length()>10){
                //dataJson = dataJson.replace(":null",":0");
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<AigoCategoryEntity>>(){}.getType();
                List<AigoCategoryEntity> categoryList =gson.fromJson(dataJson,listType);

                for( AigoCategoryEntity categoryEntity:categoryList){
                    categoryEntity.setParentAcaId(insertID);

                    db.clearQuery();
                    db.from("cb_aigo_category");

                    if((categoryEntity.getAcaId()+"").equals("")){
                        categoryEntity.setAcaId(null);
                    }
                    if((categoryEntity.getAcaId()+"").equals("null")){
                        categoryEntity.setAcaId(null);
                    }

                    commonService._INDB(db);
                    db.add("parent_aca_id",insertID+"");
                    db.add("aca_name",categoryEntity.getAcaName());
                    db.add("aca_id",categoryEntity.getAcaId()+"");
                    db.add("use_yn",categoryEntity.getUseYn());
                    if(db.flag.equals(plusQueryBuilder.queryType.INSERT)){
                        commonService.setInsert(db);

                        db.add("aca_key","ACA"+StringUtils.zeroFill(String.valueOf(db.insert_id),5));
                        db.where("aca_id",String.valueOf(db.insert_id));
                        commonService.setUpdate(db);
                    } else {
                        if(categoryEntity.getAcaId()!=null){
                            db.where("aca_id",categoryEntity.getAcaId()+"");
                            db.add("aca_key","ACA"+StringUtils.zeroFill(String.valueOf(categoryEntity.getAcaId()),5));
                            commonService.setUpdate(db);
                        } else {
                            db._values.remove("aca_id");
                            commonService.setInsert(db);

                            db.add("aca_key","ACA"+StringUtils.zeroFill(String.valueOf(db.insert_id),5));
                            db.where("aca_id",String.valueOf(db.insert_id));
                            commonService.setUpdate(db);
                        }

                    }

                }
            }
        }
        //commonResultEntity.setResultList(businessInfoList);
        Debug.log("Debug.log(db.flag);+++++++++"+db.flag);


        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
    }



}