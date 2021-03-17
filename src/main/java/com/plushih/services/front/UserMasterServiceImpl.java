package com.plushih.services.front;

import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.HashUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.UserMasterEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 
 */
@Service("UserMasterService")
public class UserMasterServiceImpl extends CiServiceImpl implements UserMasterService {

    /**
     * 게시판 -> 게시판 이력 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public CommonResultEntity getUserList(plusActiveRecord dbEntity) throws Exception {

        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        dbEntity.select("bb.*");
        dbEntity.from("plus_user_master bb");

        if(!StringUtils.isEmpty(dbEntity.input.getSearchString())){
            dbEntity.like("um_name",dbEntity.input.getSearchString());
        }
        dbEntity.order("reg_date","desc");

        CommonResultEntity res = new CommonResultEntity();
        try {
            dataHashList  = getList(dbEntity);

            int cnount = getCount(dbEntity);
            res.setDraw(dbEntity.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);


            Debug.log(dataHashList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 게시판 -> 게시판 등록/수정
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Integer setUserExcute(plusActiveRecord dbEntity, UserMasterEntity userMasterEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("plus_user_master");
        try {
            _INDB(dbEntity);
            dbEntity.checkInsertUpdate();


            Debug.log("dbEntity.flag.=="+dbEntity.flag);
            dbEntity.add("um_pw", HashUtils.encryptSHA256(dbEntity.input.post("umPw")));
            if(dbEntity.flag.equals(plusQueryBuilder.queryType.INSERT)){
                //dbEntity._values.put("um_pw",dbEntity._values.get("um_pw"));

                setInsert(dbEntity);
                //Debug.log((new Gson()).toJson(dbEntity));
            }
            else if(dbEntity.flag.equals(plusQueryBuilder.queryType.UPDATE)){
                setUpdate(dbEntity);
                Debug.log("dbEntity.input.get(\"bbSeq\")=="+dbEntity.input.get("bbSeq"));
//                if(dbEntity.input.get("bbSeq")!=null){
//
//                }
                dbEntity.insert_id = userMasterEntity.getUmSeq();//bbsEntity.getBbSeq();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Debug.log("FLAG = "+dbEntity.flag+" ::::insert_id=="+dbEntity.insert_id+"::::::::::::");

        return dbEntity.insert_id;
    }

    /**
     * 게시판 -> 게시판 등록/수정
     *
     * @param dbEntity
     * @return
     */
    @Override
    public Integer setUserDelete(plusActiveRecord dbEntity, UserMasterEntity aigoUserEntity) throws Exception {

        List<HashMap<String, Object>> dataMapList = new ArrayList<HashMap<String, Object>>();

        HashMap<String,Object> daraMapRow = new HashMap<String,Object>();

        dbEntity.from("plus_user_master");
        try {

            if(dbEntity.flag.equals(plusQueryBuilder.queryType.DELETE)){
                setDelete(dbEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Debug.log("FLAG = "+dbEntity.flag+" ::::insert_id=="+dbEntity.insert_id+"::::::::::::");

        return dbEntity.insert_id;
    }


}