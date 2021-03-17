package com.plushih.services;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.CodeMasterEntity;

import java.util.List;

public interface CodeService {

    /**
     * 공통코드 조회
     * @param dbEntity
     * @return
     */
    List<CodeMasterEntity> getCodeList(plusActiveRecord dbEntity) throws Exception;

    List<CodeMasterEntity> getFastList(plusActiveRecord dbEntity) throws Exception;

}