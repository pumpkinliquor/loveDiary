package com.plushih.services.front;


import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.AigoTendencyEntity;
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
@Service("AigoTendencyService")
public class AigoTendencyServiceImpl extends CiServiceImpl implements AigoTendencyService {

    /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public CommonResultEntity getTendencyList(plusActiveRecord dbEntity) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*");
        dbEntity.from("cb_aigo_Tendency bb");

        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("ten_name",dbEntity.input.getSearchString());
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
    public Integer setTendencyExcute(plusActiveRecord dbEntity, AigoTendencyEntity aigoTendencyEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("cb_aigo_Tendency");
        try {
            _INDB(dbEntity);
            dbEntity.checkInsertUpdate();

            Debug.log("dbEntity.flag.=="+dbEntity.flag);
            if(dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)){
                setInsert(dbEntity);
                dbEntity.where("ten_id",String.valueOf(dbEntity.insert_id));
                dbEntity.add("ten_key","TEN"+StringUtils.zeroFill(String.valueOf(dbEntity.insert_id),5));
                setUpdate(dbEntity);
                //Debug.log((new Gson()).toJson(dbEntity));
            }
            else if(dbEntity.flag.equals(plusQueryBuilder.queryType.UPDATE)){
                dbEntity.add("ten_key","TEN"+StringUtils.zeroFill(String.valueOf(aigoTendencyEntity.getTenId()),5));
                setUpdate(dbEntity);
                Debug.log("dbEntity.input.get(\"bbSeq\")=="+dbEntity.input.get("bbSeq"));
//                if(dbEntity.input.get("bbSeq")!=null){
//
//                }
                dbEntity.insert_id = aigoTendencyEntity.getTenId();//bbsEntity.getBbSeq();
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
    public Integer setTendencyDelete(plusActiveRecord dbEntity, AigoTendencyEntity aigoTendencyEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("cb_aigo_Tendency");
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


}