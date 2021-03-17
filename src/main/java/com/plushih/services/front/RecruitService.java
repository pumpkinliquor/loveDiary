package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.RecruitEntity;
import com.plushih.entities.CommonResultEntity;

public interface RecruitService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getRecruitList(plusActiveRecord dbEntity) throws Exception;

    Integer setRecruitExcute(plusActiveRecord dbEntity, RecruitEntity bannerEntity) throws Exception;
    Integer setRecruitDelete(plusActiveRecord dbEntity, RecruitEntity bannerEntity) throws Exception;

}