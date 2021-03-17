package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.AigoBannerEntity;
import com.plushih.entities.AigoSubjectEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.AigoLevelService;
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
public class AjaxAigoBannerAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoBannerAdminController.class );


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
    @RequestMapping(value =  "/bannerList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bannerList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        //commonResultEntity = bannerService.getbannerList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        db.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id");
        db.from("cb_aigo_banner bb");
        db.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        db.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");

        if(!StringUtils.isEmpty(db.input.get_post("searchString"))&&!StringUtils.isEmpty(db.input.get_post("searchType"))){
            db.like(db.input.get_post("searchType"),db.input.get_post("searchString"));
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

            for (Map<String, Object> row : dataHashList) {
                //bbsEntity.put()
                db.clearQuery();
                db.select("*");
                db.where("seq", String.valueOf(row.get("banId")));
                db.where("saf_bbs", "BANNER");
                db.from("plus_site_attach_file");
                db.order("saf_seq asc");
                db._limit = null;
                List<Map<String, Object>> getFileList = commonService.getList(db);
                Map<String, Object> fileMap = new HashMap<String, Object>();
                for(Map<String, Object> filerow:getFileList ){
                    fileMap.put(String.valueOf(filerow.get("safCode")),filerow);
                }
                row.put("fileMap",fileMap);
                //row.put("fileList", getFileList);
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
    @RequestMapping(value =  "/bannerCheck", method = {RequestMethod.POST})
    public @ResponseBody String bannerCheck (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = subjectService.getsubjectList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id");
        dbEntity.from("cb_aigo_banner bb");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");


        if(!StringUtils.isEmpty(dbEntity.input.get_post("levName"))){
            dbEntity.like("ban_name",dbEntity.input.get_post("levName"));
        }
        dbEntity.not("ban_id",dbEntity.input.get_post("levId"));
        //dbEntity.order("reg_date","desc");


        String res = "";
        List<AigoSubjectEntity> dataList = null;
        try {

            int cnount = commonService.getCount(dbEntity);
            if(cnount>0){
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
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/bannerDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bannerDelete (@RequestParam(required = false, value = "delSeqs", defaultValue = "") String delSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(delSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(delSeqs)) {

            String[] stri = delSeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"banner");
            }
            db.where_in("ban_id",delSeqs.split(","));
            db.from("cb_aigo_banner");
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
    @RequestMapping(value =  "/bannerExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bannerExcute (@ModelAttribute AigoBannerEntity aigoBannerEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);


        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();
        plusActiveRecord db =new plusActiveRecord(fullName,request);
        db.from("cb_aigo_banner");
        try {
            commonService._INDB(db);
            db.checkInsertUpdate();

            Debug.log("db.flag.=="+db.flag);
            if(db.flag.equals(plusQueryBuilder.queryType.INSERT)){
                commonService.setInsert(db);

                //Debug.log((new Gson()).toJson(db));
            }
            else if(db.flag.equals(plusQueryBuilder.queryType.UPDATE)){
                db.insert_id = aigoBannerEntity.getBanId();//bbsEntity.getBbSeq();
                commonService.setUpdate(db);

            }
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
            MultiValueMap<String, MultipartFile> fileuploads = request.getMultiFileMap();
            Iterator<String> iterator = fileuploads.keySet().iterator();

            while(iterator.hasNext()) {

                String key = iterator.next();
                LinkedList<MultipartFile> df = (LinkedList<MultipartFile>) fileuploads.get(key);

                MultipartFile fileInfo = (MultipartFile) df.getFirst();
                if (fileInfo.getSize() > 0) {
                    siteFileUploadService.uploadFile(fileInfo, 0, "/BANNER", "BANNER", db.insert_id, key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Debug.log("FLAG = "+db.flag+" ::::insert_id=="+db.insert_id+"::::::::::::");

        logEnd(fullName);
        return db.getOut();
    }



}