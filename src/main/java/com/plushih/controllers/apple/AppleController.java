package com.plushih.controllers.apple;

import java.io.PrintWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.plushih.entities.AppsResponse;
import com.plushih.entities.ServicesResponse;
import com.plushih.entities.TokenResponse;
import com.plushih.services.apple.AppleService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AppleController {

    private Logger logger = LoggerFactory.getLogger(AppleController.class);

    @Autowired
    AppleService appleService;

    @Value("${APPLE.PUBLICKEY.URL}")
    private String APPLE_PUBLIC_KEYS_URL;

    @Value("${APPLE.ISS}")
    private String ISS;

    @Value("${APPLE.AUD}")
    private String AUD;

    @Value("${APPLE.TEAM.ID}")
    private String TEAM_ID;

    @Value("${APPLE.KEY.ID}")
    private String KEY_ID;

    @Value("${APPLE.KEY.PATH}")
    private String KEY_PATH;

    @Value("${APPLE.AUTH.TOKEN.URL}")
    private String AUTH_TOKEN_URL;

    @Value("${APPLE.WEBSITE.URL}")
    private String APPLE_WEBSITE_URL;

//    /**
//     * Sign in with Apple - JS Page (index.html)
//     *
//     * @param model
//     * @return
//     */
//	@RequestMapping(value = {"/appleIndex"}, method = {RequestMethod.GET, RequestMethod.POST})
//    public String appleLoginPage(ModelMap model) {
//
//        Map<String, String> metaInfo = appleService.getLoginMetaInfo();
//
//        model.addAttribute("client_id", metaInfo.get("CLIENT_ID"));
//        model.addAttribute("redirect_uri", metaInfo.get("REDIRECT_URI"));
//        model.addAttribute("nonce", metaInfo.get("NONCE"));
//
//        return "index";
//    }

    /**
     * Apple login page Controller (SSL - https)
     *
     * @param model
     * @return
     */
	@RequestMapping(value = {"/apple/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String appleLogin(ModelMap model) {

        Map<String, String> metaInfo = appleService.getLoginMetaInfo();

        model.addAttribute("client_id", metaInfo.get("CLIENT_ID"));
        model.addAttribute("redirect_uri", metaInfo.get("REDIRECT_URI"));
        model.addAttribute("nonce", metaInfo.get("NONCE"));
        model.addAttribute("response_type", "code id_token");
        model.addAttribute("scope", "name email");
        model.addAttribute("response_mode", "form_post");

        return "redirect:https://appleid.apple.com/auth/authorize";
    }

    /**
     * Apple Login 유저 정보를 받은 후 권한 생성
     *
     * @param serviceResponse
     * @return
     */
    @RequestMapping(value = {"/redirect"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TokenResponse servicesRedirect(ServicesResponse serviceResponse, HttpServletResponse resp) throws Exception {

        if (serviceResponse == null) {
            return null;
        }

        String code = serviceResponse.getCode();
        String client_secret = appleService.getAppleClientSecret(serviceResponse.getId_token());

        logger.debug("================================");
        logger.debug("id_token ‣ " + serviceResponse.getId_token());
        logger.debug("payload ‣ " + appleService.getPayload(serviceResponse.getId_token()));
        logger.debug("client_secret ‣ " + client_secret);
        logger.debug("================================");

        ;
        resp.setContentType("text/html; charest=UTF-8"); //브라우저에 포함되는 문서내용 타입 정의
        PrintWriter out = resp.getWriter(); //
        out.println("gson : "+new Gson().toJson(serviceResponse)+
                "<br/>client_secret="+client_secret+
                "<br/>APPLE_PUBLIC_KEYS_URL="+APPLE_PUBLIC_KEYS_URL+
                "<br/>ISS="+ISS+
                "<br/>AUD="+AUD+
                "<br/>TEAM_ID="+TEAM_ID+
                "<br/>KEY_ID="+KEY_ID+
                "<br/>KEY_PATH="+KEY_PATH+
                "<br/>AUTH_TOKEN_URL="+AUTH_TOKEN_URL+
                "<br/>APPLE_WEBSITE_URL="+APPLE_WEBSITE_URL+

                "<br/>gson : "+new Gson().toJson(appleService.requestCodeValidations(client_secret, code, null)));

        return  null;
    }

    /**
     * refresh_token 유효성 검사
     *
     * @param client_secret
     * @param refresh_token
     * @return
     */
    @RequestMapping(value = {"/refresh"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TokenResponse refreshRedirect(@RequestParam String client_secret, @RequestParam String refresh_token) throws Exception {
        return appleService.requestCodeValidations(client_secret, null, refresh_token);
    }

    /**
     * Apple 유저의 이메일 변경, 서비스 해지, 계정 탈퇴에 대한 Notifications을 받는 Controller (SSL - https (default: 443))
     *
     * @param appsResponse
     */
    @RequestMapping(value = {"/apps/to/endpoint"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void appsToEndpoint(@RequestBody AppsResponse appsResponse) {
        logger.debug("[/path/to/endpoint] RequestBody ‣ " + appsResponse.getPayload());
    }

}
