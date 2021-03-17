package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.UserMasterEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.UserMasterService;
import com.plushih.services.front.SiteFileUploadService;
import com.plushih.services.front.UserMasterService;
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
@RequestMapping("plusadmin/ajax/user")
public class AjaxUserMasterAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxUserMasterAdminController.class );


    @Autowired
    private UserMasterService userMasterService;

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
    @RequestMapping(value =  "/userList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (UserMasterEntity userMasterEntity,HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = userService.getuserList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id");
        dbEntity.from("plus_user_master bb");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");
        dbEntity.order("bb.um_seq desc");


//        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
//            dbEntity.like("um_name",dbEntity.input.getSearchString());
//        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("searchString"))){
            if(dbEntity.input.get_post("searchType").equals("umCompanySub")){
                dbEntity.like("bb.um_company_sub",dbEntity.input.get_post("searchString"));
            } else {
                dbEntity.like("bb.um_name",dbEntity.input.get_post("searchString"));
            }
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("useYn"))){
            dbEntity.where("bb.use_yn",dbEntity.input.get_post("useYn"));
        }
        //dbEntity.order("reg_date","desc");
        dbEntity.order("bb.um_seq desc");

        CommonResultEntity res = new CommonResultEntity();

        
        List<UserMasterEntity> dataList = null;
        try {
            dataHashList  = commonService.getList(dbEntity);
            for(Map <String,Object> map : dataHashList){
                map.remove("umPw");
            }

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
    @RequestMapping(value =  "/userDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "deluserSeqs", defaultValue = "") String deluserSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(deluserSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(deluserSeqs)) {

            String[] stri = deluserSeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"user");
            }
            db.where_in("um_seq",deluserSeqs.split(","));
            db.from("cb_aigo_user");
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
    @RequestMapping(value =  "/userExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@ModelAttribute UserMasterEntity userMasterEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

//        Cache cache = new Cache();
//        String rules = cache.get_cache("user.bbs");
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
        Integer insertID = userMasterService.setUserExcute(db,userMasterEntity);
        //commonResultEntity.setResultList(businessInfoList);
        Debug.log("Debug.log(db.flag);+++++++++"+db.flag);


        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
    }



    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/userMojinList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity userMoinList (UserMasterEntity userMasterEntity,HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = userService.getuserList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("a.*,q.qst_key,q.qst_name");
        dbEntity.from("cb_aigo_temp_qst a");
        dbEntity.join("cb_aigo_question q","a.qst_id= q.qst_id","left");
        dbEntity.join("cb_member m","a.temp_id=m.mem_temp_id","left");
        dbEntity.gt("q.qst_id","0");


        if(!StringUtils.isEmpty(dbEntity.input.get_post("memId"))){
            dbEntity.where("m.mem_id",dbEntity.input.get_post("memId"));
        }
        dbEntity.order("a.tqst_id","desc");

        CommonResultEntity res = new CommonResultEntity();


        List<UserMasterEntity> dataList = null;
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
    @RequestMapping(value =  "/userAnsList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity ansuserMoinList (UserMasterEntity userMasterEntity,HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = userService.getuserList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("a.*,q.qst_key,q.qst_value");
        dbEntity.from("cb_aigo_my_answer_list a");
        dbEntity.join("cb_aigo_question q","a.qst_id= q.qst_id","left");
        dbEntity.join("cb_member m","a.mem_id=m.mem_id","left");
        dbEntity.gt("q.qst_id","0");


        if(!StringUtils.isEmpty(dbEntity.input.get_post("memId"))){
            dbEntity.where("m.mem_id",dbEntity.input.get_post("memId"));
        }
        dbEntity.order("a.ans_id","desc");

        CommonResultEntity res = new CommonResultEntity();


        List<UserMasterEntity> dataList = null;
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



}