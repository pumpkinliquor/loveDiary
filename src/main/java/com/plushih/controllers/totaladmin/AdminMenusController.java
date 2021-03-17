package com.plushih.controllers.totaladmin;


import com.plushih.common.ci.Debug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("totaladmin/menus")
public class AdminMenusController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminMenusController.class);


    /**
     * 사용자메뉴관리
     *
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = {"/menulist"})
    public String menuList(HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
        String requestUri = request.getRequestURI();

        return request.getRequestURI().toLowerCase();
    }

    /**
     * 사용자메뉴권한관리
     *
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = {"/menugrant"})
    public String menuGrant(HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {

        Debug.log(request.getRequestURI().toLowerCase());
        return request.getRequestURI().toLowerCase();
    }

    /**
     * 사용자메뉴관리
     *
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = {"/admmenulist"})
    public String admmenuList(HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
        String requestUri = request.getRequestURI();

        return request.getRequestURI().toLowerCase();
    }

    /**
     * 사용자메뉴권한관리
     *
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = {"/admmenugrant"})
    public String admmenuGrant(HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {

        Debug.log(request.getRequestURI().toLowerCase());
        return request.getRequestURI().toLowerCase();
    }

}