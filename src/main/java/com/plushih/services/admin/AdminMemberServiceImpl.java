package com.plushih.services.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.utils.StringUtils;
import com.plushih.services.ci.CiServiceImpl;
import com.plushih.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by 
 */
@Service("AdminMemberService")
public class AdminMemberServiceImpl extends CiServiceImpl implements AdminMemberService {
	
	/**
	 * @ClassName	: AdminMemberServiceImpl.java
	 * @Method		: getUserMemberList
	 * @Date		: 2021. 1. 6. 
	 * @author		: dev.yklee
	 * @Description	: Aigo 사용자 리스트 조회
	 */
	@Override
	public List<UserMemberEntity> getUserMemberList(plusActiveRecord dbEntity, UserMemberEntity userMemberEntity) throws Exception {
		dbEntity.select("m.mem_id"
						+", m.mem_userid"
						+", m.mem_email"
		//				+", m.mem_password"
						+", m.mem_username"
						+", m.mem_nickname"
						+", m.mem_level"
						+", m.mem_point"
						+", m.mem_homepage"
						+", m.mem_phone"
						+", m.mem_birthday"
						+", m.mem_sex"
						+", m.mem_zipcode"
						+", m.mem_address1"
						+", m.mem_address2"
						+", m.mem_address3"
						+", m.mem_address4"
						+", m.mem_receive_email"
						+", m.mem_use_note"
						+", m.mem_receive_sms"
						+", m.mem_open_profile"
						+", m.mem_denied"
						+", m.mem_email_cert"
						+", m.mem_register_datetime"
						+", m.mem_register_ip"
						+", m.mem_lastlogin_datetime"
						+", m.mem_lastlogin_ip"
						+", m.mem_is_admin"
						+", m.mem_profile_content"
						+", m.mem_adminmemo"
						+", m.mem_following"
						+", m.mem_followed"
						+", m.mem_icon"
						+", m.mem_photo"
						+", m.mem_class"
						+", m.mem_grade"
						+", m.mem_sub_id"
						+", m.mem_join_channel"
						+", mta.mta_terms_of_service"
						+", mta.mta_privacy_policy"
						+", mta.mta_marketing"
						+", mta.mta_register_datatime"
						+", mu.mem_status");
		dbEntity.from("cb_member as m");
		dbEntity.join("cb_member_terms_agree as mta","m.mem_id = mta.mem_id","left");
		dbEntity.join("cb_member_userid as mu","m.mem_id = mu.mem_id","left");
		dbEntity.order("m.mem_id","desc");
		// App 설치 관련정보 테이블 생성 후 조인문 이부분에 추가해야 함
		if(!StringUtils.isEmpty(dbEntity.request.getParameter("searchString"))){
			dbEntity.like("m.mem_userid", dbEntity.request.getParameter("searchString"));
			dbEntity.or_like("m.mem_nickname", dbEntity.request.getParameter("searchString"));
		}
		
		List<UserMemberEntity> dataList = null;
		try {
			dataList = convert(getList(dbEntity), new UserMemberEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	/**
	 * @ClassName	: AdminMemberServiceImpl.java
	 * @Method		: getUserMemberNickHistoryList
	 * @Date		: 2021. 1. 8. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 닉네임 히스토리 조회
	 */
	@Override
	public List<UserMemberHistoryEntity> getUserMemberNickHistoryList(plusActiveRecord dbEntity, UserMemberHistoryEntity userMemberHistoryEntity) throws Exception {
		
		dbEntity.select("mnh_nickname"
						+", mnh_regist_datetime");
		dbEntity.from("cb_member_nickname_history");
		dbEntity.where("mem_id", String.valueOf(userMemberHistoryEntity.getMemId()));
		dbEntity.order("mnh_regist_datetime", "desc");
		
		List<UserMemberHistoryEntity> dataList = null;
		try {
			dataList = convert(getList(dbEntity), new UserMemberHistoryEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	/**
	 * @ClassName	: AdminMemberServiceImpl.java
	 * @Method		: getUserMemberLevelHistoryList
	 * @Date		: 2021. 1. 8. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 레벨 히스토리 조회
	 */
	@Override
	public List<UserMemberHistoryEntity> getUserMemberLevelHistoryList(plusActiveRecord dbEntity, UserMemberHistoryEntity userMemberHistoryEntity) throws Exception {
		
		dbEntity.select("mlh_to"
						+", mlh_datetime");
		dbEntity.from("cb_member_level_history");
		dbEntity.where("mem_id", String.valueOf(userMemberHistoryEntity.getMemId()));
		dbEntity.order("mlh_datetime", "desc");
		
		List<UserMemberHistoryEntity> dataList = null;
		try {
			dataList = convert(getList(dbEntity), new UserMemberHistoryEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	/**
	 * @ClassName	: AdminMemberServiceImpl.java
	 * @Method		: getUserMemberPushInfoList
	 * @Date		: 2021. 1. 8. 
	 * @author		: dev.yklee
	 * @Description	: 푸시 수신동의항목 조회
	 */
	@Override
	public List<UserMemberEntity> getUserMemberPushInfoList(plusActiveRecord dbEntity, UserMemberEntity userMemberEntity) throws Exception {
		
		dbEntity.select("mlh_to"
				+", mlh_datetime");
		dbEntity.from("cb_member_level_history");
		dbEntity.where("mem_id", String.valueOf(userMemberEntity.getMemId()));
		dbEntity.order("mlh_datetime", "desc");
		
		List<UserMemberEntity> dataList = null;
		try {
			dataList = convert(getList(dbEntity), new UserMemberEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	/**
	 * @ClassName	: AdminMemberServiceImpl.java
	 * @Method		: getUserMemberUsedInfo
	 * @Date		: 2021. 1. 12. 
	 * @author		: dev.yklee
	 * @Description	: 회원 앱 사용정보 요약 카운트 조회
	 */
	@Override
	public UserMemberStatisticsEntity getUserMemberUsedInfo(plusActiveRecord dbEntity, UserMemberStatisticsEntity userMemberStatisticsEntity) throws Exception {
		
		dbEntity.select("(select sum(scd_count) from cb_stat_count_date where scd_date between '"+dbEntity.input.get_post("sdate").replace("-", "")+"' and '"+dbEntity.input.get_post("edate").replace("-", "")+"') AS total_user_count"			// 추후에 카운트 쿼리 추가해야 함
				+ ",(select count(temp_id) from cb_aigo_temp_info where substr(reg_sysdate,1,10) between '"+dbEntity.input.get_post("sdate")+"' and '"+dbEntity.input.get_post("edate")+"') as total_ins_count"
				+ ",(select count(mem_id) from cb_member where mem_register_datetime between '"+dbEntity.input.get_post("sdate").replace("-", "")+"' and '"+dbEntity.input.get_post("edate").replace("-", "")+"') as total_join_count");
		dbEntity.from("dual");
		
		UserMemberStatisticsEntity usedData = new UserMemberStatisticsEntity();
		ObjectMapper oMapper = new ObjectMapper();
		
		try {
			usedData = oMapper.convertValue(getRow(dbEntity), usedData.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usedData;
	}
	
	/**
	 * @ClassName	: AdminMemberServiceImpl.java
	 * @Method		: getUserMemberUsedDataList
	 * @Date		: 2021. 1. 12. 
	 * @author		: dev.yklee
	 * @Description	: 회원 앱 사용정보 데이터 조회 리스트
	 */
	@Override
	public List<UserMemberStatisticsEntity> getUserMemberUsedDataList(plusActiveRecord dbEntity, UserMemberStatisticsEntity userMemberStatisticsEntity) throws Exception {
		
		dbEntity.select("calendar.date as baseDate"
				+", (select scd_count from cb_stat_count_date where scd_date =  calendar.date) AS user_count"			// 추후에 카운트 쿼리 추가해야 함
				+", ifnull(appins.ins_count, 0) AS ins_count"
				+", ifnull(joinuser.join_user_count, 0) AS join_count");
		dbEntity.from("("
				+ "	select"
				+ "		cal.date"
				+ "	from ("
				+ "		select curdate() - interval (a.a + (10 * b.a) + (100 * c.a)) day as date"
				+ "		from (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as a"
				+ "		cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as b"
				+ "		cross join (select 0 as a union all select 1 union all select 2 union all select 3 union all select 4 union all select 5 union all select 6 union all select 7 union all select 8 union all select 9) as c"
				+ "	) as cal"
				+ "	where cal.date between '"+dbEntity.input.get_post("sdate").replace("-", "")+"' and '"+dbEntity.input.get_post("edate").replace("-", "")+"'" 		// 날짜 파라미터 적용해야함
				+ ") as calendar");
		dbEntity.join("("
				+ "	select"
				+ "		ins.reg_date"
				+ "		, count(ins.app_user) as ins_count"
				+ "	from ("
				+ "		select"
				+ "			date_format(ati.reg_date, '%y%m%d') as reg_date"
				+ "			, ati.temp_id as app_user"
				+ "		from cb_aigo_temp_info as ati"
				+ "	) as ins"
				+ "	group by ins.reg_date"
				+ ") as appins", "calendar.date = appins.reg_date", "left");
		dbEntity.join("("
				+ "	select"
				+ "		ju.reg_date"
				+ "		, count(ju.join_user) as join_user_count"
				+ "	from ("
				+ "		select"
				+ "			 date_format(cm.mem_register_datetime, '%y%m%d') as reg_date"
				+ "			 , cm.mem_id as join_user"
				+ "		from cb_member as cm"
				+ "	) as ju"
				+ "	group by ju.reg_date"
				+ ")as joinuser", "calendar.date = joinuser.reg_date", "left");

		if(StringUtils.isEmpty(dbEntity.input.get_post("order"))){
			dbEntity.order("calendar.date", "asc");
		} else {
			dbEntity.order(dbEntity.input.get_post("order"));
		}

		
		List<UserMemberStatisticsEntity> dataList = null;
		try {
			dataList = convert(getList(dbEntity), new UserMemberStatisticsEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
}