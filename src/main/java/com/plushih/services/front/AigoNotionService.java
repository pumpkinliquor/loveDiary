package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoNotionEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoNotionService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getNotionList(plusActiveRecord dbEntity) throws Exception;

    Integer setNotionExcute(plusActiveRecord dbEntity, AigoNotionEntity aigoNotionEntity) throws Exception;
    Integer setNotionDelete(plusActiveRecord dbEntity, AigoNotionEntity aigoNotionEntity) throws Exception;

}