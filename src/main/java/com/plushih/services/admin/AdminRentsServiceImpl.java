package com.plushih.services.admin;

import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.entities.RentMasterEntity;
import com.plushih.services.ci.CiServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by hsk3807
 */
@Service("AdminRentsService")
public class AdminRentsServiceImpl extends CiServiceImpl implements AdminRentsService {

    /**
     * 회원 -> 회원 조회
     *
     * @param dbEntity
     * @return
     */
    @Override
    public List<RentMasterEntity> getRentList(plusActiveRecord dbEntity, RentMasterEntity rentMasterEntity) throws Exception {
        dbEntity.select("rm.*,rc.*,ai.*");
        dbEntity.from("plus_rent_master rm");
        dbEntity.join("plus_rent_client rc","rm.rm_seq=rc.rm_seq","INNER");

        dbEntity.join("( SELECT "+
"ar.ar_seq, "+
"ar.ar_name, "+
"ar.af_seq, "+
"af.af_name, "+
"ar.ad_seq, "+
"ad.ad_name, "+
"ar.ab_seq, "+
"ab.ab_name "+
"FROM  plus_assets_room ar "+
"INNER JOIN plus_assets_floor af ON  ar.af_seq=af.af_seq "+
"INNER JOIN plus_assets_dong ad ON  ar.ad_seq=ad.ad_seq "+
"INNER JOIN plus_assets_building ab ON  ar.ab_seq=ab.ab_seq "+
"GROUP BY af.af_seq,ad.ad_seq,ab.ab_seq) join_all ","join_all.ar_seq=rm.ar_seq","INNER");
        //dbEntity.join("plus_zip_info z","ar.rm_seq=z.tb_seq and z.tb_type='RM'","LEFT");
        dbEntity.join("plus_address_info ai","rm.bi_seq=ai.ai_seq and ai.ai_type='RM'", plusQueryBuilder.joinType.LEFT);
        dbEntity.order("rm.reg_date","desc");

        List<RentMasterEntity> dataList = null;
        try {
            dataList = convert(getList(dbEntity),rentMasterEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }


}