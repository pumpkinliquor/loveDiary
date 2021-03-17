package com.plushih.controllers.ajax;


import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Default;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.ServicesResponse;
import com.plushih.entities.TokenResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("front/ajax/sns")
public class AjaxSnsController extends CoreController {

	private static final Logger LOGGER = LoggerFactory.getLogger( AjaxSnsController.class );

	/**
	 * @ClassName	: AjaxSnsController.java
	 * @Method		: init
	 * @Date		: 2021. 1. 14.
	 * @author		: dev.yklee
	 * @Description	: SNS Init 관련 Key Load
	 */
	@RequestMapping(value = "/init", method = {RequestMethod.POST})
	public @ResponseBody CommonResultEntity init (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {

		String fullName = getFunction();
		logStart(fullName);
		CommonResultEntity commonResultEntity = new CommonResultEntity();
		String functionName = new Object(){}.getClass().getEnclosingMethod().getName();

		Map<String, Object> snsInfo = new HashMap<String, Object>();

		snsInfo.put("serverDomain", Default.Site.SERVER_DOMAIN);
		snsInfo.put("loginReturnUrl", Default.Sns.LOGIN_PROC_URL);
		snsInfo.put("joinReturnUrl", Default.Sns.JOIN_PROC_URL);

		snsInfo.put("kakao", Default.Sns.KAKAO);
		snsInfo.put("facebook", Default.Sns.FACEBOOK);
		snsInfo.put("naver", Default.Sns.NAVER);

		snsInfo.put("kakaoKey", Default.Sns.KAKAO_JS_KEY);
		snsInfo.put("facebookKey", Default.Sns.FACEBOOK_KEY);
		snsInfo.put("naverKey", Default.Sns.NAVER_CLIENT_ID_KEY);

		snsInfo.put("facebookAuthUrl", Default.Sns.FACEBOOK_AUTH_REQUEST_API_URL);
		snsInfo.put("naverAuthUrl", Default.Sns.NAVER_AUTH_REQUEST_API_URL);

		commonResultEntity.setResultValue(snsInfo);

		logEnd(fullName);

		return commonResultEntity;
	}

}