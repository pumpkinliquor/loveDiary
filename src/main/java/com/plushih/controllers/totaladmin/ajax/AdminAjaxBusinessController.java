package com.plushih.controllers.totaladmin.ajax;


import com.plushih.common.ci.Cache;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.BusinessClientEntity;
import com.plushih.entities.BusinessInfoEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.admin.AdminBusinessService;
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

/**
 * hsk3807
 */
@Controller
@RequestMapping("totaladmin/ajax/business")
public class AdminAjaxBusinessController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminAjaxBusinessController.class );

    @Autowired
    private AdminBusinessService adminBusinessService;
    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/businessInfoList", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity businessInfoList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        List<BusinessInfoEntity> businessInfoList = adminBusinessService.getBusinessInfoList(db);



        commonResultEntity.setResultList(businessInfoList);
        logEnd(fullName);
        return commonResultEntity;
    }
    /**
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/businessInfoExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity businessInfoExcute (BusinessInfoEntity businessInfoEntity,HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Cache cache = new Cache();
        String rules = cache.get_cache("Admin.businessInfo");

        Debug.log("===rules==="+rules);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        int businessInfoCnt = adminBusinessService.setBusinessInfoExcute(db,businessInfoEntity);
        //commonResultEntity.setResultList(businessInfoList);
        logEnd(fullName);
        return commonResultEntity;
    }

    /**
     * 고객사관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/businessClientList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity getBusinessClientList (HttpServletRequest request, HttpServletResponse response,Locale localeParam  ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        List<BusinessClientEntity> businessClientList = adminBusinessService.getBusinessClientList(db);

        commonResultEntity.setResultList(businessClientList);
        logEnd(fullName);
        return commonResultEntity;
    }

    /**
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/businessClientExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity businessClientExcute (BusinessClientEntity businessClientEntity,HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        int businessInfoCnt = adminBusinessService.setBusinessClientExcute(db,businessClientEntity);
        //commonResultEntity.setResultList(businessInfoList);
        logEnd(fullName);
        return commonResultEntity;
    }

}