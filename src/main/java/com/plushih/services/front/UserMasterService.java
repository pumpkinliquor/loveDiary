package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserMasterEntity;

public interface UserMasterService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getUserList(plusActiveRecord dbEntity) throws Exception;

    Integer setUserExcute(plusActiveRecord dbEntity, UserMasterEntity userMasterEntity) throws Exception;
    Integer setUserDelete(plusActiveRecord dbEntity, UserMasterEntity userMasterEntity) throws Exception;

}