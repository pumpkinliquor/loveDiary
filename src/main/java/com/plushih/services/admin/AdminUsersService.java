package com.plushih.services.admin;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.UserGroupEntity;
import com.plushih.entities.UserMasterEntity;

import java.util.List;

public interface AdminUsersService {
  List<UserMasterEntity> getUserMasterList(plusActiveRecord dbEntity, UserMasterEntity userMasterEntity) throws Exception;
  List<UserGroupEntity> getUserGroupList(plusActiveRecord dbEntity, UserGroupEntity userGroupEntity) throws Exception;
  Integer setUserExcute(plusActiveRecord dbEntity,UserMasterEntity userMasterEntity) throws Exception;
  Integer setUserGroupExcute(plusActiveRecord dbEntity,UserGroupEntity userGroupEntity) throws Exception;
  Integer setUserPermissionExcute(plusActiveRecord dbEntity) throws Exception;



}