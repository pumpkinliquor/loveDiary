package com.plushih.controllers.totaladmin.ajax;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.DateUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.ajax.AjaxCodeController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.MenuGrantEntity;
import com.plushih.entities.MenuUsersEntity;
import com.plushih.services.admin.AdminMenusService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("totaladmin/ajax/configs")
public class AdminAjaxConfigsController extends AjaxCodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminAjaxConfigsController.class );

    @Autowired
    private AdminMenusService menuService;

    @Autowired
    private CommonService commonService;

    /**
     * 메뉴리스트
     * @param menuUsersEntity
     * @param request
     * @param response
     * @param localeParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  "/codeList", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity codeList (MenuUsersEntity menuUsersEntity, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        db.from("plus_code_master");
        db.order("cm_gubun,cm_code");

        //List<MenuUsersEntity> userList = menuService.getMenuList(db,menuUsersEntity);
        commonResultEntity.setResultList(commonService.getList(db));
        int cnount = commonService.getCount(db);
        commonResultEntity.setDraw(db.draw);
        commonResultEntity.setRecordsTotal(cnount);
        commonResultEntity.setRecordsFiltered(cnount);
        logEnd(fullName);
        return commonResultEntity;
    }

    /**
     * 메뉴리스트
     * @param menuUsersEntity
     * @param request
     * @param response
     * @param localeParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  "/languageList", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity languageList (MenuUsersEntity menuUsersEntity, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        db.from("plus_language");
        db.order("lg_seq asc");

        if(!StringUtils.isEmpty(db.input.getSearchString())){
            db.like("lg_title",db.input.getSearchString());
        }

        //List<MenuUsersEntity> userList = menuService.getMenuList(db,menuUsersEntity);
        commonResultEntity.setResultList(commonService.getList(db));
        int cnount = commonService.getCount(db);
        commonResultEntity.setDraw(db.draw);
        commonResultEntity.setRecordsTotal(cnount);
        commonResultEntity.setRecordsFiltered(cnount);
        logEnd(fullName);
        return commonResultEntity;
    }

}