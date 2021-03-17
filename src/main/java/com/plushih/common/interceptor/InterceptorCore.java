package com.plushih.common.interceptor;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.LoginSession;
import com.plushih.entities.UserMasterEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class InterceptorCore extends HandlerInterceptorAdapter {


    /**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	}

   /**
   * 로그인 체크
   * @param request
   * @param response
   * @return
   * @throws IOException
   */
  public boolean loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String ID = (String) request.getSession().getAttribute(LoginSession.ID);
    UserMasterEntity userMasterEntity = (UserMasterEntity) request.getSession().getAttribute(LoginSession.INFO);
    String referer = request.getHeader("referer");
    String loginUrl = "";
    StringBuilder loginReturnUrl = new StringBuilder(request.getRequestURI());
    if (StringUtils.isEmpty(ID) ) {


      request.getSession().setAttribute(LoginSession.RETURN_URL, loginReturnUrl.toString());

      if(loginReturnUrl.toString().length()==1){
          loginUrl = "/";
      }
      if (loginReturnUrl.toString().contains("totaladmin")) {
        loginUrl = "/static/pluslogin";
      }
      if (loginReturnUrl.toString().contains("plusadmin")) {
        loginUrl = "/static/pluslogin";
      }
//      if (loginReturnUrl.toString().contains("front")) {
//        loginUrl = "/static/syslogin";
//      }
      response.sendRedirect(request.getContextPath() + loginUrl);
      //response.sendRedirect(request.getContextPath() + loginUrl);
      return false;
    } else {
      loginUrl = "";
      String nowUrl = request.getRequestURI();
      if (!(Code.UmType.ADMIN.equals(userMasterEntity.getUmType())  || Code.UmType.MEMBER.equals(userMasterEntity.getUmType())   ) ) {
        response.sendRedirect(request.getContextPath() + "/static/pluslogin");
        return false;
      }
//      if (loginReturnUrl.toString().contains("totaladmin")) {
//        loginUrl = "/static/syslogin";
//        response.sendRedirect(request.getContextPath() + "/static/syslogin");
//        return false;
//      }
      String ipAddress = request.getHeader("X-FORWARDED-FOR");
      if (ipAddress == null) {
        ipAddress = request.getRemoteAddr();
      }
      request.setAttribute(LoginSession.IP, ipAddress);
//      request.setAttribute(LoginSession.ID, userInfo.getUserId());
//      request.setAttribute(LoginSession.NAME, userInfo.getUserName());
//      request.setAttribute(LoginSession.DATE, userInfo.getUserName());

    }
    return true;
  }
  public boolean adminCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
      UserMasterEntity userMasterEntity = (UserMasterEntity) request.getSession().getAttribute(LoginSession.INFO);
      if(userMasterEntity==null){
          response.sendRedirect(request.getContextPath() + "/static/pluslogin");
          return false;
      }
      if (!(Code.UmType.ADMIN.equals(userMasterEntity.getUmType())  ) ) {
          response.sendRedirect(request.getContextPath() + "/");
          return false;
      }
      if (!(Code.UmType.ADMIN.equals(userMasterEntity.getUmType())  ) ) {
          response.sendRedirect(request.getContextPath() + "/");
          return false;
      }
    return true;
  }

  public boolean totaladminCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
      UserMasterEntity userMasterEntity = (UserMasterEntity) request.getSession().getAttribute(LoginSession.INFO);
      if(userMasterEntity==null){
          response.sendRedirect(request.getContextPath() + "/static/pluslogin");
          return false;
      }
      if(!userMasterEntity.getUmId().equals("hsk3807")){
          response.sendRedirect(request.getContextPath() + "/");
          return false;
      }
      if (!(Code.UmType.ADMIN.equals(userMasterEntity.getUmType())  ) ) {
          response.sendRedirect(request.getContextPath() + "/");
          return false;
      }
      if(userMasterEntity.getUgSeq()!=null){
          if(userMasterEntity.getUgSeq()>2){
              response.sendRedirect(request.getContextPath() + "/");
              return false;
          }
      }

    return true;
  }

}
