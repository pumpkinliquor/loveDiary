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
@RequestMapping("totaladmin/boards")
public class AdminBoardsController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBoardsController.class );
        ////afd /

    /**
     * 사업장관리
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/boardlist"})
    public String boardList (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {

        return "/totaladmin/boards/boardlist";
    }

}