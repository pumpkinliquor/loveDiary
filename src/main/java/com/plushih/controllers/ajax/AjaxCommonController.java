package com.plushih.controllers.ajax;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.Cache;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.constant.LoginSession;
import com.plushih.entities.BbsAttachFileEntity;
import com.plushih.entities.BbsEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.BbsService;
import com.plushih.services.front.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * hsk3807
 */
@Controller
@RequestMapping("front/ajax/common")
public class AjaxCommonController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxCommonController.class );

    @Autowired
    private BbsService bbsService;
    @Autowired
    private CommonService commonService;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/selectLanguage", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity selectLanguage (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        HttpSession session = request.getSession(true);

        if(!StringUtils.isEmpty(db.input.post("language"))){

            if(db.input.post("language").equals("KR")){
                session.setAttribute(LoginSession.LANG, db.input.post("language"));
                session.setAttribute(LoginSession.LANGIDX, "1");
                System.out.println(db.input.post("language"));
            } else if(db.input.post("language").equals("EN")){
                session.setAttribute(LoginSession.LANG, db.input.post("language"));
                session.setAttribute(LoginSession.LANGIDX, "2");
                System.out.println(db.input.post("language"));
            }

        }

        commonResultEntity.getResultList();

        logEnd(fullName);
        return commonResultEntity;
    }



}