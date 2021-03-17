package com.plushih.services.front;

import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.MenuAdmUsersEntity;
import com.plushih.entities.MenuUsersEntity;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by 
 */
@Service("MenusService")
public class MenusServiceImpl extends CiServiceImpl implements MenusService {

    /**
     * 메뉴 -> 메뉴 리스트
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<MenuUsersEntity> getMenuUsersList(plusActiveRecord dbEntity, MenuUsersEntity userMasterEntity) throws Exception {
        dbEntity.select("mu.*");
        dbEntity.from("plus_menu_users mu");
        dbEntity.join("plus_menu_grant mg", "mu.mu_seq = mg.mu_seq and mg.mg_grant in ('A','R') ",plusActiveRecord.joinType.INNER);
        dbEntity.where("mu.mu_is_use","y");

        List<MenuUsersEntity> dataList = null;
        try {
            dataList = convertReal(getList(dbEntity),new MenuUsersEntity());

            Debug.log(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
    /**
     * 메뉴 -> 메뉴 리스트
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<MenuAdmUsersEntity> getMenuAdmList(plusActiveRecord dbEntity, MenuUsersEntity userMasterEntity) throws Exception {
        dbEntity.select("mu.*,mg.use_yn,mg.mg_grant");
        dbEntity.from("plus_menu_admin mu");
        dbEntity.join("plus_menu_admin_grant mg", "mu.ma_seq = mg.ma_seq and mg.mg_grant in ('A','R') ",plusActiveRecord.joinType.INNER);
        dbEntity.where("mu.ma_is_use","y");

        List<MenuAdmUsersEntity> dataList = null;
        try {
            dataList = convertReal(getList(dbEntity),new MenuAdmUsersEntity());

            Debug.log(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

}