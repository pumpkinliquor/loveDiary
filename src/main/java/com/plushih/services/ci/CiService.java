package com.plushih.services.ci;



import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.FieldIndexEntity;
import com.plushih.entities.FieldInfoEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CiService {


    List<Map<String,Object>> getList(plusActiveRecord dbEntity) throws Exception;
    List<Map<String,Object>> getList_bySubQuery(plusActiveRecord dbEntity) throws Exception;
    Integer getCount(plusActiveRecord dbEntity) throws Exception;

    Map<String,Object> getRow(plusActiveRecord dbEntity) throws Exception;

    void _INDB(plusActiveRecord dbEntity) throws Exception;
    Map<String,Object> getPK(plusActiveRecord dbEntity) throws Exception;

    Integer getSeq(plusActiveRecord dbEntity) throws Exception;

    Integer setInsert(plusActiveRecord dbEntity) throws Exception;
    Integer setInsert(plusActiveRecord dbEntity,boolean istrue) throws Exception;
    Integer setBatch(plusActiveRecord dbEntity) throws Exception;

    Integer setDelete(plusActiveRecord dbEntity) throws Exception;
    Integer setDelete(plusActiveRecord dbEntity,boolean istrue) throws Exception;

    Integer setDeletePK(plusActiveRecord dbEntity) throws Exception;

    Integer setUpdate(plusActiveRecord dbEntity) throws Exception;
    Integer setUpdate(plusActiveRecord dbEntity,boolean istrue) throws Exception;


    Integer setQuery(plusActiveRecord dbEntity) throws Exception;

    Integer setHistory(plusActiveRecord dbEntity) throws Exception;

    Integer setUpdatePK(plusActiveRecord dbEntity) throws Exception;

    List<FieldInfoEntity> get_full_field(plusActiveRecord dbEntity) throws Exception;
    List<Map<String,Object>> get_tables(plusActiveRecord dbEntity) throws Exception;
    List<FieldIndexEntity> get_index(plusActiveRecord dbEntity) throws Exception;
    FieldIndexEntity get_primary(plusActiveRecord dbEntity) throws Exception;
    List<FieldIndexEntity> get_uniqe(plusActiveRecord dbEntity) throws Exception;

    <T> List<T> convert(List list, T t)throws Exception;
    <T> List<T> convertReal(List list, T t)throws Exception;
    <T> List<T> convertGson(List list, T t)throws Exception;
    <T> List<T> convertJson(String jsonList, T t)throws Exception;

    /**
     * 로그인기록
     * @param ulType
     * @return
     * @throws Exception
     */
    Integer setUserWorkHistory(String ulType,String umId,Object obj) throws Exception;

    /**
     * 로그인기록
     * @param ulType
     * @return
     * @throws Exception
     */
    Integer setUserWorkHistory(String ulType,String umId,Object obj,String strLocation,String strTable,String strPageName) throws Exception;


}
