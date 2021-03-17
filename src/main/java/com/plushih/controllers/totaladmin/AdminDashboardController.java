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
public class AdminDashboardController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminDashboardController.class );


    /**
     * 대쉬보드
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/totaladmin/main", "/totaladmin/dashboard"})
    public String main (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {

        return "/totaladmin/dashboard";
    }

}