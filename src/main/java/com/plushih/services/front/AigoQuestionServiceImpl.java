package com.plushih.services.front;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.AigoQuestionEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 
 */
@Service("AigoQuestionService")
public class AigoQuestionServiceImpl extends CiServiceImpl implements AigoQuestionService {

    /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public CommonResultEntity getQuestionList(plusActiveRecord dbEntity) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*");
        dbEntity.from("cb_aigo_question bb");

        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("sub_name",dbEntity.input.getSearchString());
        }
        dbEntity.order("reg_date","desc");

        CommonResultEntity res = new CommonResultEntity();
        try {
            dataHashList  = getList(dbEntity);

            int cnount = getCount(dbEntity);
            res.setDraw(dbEntity.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);


            Debug.log(dataHashList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 게시판 -> 게시판 등록/수정
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Integer setQuestionExcute(plusActiveRecord dbEntity, AigoQuestionEntity aigoQuestionEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("cb_aigo_question");
        try {
            _INDB(dbEntity);
            dbEntity.checkInsertUpdate();

            Debug.log("dbEntity.flag.=="+dbEntity.flag);
            if(dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)){

                setInsert(dbEntity);
                dbEntity.where("qst_id",String.valueOf(dbEntity.insert_id));
                dbEntity.add("qst_key","QST"+StringUtils.zeroFill(String.valueOf(dbEntity.insert_id),5));
                setUpdate(dbEntity);
                //Debug.log((new Gson()).toJson(dbEntity));
            }
            else if(dbEntity.flag.equals(plusQueryBuilder.queryType.UPDATE)){
                dbEntity.add("qst_key","QST"+StringUtils.zeroFill(String.valueOf(aigoQuestionEntity.getQstId()),5));
                setUpdate(dbEntity);
                Debug.log("dbEntity.input.get(\"bbSeq\")=="+dbEntity.input.get("bbSeq"));
//                if(dbEntity.input.get("bbSeq")!=null){
//
//                }
                dbEntity.insert_id = aigoQuestionEntity.getQstId();//bbsEntity.getBbSeq();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Debug.log("FLAG = "+dbEntity.flag+" ::::insert_id=="+dbEntity.insert_id+"::::::::::::");

        return dbEntity.insert_id;
    }

    /**
     * 게시판 -> 게시판 등록/수정
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Integer setQuestionDelete(plusActiveRecord dbEntity, AigoQuestionEntity aigoQuestionEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("cb_aigo_question");
        try {

            if(dbEntity.flag.equals(plusQueryBuilder.queryType.DELETE)){
                setDelete(dbEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Debug.log("FLAG = "+dbEntity.flag+" ::::insert_id=="+dbEntity.insert_id+"::::::::::::");

        return dbEntity.insert_id;
}
    /**
     * @ClassName	: AigoQuestionServiceImpl.java
     * @Method		: getQuestionInfo
     * @Date		: 2021. 1. 10. 
     * @author		: dev.yklee
     * @Description	: 문항 정보 조회
     */
    @Override
    public AigoQuestionEntity getQuestionInfo(plusActiveRecord dbEntity, AigoQuestionEntity aigoQuestionEntity) throws Exception {
    	
		dbEntity.select("caq.qst_id"
						+", caq.qst_key"
						+", caq.qst_name"
						+", cas.sub_id"
						+", cas.sub_name"
						+", cal.lev_id"
						+", cal.lev_name"
						+", cau.unit_id"
						+", cau.unit_name"
						+", caa.acv_id"
						+", caa.acv_name");
		dbEntity.from("cb_aigo_question as caq");
		dbEntity.join("cb_aigo_subject AS cas", "caq.sub_id = cas.sub_id", "left");
		dbEntity.join("cb_aigo_level as cal", "caq.lev_id = cal.lev_id", "left");
		dbEntity.join("cb_aigo_unit as cau", "caq.unit_id = cau.unit_id", "left");
		dbEntity.join("cb_aigo_achievement as caa", "caq.acv_id = caa.acv_id and caq.sub_id = caa.sub_id and caq.lev_id = caa.lev_id", "left");
		dbEntity.like("caq.qst_key", aigoQuestionEntity.getQstKey());

		if(!StringUtils.isEmpty(dbEntity.input.get_post("levId"))){
            dbEntity.where("cal.lev_id",dbEntity.input.get_post("levId"));
        }
		if(!StringUtils.isEmpty(dbEntity.input.get_post("acvId"))){
            dbEntity.where("caa.acv_id",dbEntity.input.get_post("acvId"));
        }

		AigoQuestionEntity questionInfo = null;
		
		try {
			if(!StringUtils.isEmpty(aigoQuestionEntity.getQstKey()) ){
				ObjectMapper obejctMapper = new ObjectMapper();
				questionInfo = obejctMapper.convertValue(getRow(dbEntity), aigoQuestionEntity.getClass());
			}else {
				questionInfo = new AigoQuestionEntity();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return questionInfo;
    	
    }
}