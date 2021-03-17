package com.plushih.services.admin;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.DateUtils;
import com.plushih.entities.UserGroupEntity;
import com.plushih.entities.UserMasterEntity;
import com.plushih.services.ci.CiServiceImpl;
import com.plushih.entities.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created by 
 */
@Service("AdminUsersService")
public class AdminUsersServiceImpl extends CiServiceImpl implements AdminUsersService {

    /**
     * 회원 -> 회원 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<UserMasterEntity> getUserMasterList(plusActiveRecord dbEntity, UserMasterEntity userMasterEntity) throws Exception {
        dbEntity.select("a.um_seq"
        +" , a.bi_seq"
        +" , a.ug_seq"
        +" , a.ab_seq"
        +" , a.um_id"
        +" , a.um_name"
        +" , a.um_hp"
        +" , a.um_tel"
        +" , a.um_email"
        +" , a.um_um_addr"
        +" , a.um_zipcode"
        +" , a.um_company"
        +" , a.um_company_sub"
        +" , a.um_sort"
        +" , a.um_jichek"
        +" , a.um_jicgup"
        +" , a.um_work"
        +" , a.um_type"
        +" , a.um_in_date"
        +" , a.um_out_date"
        +" , a.um_img"
        +" , a.um_etc"
        +" , a.um_step"
        +" , a.reg_date "
        +" ,(case when b.bi_name is null then '미정' else b.bi_name end) as   bi_name"
        +" ,(case when d.ug_name is null then '미정' else d.ug_name end) as   ug_name");
        dbEntity.from("plus_user_master a");
        dbEntity.join("plus_business_info b","a.bi_seq=b.bi_seq","left");
//        dbEntity.join("plus_assets_building c","a.ab_seq=c.ab_seq","left");
        dbEntity.join("plus_user_group d","a.ug_seq=d.ug_seq","left");

        List<UserMasterEntity> dataList = null;
        try {
            dataList = convert(getList(dbEntity),new UserMasterEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }


    /**
     * 자산 -> 건물 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<UserGroupEntity> getUserGroupList(plusActiveRecord dbEntity, UserGroupEntity userGroupEntity) throws Exception {
        dbEntity.select("ug_seq"
    +" , ug_name"
    +" , ug_is_default"
    +" , reg_date"
    +" , ug_order"
    +" , ug_permission"
    +" , ug_desc ");
        dbEntity.from("plus_user_group a");
        dbEntity.order("a.ug_order");

        List<UserGroupEntity> dataList = null;
        try {
            dataList = convert(getList(dbEntity),userGroupEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    @Override
    public Integer setUserExcute(plusActiveRecord dbEntity,UserMasterEntity userMasterEntity) throws Exception {

        dbEntity.from("plus_user_master");
        try {
            _INDB(dbEntity);
            dbEntity.checkInsertUpdate();
            if (dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)) {
                /*사후 처리 하시고*/
                setInsert(dbEntity);
            } else if (dbEntity.flag.equals(plusQueryBuilder.queryType.UPDATE)) {
                /*사후 처리 하시고*/
                setUpdate(dbEntity);
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public Integer setUserGroupExcute(plusActiveRecord dbEntity,UserGroupEntity userGroupEntity) throws Exception {

        dbEntity.from("plus_user_group");
        try {
            _INDB(dbEntity);
            dbEntity.checkInsertUpdate();
            if (dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)) {
                /*사후 처리 하시고*/
                setInsert(dbEntity);
            } else if (dbEntity.flag.equals(plusQueryBuilder.queryType.UPDATE)) {
                /*사후 처리 하시고*/
                setUpdate(dbEntity);
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public Integer setUserPermissionExcute(plusActiveRecord dbEntity) throws Exception {

        dbEntity.from("plus_user_master");
        try {

            //setBatch(dbEntity);
            dbEntity._values = new LinkedHashMap<String,String>();
            for(HashMap<String,String> row : dbEntity.batchItem) {
                dbEntity.add("ug_seq",row.get("ugSeq"),false);
                dbEntity.add("upd_date", DateUtils.getDate2String(new Date()));
                dbEntity.where("um_seq",row.get("umSeq"));
                setUpdate(dbEntity);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}