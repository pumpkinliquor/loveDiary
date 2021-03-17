package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.MenuAdmUsersEntity;
import com.plushih.entities.MenuUsersEntity;

import java.util.List;

public interface MenusService {
  List<MenuUsersEntity> getMenuUsersList(plusActiveRecord dbEntity, MenuUsersEntity menuUsersEntity) throws Exception;
  List<MenuAdmUsersEntity> getMenuAdmList(plusActiveRecord dbEntity, MenuUsersEntity menuUsersEntity) throws Exception;


}