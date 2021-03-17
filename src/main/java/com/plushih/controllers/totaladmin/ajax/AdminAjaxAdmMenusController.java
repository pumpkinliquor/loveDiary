package com.plushih.controllers.totaladmin.ajax;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.DateUtils;
import com.plushih.controllers.ajax.AjaxCodeController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.MenuGrantEntity;
import com.plushih.entities.MenuUsersEntity;
import com.plushih.services.admin.AdminAdmMenusService;
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
@RequestMapping("totaladmin/ajax/admmenus")
public class AdminAjaxAdmMenusController extends AjaxCodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminAjaxAdmMenusController.class );

    @Autowired
    private AdminAdmMenusService menuService;

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
    @RequestMapping(value =  "/menuList", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity menuList (MenuUsersEntity menuUsersEntity, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        List<MenuUsersEntity> userList = menuService.getMenuList(db,menuUsersEntity);
        commonResultEntity.setResultList(userList);
        commonResultEntity.setDraw(db.draw);
        commonResultEntity.setRecordsTotal(userList.size());
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
    @RequestMapping(value =  "/menuExcute", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity menuExcute (MenuUsersEntity menuUsersEntity, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
//        List<MenuUsersEntity> userList = menuService.getMenuList(db,menuUsersEntity);
//        commonResultEntity.setResultList(userList);
//        logEnd(fullName);

        db.from("plus_menu_users");
        try {
            commonService._INDB(db);
            db.checkInsertUpdate();
            if (db.flag.equals(plusQueryBuilder.queryType.INSERT)) {
                /*사후 처리 하시고*/
                commonService.setInsert(db);
            } else if (db.flag.equals(plusQueryBuilder.queryType.UPDATE)) {
                /*사후 처리 하시고*/
                commonService.setUpdate(db);
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResultEntity;
    }


    /**
     * 메뉴권한리스트
     * @param menuGrantEntity
     * @param request
     * @param response
     * @param localeParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  "/menuGrant", method = {RequestMethod.POST})
    public @ResponseBody
    CommonResultEntity menuGrant (MenuGrantEntity menuGrantEntity, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        List<MenuGrantEntity> userList = menuService.getMenuGrantList(db,menuGrantEntity);
        commonResultEntity.setResultList(userList);
        commonResultEntity.setDraw(db.draw);
        commonResultEntity.setRecordsTotal(userList.size());
        logEnd(fullName);
        return commonResultEntity;
    }

    /**
     * 메뉴권한수정
     * @param menuGrantEntity
     * @param request
     * @param response
     * @param localeParam
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  "/menuGrantBatch", method = {RequestMethod.POST})
    public @ResponseBody
        CommonResultEntity menuGrantBatch (MenuGrantEntity menuGrantEntity, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        //Debug.log(db.input.post("putData"));
        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String,String>> menuGrantList = mapper.readValue(db.input.post("putData"), new TypeReference<List<HashMap<String,String>>>(){});
        HashMap<String,String> addRow=  new HashMap<String,String>();
        addRow.put("mg_gubun","U");
        addRow.put("reg_date", DateUtils.getDate2String(new Date()));

        db.batchItem(menuGrantList,addRow);
        menuService.getMenuGrantBatch(db);
        logEnd(fullName);
        return commonResultEntity;
    }
}