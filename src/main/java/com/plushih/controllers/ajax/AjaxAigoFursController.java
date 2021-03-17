package com.plushih.controllers.ajax;


import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.FieldInfoEntity;
import com.plushih.entities.UserMemberEntity;
import com.plushih.services.ci.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * hsk3807
 */
@Controller
@RequestMapping("front/ajax/aigo/furs")
public class AjaxAigoFursController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoFursController.class );

    @Autowired
    private CommonService commonService;

    @Autowired
    private FrontUserService frontUserService;
    
    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/step01", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity step01 (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        HttpSession session = request.getSession(true);

        //session.setAttribute(LoginSession.TEMP_ID, db.insert_id);
        session.removeAttribute(LoginSession.TEMP_ID);
        Debug.log("세션초기화 함!!");

        commonResultEntity.getResultList();

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
    @RequestMapping(value =  "/step02", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity step02 (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        HttpSession session = request.getSession();

        String tempId = LoginSession.getTempId(request.getSession());

        if(StringUtils.isEmpty(tempId) || tempId.equals("0")){

            db.from("cb_aigo_temp_info");
            db.add("temp_class",db.input.get_post("temp_class"));
            db.add("reg_date",db.getYmdHis());
            commonService.setInsert(db);
            db.flag= plusQueryBuilder.inType.INSERT;
            tempId = String.valueOf(db.insert_id);
            if(db.insert_id==null){
                db.flag= plusQueryBuilder.inType.ERR;
            }
            else if(db.insert_id==0) {
                db.flag = plusQueryBuilder.inType.ERR;
            }

        }
        if(!StringUtils.isEmpty(tempId) || !tempId.equals("0")){
            session.setAttribute(LoginSession.TEMP_ID, tempId);
            session.setAttribute(LoginSession.TEMP_CLASS, db.input.get_post("temp_class") );
            String memId = LoginSession.getSeq(request.getSession());
            if(!StringUtils.isEmpty(memId)) {
            	UserMemberEntity user = new UserMemberEntity();
            	user.setMemId(NumberUtils.stringToInt(memId));
            	user.setMemTempId(tempId);
            	frontUserService.updateUserInfoTempId(user);
            }
        }
        logEnd(fullName);
        commonResultEntity= db.getOut();
        return commonResultEntity;
    }
    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/step03", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity step03 (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        String tempId = LoginSession.getTempId(request.getSession());
        if(StringUtils.isEmpty(tempId)){
             db.flag= plusQueryBuilder.inType.ERR;
             return db.getOut();
        }

        try {
            db.from("cb_aigo_temp_info");
            db.add("temp_grade",db.input.get_post("temp_grade"));
            db.where("temp_id",tempId);
            commonService.setUpdate(db);
            db.flag= plusQueryBuilder.inType.UPDATE;
            HttpSession session = request.getSession(true);
            session.setAttribute(LoginSession.TEMP_GRADE, db.input.get_post("temp_grade") );
        } catch (Exception e){
            e.printStackTrace();
        }

        logEnd(fullName);
        return db.getOut();
    }
    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/step04", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity step04 (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        String tempId = LoginSession.getTempId(request.getSession());
        if(StringUtils.isEmpty(tempId)){
             db.flag= plusQueryBuilder.inType.ERR;
             return db.getOut();
        }

        try {
            db.from("cb_aigo_temp_info");
            db.add("sub_id",db.input.get_post("sub_id"));
            db.where("temp_id",tempId);
            commonService.setUpdate(db);
            db.flag= plusQueryBuilder.inType.UPDATE;
            HttpSession session = request.getSession(true);
            session.setAttribute(LoginSession.TEMP_SUBJECT, db.input.get_post("sub_id") );
        } catch (Exception e){
            e.printStackTrace();
        }

        logEnd(fullName);
        return db.getOut();
    }
    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/step05", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity step05 (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        String tempId = LoginSession.getTempId(request.getSession());
        if(StringUtils.isEmpty(tempId)){
             db.flag= plusQueryBuilder.inType.ERR;
             return db.getOut();
        }

        try {
            db.from("cb_aigo_temp_info");
            db.add("qst_num",db.input.get_post("qst_num"));
            db.where("temp_id",tempId);
            commonService.setUpdate(db);
            db.flag= plusQueryBuilder.inType.UPDATE;
        } catch (Exception e){
            e.printStackTrace();
        }

        logEnd(fullName);
        return db.getOut();
    }
    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/step06", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity step06 (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        String tempId = LoginSession.getTempId(request.getSession());
        if(StringUtils.isEmpty(tempId)){
             db.flag= plusQueryBuilder.inType.ERR;
             return db.getOut();
        }
        try {
            db.from("cb_aigo_temp_info");
            db.add("my_style",db.input.get_post("my_style"));
            db.add("ai_ready","1");
            db.where("temp_id",tempId);
            commonService.setUpdate(db);
            db.flag= plusQueryBuilder.inType.UPDATE;
        } catch (Exception e){
            e.printStackTrace();
        }

        logEnd(fullName);
        return db.getOut();
    }
    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/checkState", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity checkState (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        String tempId = LoginSession.getTempId(request.getSession());
        if(StringUtils.isEmpty(tempId)){
             db.flag= plusQueryBuilder.inType.ERR;
             return db.getOut();
        }
        try {
            db.select("*");
            db.from("cb_aigo_temp_info");
            db.where("temp_id",tempId);
            commonResultEntity.setResultData(commonService.getRow(db));
            db.flag= plusQueryBuilder.inType.UPDATE;
        } catch (Exception e){
            e.printStackTrace();
        }

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
    @RequestMapping(value =  "/questionList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity questionList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity res = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        String tempId = LoginSession.getTempId(request.getSession());
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> dataFileList = new ArrayList<Map<String,Object>>();
        if(StringUtils.isEmpty(tempId)){
             db.flag= plusQueryBuilder.inType.ERR;
             return db.getOut();
        }
        try {
            db.select("bb.*,b.sub_name,c.lev_name,c.lev_value,d.acv_name,e.aca_name sub_aca_name");
            db.from("cb_aigo_question bb");
            db.join("cb_aigo_subject b","bb.sub_id=b.sub_id","left");
            db.join("cb_aigo_level c","bb.lev_id=c.lev_id","left");
            db.join("cb_aigo_achievement d","bb.acv_id = d.acv_id","left");
            db.join("cb_aigo_category e","bb.sub_aca_id = e.aca_id","left");
            db.where("bb.aca_id","1");
            db.order("RAND()");
            dataHashList  = commonService.getList(db);


            //String [] arrQstId =
            ArrayList seqs = new ArrayList<String>();
            for(Map<String,Object> row : dataHashList){
                seqs.add(String.valueOf(row.get("qstId")));

            }



            if(seqs.size()>0){

                db.clearQuery();
                db.select("*");
                db.from("plus_site_attach_file");
                db.where("saf_bbs","QST");
                db.where_in("seq",String.join(",",seqs).split(","));

                dataFileList  = commonService.getList(db);
                for(Map<String,Object> row : dataHashList){
                    for(Map<String,Object> row2 :dataFileList){

                        if(String.valueOf(row.get("qstId")).equals(String.valueOf(row2.get("seq")))){
                            if(!row.containsKey("fileList")){
                                row.put("fileList",row2);
                            }

                        }
                    }
                }
                //res.add("fileList",dataFileList);
            }

            Debug.log("tempId====tempId"+tempId);
            Debug.log("tempId====tempId"+tempId);
            res.setResultList(dataHashList);

        } catch (Exception e){
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
    @RequestMapping(value =  "/completeList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity completeList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        String tempId = LoginSession.getTempId(request.getSession());
        CommonResultEntity res = new CommonResultEntity();
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();

        if(StringUtils.isEmpty(tempId)){
             db.flag= plusQueryBuilder.inType.ERR;
             return db.getOut();
        }
        try {
            db.select("bb.*,b.sub_name,c.lev_name,c.lev_value,d.acv_name,e.aca_name sub_aca_name,m.*");
            db.from("cb_aigo_question bb");
            db.join("cb_aigo_subject b","bb.sub_id=b.sub_id","left");
            db.join("cb_aigo_level c","bb.lev_id=c.lev_id","left");
            db.join("cb_aigo_achievement d","bb.acv_id = d.acv_id","left");
            db.join("cb_aigo_category e","bb.sub_aca_id = e.aca_id","left");
            db.join("cb_aigo_temp_qst m","bb.qst_id = m.qst_id and m.temp_id = "+tempId,"inner");
            db.where("bb.aca_id","1");
            db.order("tqst_id","desc");
            db.limit(15);
            dataHashList  = commonService.getList(db);




            res.setResultList(dataHashList);

        } catch (Exception e){
            e.printStackTrace();
        }

        logEnd(fullName);
        return res;
    }



//     temp_id
//    , temp_class
//    , temp_grade
//    , sub_id
//    , qst_num
//    , my_style
//    , join_type
//    , ai_ready
//    , temp_qst
//    , ai_complit
//    , use_yn
//    , reg_date


}