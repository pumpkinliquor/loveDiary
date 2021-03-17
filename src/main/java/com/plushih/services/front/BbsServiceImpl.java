package com.plushih.services.front;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.BbsEntity;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
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
@Service("BbsService")
public class BbsServiceImpl extends CiServiceImpl implements BbsService {

    /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public CommonResultEntity getBbsList(plusActiveRecord dbEntity) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*");
        dbEntity.from("plus_bbs bb");
        dbEntity.where("bb_bbs");
        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("bb_title",dbEntity.input.getSearchString());
        }
        dbEntity.order("bb_date","desc");

        CommonResultEntity res = new CommonResultEntity();
        List<BbsEntity> dataList = null;
        try {
            dataHashList  = getList(dbEntity);

            int cnount = getCount(dbEntity);
            res.setDraw(dbEntity.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);

            List<String> bbsSeqs = new ArrayList<String>();
            for (Map<String, Object> bbsEntity:dataHashList) {

                bbsSeqs.add(String.valueOf(bbsEntity.get("bbSeq")));

                //bbsEntity.put()
                dbEntity.clearQuery();
                dbEntity.select("*");
                dbEntity.where("bb_seq",String.valueOf(bbsEntity.get("bbSeq")));
                dbEntity.where("baf_bbs",(String)bbsEntity.get("bbBbs"));
                dbEntity.from("plus_bbs_attach_file");
                dbEntity._limit=null;
                List<Map<String, Object>> getFileList = getList(dbEntity);
                Map<String, Object> fileMap = new HashMap<String, Object>();
                for(Map<String, Object> row:getFileList ){
                    fileMap.put(String.valueOf(row.get("bafCode")),row);
                }
                bbsEntity.put("fileMap",fileMap);
            }

            //dataList = convertReal(getList(dbEntity),new BbsEntity());
//            ObjectMapper oMapper = new ObjectMapper();
//            CollectionType typeReference =
//                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, BbsEntity.class);
//            dataList = oMapper.convertValue(dataHashList, typeReference);


//            String[] array = bbsSeqs.toArray(new String[bbsSeqs.size()]);





            Debug.log(dataHashList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public CommonResultEntity getBbsDetail(plusActiveRecord dbEntity) throws Exception {

        Map<String,Object> dataHashList = new HashMap<String,Object>();
        dbEntity.select("bb.*");
        dbEntity.from("plus_bbs bb");
        dbEntity.where("bb_seq");
        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("bb_title",dbEntity.input.getSearchString());
        }
        dbEntity.order("bb_seq","desc");

        CommonResultEntity res = new CommonResultEntity();
        List<BbsEntity> dataList = null;
        try {
            dataHashList  = getRow(dbEntity);

            int cnount = getCount(dbEntity);
            res.setDraw(dbEntity.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultData(dataHashList);

            List<String> bbsSeqs = new ArrayList<String>();
//            for (Map<String, Object> bbsEntity:dataHashList) {

                bbsSeqs.add(String.valueOf(dataHashList.get("bbSeq")));

                //bbsEntity.put()
                dbEntity.clearQuery();
                dbEntity.select("*");
                dbEntity.where("bb_seq",String.valueOf(dataHashList.get("bbSeq")));
                dbEntity.where("baf_bbs",(String)dataHashList.get("bbBbs"));
                dbEntity.from("plus_bbs_attach_file");
                dbEntity._limit=null;
                List<Map<String, Object>> getFileList = getList(dbEntity);
                Map<String, Object> fileMap = new HashMap<String, Object>();
                for(Map<String, Object> row:getFileList ){
                    fileMap.put(String.valueOf(row.get("bafCode")),row);
                }
                dataHashList.put("fileMap",fileMap);
//            }
            dbEntity.clearQuery();
            dbEntity.select("bb.*");
            dbEntity.from("plus_bbs bb");
            dbEntity.lteq("bb_date",(String)dataHashList.get("bbDate"));
            dbEntity.where("bb_bbs",(String)dataHashList.get("bbBbs"));
            dbEntity.lt("bb_seq",String.valueOf(dataHashList.get("bbSeq")));
            dbEntity.order("bb_seq","desc");
            dbEntity.limit(1);

            res.add("prev",getRow(dbEntity));
            dbEntity.clearQuery();
            dbEntity.select("bb.*");
            dbEntity.from("plus_bbs bb");

            dbEntity.gteq("bb_date",(String)dataHashList.get("bbDate"));
            dbEntity.where("bb_bbs",(String)dataHashList.get("bbBbs"));
            dbEntity.not("bb_seq",String.valueOf(dataHashList.get("bbSeq")));
            dbEntity.gt("bb_seq",String.valueOf(dataHashList.get("bbSeq")));
            dbEntity.order("bb_seq","asc");
            dbEntity.limit(1);

            res.add("next",getRow(dbEntity));




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
    public Integer setBbsExcute(plusActiveRecord dbEntity, BbsEntity bbsEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("plus_bbs");
        try {
            _INDB(dbEntity);
            dbEntity.checkInsertUpdate();

            Debug.log("dbEntity.flag.=="+dbEntity.flag);
            if(dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)){
                setInsert(dbEntity);
                //Debug.log((new Gson()).toJson(dbEntity));
            }
            else if(dbEntity.flag.equals(plusQueryBuilder.queryType.UPDATE)){
                setUpdate(dbEntity);
                Debug.log("dbEntity.input.get(\"bbSeq\")=="+dbEntity.input.get("bbSeq"));
//                if(dbEntity.input.get("bbSeq")!=null){
//
//                }
                dbEntity.insert_id = bbsEntity.getBbSeq();//bbsEntity.getBbSeq();
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
    public Integer setBbsDelete(plusActiveRecord dbEntity, BbsEntity bbsEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("plus_bbs");
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