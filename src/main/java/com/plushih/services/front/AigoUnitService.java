package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoUnitEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoUnitService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getUnitList(plusActiveRecord dbEntity) throws Exception;

    Integer setUnitExcute(plusActiveRecord dbEntity, AigoUnitEntity aigoUnitEntity) throws Exception;
    Integer setUnitDelete(plusActiveRecord dbEntity, AigoUnitEntity aigoUnitEntity) throws Exception;

}