package com.plushih.services.front;

import com.plushih.entities.BbsEntity;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.CommonResultEntity;

import java.util.List;

public interface BbsService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getBbsList(plusActiveRecord dbEntity) throws Exception;

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getBbsDetail(plusActiveRecord dbEntity) throws Exception;

    Integer setBbsExcute(plusActiveRecord dbEntity, BbsEntity businessInfoEntity) throws Exception;
    Integer setBbsDelete(plusActiveRecord dbEntity, BbsEntity businessInfoEntity) throws Exception;

}