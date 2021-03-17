package com.plushih.controllers.plusadmin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.plushih.common.constant.Default;
import com.plushih.services.admin.AdminStatisticsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

@Controller
public class PlusMainController {
    private static final Logger LOGGER = LoggerFactory.getLogger( PlusMainController.class );
    
    @Autowired
    private AdminStatisticsService adminStatisticsService;
    
    /**
     * 대쉬보드
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/plusadmin/main", "/plusadmin/dashboard"})
    public String main (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/dashboard";
    }
    @RequestMapping(value = { "/plusadmin/AD_AU_01"})
    public String AD_AU_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_AU_01";
    }
    @RequestMapping(value = { "/plusadmin/AD_AU_02"})
    public String AD_AU_02 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_AU_02";
    }
    @RequestMapping(value = { "/plusadmin/AD_MA_01_01"})
    public String AD_MA_01_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_MA_01_01";
    }
    @RequestMapping(value = { "/plusadmin/AD_MA_01_02"})
    public String AD_MA_01_02 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_MA_01_02";
    }
    @RequestMapping(value = { "/plusadmin/AD_MA_01_03"})
    public String AD_MA_01_03 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_MA_01_03";
    }
    @RequestMapping(value = { "/plusadmin/AD_MA_01_04"})
    public String AD_MA_01_04 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_MA_01_04";
    }
    @RequestMapping(value = { "/plusadmin/AD_MA_01_05"})
    public String AD_MA_01_05 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_MA_01_05";
    }
    @RequestMapping(value = { "/plusadmin/AD_MA_01_06"})
    public String AD_MA_01_06 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_MA_01_06";
    }
    @RequestMapping(value = { "/plusadmin/AD_ME_01"})
    public String AD_ME_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_ME_01";
    }
    @RequestMapping(value = { "/plusadmin/AD_ME_02_01"})
    public String AD_ME_02_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_ME_02_01";
    }
    @RequestMapping(value = { "/plusadmin/AD_ME_02_02"})
    public String AD_ME_02_02 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_ME_02_02";
    }
    @RequestMapping(value = { "/plusadmin/AD_LE_01"})
    public String AD_LE_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_LE_01";
    }
    @RequestMapping(value = { "/plusadmin/AD_LE_02_01"})
    public String AD_LE_02_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_LE_02_01";
    }
    @RequestMapping(value = { "/plusadmin/AD_LE_02_02"})
    public String AD_LE_02_02 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_LE_02_02";
    }
    @RequestMapping(value = { "/plusadmin/AD_LE_02_03"})
    public String AD_LE_02_03 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_LE_02_03";
    }
    @RequestMapping(value = { "/plusadmin/AD_LE_03"})
    public String AD_LE_03 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_LE_03";
    }
    @RequestMapping(value = { "/plusadmin/AD_SA_01"})
    public String AD_SA_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam, @RequestParam Map<String, Object> paramMap) throws Exception {
    	model.addAttribute("data", adminStatisticsService.selectSalesInfoList(paramMap));
    	model.addAttribute(Default.ResultValue.REQUEST_PARAMETER_MAP, paramMap);
        return "/plusadmin/AD_SA_01";
    }
    @RequestMapping(value = { "/plusadmin/AD_SA_02"})
    public String AD_SA_02 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_SA_02";
    }
    @RequestMapping(value = { "/plusadmin/AD_SA_03_01"})
    public String AD_SA_03_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_SA_03_01";
    }
    @RequestMapping(value = { "/plusadmin/AD_SA_03_02"})
    public String AD_SA_03_02 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_SA_03_02";
    }
    @RequestMapping(value = { "/plusadmin/AD_SI_01"})
    public String AD_SI_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_SI_01";
    }
    @RequestMapping(value = { "/plusadmin/AD_SI_02"})
    public String AD_SI_02 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_SI_02";
    }
    @RequestMapping(value = { "/plusadmin/AD_SI_03"})
    public String AD_SI_03 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_SI_03";
    }
    @RequestMapping(value = { "/plusadmin/AD_SI_04"})
    public String AD_SI_04 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_SI_04";
    }
    @RequestMapping(value = { "/plusadmin/AD_BO_01"})
    public String AD_BO_01 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_BO_01";
    }

    @RequestMapping(value = { "/plusadmin/AD_BO_02"})
    public String AD_BO_02 (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
        return "/plusadmin/AD_BO_02";
    }

}