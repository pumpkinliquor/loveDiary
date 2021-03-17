package com.plushih.controllers.ajax.plusadmin;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.DateUtils;
import com.plushih.common.utils.HashUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.ajax.AjaxCodeController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserGroupEntity;
import com.plushih.entities.UserMasterEntity;
import com.plushih.services.admin.AdminUsersService;
import com.plushih.services.ci.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("plusadmin/ajax/users")
public class AjaxUsersAdminController extends AjaxCodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxUsersAdminController.class );


    @Autowired
    private AdminUsersService adminUsersService;


    @Autowired
    private CommonService commonService;



    /**
     * 사용자관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/useraction", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity userExcute (UserMasterEntity userMasterEntity,HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        db.where("um_seq",db.input.post("umSeq"));
        db.from("plus_user_master");
        Map<String,Object> row = commonService.getRow(db);
        if(row.size()>0){

            String umPw = (String)row.get("umPw");

            db.clearQuery();
            if(HashUtils.encryptSHA256(db.input.post("now_pass")).equals(umPw)){
                db.add("um_pw",HashUtils.encryptSHA256(db.input.post("new_pass")));
                db.where("um_seq",db.input.post("umSeq"));
                db.from("plus_user_master");
                commonService.setUpdate(db);

                db.flag= plusQueryBuilder.inType.UPDATE;
                commonResultEntity = db.getOut();
            }
        } else {
            db.flag= plusQueryBuilder.inType.ERR;
            commonResultEntity = db.getOut();
        }
        //Integer umSeq = adminUsersService.setUserExcute(db,userMasterEntity);

        CoreController.logEnd(fullName);
        return commonResultEntity;
    }


}