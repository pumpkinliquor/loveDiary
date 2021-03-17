package com.plushih.services.front;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.MainBannerEntity;
import com.plushih.entities.CommonResultEntity;

public interface BannerService {

    /**
     * 게시판 조회
     * @param dbEntity
     * @return
     */
    CommonResultEntity getBannerList(plusActiveRecord dbEntity) throws Exception;

    Integer setBannerExcute(plusActiveRecord dbEntity, MainBannerEntity bannerEntity) throws Exception;
    Integer setBannerDelete(plusActiveRecord dbEntity, MainBannerEntity bannerEntity) throws Exception;

}