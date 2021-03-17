package com.plushih.services.front;

import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.ProductEntity;
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
@Service("ProductService")
public class ProductServiceImpl extends CiServiceImpl implements ProductService {

    /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public CommonResultEntity getProductList(plusActiveRecord dbEntity,ProductEntity productEntity) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*");
        dbEntity.from("plus_product bb");
        String pdCtg = (String)dbEntity.input.post("pdCtg");
        if(!StringUtils.isEmpty((String)dbEntity.input.post("pdCtg"))) {
            dbEntity.where("pd_ctg");
        }
        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("pd_title",dbEntity.input.getSearchString());
        }
        dbEntity.order("pd_type, pd_date desc");

        CommonResultEntity res = new CommonResultEntity();
        List<ProductEntity> dataList = null;
        try {
            dataHashList  = getList(dbEntity);

            int cnount = getCount(dbEntity);
            res.setDraw(dbEntity.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);

            List<String> bbsSeqs = new ArrayList<String>();
            for (Map<String, Object> bbsEntity:dataHashList) {

                bbsSeqs.add(String.valueOf(bbsEntity.get("pdSeq")));

                //bbsEntity.put()
                dbEntity.clearQuery();
                dbEntity.select("*");
                dbEntity.where("seq",String.valueOf(bbsEntity.get("pdSeq")));
                dbEntity.where("saf_bbs","PRODUCT");
                dbEntity.from("plus_site_attach_file");
                dbEntity._limit=null;
                List<Map<String, Object>> getFileList = getList(dbEntity);
                Map<String, Object> fileMap = new HashMap<String, Object>();
                for(Map<String, Object> row:getFileList ){
                    fileMap.put(String.valueOf(row.get("safCode")),row);
                }
                bbsEntity.put("fileMap",fileMap);
            }
            //dataList = convertReal(getList(dbEntity),new ProductEntity());
//            ObjectMapper oMapper = new ObjectMapper();
//            CollectionType typeReference =
//                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, ProductEntity.class);
//            dataList = oMapper.convertValue(dataHashList, typeReference);


            String[] array = bbsSeqs.toArray(new String[bbsSeqs.size()]);





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
    public Integer setProductExcute(plusActiveRecord dbEntity, ProductEntity bbsEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("plus_product");
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
                Debug.log("dbEntity.input.get(\"pdSeq\")=="+dbEntity.input.get("pdSeq"));
//                if(dbEntity.input.get("pdSeq")!=null){
//
//                }
                dbEntity.insert_id = bbsEntity.getPdSeq();//bbsEntity.getpdSeq();
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
    public Integer setProductDelete(plusActiveRecord dbEntity, ProductEntity bbsEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("plus_product");
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