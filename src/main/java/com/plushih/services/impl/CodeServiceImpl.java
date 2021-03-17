package com.plushih.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.daos.CiDAO;
import com.plushih.entities.CodeMasterEntity;
import com.plushih.services.CodeService;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 
 */
@Service("CodeService")
public class CodeServiceImpl extends CiServiceImpl implements CodeService {
    @Autowired
    private CiDAO ciDAO;

    /**
     * 공통코드 -> 공통코드 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<CodeMasterEntity> getCodeList(plusActiveRecord dbEntity) throws Exception {
        dbEntity.select("cm_gubun,cm_code,cm_name");
        dbEntity.from("plus_code_master");
        List<Map<String,Object>> dataList = getList(dbEntity) ;

        ObjectMapper oMapper = new ObjectMapper();

        CollectionType typeReference =
        TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, CodeMasterEntity.class);
        List<CodeMasterEntity>  dataList2 = null;
        dataList2 = oMapper.convertValue(dataList, typeReference);
        return dataList2;
    }
    /**
     * 공통코드 -> 공통코드 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<CodeMasterEntity> getFastList(plusActiveRecord dbEntity) throws Exception {
        List<Map<String,Object>> dataList = getList(dbEntity) ;
        ObjectMapper oMapper = new ObjectMapper();
        CollectionType typeReference =
        TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, CodeMasterEntity.class);
        List<CodeMasterEntity>  dataList2 = null;
        dataList2 = oMapper.convertValue(dataList, typeReference);
        return dataList2;
    }

}