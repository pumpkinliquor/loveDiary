package com.plushih.services.admin;

import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.MenuGrantEntity;
import com.plushih.entities.MenuUsersEntity;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hsk3807
 */
@Service("AdminAdmMenusService")
public class AdminAdmMenusServiceImpl extends CiServiceImpl implements AdminAdmMenusService {

    /**
     * 메뉴 -> 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<MenuUsersEntity> getMenuList(plusActiveRecord dbEntity, MenuUsersEntity menuUsersEntity) throws Exception {
//        dbEntity.from("plus_menu_admin a");
//        dbEntity.join("plus_menu_admin b","a.ma_code=b.ma_parent OR a.ma_code =b.ma_code","left");
//        dbEntity.where("a.ma_level","1");
//        dbEntity.order("a.ma_sort");
//        dbEntity.order("b.ma_sort");

        dbEntity.select("(CASE WHEN b.ma_level = 1 THEN a.ma_seq ELSE IFNULL(c.ma_seq,b.ma_seq) END ) ma_seq"
+",(CASE WHEN b.ma_level = 1 THEN a.ma_parent ELSE IFNULL(c.ma_parent,b.ma_parent) END ) ma_parent"
+",IFNULL(c.ma_code,b.ma_code) ma_code"
+",IFNULL(c.ma_module,b.ma_module) ma_module"
+",IFNULL(c.ma_name,b.ma_name) ma_name "
+",IFNULL(c.ma_level,b.ma_level) ma_level"
+",IFNULL(c.ma_sort,b.ma_sort) ma_sort"
+",IFNULL(c.ma_navi,b.ma_navi) ma_navi"
+",IFNULL(c.ma_is_use,b.ma_is_use) ma_is_use"
+",IFNULL(c.ma_icon,b.ma_icon) ma_icon"
+",IFNULL(c.ma_path,b.ma_path) ma_path");
        dbEntity.from("plus_menu_admin a");
        dbEntity.join("plus_menu_admin b","b.ma_level IN ('1','2') AND (CASE WHEN b.ma_level = 1 THEN  a.ma_code=b.ma_code ELSE  a.ma_code=b.ma_parent END )","left");
        dbEntity.join("plus_menu_admin c","c.ma_level IN ('2','3') AND (CASE WHEN c.ma_level = 2 THEN  b.ma_code=c.ma_code ELSE  b.ma_code=c.ma_parent END )","left");
        dbEntity.where("a.ma_level","1");
        dbEntity.order("a.ma_sort , b.ma_sort ,c.ma_sort");


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

        if(dbEntity.input.post("umSeq")==null) return new ArrayList<MenuGrantEntity>();
        Integer umSeq= menuGrantEntity.getUmSeq()>0?menuGrantEntity.getUmSeq():(Integer)dbEntity.input.post("umSeq","INT");

        Debug.log("umSequmSequmSequmSequmSequmSequmSequmSequmSeq=============="+umSeq);
        Debug.log("umSequmSequmSequmSequmSequmSequmSequmSequmSeq=============="+umSeq);
        Debug.log("umSequmSequmSequmSequmSequmSequmSequmSequmSeq=============="+umSeq);
        Debug.log("umSequmSequmSequmSequmSequmSequmSequmSequmSeq=============="+umSeq);
        Debug.log("umSequmSequmSequmSequmSequmSequmSequmSequmSeq=============="+umSeq);
        Debug.log("umSequmSequmSequmSequmSequmSequmSequmSequmSeq=============="+umSeq);

        dbEntity.select("(CASE WHEN b.ma_level = 1 THEN a.ma_seq ELSE IFNULL(c.ma_seq,b.ma_seq) END ) ma_seq"
+",(CASE WHEN b.ma_level = 1 THEN a.ma_parent ELSE IFNULL(c.ma_parent,b.ma_parent) END ) ma_parent"
+",IFNULL(c.ma_code,b.ma_code) ma_code"
+",IFNULL(c.ma_module,b.ma_module) ma_module"
+",IFNULL(c.ma_name,b.ma_name) ma_name "
+",IFNULL(c.ma_level,b.ma_level) ma_level"
+",IFNULL(c.ma_sort,b.ma_sort) ma_sort"
+",IFNULL(c.ma_navi,b.ma_navi) ma_navi"
+",IFNULL(c.ma_is_use,b.ma_is_use) ma_is_use"
+",IFNULL(c.ma_icon,b.ma_icon) ma_icon"
+",d.mg_grant,d.use_yn,"+umSeq + " um_seq");
        dbEntity.from("plus_menu_admin a");
        dbEntity.join("plus_menu_admin b","b.ma_level IN ('1','2') AND (CASE WHEN b.ma_level = 1 THEN  a.ma_code=b.ma_code ELSE  a.ma_code=b.ma_parent END )","left");
        dbEntity.join("plus_menu_admin c","c.ma_level IN ('2','3') AND (CASE WHEN c.ma_level = 2 THEN  b.ma_code=c.ma_code ELSE  b.ma_code=c.ma_parent END )","left");
        dbEntity.join("plus_menu_admin_grant d","(CASE WHEN b.ma_level = 1 THEN a.ma_seq ELSE IFNULL(c.ma_seq,b.ma_seq) END )=d.ma_seq and d.um_seq="+umSeq,"left");

        dbEntity.where("a.ma_parent","ROOT");
        dbEntity.order("a.ma_sort , b.ma_sort ,c.ma_sort");
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
        dbEntity.from("plus_menu_admin_grant");
        try {
            setBatch(dbEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}