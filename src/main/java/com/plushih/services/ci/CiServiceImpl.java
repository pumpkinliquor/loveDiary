package com.plushih.services.ci;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;


import com.plushih.common.ci.Cache;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.DateUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.daos.CiDAO;
import com.plushih.entities.FieldIndexEntity;
import com.plushih.entities.FieldInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


/**
 * Created by hsk3807
 */
@Service("CiService")
public class CiServiceImpl implements CiService {

  @Autowired
  private CiDAO ciDAO;

     /**
     * 오브젝트변환요건 임시로
     * @param list
     * @param t
     * @param <T>
     * @return
     */
    public <T> List<T> convert(List list, T t){

        return list;
    }
    /**
     * hsk3807
     * 오브젝트변환
     * 실제 오브젝트에 리턴된다.
     * 컬럼명이 없거나 컬럼이없으면 에러난다;;!! 중요
     * @param list
     * @param t
     * @param <T>
     * @return
     */
    public <T> List<T> convertReal(List list, T t){

        Long logStart = System.currentTimeMillis();
        ObjectMapper oMapper = new ObjectMapper();
        CollectionType typeReference =
            TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, t.getClass());

        long end = System.currentTimeMillis();

		 List<T> rs = oMapper.convertValue(list, typeReference);
        System.out.println( "리스트 변환 ("+t.getClass().getName()+") 실행 시간 : " + ( end -logStart  )/1000.0 +"초");
        return rs;

    }

    /**
     * hsk3807 //작동안함
     * 오브젝트변환
     * 실제 오브젝트에 리턴된다.
     * 컬럼명이 없거나 컬럼이없으면 에러난다;;!! 중요
     * @param list
     * @param t
     * @param <T>
     * @return
     */
    public <T> List<T> convertGson(List list, T t){

        Long logStart = System.currentTimeMillis();
//        Gson gson = new Gson();
//            Type listType = new TypeToken<ArrayList<T>>(){}.getType();
//        List<T> dataList = gson.(list, listType);
        long end = System.currentTimeMillis();

        System.out.println( "리스트 변환 ("+t.getClass().getName()+") 실행 시간 : " + ( end -logStart  )/1000.0 +"초");
        return list;

    }



    /**
     * hsk3807
     * 오브젝트변환
     * 실제 오브젝트에 리턴된다.
     * 컬럼명이 없거나 컬럼이없으면 에러난다;;!! 중요
     * @param jsonList
     * @param t
     * @param <T>
     * @return
     */
    public <T> List<T> convertJson(String jsonList, T t) throws IOException {

        Long logStart = System.currentTimeMillis();
        ObjectMapper oMapper = new ObjectMapper();
        CollectionType typeReference =
            TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, t.getClass());
		 List<T> rs = oMapper.readValue(jsonList, typeReference);
        long end = System.currentTimeMillis();
        System.out.println( "jsonList => 리스트 변환 ("+t.getClass().getName()+") 실행 시간 : " + ( end -logStart  )/1000.0 +"초");
        return rs;

    }


    /**
     * hsk3807
     * 쿼리에대한 리스트 리턴
     * 에러는 안나는데.. 제약이 많음;
     * java에서 foreach 등 사용을 못할수있음;
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<Map<String,Object>> getList(plusActiveRecord dbEntity) throws Exception {
        int totalListCnt = Default.ZERO;

        List<Map<String,Object>> dataList =  new  ArrayList<Map<String,Object>>();

        dbEntity.checkBefore(plusQueryBuilder.queryType.SELECT);
        dataList =  ciDAO.getList(dbEntity);


        Debug.log("#### getList.size=="+dataList.size());

        return dataList;
    }

    /**
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<Map<String,Object>> getList_bySubQuery(plusActiveRecord dbEntity) throws Exception {
        List<Map<String,Object>> dataList =  new  ArrayList<Map<String,Object>>();

        dbEntity.checkBefore(plusQueryBuilder.queryType.SELECT_SEB_QUERY);
        dataList =  ciDAO.getList_bySubQuery(dbEntity);


        Debug.log("#### getList.size=="+dataList.size());

        return dataList;
    }



    /**
     * rowData 리턴
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Map<String,Object> getRow(plusActiveRecord dbEntity) throws Exception {

        HashMap<String,Object> dataRow =  new  HashMap<String,Object>();

        dbEntity.checkBefore(plusQueryBuilder.queryType.SELECT);
        Map<String,Object> dataRowx =  ciDAO.getRow(dbEntity);

//        for (Map.Entry<String, Object> entry : dataRowx.entrySet()) {
//            System.out.println(String.format("키 : %s, 값 : %s", entry.getKey(), entry.getValue()));
//        }
        if(dataRowx!=null);
        Debug.log("#### getRow.size=="+dataRowx);

        return dataRowx;
    }

    @Override
    public HashMap<String, Object> getPK(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.SELECT);
        //return ciDAO.getRow(dbEntity);
        return new HashMap<String, Object>();

    }

    @Override
    public Integer setInsert(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.INSERT);

        //setUserWorkHistory("INSERT",dbEntity.getUmId(),dbEntity._values,dbEntity.getReferer(),dbEntity._table,dbEntity.pageName);
        return ciDAO.insert(dbEntity);
    }

    @Override
    public Integer setInsert(plusActiveRecord dbEntity,boolean istrue) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.INSERT);
        String ref = "";
        if (istrue == true) {
            //setUserWorkHistory("INSERT", dbEntity.getUmId(), dbEntity._values,dbEntity.getReferer(),dbEntity._table);
        }
        return ciDAO.insert(dbEntity);
    }

    @Override
    public Integer setBatch(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.TABLE);
        List<FieldInfoEntity> getFieldList  = get_full_field(dbEntity);

        for(FieldInfoEntity row:getFieldList){
            dbEntity.add(row.getColumnName(),"");
        }
        dbEntity.checkBefore(plusQueryBuilder.queryType.BATCH);
        //setUserWorkHistory("BATCH",dbEntity.getUmId(),dbEntity._values);
        return ciDAO.batch(dbEntity);
    }

    @Override
    public Integer setDelete(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.DELETE);
        //setUserWorkHistory("DELETE",dbEntity.getUmId(),dbEntity._values,dbEntity.getReferer(),dbEntity._table,dbEntity.pageName);
        return ciDAO.delete(dbEntity);
    }
    @Override
    public Integer setDelete(plusActiveRecord dbEntity,boolean istrue) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.DELETE);
        //setUserWorkHistory("DELETE",dbEntity.getUmId(),dbEntity._values,dbEntity.getReferer(),dbEntity._table,dbEntity.pageName);
        return ciDAO.delete(dbEntity);
    }

    @Override
    public Integer setDeletePK(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.DELETE);
        return ciDAO.delete(dbEntity);
    }

    @Override
    public Integer setQuery(plusActiveRecord dbEntity) throws Exception {
        Debug.log(dbEntity.querySql);
        //dbEntity.checkBefore(plusQueryBuilder.queryType.QUERY);
        return ciDAO.query(dbEntity);
    }

    @Override
    public Integer setUpdate(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.UPDATE);
        //setUserWorkHistory("UPDATE",dbEntity.getUmId(),dbEntity._values,dbEntity.getReferer(),dbEntity._table,dbEntity.pageName);
        return ciDAO.update(dbEntity);
    }
    @Override
    public Integer setUpdate(plusActiveRecord dbEntity,boolean istrue) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.UPDATE);
        return ciDAO.update(dbEntity);
    }

    @Override
    public Integer setUpdatePK(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.UPDATE);
        return ciDAO.update(dbEntity);
    }


    @Override
    public Integer setHistory(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.UPDATE);
        return ciDAO.history(dbEntity);
    }

    /**
     * 컬럼정보리턴
     * @param dbEntity
     * @return
     * @throws Exception
     */
    @Override
    public List<FieldInfoEntity> get_full_field(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.TABLE);
        List<Map<String, Object>> dataMapList = ciDAO.get_full_field(dbEntity);


        //List<camelMap> 을 형변환
        ObjectMapper oMapper = new ObjectMapper();
        CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, FieldInfoEntity.class);
        List<FieldInfoEntity> dataList = oMapper.convertValue(dataMapList, typeReference);
        return dataList;
    }

    /**
     * 테이블정보리턴
     * @param dbEntity
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> get_tables(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.TABLE);
        return ciDAO.get_tables(dbEntity);
    }

    /**
     * 인덱스정보리턴
     * @param dbEntity
     * @return
     * @throws Exception
     */
    @Override
    public List<FieldIndexEntity> get_index(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.TABLE);
        Cache cc =new Cache();
        String cacheName = "index."+dbEntity._table;

        List<FieldIndexEntity> res = new ArrayList<FieldIndexEntity>();
        if(cc.is_cached(cacheName)){

		    ObjectMapper oMapper = new ObjectMapper();
            CollectionType typeReference =
                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, FieldIndexEntity.class);
            List<FieldIndexEntity> dataList = oMapper.convertValue(cc.get_cache(cacheName), typeReference);

            res  = dataList;
        } else {
            res =ciDAO.get_index(dbEntity);
            cc.set_cache(cacheName,(new Gson()).toJson(res));
        }
        return res;
    }

    /**
     * 프라이머리리턴
     * @param dbEntity
     * @return
     * @throws Exception
     */
    @Override
    public FieldIndexEntity get_primary(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.TABLE);
        String cacheName = "primary."+dbEntity._table;
        Cache cc =new Cache();
        FieldIndexEntity res = new FieldIndexEntity();
        if(cc.is_cached(cacheName)){

		    ObjectMapper oMapper = new ObjectMapper();

//		    Debug.log(cc.get_cache(cacheName));
//            FieldIndexEntity dataRow = oMapper.convertValue(cc.get_cache(cacheName), FieldIndexEntity.class);

            Gson gson = new Gson();
    FieldIndexEntity dataRow = gson.fromJson(cc.get_cache(cacheName), FieldIndexEntity.class);
            res  = dataRow;
        } else {
            res =ciDAO.get_primary(dbEntity);
            cc.set_cache(cacheName,(new Gson()).toJson(res));
        }

        return res;
    }

    /**
     * 유니크리턴
     * @param dbEntity
     * @return
     * @throws Exception
     */
    @Override
    public List<FieldIndexEntity> get_uniqe(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.TABLE);
        String cacheName = "uniqe."+dbEntity._table;
        Cache cc =new Cache();

        List<FieldIndexEntity> res = new ArrayList<FieldIndexEntity>();
        if(cc.is_cached(cacheName)){

//		    ObjectMapper oMapper = new ObjectMapper();
//            CollectionType typeReference =
//                TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, FieldIndexEntity.class);
            //List<FieldIndexEntity> dataList = oMapper.convertValue(cc.get_cache(cacheName), typeReference);

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<FieldIndexEntity>>(){}.getType();
     List<FieldIndexEntity> dataList = gson.fromJson(cc.get_cache(cacheName), listType);

            res  = dataList;
        } else {
            res =ciDAO.get_uniqe(dbEntity);
            cc.set_cache(cacheName,(new Gson()).toJson(res));
        }

        return res;
    }

    /**
     * 카운트조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Integer getCount(plusActiveRecord dbEntity) throws Exception {
        dbEntity.select("COUNT(*)");
        dbEntity.checkBefore(plusQueryBuilder.queryType.SELECT);
        Integer dataCount =  ciDAO.getCount(dbEntity);

        return dataCount;
    }
    /**
     * 카운트조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Integer getSeq(plusActiveRecord dbEntity) throws Exception {
        dbEntity.checkBefore(plusQueryBuilder.queryType.SELECT);
        Integer dataSeq =  ciDAO.getSeq(dbEntity);
        if(dataSeq==null){
            dataSeq=0;
        }

        return dataSeq;
    }


    /**
     * 인서트전 체크
     *
     * @param dbEntity
     * @return
     */
    @Override
    public void _INDB(plusActiveRecord dbEntity) throws Exception {
        HashMap<String,Object> dataHelper =  new  HashMap<String,Object>();

        FieldIndexEntity primary = get_primary(dbEntity);
        List<FieldIndexEntity> uniqeList = get_uniqe(dbEntity);

        Integer seq = 0;
        Integer uniqeSeq = 0;
        dbEntity.flag = plusQueryBuilder.queryType.INSERT;

        Debug.log("#PriMaget_primary(dbEntity); == "+primary);
        if(primary!=null){

            dbEntity.select(primary.getColumnNames());
            String camelKey = StringUtils.toCamel(primary.getColumnNames());
            if(!StringUtils.isEmpty(dbEntity.input.post(camelKey))){
                //dbEntity.where(primary.getColumnNames(),Integer.parseInt(dbEntity.input.post(camelKey)));
                dbEntity.where(primary.getColumnNames(),dbEntity.input.post(camelKey));
                seq = getSeq(dbEntity);
            }
            Debug.log("CHECK[PRIMARY] : TB =  "+dbEntity._table  + " PK = "+primary.getColumnNames() + " POST = " +dbEntity.input.post(camelKey) +" DBExists = " +seq);
            if(seq>0){

                dbEntity.flag = plusQueryBuilder.queryType.UPDATE;
            }

        }
        if(uniqeList!=null){
            //dbEntity.clearWhere();
            for(int i=0;i< uniqeList.size();i++){
                FieldIndexEntity uniqueEntity = uniqeList.get(i);
                if(uniqueEntity.getColumnNames().indexOf(",")>-1){
                    String[] uniques =uniqueEntity.getColumnNames().split(",");
                    for(int ix=0;ix< uniques.length;ix++){
                        String camelKey = StringUtils.toCamel(uniques[ix]);
                        dbEntity.where(uniques[ix],dbEntity.input.post(camelKey));
                    }
                } else {
                    String unique =uniqueEntity.getColumnNames();
                    String camelKey = StringUtils.toCamel(unique);
                    dbEntity.where(unique,dbEntity.input.post(camelKey));
                }
                //seq=0 ;// 강제로 에러내본다
                //dbEntity.flag=plusQueryBuilder.queryType.INSERT; // 강제로 에러내본다
                uniqeSeq = getSeq(dbEntity);

                if(uniqeSeq!=null) {
                    Debug.log("CHECK[UNIQE] : PRIMARY Value = " + seq + " + UNIQUE Value =  " + uniqeSeq);
                    if (dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)) {
                        if (uniqeSeq > 0) {
                            dbEntity.flag = plusQueryBuilder.queryType.DUP;
                            dbEntity.errMsg = "CHECK[UNIQE]#1 :[dbEntity.flag=" + dbEntity.flag + "] INSERT인데  + UNIQUE Value =  " + uniqeSeq + " 가 있는경우 중복으로 판단";
                            Debug.log("CHECK[UNIQE]#1 :[dbEntity.flag=" + dbEntity.flag + "] INSERT인데  + UNIQUE Value =  " + uniqeSeq + " 가 있는경우 중복으로 판단");
                        }
                    } else { //update

                        if (uniqeSeq.intValue() != seq.intValue()) {

                            dbEntity.flag = plusQueryBuilder.queryType.ERR;
                            dbEntity.errMsg = "CHECK[UNIQE]#2 :[dbEntity.flag=" + dbEntity.flag + "] UPDATE인데  PRIMARY Value = " + seq + " , UNIQUE Value =  " + uniqeSeq + " 가 값이 틀린경우 에러로  판단";

                            Debug.log("CHECK[UNIQE]#2 :[dbEntity.flag=" + dbEntity.flag + "] UPDATE인데  PRIMARY Value = " + seq + " , UNIQUE Value =  " + uniqeSeq + " 가 값이 틀린경우 에러로  판단");
                        }
                    }
                }

            }
        }
        Debug.log("#######dbEntity.flag======="+dbEntity.flag);
        dbEntity.checkInsertUpdate();
        if(! (dbEntity.flag.equals(plusQueryBuilder.queryType.ERR) || dbEntity.flag.equals(plusQueryBuilder.queryType.DUP)))
        {

            if(dbEntity.flag.equals(plusQueryBuilder.queryType.UPDATE)){
                Integer checkSeq=getCount(dbEntity);
                if(checkSeq==0 || checkSeq==null){
                    dbEntity.flag = plusQueryBuilder.inType.UNI;
                    throw new Exception("해당 데이터에 할당된 고유값이 다릅니다.");
                }
            }

            List<FieldInfoEntity> getFieldList  = get_full_field(dbEntity);
            String paramSql = "######## PARAM #######\r\ndbEntity._values.clear();\r\n";
            dbEntity._values.clear();
            dbEntity._fieldList.clear();
            for(int i =0;i<getFieldList.size();i++){


                String fieldName = getFieldList.get(i).getColumnName(),camelField = StringUtils.toCamel(getFieldList.get(i).getColumnName());
                dbEntity._fieldList.put(getFieldList.get(i).getColumnName(),getFieldList.get(i));

                if(fieldName.equals(primary.getColumnNames())){
                    continue;
                }
                else if(camelField.equals("udtDate")) {
                    dbEntity.add(fieldName,dbEntity.getYmdHis());
                    continue;
                }
                else if(camelField.equals("udtUmSeq")) {
                    dbEntity.add(fieldName,dbEntity.getUmSeq());
                    continue;
                }
                else if(camelField.equals("regUmSeq")) {
                    //dbEntity.tm
                    if(dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)) {
                        dbEntity.add(fieldName, dbEntity.getUmSeq());
                    }
                    continue;
                }
                else if(camelField.equals("regDate")) {
                    if(dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)){
                        dbEntity.add(fieldName,dbEntity.getYmdHis());
                    }
                    continue;
                }
                dbEntity.add(getFieldList.get(i));
                if(dbEntity._DEBUG==true){
                    ////inData.put(getFieldList.get(i).getColumnName(),dbEntity.input.post(StringUtils.toCamel(getFieldList.get(i).getColumnName()),getFieldList.get(i).getDataType()));

                    paramSql +="\r\n/* Comment : ["+getFieldList.get(i).getColumnComment()+"] dbEntity.input.post(\""+camelField+"\")="+dbEntity.input.post(camelField)+"*/";
                    paramSql +="\r\ndbEntity.add(\""+getFieldList.get(i).getColumnName()+"\",\"dbEntity.input.post(\""+camelField+"\"); or dbEntity.add(getFieldList.get(0));\r\n";

                }
            }
            Debug.log(paramSql);
        }



    }
    /**
     * 로그인기록 저장하자
     *
     * @return
     */
    @Override
    public Integer setUserWorkHistory(String ulType,String umId,Object obj) throws Exception {


        plusActiveRecord dbEntity =new plusActiveRecord();
        dbEntity.from("plus_user_login");
        String tableName = "";
        String location = "";
        if(!StringUtils.isEmpty(ulType)){
            if(ulType.indexOf("|")>-1){

                Debug.log("ulType=="+ulType);

                String[] expUlType = ulType.split("|");
                ulType = expUlType[0];
                tableName = expUlType[1];
                location = expUlType[2];

            }
        }

        Debug.log("@@@@@@@@@@@@@@@@@ttttttttttxdfwefawefawefawefawefawefawefweaf");
        try {
            dbEntity.add("ul_type",ulType);
            dbEntity.add("um_id", umId);
            dbEntity.add("ul_date", DateUtils.getDate2String(new Date()).substring(0,10));
            dbEntity.add("ul_time", DateUtils.getDate2String(new Date()).substring(11));
            dbEntity.add("reg_date", DateUtils.getDate2String(new Date()));
            dbEntity.add("ul_location",location);
            dbEntity.add("ul_table",tableName);

            dbEntity.add("ul_text",(new Gson().toJson(obj)));
            setInsert(dbEntity,false);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 1;
    }

    /**
     * 로그인기록 저장하자
     *
     * @return
     */
    @Override
    public Integer setUserWorkHistory(String ulType,String umId,Object obj,String strLocation,String strTable,String strPageName) throws Exception {


        plusActiveRecord dbEntity =new plusActiveRecord();
        dbEntity.from("plus_user_login");


        try {
            dbEntity.add("ul_type",ulType);
            dbEntity.add("um_id", umId);
            dbEntity.add("ul_date", DateUtils.getDate2String(new Date()).substring(0,10));
            dbEntity.add("ul_time", DateUtils.getDate2String(new Date()).substring(11));
            dbEntity.add("reg_date", DateUtils.getDate2String(new Date()));
            dbEntity.add("ul_location",strLocation);
            dbEntity.add("ul_off_location",strPageName);
            dbEntity.add("ul_table",strTable);

            dbEntity.add("ul_text",(new Gson().toJson(obj)));
            setInsert(dbEntity,false);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 1;
    }

}