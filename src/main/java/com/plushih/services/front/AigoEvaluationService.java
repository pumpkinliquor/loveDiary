package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoEvaluationEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoEvaluationService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getEvaluationList(plusActiveRecord dbEntity) throws Exception;

    Integer setEvaluationExcute(plusActiveRecord dbEntity, AigoEvaluationEntity aigoEvaluationEntity) throws Exception;
    Integer setEvaluationDelete(plusActiveRecord dbEntity, AigoEvaluationEntity aigoEvaluationEntity) throws Exception;

}