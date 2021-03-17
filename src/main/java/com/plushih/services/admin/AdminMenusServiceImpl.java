package com.plushih.services.admin;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.MenuGrantEntity;
import com.plushih.entities.MenuUsersEntity;
import com.plushih.entities.*;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by hsk3807
 */
@Service("AdminMenusService")
public class AdminMenusServiceImpl extends CiServiceImpl implements AdminMenusService {

    /**
     * 메뉴 -> 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<MenuUsersEntity> getMenuList(plusActiveRecord dbEntity, MenuUsersEntity menuUsersEntity) throws Exception {
//        dbEntity.from("plus_menu_users a");
//        dbEntity.join("plus_menu_users b","a.mu_code=b.mu_parent OR a.mu_code =b.mu_code","left");
//        dbEntity.where("a.mu_level","1");
//        dbEntity.order("a.mu_sort");
//        dbEntity.order("b.mu_sort");

        dbEntity.select("(CASE WHEN b.mu_level = 1 THEN a.mu_seq ELSE IFNULL(c.mu_seq,b.mu_seq) END ) mu_seq"
+",(CASE WHEN b.mu_level = 1 THEN a.mu_parent ELSE IFNULL(c.mu_parent,b.mu_parent) END ) mu_parent"
+",IFNULL(c.mu_code,b.mu_code) mu_code"
+",IFNULL(c.mu_module,b.mu_module) mu_module"
+",IFNULL(c.mu_name,b.mu_name) mu_name "
+",IFNULL(c.mu_level,b.mu_level) mu_level"
+",IFNULL(c.mu_sort,b.mu_sort) mu_sort"
+",IFNULL(c.mu_navi,b.mu_navi) mu_navi"
+",IFNULL(c.mu_is_use,b.mu_is_use) mu_is_use"
+",IFNULL(c.mu_icon,b.mu_icon) mu_icon"

+",IFNULL(c.mu_path,b.mu_path) mu_path");
        dbEntity.from("plus_menu_users a");
        dbEntity.join("plus_menu_users b","b.mu_level IN ('1','2') AND (CASE WHEN b.mu_level = 1 THEN  a.mu_code=b.mu_code ELSE  a.mu_code=b.mu_parent END )","left");
        dbEntity.join("plus_menu_users c","c.mu_level IN ('2','3') AND (CASE WHEN c.mu_level = 2 THEN  b.mu_code=c.mu_code ELSE  b.mu_code=c.mu_parent END )","left");
        dbEntity.where("a.mu_level","1");
        dbEntity.order("a.mu_sort , b.mu_sort ,c.mu_sort");


        List<MenuUsersEntity> dataList = null;
        try {
            dataList = convert(getList(dbEntity),menuUsersEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }


    /**
     * 메뉴 -> 메뉴권한
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<MenuGrantEntity> getMenuGrantList(plusActiveRecord dbEntity, MenuGrantEntity menuGrantEntity) throws Exception {

        if(dbEntity.input.post("ugSeq")==null) return null;
        Integer ugSeq= menuGrantEntity.getUgSeq()>0?menuGrantEntity.getUgSeq():(Integer)dbEntity.input.post("ugSeq","INT");

        dbEntity.select("(CASE WHEN b.mu_level = 1 THEN a.mu_seq ELSE IFNULL(c.mu_seq,b.mu_seq) END ) mu_seq"
+",(CASE WHEN b.mu_level = 1 THEN a.mu_parent ELSE IFNULL(c.mu_parent,b.mu_parent) END ) mu_parent"
+",IFNULL(c.mu_code,b.mu_code) mu_code"
+",IFNULL(c.mu_module,b.mu_module) mu_module"
+",IFNULL(c.mu_name,b.mu_name) mu_name "
+",IFNULL(c.mu_level,b.mu_level) mu_level"
+",IFNULL(c.mu_sort,b.mu_sort) mu_sort"
+",IFNULL(c.mu_navi,b.mu_navi) mu_navi"
+",IFNULL(c.mu_is_use,b.mu_is_use) mu_is_use"
+",IFNULL(c.mu_icon,b.mu_icon) mu_icon"
+",d.mg_grant,e.ug_name,e.ug_seq");
        dbEntity.from("plus_menu_users a");
        dbEntity.join("plus_menu_users b","b.mu_level IN ('1','2') AND (CASE WHEN b.mu_level = 1 THEN  a.mu_code=b.mu_code ELSE  a.mu_code=b.mu_parent END )","left");
        dbEntity.join("plus_menu_users c","c.mu_level IN ('2','3') AND (CASE WHEN c.mu_level = 2 THEN  b.mu_code=c.mu_code ELSE  b.mu_code=c.mu_parent END )","left");
        dbEntity.join("plus_menu_users_grant d","(CASE WHEN b.mu_level = 1 THEN a.mu_seq ELSE IFNULL(c.mu_seq,b.mu_seq) END )=d.mu_seq and d.ug_seq="+ugSeq,"left");
        dbEntity.join("plus_user_group e","e.ug_seq="+ugSeq,"left");
        dbEntity.where("a.mu_parent","ROOT");
        dbEntity.order("a.mu_sort , b.mu_sort ,c.mu_sort");
        dbEntity.limit(1000);
        List<MenuGrantEntity> dataList = null;
        try {
            dataList = convert(getList(dbEntity),menuGrantEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }


    /**
     * 권한일괄수정
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Integer getMenuGrantBatch(plusActiveRecord dbEntity) throws Exception {
        dbEntity.from("plus_menu_users_grant");
        try {
            setBatch(dbEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}