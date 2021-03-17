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
@RequestMapping("front/bbs")
public class FrontBbsController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );


    /**
     * 사업장관리
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/noticeList"})
    public String noticeList (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {

        return request.getRequestURI().toLowerCase();
    }
    /**
     * 고객사관리
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/documentList"})
    public String documentList (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam  ) throws Exception {

        return request.getRequestURI().toLowerCase();
    }

}