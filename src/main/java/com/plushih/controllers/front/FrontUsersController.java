package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("front/users")
public class FrontUsersController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );


    /**
     * 회원관리
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/usersList"})
    public String usersList (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {

        return request.getRequestURI().toLowerCase();
    }
    /**
     * 회원그룹
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/usersGroupList"})
    public String usersGroupList (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam  ) throws Exception {

        return request.getRequestURI().toLowerCase();
    }

    /**
     * 회원 그룹 권한관리
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/usersGrant"})
    public String usersGrant (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam  ) throws Exception {

        return request.getRequestURI().toLowerCase();
    }

    /**
     * 회원 사업장 지정관리
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/usersTarget"})
    public String usersTarget (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam  ) throws Exception {

        return request.getRequestURI().toLowerCase();
    }

}