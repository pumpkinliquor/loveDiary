package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.UserGroupEntity;
import com.plushih.entities.UserMasterEntity;

import java.util.List;

public interface UsersService {
  List<UserMasterEntity> getUserMasterList(plusActiveRecord dbEntity, UserMasterEntity userMasterEntity) throws Exception;
  List<UserGroupEntity> getUserGroupList(plusActiveRecord dbEntity, UserGroupEntity userGroupEntity) throws Exception;

  UserMasterEntity getUserInfo (plusActiveRecord dbEntity,UserMasterEntity userMasterEntity) throws Exception;


}