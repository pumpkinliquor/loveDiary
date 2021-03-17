package com.plushih.common.constant;

import javax.servlet.http.HttpSession;

public class LoginSession {

  public static final String RETURN_URL = "loginSessionReturnUrl";
  public static final String INFO = "userLoginSession";
  public static final String NAME = "loginSessionName";
  public static final String NICK_NAME = "loginSessionNickName";
  public static final String SUBJECT_ID = "loginSessionSubject";
  public static final String GRANT = "loginSessionGrant";
  public static final String KEY = "loginSessionKey";
  public static final String SEQ = "loginSessionSeq";
  public static final String ID = "loginSessionId";
  public static final String IP = "loginSessionIp";
  public static final String DATE = "loginSessionDate";
  public static final String Menu1 = "loginMenu1";
  public static final String Menu2 = "loginMenu2";
  public static final String Menu3 = "loginMenu3";
  public static final String MenuALL = "loginMenuALL";
  public static final String LANG = "languageCURRENT";
  public static final String EMAIL = "loginSessionEmail";
  public static final String LEVEL = "loginSessionLevel";
  public static final String SAF_BBS = "loginSessionSafBbs";
  public static final String SAF_SEQ = "loginSessionSafSeq";
  public static final String JOIN_CHANNEL = "loginSessionJoinChannel";

  public static final String LANGIDX = "languageIDX";
  
  public static final String TEMP_ID = "temp_id";
  public static final String TEMP_CLASS = "temp_class";
  public static final String TEMP_GRADE = "temp_grade";
  public static final String TEMP_SUBJECT = "temp_subject";
  public static final String TEMP_STATE = "temp_state";
  public static final String TOKEN = "dvicetoken";
  public static final String LEARNING_STATUS = "loginSessionLearningStatus";

  public static Integer getLoginKey(final HttpSession session) {
    return (Integer) session.getAttribute(LoginSession.KEY);
  }
  public static String getLoginId(final HttpSession session) {
    return (String) session.getAttribute(LoginSession.ID);
  }
  public static String getLoginIP(final HttpSession session) {
    return (String) session.getAttribute(LoginSession.IP);
  }
  public static String getLoginName(final HttpSession session) {
    return (String) session.getAttribute(LoginSession.NAME);
  }
  public static String getLoginNickName(final HttpSession session) {
	  return (String) session.getAttribute(LoginSession.NICK_NAME);
  }
  public static String getLoginSessionSafBbs(final HttpSession session) {
	  return (String) session.getAttribute(LoginSession.SAF_BBS);
  }
  public static String getLoginSessionSafSeq(final HttpSession session) {
	  return (String) session.getAttribute(LoginSession.SAF_SEQ);
  }
  public static String getLoginSubjectId(final HttpSession session) {
	  String subjectId =  String.valueOf(session.getAttribute(LoginSession.SUBJECT_ID)) ;
	  if(subjectId==null || "null".equals(subjectId)){
		  subjectId ="0";
	  }
	  return subjectId;
  }
  public static String getLoginGrant(final HttpSession session) {
    return (String) session.getAttribute(LoginSession.GRANT);
  }
  public static String getReturnUrl(final HttpSession session) {
    return (String) session.getAttribute(LoginSession.RETURN_URL);
  }
  public static String getReturnMenu1(final HttpSession session) {
    return (String) session.getAttribute(LoginSession.Menu1);
  }
  public static String getReturnMenu2(final HttpSession session) {
    return (String) session.getAttribute(LoginSession.Menu1);
  }
  public static String getReturnMenu3(final HttpSession session) {
    return (String) session.getAttribute(LoginSession.Menu3);
  }
  public static String getLanguage(final HttpSession session) {
    String lang = (String) session.getAttribute(LoginSession.LANG);
    if(lang==null){
      lang ="KR";
    }
    return lang;
  }
  public static String getLoginEmail(final HttpSession session) {
    return (String) session.getAttribute(LoginSession.EMAIL);
  }
  public static String getLoginLevel(final HttpSession session) {
	  String level =  String.valueOf(session.getAttribute(LoginSession.LEVEL)) ;
	  if(level==null || "null".equals(level)){
		  level ="0";
	  }
	  return level;
  }
  public static String getLanIdx(final HttpSession session) {
    String langIdx = (String) session.getAttribute(LoginSession.LANGIDX);
    if(langIdx==null){
      langIdx ="1";
    }
    return langIdx;
  }
  public static String getTempId(final HttpSession session) {
	  String tempId = (String) session.getAttribute(LoginSession.TEMP_ID);
	  if(tempId==null || "null".equals(tempId)){
		  tempId ="0";
	  }
	  return tempId;
  }
  public static String getTempClass(final HttpSession session) {
	  String tempClass = (String) session.getAttribute(LoginSession.TEMP_CLASS);
	  if(tempClass==null){
		  tempClass ="0";
	  }
	  return tempClass;
  }
  public static String getTempGrade(final HttpSession session) {
	  String tempGrade = String.valueOf(session.getAttribute(LoginSession.TEMP_GRADE));
	  if(tempGrade==null || "null".equals(tempGrade)){
		  tempGrade ="0";
	  }
	  return tempGrade;
  }
  public static String getTempState(final HttpSession session) {
	  String tempState = String.valueOf(session.getAttribute(LoginSession.TEMP_STATE));
	  if(tempState==null || "null".equals(tempState)){
		  tempState ="0";
	  }
	  return tempState;
  }
  public static String getSeq(final HttpSession session) {
	  String seq =  String.valueOf(session.getAttribute(LoginSession.SEQ)) ;
	  if(seq==null || "null".equals(seq)){
		  seq ="0";
	  }
	  return seq;
  }
  public static String getTempSubject(final HttpSession session) {
    String tempSubject = (String) session.getAttribute(LoginSession.TEMP_SUBJECT);
    if(tempSubject==null){
    	tempSubject ="0";
    }
    return tempSubject;
  }
  public static Object getSessionInfo(final HttpSession session) {
    return  (Object) session.getAttribute(LoginSession.INFO);
  }
  public void getSessionInfo(HttpSession session,Object sessionInfo) {
    session.setAttribute(LoginSession.INFO,sessionInfo);
  }
  
  public static String getToken(final HttpSession session) {
	  String token = (String) session.getAttribute(LoginSession.TOKEN);
	  if(token==null){
		  token ="";
	  }
	  return token;
  }
  
  public static String getLearningStatus(final HttpSession session) {
	  String status = (String) session.getAttribute(LoginSession.LEARNING_STATUS);
	  if(status==null || "null".equals(status)){
		  status ="y";
	  }
	  return status;
  }

  public static String getJoinChannel(final HttpSession session) {
	  String joinChannel = (String) session.getAttribute(LoginSession.JOIN_CHANNEL);
	  if(joinChannel==null || "null".equals(joinChannel)){
		  joinChannel ="null";
	  }
	  return joinChannel;
  }
  
}

