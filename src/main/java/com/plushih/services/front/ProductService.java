package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.ProductEntity;
import com.plushih.entities.CommonResultEntity;

public interface ProductService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getProductList(plusActiveRecord dbEntity,ProductEntity bannerEntity) throws Exception;

    Integer setProductExcute(plusActiveRecord dbEntity, ProductEntity bannerEntity) throws Exception;
    Integer setProductDelete(plusActiveRecord dbEntity, ProductEntity bannerEntity) throws Exception;

}