package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.AigoCategoryEntity;
import com.plushih.entities.CommonResultEntity;

public interface AigoCategoryService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getCategoryList(plusActiveRecord dbEntity) throws Exception;

    Integer setCategoryExcute(plusActiveRecord dbEntity, AigoCategoryEntity aigoCategoryEntity) throws Exception;
    Integer setCategoryDelete(plusActiveRecord dbEntity, AigoCategoryEntity aigoCategoryEntity) throws Exception;

}