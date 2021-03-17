package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoSubjectEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoSubjectService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getSubjectList(plusActiveRecord dbEntity) throws Exception;

    Integer setSubjectExcute(plusActiveRecord dbEntity, AigoSubjectEntity aigoSubjectEntity) throws Exception;
    Integer setSubjectDelete(plusActiveRecord dbEntity, AigoSubjectEntity aigoSubjectEntity) throws Exception;

}