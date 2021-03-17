package com.plushih.controllers.totaladmin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("totaladmin/business")
public class AdminBusinessController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );


    /**
     * 사업장관리
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/businessinfolist"})
    public String businessInfoList (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {

        return request.getRequestURI().toLowerCase();
    }
    /**
     * 고객사관리
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/businessclientlist"})
    public String businessClientList (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam  ) throws Exception {

        return request.getRequestURI().toLowerCase();
    }

}