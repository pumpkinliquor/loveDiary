package com.plushih.services.admin;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.UserMemberEntity;
import com.plushih.entities.UserMemberHistoryEntity;
import com.plushih.entities.UserMemberStatisticsEntity;

import java.util.List;

/**
 * @PackageName	: com.plushih.services.admin
 * @ClassName	: AdminMemberService.java
 * @Date		: 2021. 1. 6. 
 * @author		: dev.yklee
 * @Description	: Aigo 사용자 리스트 조회
 */
public interface AdminMemberService {
	List<UserMemberEntity> getUserMemberList(plusActiveRecord dbEntity, UserMemberEntity userMemberEntity) throws Exception;
	List<UserMemberHistoryEntity> getUserMemberNickHistoryList(plusActiveRecord dbEntity, UserMemberHistoryEntity userMemberHistoryEntity) throws Exception;
	List<UserMemberHistoryEntity> getUserMemberLevelHistoryList(plusActiveRecord dbEntity, UserMemberHistoryEntity userMemberHistoryEntity) throws Exception;
	List<UserMemberEntity> getUserMemberPushInfoList(plusActiveRecord dbEntity, UserMemberEntity userMemberEntity) throws Exception;
	UserMemberStatisticsEntity getUserMemberUsedInfo(plusActiveRecord dbEntity, UserMemberStatisticsEntity userMemberStatisticsEntity) throws Exception;
	List<UserMemberStatisticsEntity> getUserMemberUsedDataList(plusActiveRecord dbEntity, UserMemberStatisticsEntity userMemberStatisticsEntity) throws Exception;
}