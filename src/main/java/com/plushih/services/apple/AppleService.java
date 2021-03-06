package com.plushih.services.apple;


import java.util.Map;

import com.plushih.entities.TokenResponse;

import javax.servlet.http.HttpServletResponse;

public interface AppleService {

    String getAppleClientSecret(String id_token, HttpServletResponse resp);

    TokenResponse requestCodeValidations(String client_secret, String code, String refresh_token) throws Exception;

    Map<String, String> getLoginMetaInfo();

    String getPayload(String id_token);

}
