package com.plushih.controllers;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.plushih.common.ci.Cache;
import com.plushih.common.ci.Debug;
import com.plushih.common.utils.HashUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.CodeMasterEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("static")
public class StaticController {
    private static final Logger LOGGER = LoggerFactory.getLogger( StaticController.class );

    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/Photo_Quick_UploadPopup")
    public String Photo_Quick_UploadPopup (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/Photo_Quick_UploadPopup";
    }
    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/imgUploadProc")
    public String imgUploadProc (Model model, MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {

        //response.
        return "/static/imgUploadProc";
    }

    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/error")
    public String error (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/error";
    }

    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/login")
    public String login (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/login";
    }


    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/pluslogin")
    public String pluslogin (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/pluslogin";
    }

    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/layout")
    public String layout (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/layout";
    }
    /**
     * 사용자관리
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/pass")
    public String passPage (@RequestParam(required = false, value = "pass", defaultValue = "") String pass, Model model, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        HashUtils hashUtils             = new HashUtils();
        return  hashUtils.encryptSHA256(pass.toString());
    }

    /**
     * 사용자관리
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/camel")
    public String camelPage (@RequestParam(required = false, value = "camel", defaultValue = "") String camel, Model model, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        return  StringUtils.toCamel(camel.toString());
    }

    /**
     * 사용자관리
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/entity")
    public String entityPage (@RequestParam(required = false, value = "camel", defaultValue = "") String camel, Model model, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String defaultPath = request.getSession().getServletContext().getRealPath("/");
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");

         String fileInfo = "";
        int size = 10 * 1024 * 1024; //byte
        String encoding = "UTF-8";

        String filePath = defaultPath + "/img" + File.separator + "smarteditor2" + File.separator + dtFormat.format(new Date()) + File.separator;
        FileRenamePolicy policy = new DefaultFileRenamePolicy();
        MultipartRequest mrequest
                = null; //파일 정책
        try {
            mrequest = new MultipartRequest(request //MultipartRequest를 만들기 위한 request
            , filePath //저장 위치
            , size //최대크기
            , encoding //인코딩 타입
            , policy);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File uploadFile = mrequest.getFile("file");
        String fileName = uploadFile.getName();
        long uploadFile_length = uploadFile.length();
        String a = "a_b_c";
//        a = "a_b_c";
//        Debug.log(a);
//        Debug.log(StringUtils.toCamel(a));
//        a = "entityPage";
//        Debug.log(a);
//        Debug.log(StringUtils.unCamel(a));
//
//        a = "EntityPageResteTefe";
//        Debug.log(a);
//        Debug.log(StringUtils.unCamel(a));
        CodeMasterEntity cm = new CodeMasterEntity();
        cm.setCmCode("cmcodetests");
        cm.setCmSeq(1);
        cm.set("cm_etc","etcxxxxxxxx");
        //Debug.log("cm.getType ==[cmcode] "+cm.getType("cmcode"));
        Debug.log("cm.get ==[cmcode] "+cm.get("cmcode"));
        Debug.log("cm.get ==[cm_code] "+cm.get("cm_code"));
        Debug.log("cm.get ==[cmCode] "+cm.get("cmCode"));
        Debug.log("cm.get ==[cmseq] "+cm.get("cmseq"));


        Cache cache = new Cache();
        cache.set_cache("test","test");

        Debug.log(cache.get_cache("test"));
            Path currentRelativePath = Paths.get("");
    String s = currentRelativePath.toAbsolutePath().toString();
    System.out.println("Current relative path is: " + s);



String name = new Object(){}.getClass().getEnclosingMethod().getName();
System.out.println("name is: " + name);
        /*
        try {
            Field field = cm.getClass().getDeclaredField("cmCode");
            field.setAccessible(true);
            Object value=  field.get(cm);
            Debug.log(value.toString());


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Debug.log("fields.length"+fields.length);

        for(int i=0;i<fields.length;i++){
				Debug.log("@fggse@"+fields[i]);
			}
        //Debug.log(EntityUtils.fieldValue("cmCode",CodeMasterEntity.class).toString());
        //return   EntityUtils.fieldValue("cmCode",cm.getClass()).toString();
        */
        return "";
    }
    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/newUser")
    public String newUser (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

//        UsersMasterEntity usersMasterEntity =(UsersMasterEntity)request.getSession().getAttribute(LoginSession.INFO);
//        if(usersMasterEntity!=null){
//            response.sendRedirect("/");
//        } else {
//
//        }
        return "/static/newUser";
    }
    /**
     * 아이디 찾기
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/idSearch")
    public String idSearch (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/idSearch";
    }

    /**
     * 비밀번호 변경
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/passwdChange")
    public String passwdChange (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/passwdChange";
    }


}