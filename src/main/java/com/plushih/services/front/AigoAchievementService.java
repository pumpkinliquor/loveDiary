package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoAchievementEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoAchievementService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getAchievementList(plusActiveRecord dbEntity) throws Exception;

    Integer setAchievementExcute(plusActiveRecord dbEntity, AigoAchievementEntity aigoAchievementEntity) throws Exception;
    Integer setAchievementDelete(plusActiveRecord dbEntity, AigoAchievementEntity aigoAchievementEntity) throws Exception;

}