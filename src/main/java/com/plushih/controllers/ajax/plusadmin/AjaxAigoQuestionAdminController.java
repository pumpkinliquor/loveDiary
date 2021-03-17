package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.AigoSubjectEntity;
import com.plushih.entities.AigoCommentaryEntity;
import com.plushih.entities.AigoQuestionEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.entities.UserMemberEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.AigoCommentaryService;
import com.plushih.services.front.AigoQuestionService;
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
public class AjaxAigoQuestionAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxAigoQuestionAdminController.class );



    @Autowired
    private SiteFileUploadService siteFileUploadService;
    @Autowired
    private CommonService commonService;

    @Autowired
    private AigoQuestionService aigoQuestionService;

    @Autowired
    private AigoCommentaryService aigoCommentaryService;

    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/questionList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
        //commonResultEntity = questionService.getquestionList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id,b.sub_name,c.lev_name,d.acv_name,e.aca_name");
        dbEntity.from("cb_aigo_question bb");
        dbEntity.join("cb_aigo_subject b","bb.sub_id=b.sub_id","left");
        dbEntity.join("cb_aigo_level c","bb.lev_id=c.lev_id","left");
        dbEntity.join("cb_aigo_achievement d","bb.acv_id = d.acv_id","left");
        dbEntity.join("cb_aigo_category e","bb.aca_id = e.aca_id","left");
        dbEntity.join("plus_user_master rum","bb.reg_um_seq = rum.um_seq","left");
        dbEntity.join("plus_user_master uum","bb.udt_um_seq = uum.um_seq","left");


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
            dbEntity.where("bb.lev_id",dbEntity.input.get_post("levId"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("unitId"))){
            dbEntity.where("bb.unit_id",dbEntity.input.get_post("unitId"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("searchUseYn"))){
            dbEntity.like("bb.use_yn",dbEntity.input.get_post("searchUseYn"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("searchType"))){
            dbEntity.like(dbEntity.input.get_post("searchType"),dbEntity.input.get_post("searchString"));
        }
        //dbEntity.order("reg_date","desc");
        dbEntity.order("bb.qst_id desc");

        CommonResultEntity res = new CommonResultEntity();
        List<AigoSubjectEntity> dataList = null;
        try {
            dataHashList = commonService.getList(dbEntity);
            int cnount = commonService.getCount(dbEntity);
            for (Map<String, Object> row : dataHashList) {


                //bbsEntity.put()
                dbEntity.clearQuery();
                dbEntity.select("*");
                dbEntity.where("seq", String.valueOf(row.get("qstId")));
                dbEntity.where("saf_bbs", "QST");
                dbEntity.from("plus_site_attach_file");
                dbEntity.order("saf_seq asc");
                dbEntity._limit = null;
                List<Map<String, Object>> getFileList = commonService.getList(dbEntity);

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
    @RequestMapping(value =  "/questionDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "delquestionSeqs", defaultValue = "") String delquestionSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(delquestionSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(delquestionSeqs)) {

            String[] stri = delquestionSeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"question");
            }
            db.where_in("acv_id",delquestionSeqs.split(","));
            db.from("cb_aigo_question");
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
    @RequestMapping(value =  "/questionExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@ModelAttribute AigoQuestionEntity aigoQuestionEntity, @RequestParam(required = false, value = "fileLists", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

//        Cache cache = new Cache();
//        String rules = cache.get_cache("question.bbs");
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
        db.from("cb_aigo_question");;
        Integer insertID = aigoQuestionService.setQuestionExcute(db,aigoQuestionEntity);
        //commonResultEntity.setResultList(businessInfoList);


        MultiValueMap<String, MultipartFile> fileuploads = request.getMultiFileMap();
        Iterator<String> iterator = fileuploads.keySet().iterator();

        while(iterator.hasNext()) {

            String key = iterator.next();
            LinkedList<MultipartFile> df = (LinkedList<MultipartFile>) fileuploads.get(key);

            MultipartFile fileInfo = (MultipartFile) df.getFirst();
            if (fileInfo.getSize() > 0) {
                siteFileUploadService.uploadFile(fileInfo, 0, "/QST", "QST", insertID, key);
            }
        }
        Debug.log("Debug.log(db.flag);+++++++++"+db.flag);


        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
	}


}