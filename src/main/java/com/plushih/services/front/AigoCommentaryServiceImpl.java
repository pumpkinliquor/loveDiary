package com.plushih.services.front;

import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.services.ci.CiServiceImpl;
import com.plushih.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("AigoCommentaryService")
public class AigoCommentaryServiceImpl extends CiServiceImpl implements AigoCommentaryService {
	
	/**
	 * @ClassName	: AigoCommentaryServiceImpl.java
	 * @Method		: getCommentaryList
	 * @Date		: 2021. 1. 8. 
	 * @author		: dev.yklee
	 * @Description	: 해설 리스트 조회
	 */
	@Override
	public List<AigoCommentaryEntity> getCommentaryList(plusActiveRecord dbEntity, AigoCommentaryEntity aigoCommentaryEntity) throws Exception {
		
		dbEntity.select("cac.cmtr_id"
						+", caq.lev_id"
						+", caq.sub_id"
						+", cau.unit_id"
						+", caq.acv_id"
						+", caq.qst_id"
						+", caq.qst_key"
						+", cac.cmtr_key"
						+", cac.cmtr_type"
						+", cac.cmtr_text"
						+", cac.cmtr_value"
						+", cac.use_yn"
						+", cac.reg_um_seq"
						+", pum.um_id as reg_um_id"
						+", pum.um_name as reg_um_name"
						+", cac.reg_date");
		dbEntity.from("cb_aigo_commentary as cac");
		dbEntity.join("cb_aigo_question as caq", "cac.qst_id = caq.qst_id");
		dbEntity.join("cb_aigo_unit as cau", "caq.unit_id = cau.unit_id");
		dbEntity.join("plus_user_master as pum", "cac.reg_um_seq = pum.um_seq", "left");

		// 검색조건 처리
		// 1. 날짜
		 if(!StringUtils.isEmpty(dbEntity.input.get_post("sdate")) && !StringUtils.isEmpty(dbEntity.input.get_post("edate"))){
             dbEntity.gteq("substr(cac.reg_sysdate,1,10)",dbEntity.input.get_post("sdate"));
             dbEntity.lteq("substr(cac.reg_sysdate,1,10)",dbEntity.input.get_post("edate"));
        } else if(!StringUtils.isEmpty(dbEntity.input.get_post("sdate"))){
            dbEntity.gteq("substr(cac.reg_sysdate,1,10)",dbEntity.input.get_post("sdate"));
        } else if(!StringUtils.isEmpty(dbEntity.input.get_post("edate"))){
            dbEntity.lteq("substr(cac.reg_sysdate,1,10)",dbEntity.input.get_post("edate"));

        }
		// 2. 상태
		if(!StringUtils.isEmpty(dbEntity.input.get_post("searchUseYn"))){
			dbEntity.where("cac.use_yn", dbEntity.input.get_post("searchUseYn"));
		}
		// 3. 분류 (레벨/과목/단원/성취기준 순서)
		if(!StringUtils.isEmpty(dbEntity.input.get_post("levId"))){
			dbEntity.where("caq.lev_id", dbEntity.input.get_post("levId"));
		}
		if(!StringUtils.isEmpty(dbEntity.input.get_post("subId"))){
			dbEntity.where("caq.sub_id", dbEntity.input.get_post("subId"));
		}
		if(!StringUtils.isEmpty(dbEntity.input.get_post("unitId"))){
			dbEntity.where("cau.unit_id", dbEntity.input.get_post("unitId"));
		}
		if(!StringUtils.isEmpty(dbEntity.input.get_post("acvId"))){
			dbEntity.where("caq.acv_id", dbEntity.input.get_post("acvId"));
		}
		// 4. 검색타입 및 검색문구
		if(!StringUtils.isEmpty(dbEntity.input.get_post("searchType"))){
			String searchTextType = dbEntity.input.get_post("searchType");
			String searchText = dbEntity.input.get_post("searchString");
			if("qstKey".equals(searchTextType)) {
				dbEntity.where("caq.qst_key", searchText);
			}else if("cmtrKey".equals(searchTextType)) {
				dbEntity.where("cac.cmtr_key", searchText);
			}else if("notKey".equals(searchTextType)) {
				dbEntity.where("not_key", searchText);
			}
		}
		// 정렬 설정
		dbEntity.order("cac.reg_sysdate", "desc");
		
		List<AigoCommentaryEntity> dataList = null;
		try {
			dataList = convert(getList(dbEntity), new AigoCommentaryEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	/**
	 * @ClassName	: AigoCommentaryServiceImpl.java
	 * @Method		: setCommentaryExcute
	 * @Date		: 2021. 1. 10. 
	 * @author		: dev.yklee
	 * @Description	: 해설 등록/수정 저장
	 */
	@Override
	public Integer setCommentaryExcute(plusActiveRecord dbEntity, AigoCommentaryEntity aigoCommentaryEntity) throws Exception {
		
		dbEntity.from("cb_aigo_commentary");
		
		try {
			_INDB(dbEntity);
			dbEntity.checkInsertUpdate();
			
			Debug.log("dbEntity.flag.=="+dbEntity.flag);
			if(dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)){
				setInsert(dbEntity);

				dbEntity.add("cmtr_key","CMTR"+StringUtils.zeroFill(String.valueOf(dbEntity.insert_id),5));
				dbEntity.where("cmtr_id",String.valueOf(dbEntity.insert_id));
				setUpdate(dbEntity);
			}
			else if(dbEntity.flag.equals(plusQueryBuilder.queryType.UPDATE)){
				dbEntity.add("cmtr_key","CMTR"+StringUtils.zeroFill(String.valueOf(aigoCommentaryEntity.getCmtrId()),5));
				setUpdate(dbEntity);
				Debug.log("dbEntity.input.get(\"cmtrId\")=="+dbEntity.input.get("cmtrId"));
				dbEntity.insert_id = aigoCommentaryEntity.getCmtrId();//bbsEntity.getBbSeq();
			}

			dbEntity.clearWhere();
			dbEntity._values.clear();;
			dbEntity.from("cb_aigo_question");
			dbEntity.where("qst_id",String.valueOf(aigoCommentaryEntity.getQstId()));
			dbEntity.add("cmtr_id",String.valueOf(dbEntity.insert_id ));

			setUpdate(dbEntity);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Debug.log("FLAG = "+dbEntity.flag+" ::::insert_id=="+dbEntity.insert_id+"::::::::::::");
		
		return dbEntity.insert_id;
	}
	
}