package com.plushih.common.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

/**
 * Created by sangyong on 10/20/14.
 */
public class ValidationUtils {

  private static String PATTERN_CHECK_USER_ID                  = "^.*(?=.{4,16})(?=.*[0-9])(?=.*[a-z]).*$";
  private static String PATTERN_CHECK_NAME                     = "^[ㄱ-ㅎ가-힣a-zA-Z0-9 ]*$";
  private static String PATTERN_CHECK_PASSWORD                 = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
  private static String PATTERN_CHECK_PASSWORD_NO_SPECIAL_CHAR = "^[0-9a-zA-Z ]*$";

  /**
   * 길이 4~16, 숫자, 영문소문자 필수
   *
   * @param username
   * @return
   */
  public static boolean checkUserId(String username) {
    return username.matches(PATTERN_CHECK_USER_ID);
  }

  /**
   * 한글 및 a-zA-Z0-9,스페이스
   *
   * @param name
   * @return
   */
  public static boolean checkName(String name) {
    if (StringUtils.isEmptyWithTrim(name))
      return false;

    return name.matches(PATTERN_CHECK_NAME);
  }

  /**
   * 정책에 따라 상이함. 기본으로 0-9a-zA-Z,특수문자(1자리 이상) 6~20자리
   *
   * @param password
   * @return
   */
  public static boolean checkPassword(String password) {
    if (password.matches(PATTERN_CHECK_PASSWORD) && !password.matches(PATTERN_CHECK_PASSWORD_NO_SPECIAL_CHAR)) {
      return true;
    }

    return false;
  }

  public static boolean checkEmail(String email) {
    return true;//EmailValidator.getInstance().isValid(email);
  }

  /**
   * 숫자체크
   * @param str
   * @return
   */
  public boolean isNumber(String str) {
    return str.matches("^[0-9]*$");
  }

  /**
   * 영문소문자만
   * @param str
   * @return
   */
  public boolean isLower(String str) {
    return str.matches("^[a-z]*$");
  }

  /**
   * 영문대문자만
   * @param str
   * @return
   */
  public boolean isUpper(String str) {
    return str.matches("^[A-Z]*$");
  }

  /**
   * 영문대소문자만
   * @param str
   * @return
   */
  public boolean isAlphabet(String str) {
    return str.matches("^[A-Za-z]*$");
  }

  /**
   * 한글만
   * @param str
   * @return
   */
  public boolean isKorean(String str) {
    return str.matches("^[가-힣ㄱ-ㅎㅏ-ㅣ]*$");
  }

  /**
   * 핸드폰번호유효성
   * @param str
   * @return
   */
  public boolean isCellphone(String str) {
    //010, 011, 016, 017, 018, 019
    return str.matches("(01[016789])-(\\d{3,4})-(\\d{4})");
  }

  /**
   * 전화번호유효성
   * @param str
   * @return
   */
  public boolean isTelePhone(String str) {
    /*
    02  서울특별시
    031 경기도
    032 인천광역시
    033 강원도
    041 충청남도
    042 대전광역시
    043 충청북도
    051 부산광역시
    052 울산광역시
    053 대구광역시
    054 경상북도
    055 경상남도
    061 전라남도
    062 광주광역시
    063 전라북도
    064 제주특별자치도
    */
    return str.matches("(0(2|3(1|2|3)|4(1|2|3)|5(1|2|3|4|5)|6(1|2|3|4)))-(\\d{3,4})-(\\d{4})");
  }

  public static HashMap<String, String> checkValidate(Object obj, String rulesJson) {

    boolean res = false;
    HashMap<String, String> resMap = new HashMap<String, String>();
    HashMap<String, String> classMap = makeClassToMap(obj);

    if(StringUtils.isEmpty(rulesJson)){
        return resMap;
    }
    JSONParser jsonParser = new JSONParser();
      try {
          Object parseObj = jsonParser.parse(rulesJson);
          JSONObject jsonObj = (JSONObject)parseObj;
          //Iterator<String> keys = ;
          //String[] keyName = jsonObj.keySet();
          for(Object key:jsonObj.keySet()){
              String keystr = (String)key;
              debug(keystr +"::");
              if(jsonObj.containsKey(keystr) && classMap.containsKey(keystr)){
                  JSONObject subJsonObj =  (JSONObject)jsonObj.get(keystr);
                  if(subJsonObj.containsKey("required")){
                      if(subJsonObj.get("required").toString().equals("true")){
                          if(StringUtils.isEmpty(classMap.get(keystr))){
                              resMap.put("field",keystr);
                              resMap.put("msg","항목은 필수입니다.");
                              break;
                          }
                      }
                      //classMap.
                  }
                  if(subJsonObj.containsKey("minlength")){
                      if(classMap.get(keystr).length()<Integer.parseInt(subJsonObj.get("minlength").toString())){
                          resMap.put("field",keystr);
                          resMap.put("msg","항목은 문자열 길이가 "+(subJsonObj.get("minlength"))+" 보다 길이가 길어야합니다.");
                          break;
                      }
                  }
                  if(subJsonObj.containsKey("maxlength")){
                      if(classMap.get(keystr).length()>Integer.parseInt(subJsonObj.get("maxlength").toString())){
                          resMap.put("field",keystr);
                          resMap.put("msg","항목은 문자열 길이가 "+(subJsonObj.get("maxlength"))+" 보다 길이가 작아야합니다.");
                          break;
                      }
                  }
              } else {
                  resMap.put("field",keystr);
                  resMap.put("msg","존재하지 않습니다.");
                  break;
              }
          }
      } catch (ParseException e) {
          e.printStackTrace();
      }
      return resMap;
  }
    public static void debug(String sx){
      System.out.println(sx);
    }
  public static String makeClassToJson(Object obj){
    return new Gson().toJson(obj);
  }
  public static HashMap<String, String> makeJsonToMap(String json){
    if(StringUtils.isEmpty(json)) json = "{}";
    HashMap<String, String> retMap = new Gson().fromJson(
	    json, new TypeToken<HashMap<String, Object>>() {}.getType()
	  );
    return retMap;
  }
  public static HashMap<String, String> makeClassToMap(Object obj) {
    return makeJsonToMap(makeClassToJson(obj));
  }
}
