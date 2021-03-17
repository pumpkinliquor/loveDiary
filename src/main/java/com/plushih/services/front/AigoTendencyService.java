package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoTendencyEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoTendencyService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getTendencyList(plusActiveRecord dbEntity) throws Exception;

    Integer setTendencyExcute(plusActiveRecord dbEntity, AigoTendencyEntity aigoTendencyEntity) throws Exception;
    Integer setTendencyDelete(plusActiveRecord dbEntity, AigoTendencyEntity aigoTendencyEntity) throws Exception;

}