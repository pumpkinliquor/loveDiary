package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoConceptEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoConceptService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getConceptList(plusActiveRecord dbEntity) throws Exception;

    Integer setConceptExcute(plusActiveRecord dbEntity, AigoConceptEntity aigoConceptEntity) throws Exception;
    Integer setConceptDelete(plusActiveRecord dbEntity, AigoConceptEntity aigoConceptEntity) throws Exception;

}