package com.plushih.services.front;

import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.MainBannerEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.MainBannerEntity;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 
 */
@Service("BannerService")
public class BannerServiceImpl extends CiServiceImpl implements BannerService {

    /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public CommonResultEntity getBannerList(plusActiveRecord dbEntity) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*");
        dbEntity.from("plus_main_banner bb");

        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("bn_title",dbEntity.input.getSearchString());
        }
        dbEntity.order("bn_date","desc");

        CommonResultEntity res = new CommonResultEntity();
        List<MainBannerEntity> dataList = null;
        try {
            dataHashList  = getList(dbEntity);

            int cnount = getCount(dbEntity);
            res.setDraw(dbEntity.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);

            List<String> bbsSeqs = new ArrayList<String>();
            for (Map<String, Object> bbsEntity:dataHashList) {

                bbsSeqs.add(String.valueOf(bbsEntity.get("bnSeq")));

                //bbsEntity.put()
                dbEntity.clearQuery();
                dbEntity.select("*");
                dbEntity.where("seq",String.valueOf(bbsEntity.get("bnSeq")));
                dbEntity.where("saf_bbs","BANNER");
                dbEntity.from("plus_site_attach_file");
                dbEntity._limit=null;
                List<Map<String, Object>> getFileList = getList(dbEntity);
                Map<String, Object> fileMap = new HashMap<String, Object>();
                for(Map<String, Object> row:getFileList ){
                    fileMap.put(String.valueOf(row.get("safCode")),row);
                }
                bbsEntity.put("fileMap",fileMap);
            }
            //dataList = convertReal(getList(dbEntity),new BannerEntity());
//            ObjectMapper oMapper = new ObjectMapper();
//            CollectionType typeReference =
//                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, BannerEntity.class);
//            dataList = oMapper.convertValue(dataHashList, typeReference);







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
    public Integer setBannerExcute(plusActiveRecord dbEntity, MainBannerEntity bbsEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("plus_main_banner");
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
                dbEntity.insert_id = bbsEntity.getBnSeq();//bbsEntity.getBbSeq();
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
    public Integer setBannerDelete(plusActiveRecord dbEntity, MainBannerEntity bbsEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("plus_main_banner");
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