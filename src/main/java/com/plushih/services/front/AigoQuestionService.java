package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoQuestionEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoQuestionService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getQuestionList(plusActiveRecord dbEntity) throws Exception;

    Integer setQuestionExcute(plusActiveRecord dbEntity, AigoQuestionEntity aigoQuestionEntity) throws Exception;
    Integer setQuestionDelete(plusActiveRecord dbEntity, AigoQuestionEntity aigoQuestionEntity) throws Exception;
    AigoQuestionEntity getQuestionInfo(plusActiveRecord dbEntity, AigoQuestionEntity aigoQuestionEntity) throws Exception;

}