package com.plushih.services.admin;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.MenuGrantEntity;
import com.plushih.entities.MenuUsersEntity;

import java.util.List;

public interface AdminMenusService {
  /**
   * 메뉴리스트
   * @param dbEntity
   * @param menuUsersEntity
   * @return
   * @throws Exception
   */
  List<MenuUsersEntity> getMenuList(plusActiveRecord dbEntity, MenuUsersEntity menuUsersEntity) throws Exception;

  /**
   * 메뉴권한리스트
   * @param dbEntity
   * @param menuGrantEntity
   * @return
   * @throws Exception
   */
  List<MenuGrantEntity> getMenuGrantList(plusActiveRecord dbEntity, MenuGrantEntity menuGrantEntity) throws Exception;

  /**
   * 메뉴권한수정
   * @param dbEntity
   * @return
   * @throws Exception
   */
  Integer getMenuGrantBatch(plusActiveRecord dbEntity) throws Exception;

}