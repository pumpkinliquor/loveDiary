package com.plushih.controllers.totaladmin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("totaladmin/users")
public class AdminUsersController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminUsersController.class );


    /**
     * 사용자관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/userlist"})
    public String userList (HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {

        return "/totaladmin/users/userlist";
    }

    /**
     * 사용자 기본설정
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/userdefault"})
    public String userDefault (HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {

        return "/totaladmin/users/userdefault";
    }


    /**
     * 사용자그룹관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/usergrouplist"})
    public String userGroup (HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {

        return "/totaladmin/users/usergrouplist";
    }

    /**
     * 그릅권한관리
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/userpermission"})
    public String userPermission (HttpServletRequest request, HttpServletResponse response,  Locale localeParam ) throws Exception {

        return "/totaladmin/users/userpermission";
    }

}