package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.*;
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
        if(!dbEntity.input.get_post("file").equals("NONE")){
            dbEntity.select("bb.*,rum.um_name reg_um_name,rum.um_id reg_um_id,uum.um_name udt_um_name,uum.um_id udt_um_id,b.sub_name,c.lev_name,d.acv_name,e.aca_name," +
                    "(select count(*) from cb_aigo_my_answer_list an where bb.qst_id = an.qst_id) rcount ");
        } else {
            dbEntity.select("bb.qst_id,bb.qst_key,bb.qst_name");
        }

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
             dbEntity.gteq("substr(bb.reg_sysdate,1,10)",dbEntity.input.get_post("sdate"));
             dbEntity.lteq("substr(bb.reg_sysdate,1,10)",dbEntity.input.get_post("edate"));
        } else if(!StringUtils.isEmpty(dbEntity.input.get_post("sdate"))){
            dbEntity.gteq("substr(bb.reg_sysdate,1,10)",dbEntity.input.get_post("sdate"));
        } else if(!StringUtils.isEmpty(dbEntity.input.get_post("edate"))){
            dbEntity.lteq("substr(bb.reg_sysdate,1,10)",dbEntity.input.get_post("edate"));

        }

        if(!StringUtils.isEmpty(dbEntity.input.get_post("subId"))){
            dbEntity.where("bb.sub_id",dbEntity.input.get_post("subId"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("acvId"))){
            dbEntity.where("bb.acv_id",dbEntity.input.get_post("acvId"));
        }
        if(!StringUtils.isEmpty(dbEntity.input.get_post("acaId"))){
            dbEntity.where("bb.aca_id",dbEntity.input.get_post("acaId"));
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
            dbEntity.where(dbEntity.input.get_post("searchType"),dbEntity.input.get_post("searchString"));
        }
        //dbEntity.order("reg_date","desc");
        dbEntity.order("bb.qst_id desc");

        CommonResultEntity res = new CommonResultEntity();
        List<AigoSubjectEntity> dataList = null;
        try {
            dataHashList = commonService.getList(dbEntity);
            int cnount = commonService.getCount(dbEntity);
            ArrayList seqs = new ArrayList<String>();


            if(!dbEntity.input.get_post("file").equals("NONE")){



                for (Map<String, Object> row : dataHashList) {
                    seqs.add(String.valueOf(row.get("qstId")));

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
            }
            /*
            //Map<String, ArrayList<Map<String, Object>>> fileMap = new Map<String,ArrayList<Map<Object>>>();
            //List<ArrayList<Map<String, Object>>> listlistMap = new ArrayList<ArrayList<Map<String, Object>>>();
            //Map<String,ArrayList<Map<String, Object>>> fileListMap = new Map<String,ArrayList<Map<String, Object>>>();

            if(seqs.size()>0){

                dbEntity.clearQuery();
                dbEntity.select("*");
                dbEntity.where_in("seq", String.join(",",seqs).split(","));
                dbEntity.where("saf_bbs", "QST");
                dbEntity.from("plus_site_attach_file");
                dbEntity.order("saf_seq asc");
                dbEntity._limit = null;
                List<Map<String, Object>> getFileList = commonService.getList(dbEntity);


                for (Map<String, Object> row : dataHashList) {

                    ArrayList<Map<String, Object>> lmap = (ArrayList<Map<String, Object>>)row.get("fileList") ;
                    if(lmap==null){
                        row.put("fileList" , new ArrayList<Map<String, Object>>());
                        lmap = new ArrayList<Map<String, Object>>();
                    }

                    for(Map<String, Object> frow : getFileList){

                        if(String.valueOf(row.get("qstId")).equals(String.valueOf(frow.get("seq")))){
                            lmap.add(frow);
                            row.put("fileList" ,lmap);
                        }
                    }

                    //lmap.add()
                    //row.put("fileList", fileMap.get(String.valueOf(row.get("qstId"))));
                }

            }

             */




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

    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/questionCopy", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity questionCopy (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);


        CommonResultEntity res = new CommonResultEntity();
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        try {
//            db.select("bb.*,b.sub_name,c.lev_name,d.acv_name,e.aca_name sub_aca_name");
//            db.from("cb_aigo_question bb");
//            db.join("cb_aigo_subject b","bb.sub_id=b.sub_id","left");
//            db.join("cb_aigo_level c","bb.lev_id=c.lev_id","left");
//            db.join("cb_aigo_achievement d","bb.acv_id = d.acv_id","left");
//            db.join("cb_aigo_category e","bb.sub_aca_id = e.aca_id","left");
//            db.where("bb.aca_id","1");
//            db.order("qst_order","asc");
            db.from("cb_aigo_question");
            List<FieldInfoEntity> fields = commonService.get_full_field(db);
            StringBuffer isb = new StringBuffer();
            StringBuffer ssb = new StringBuffer();
            int i=0;
            isb.append("insert into cb_aigo_question ");
            ssb.append("select ");
            for(FieldInfoEntity rx : fields){
                if(!rx.getColumnName().equals("qst_id")){

                    ssb.append((i>0?",":"")+rx.getColumnName());
                    isb.append((i>0?",":"")+rx.getColumnName());
                    i++;
                }
            }

            db.querySql = isb.toString() + " " + ssb.toString() + " WHERE qst_id = "+db.input.get_post("qstId");

            commonService.setQuery(db);



            res.setResultList(dataHashList);
        } catch (Exception e){
            e.printStackTrace();
        }

        logEnd(fullName);
        return res;
    }

}