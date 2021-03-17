package com.plushih.services.admin;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.RentMasterEntity;

import java.util.List;

public interface AdminRentsService {
  /**
   * 임대리스트
   * @param dbEntity
   * @param mentMasterEntity
   * @return
   * @throws Exception
   */
  List<RentMasterEntity> getRentList(plusActiveRecord dbEntity, RentMasterEntity mentMasterEntity) throws Exception;

}