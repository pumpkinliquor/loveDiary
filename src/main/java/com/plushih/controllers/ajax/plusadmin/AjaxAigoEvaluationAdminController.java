package com.plushih.controllers.ajax.plusadmin;


import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.AigoEvaluationEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.AigoEvaluationService;
import com.plushih.services.front.SiteFileUploadService;
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
public class AjaxAigoEvaluationAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoEvaluationAdminController.class );


    @Autowired
    private AigoEvaluationService evaluationService;

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
    @RequestMapping(value =  "/evaluationList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        //commonResultEntity = evaluationService.getevaluationList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        db.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id,c.lev_name");
        db.from("cb_aigo_evaluation bb");
        db.join("cb_aigo_level c","bb.lev_id=c.lev_id","left");
        db.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        db.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");

        if(!StringUtils.isEmpty(db.input.getSearchString())){
            db.like("lev_name",db.input.getSearchString());
        }
        db.order("bb.reg_sysdate","desc");

        CommonResultEntity res = new CommonResultEntity();
        List<AigoEvaluationEntity> dataList = null;
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
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/evaluationDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "delevaluationSeqs", defaultValue = "") String delevaluationSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(delevaluationSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(delevaluationSeqs)) {

            String[] stri = delevaluationSeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"evaluation");
            }
            db.where_in("evt_id",delevaluationSeqs.split(","));
            db.from("cb_aigo_evaluation");
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
    @RequestMapping(value =  "/evaluationExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@ModelAttribute AigoEvaluationEntity aigoEvaluationEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);


        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();
        plusActiveRecord db =new plusActiveRecord(fullName,request);
        db.from("cb_aigo_evaluation");
        try {
            commonService._INDB(db);
            db.checkInsertUpdate();

            Debug.log("db.flag.=="+db.flag);
            if(db.flag.equals(plusQueryBuilder.queryType.INSERT)){
                commonService.setInsert(db);
                //Debug.log((new Gson()).toJson(db));
            }
            else if(db.flag.equals(plusQueryBuilder.queryType.UPDATE)){
                commonService.setUpdate(db);
                Debug.log("db.input.get(\"bbSeq\")=="+db.input.get("bbSeq"));
//                if(db.input.get("bbSeq")!=null){
//
//                }
                db.insert_id = aigoEvaluationEntity.getEvtId();//bbsEntity.getBbSeq();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Debug.log("FLAG = "+db.flag+" ::::insert_id=="+db.insert_id+"::::::::::::");

        logEnd(fullName);
        return db.getOut();
    }



}