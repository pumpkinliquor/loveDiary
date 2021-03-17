package com.plushih.services.front;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.plushih.entities.BusinessInfoEntity;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.entities.BusinessClientEntity;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 
 */
@Service("BusinessService")
public class BusinessServiceImpl extends CiServiceImpl implements BusinessService {

    /**
     * 고객 -> 고객 이력 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<BusinessInfoEntity> getBusinessInfoList(plusActiveRecord dbEntity) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bi.bi_seq" +
                ", bi.bi_name" +
                ", bi.bi_biz_num" +
                ", bi.bi_ceo_name" +
                ", bi.bi_tel" +
                ", bi.bi_fax" +
                ", bi.bi_email" +
                ", bi.bi_etc" +
                ", bi.bi_is_del" +

                ", ai.*");
        dbEntity.from("plus_business_info bi");
        dbEntity.join("plus_address_info ai","bi.bi_seq=ai.ai_seq and ai.ai_type='BI'", plusQueryBuilder.joinType.LEFT);

        List<BusinessInfoEntity> dataList = null;
        try {
            dataHashList  = getList(dbEntity);
            //dataList = convertReal(getList(dbEntity),new BusinessInfoEntity());
            ObjectMapper oMapper = new ObjectMapper();
            CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, BusinessInfoEntity.class);
            dataList = oMapper.convertValue(dataHashList, typeReference);


            Debug.log(dataList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    /**
     * 고객 -> 고객 등록/수정
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Integer setBusinessInfoExcute(plusActiveRecord dbEntity,BusinessInfoEntity businessInfoEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("plus_business_info");
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
                dbEntity.insert_id = businessInfoEntity.getBiSeq();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Debug.log("FLAG = "+dbEntity.flag+" ::::insert_id=="+dbEntity.insert_id+"::::::::::::");
        /*
        String inType = plusActiveRecord.inType.ERR;

        daraMapRow =  ciDAO.get_primary(dbEntity);
        if(!daraMapRow.isEmpty()){
            Debug.log("eeeeeeeeee"+daraMapRow);
            String pkID = daraMapRow.get("Column_name").toString();
            Debug.log("businessInfoEntity.get(pkID).toString()=="+pkID);
            Debug.log(businessInfoEntity.get(pkID).toString());
        }
        dataMapList =  ciDAO.get_uniqe(dbEntity);

        Debug.log(dataMapList);






        if(dataMapList.size()>0 && dataMapList.size()==1){
//            daraMapRow = dataMapList.get(0);
//            //Debug.log(dataMapList.get(0).toString());
//            //Debug.log(dataMapList.get(0).getClass().getComponentType());
//            //daraMapRow =
        }
        Debug.log(daraMapRow);

        List<FieldInfoEntity> dataList = new ArrayList<FieldInfoEntity>();
        try {
            dataList = get_full_field(dbEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }



        for(int i=0;i<dataList.size();i++){

            if(dataList.get(i).get("columnName").equals("reg_date")){
                businessInfoEntity.set("reg_date","2018-01-10 12:12:00");

            }
            if(dataList.get(i).get("columnName").equals("upd_date")) {
                businessInfoEntity.set("upd_date","2018-01-10 12:12:00");
            }

            if(dataList.get(i).getColumnKey().equals("PRI")) continue;
            //Debug.log("dataList==i=="+dataList.get(i));

//            Debug.log(dataList.get(i).get("columnName").toString());
//            Debug.log(businessInfoEntity.get(dataList.get(i).get("columnName").toString()));
//            Debug.log((Map)dataList.get(i).get("columnName"));
            //.get("columnName").toString());

            dbEntity.add(
                    dataList.get(i).get("columnName").toString()
                    ,businessInfoEntity.get(dataList.get(i).get("columnName").toString()).toString()
                    ,dataList.get(i).getColumnDefault());

        }
        Debug.log("Debug.log(dbEntity._values);");
        Debug.log(dbEntity._values);
        dbEntity.where("bi_seq",9);
        ciDAO.update(dbEntity);
        */

        /*
        dbEntity.from("plus_business_info");
        dataList = ciDAO.getList(dbEntity);

        ObjectMapper oMapper = new ObjectMapper();

        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, BusinessInfoEntity.class);
        List<BusinessInfoEntity> dataList2 = null;
        dataList2 = oMapper.convertValue(dataList, typeReference);



        */
        return dbEntity.insert_id;
    }

    /**
     * 고객 -> 고객 이력 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<BusinessClientEntity> getBusinessClientList(plusActiveRecord dbEntity) throws Exception {

        List<HashMap<String, Object>> dataHashList = new ArrayList<HashMap<String, Object>>();

        //dbEntity.select("a.*,b.bi_name,z.*");
        dbEntity.select("bc.*,bi.bi_name,(select cm_name from plus_code_master where cm_code= bc.bc_type) bc_type_name");
        dbEntity.from("plus_business_client bc");
        dbEntity.join("plus_address_info ai","bc.bi_seq=ai.ai_seq and ai.ai_type='BC'", plusQueryBuilder.joinType.LEFT);
        dbEntity.join("plus_business_info bi","bc.bi_seq=bi.bi_seq","left");
        List<BusinessClientEntity> dataList = new ArrayList<BusinessClientEntity>();
        try {
            dataList = convert(getList(dbEntity),new BusinessClientEntity());
//             ObjectMapper oMapper = new ObjectMapper();
//        CollectionType typeReference =
//                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, BusinessClientEntity.class);
//         dataList = oMapper.convertValue(dataHashList, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
    /**
     * 고객 -> 고객 등록/수정
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Integer setBusinessClientExcute(plusActiveRecord dbEntity,BusinessClientEntity businessClientEntity) throws Exception {

        dbEntity.from("plus_business_client");
        try {
            _INDB(dbEntity);
            dbEntity.checkInsertUpdate();
            if (dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)) {
                /*사후 처리 하시고*/
                setInsert(dbEntity);
            } else if (dbEntity.flag.equals(plusQueryBuilder.queryType.UPDATE)) {
                /*사후 처리 하시고*/
                setUpdate(dbEntity);
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

}