package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.AigoNotionEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.AigoNotionService;
import com.plushih.services.front.AigoNotionService;
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
public class AjaxAigoNotionAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoNotionAdminController.class );


    @Autowired
    private AigoNotionService notionService;

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
    @RequestMapping(value =  "/notionList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = notionService.getnotionList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id,sub.sub_name,acv.acv_name,cpt.cpt_name");
        dbEntity.from("cb_aigo_notion bb");
        dbEntity.join("cb_aigo_subject sub","bb.sub_id = sub.sub_id","left");
        dbEntity.join("cb_aigo_achievement acv","bb.acv_id = acv.acv_id","left");
        dbEntity.join("cb_aigo_concept cpt","bb.cpt_id = cpt.cpt_id","left");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");


        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("not_name",dbEntity.input.getSearchString());
        }

        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("qst_id",dbEntity.input.getSearchString());
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("sdate")) && !StringUtils.isEmpty(dbEntity.input.get_post("edate"))){
             dbEntity.gteq("substr(bb.reg_date,1,10)",dbEntity.input.get_post("sdate"));
             dbEntity.lteq("substr(bb.reg_date,1,10)",dbEntity.input.get_post("edate"));
        } else if(!StringUtils.isEmpty(dbEntity.input.get_post("sdate"))){
            dbEntity.gteq("substr(bb.reg_date,1,10)",dbEntity.input.get_post("sdate"));
        } else if(!StringUtils.isEmpty(dbEntity.input.get_post("edate"))){
            dbEntity.lteq("substr(bb.reg_date,1,10)",dbEntity.input.get_post("edate"));

        }

        if(!StringUtils.isEmpty(dbEntity.input.get_post("subId"))){
            dbEntity.where("bb.sub_id",dbEntity.input.get_post("subId"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("acvId"))){
            dbEntity.where("bb.acv_id",dbEntity.input.get_post("acvId"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("levId"))){
            dbEntity.where("bb.cptid",dbEntity.input.get_post("cptId"));
        }
//        if(!StringUtils.isEmpty(dbEntity.input.get_post("unitId"))){
//            dbEntity.where("bb.unit_id",dbEntity.input.get_post("unitId"));
//        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("searchUseYn"))){
            dbEntity.like("bb.use_yn",dbEntity.input.get_post("searchUseYn"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("searchType"))){
            dbEntity.like(dbEntity.input.get_post("searchType"),dbEntity.input.get_post("searchString"));
        }
        //dbEntity.order("reg_date","desc");
        dbEntity.order("bb.not_id desc");

        CommonResultEntity res = new CommonResultEntity();
        List<AigoNotionEntity> dataList = null;
        try {
            dataHashList  = commonService.getList(dbEntity);

            int cnount = commonService.getCount(dbEntity);
            for (Map<String, Object> row : dataHashList) {
                //bbsEntity.put()
                dbEntity.clearQuery();
                dbEntity.select("*");
                dbEntity.where("seq", String.valueOf(row.get("notId")));
                dbEntity.where("saf_bbs", "NOT");
                dbEntity.from("plus_site_attach_file");
                dbEntity.order("saf_seq asc");
                dbEntity._limit = null;
                List<Map<String, Object>> getFileList = commonService.getList(dbEntity);
                Map<String, Object> fileMap = new HashMap<String, Object>();
                for(Map<String, Object> filerow:getFileList ){
                    fileMap.put(String.valueOf(filerow.get("safCode")),filerow);
                }
                row.put("fileMap",fileMap);
                row.put("fileList", getFileList);
            }
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
    @RequestMapping(value =  "/notionDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "delnotionSeqs", defaultValue = "") String delnotionSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(delnotionSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(delnotionSeqs)) {

            String[] stri = delnotionSeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"notion");
            }
            db.where_in("not_id",delnotionSeqs.split(","));
            db.from("cb_aigo_notion");
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
    @RequestMapping(value =  "/notionExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@ModelAttribute AigoNotionEntity aigoNotionEntity, @RequestParam(required = false, value = "fileLists", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

//        Cache cache = new Cache();
//        String rules = cache.get_cache("notion.bbs");
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
        Integer insertID = notionService.setNotionExcute(db,aigoNotionEntity);
        //commonResultEntity.setResultList(businessInfoList);
        Debug.log("Debug.log(db.flag);+++++++++"+db.flag);

        MultiValueMap<String, MultipartFile> fileuploads = request.getMultiFileMap();
        Iterator<String> iterator = fileuploads.keySet().iterator();

        while(iterator.hasNext()) {

            String key = iterator.next();
            LinkedList<MultipartFile> df = (LinkedList<MultipartFile>) fileuploads.get(key);

            MultipartFile fileInfo = (MultipartFile) df.getFirst();
            if (fileInfo.getSize() > 0) {
                siteFileUploadService.uploadFile(fileInfo, 0, "/NOT", "NOT", insertID, key);
            }
        }


        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
    }



}