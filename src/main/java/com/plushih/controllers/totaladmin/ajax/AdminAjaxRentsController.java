package com.plushih.controllers.totaladmin.ajax;


import com.plushih.common.ci.plusActiveRecord;
import com.plushih.controllers.ajax.AjaxCodeController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.RentMasterEntity;
import com.plushih.services.admin.AdminRentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("totaladmin/ajax/rents")
public class AdminAjaxRentsController extends AjaxCodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminAjaxRentsController.class );


    @Autowired
    private AdminRentsService adminRentsService;
    /**
     * 사용자관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/rentList", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity userList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        List<RentMasterEntity> userList = adminRentsService.getRentList(db,new RentMasterEntity());
        commonResultEntity.setResultList(userList);
        logEnd(fullName);
        return commonResultEntity;
    }


}