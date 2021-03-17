package com.plushih.controllers.ajax.plusadmin;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.DateUtils;
import com.plushih.common.utils.HashUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.ajax.AjaxCodeController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserGroupEntity;
import com.plushih.entities.UserMasterEntity;
import com.plushih.services.admin.AdminUsersService;
import com.plushih.services.ci.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("plusadmin/ajax/users")
public class AjaxUsersAdminController extends AjaxCodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxUsersAdminController.class );


    @Autowired
    private AdminUsersService adminUsersService;


    @Autowired
    private CommonService commonService;


    /**
     * 사용자관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/useraction", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity useraction (UserMasterEntity userMasterEntity,HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        db.where("um_seq",db.input.post("umSeq"));
        db.from("plus_user_master");
        Map<String,Object> row = commonService.getRow(db);
        if(row.size()>0){

            String umPw = (String)row.get("umPw");

            db.clearQuery();
            if(HashUtils.encryptSHA256(db.input.post("now_pass")).equals(umPw)){
                db.add("um_pw",HashUtils.encryptSHA256(db.input.post("new_pass")));
                db.where("um_seq",db.input.post("umSeq"));
                db.from("plus_user_master");
                commonService.setUpdate(db);

                db.flag= plusQueryBuilder.inType.UPDATE;
                commonResultEntity = db.getOut();
            }
        } else {
            db.flag= plusQueryBuilder.inType.ERR;
            commonResultEntity = db.getOut();
        }
        //Integer umSeq = adminUsersService.setUserExcute(db,userMasterEntity);

        CoreController.logEnd(fullName);
        return commonResultEntity;
    }


    /**
     * 사용자관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/userList", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity userList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        //List<UserMasterEntity> userList = adminUsersService.getUserMasterList(db,new UserMasterEntity());
        //commonService.getList(db);
        CommonResultEntity res = new CommonResultEntity();
        db.select("a.um_seq"
        +" , a.bi_seq"
        +" , a.ug_seq"
        +" , a.ab_seq"
        +" , a.um_id"
        +" , a.um_name"
        +" , a.um_hp"
        +" , a.um_tel"
        +" , a.um_email"
        +" , a.um_um_addr"
        +" , a.um_zipcode"
        +" , a.um_company"
        +" , a.um_company_sub"
        +" , a.um_sort"
        +" , a.um_jichek"
        +" , a.um_jicgup"
        +" , a.um_work"
        +" , a.um_type"
        +" , a.um_in_date"
        +" , a.um_out_date"
        +" , a.um_img"
        +" , a.um_etc"
        +" , a.um_step"
        +" , a.reg_date "
        +" ,(case when b.bi_name is null then '미정' else b.bi_name end) as   bi_name"
        +" ,(case when d.ug_name is null then '미정' else d.ug_name end) as   ug_name");
        db.from("plus_user_master a");
        db.join("plus_business_info b","a.bi_seq=b.bi_seq","left");
//        db.join("plus_assets_building c","a.ab_seq=c.ab_seq","left");
        db.join("plus_user_group d","a.ug_seq=d.ug_seq","left");
        db.order("a.um_seq desc");

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        try {
            dataHashList = commonService.getList(db);
            int cnount = commonService.getCount(db);
            res.setDraw(db.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //commonResultEntity.setResultList(userList);
        CoreController.logEnd(fullName);
        return res;
    }


    /**
     * 사용자그룹관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/usergrouplist", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity userGroup (UserGroupEntity userGroupEntity, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
//        List<UserGroupEntity> userList = adminUsersService.getUserGroupList(db,userGroupEntity);
//        commonResultEntity.setResultList(userList);
//        CoreController.logEnd(fullName);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        db.select("bb.*");
        db.from("plus_user_group bb");
        //db.where("rc_bbs");
        if(!StringUtils.isEmpty(db.input.getSearchString())){
            db.like("ug_name",db.input.getSearchString());
        }
        db.order("ug_order","desc");
        db.limit(1000);

        CommonResultEntity res = new CommonResultEntity();

        try {
            dataHashList = commonService.getList(db);

            int cnount = commonService.getCount(db);
            res.setDraw(db.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);
        } catch(Exception e){
            e.printStackTrace();
        }
        CoreController.logEnd(fullName);
        return res;
    }

    /**
     * 사용자그룹관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/userPermission", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity userPermission (UserGroupEntity userGroupEntity,HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
//        List<UserGroupEntity> userList = adminUsersService.getUserGroupList(db,userGroupEntity);
//        commonResultEntity.setResultList(userList);
//        CoreController.logEnd(fullName);
//        return commonResultEntity;

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        db.select("bb.*");
        db.from("plus_user_group bb");
        db._limit=null;
        //db.where("rc_bbs");
        if(!StringUtils.isEmpty(db.input.getSearchString())){
            db.like("ug_name",db.input.getSearchString());
        }
        db.order("ug_order","desc");

        CommonResultEntity res = new CommonResultEntity();

        try {
            dataHashList = commonService.getList(db);

            int cnount = commonService.getCount(db);
            res.setDraw(db.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);
        } catch(Exception e){
            e.printStackTrace();
        }
        CoreController.logEnd(fullName);
        return res;
    }


    /**
     * 사용자관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/userExcute", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity userExcute (UserMasterEntity userMasterEntity,HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        Integer umSeq = adminUsersService.setUserExcute(db,userMasterEntity);
        commonResultEntity = db.getOut();
        CoreController.logEnd(fullName);
        return commonResultEntity;
    }


    /**
     * 사용자그룹관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/userGroupExcute", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity userGroupExcute (UserGroupEntity userGroupEntity,HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        Integer ugSeq = adminUsersService.setUserGroupExcute(db,userGroupEntity);
        CoreController.logEnd(fullName);
        commonResultEntity = db.getOut();
        return commonResultEntity;
    }

    /**
     * 사용자그룹관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/userPermissionExcute", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity userPermissionExcute (UserGroupEntity userGroupEntity,HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);


        if(db.input.post("updateGroup")==null) return commonResultEntity;

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> userGroupList = mapper.readValue(db.input.post("updateGroup"), new TypeReference<List<HashMap<String,String>>>(){});
        HashMap<String,String> addRow=  new HashMap<String,String>();
        addRow.put("upd_date", DateUtils.getDate2String(new Date()));

        db.batchItem(userGroupList,addRow);
        adminUsersService.setUserPermissionExcute(db);
        commonResultEntity = db.getOut();
        CoreController.logEnd(fullName);
        return commonResultEntity;
    }
}