package com.plushih.services.front;

import com.plushih.entities.BusinessInfoEntity;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.BusinessClientEntity;

import java.util.List;

public interface BusinessService {

    /**
     * 사업장 조회
     * @param dbEntity
     * @return
     */
    List<BusinessInfoEntity> getBusinessInfoList(plusActiveRecord dbEntity) throws Exception;

    Integer setBusinessInfoExcute(plusActiveRecord dbEntity, BusinessInfoEntity businessInfoEntity) throws Exception;

    List<BusinessClientEntity> getBusinessClientList(plusActiveRecord dbEntity) throws Exception;

    Integer setBusinessClientExcute(plusActiveRecord dbEntity, BusinessClientEntity businessClientEntity) throws Exception;
}