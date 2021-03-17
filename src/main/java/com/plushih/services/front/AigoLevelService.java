package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoLevelEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoLevelService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getLevelList(plusActiveRecord dbEntity) throws Exception;

    Integer setLevelExcute(plusActiveRecord dbEntity, AigoLevelEntity aigoLevelEntity) throws Exception;
    Integer setLevelDelete(plusActiveRecord dbEntity, AigoLevelEntity aigoLevelEntity) throws Exception;

}