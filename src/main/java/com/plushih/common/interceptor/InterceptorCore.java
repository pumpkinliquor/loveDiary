package com.plushih.common.interceptor;

import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.UserMasterEntity;
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


		UserMasterEntity userMasterEntity = null;
		try {
			userMasterEntity = (UserMasterEntity) request.getSession().getAttribute(LoginSession.INFO);
		} catch(Exception e){

			response.sendRedirect(request.getContextPath() + "/static/ajax/loginOut");
			return false;
		}

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

   /**
   * 로그인 체크
   * @param request
   * @param response
   * @return
   * @throws IOException
   */
	public boolean loginCheckFront(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ID = (String) request.getSession().getAttribute(LoginSession.ID);
		//UserMasterEntity userMasterEntity = (UserMasterEntity) request.getSession().getAttribute(LoginSession.INFO);
		String MEM_KEY = "";
		
		if(!StringUtils.isEmpty(ID) ){
			try {
				MEM_KEY = LoginSession.getSeq(request.getSession());
			} catch(Exception e){
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/static/ajax/loginOut");
				return false;
			}
		}
		
		MEM_KEY = LoginSession.getSeq(request.getSession());
		
		String referer = request.getHeader("referer");
		String loginUrl = "";
		StringBuilder loginReturnUrl = new StringBuilder(request.getRequestURI());
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		
		if (StringUtils.isEmpty(ID) || (StringUtils.isEmpty(MEM_KEY) && !"null".equals(MEM_KEY) && !Default.STRING_ZERO.equals(MEM_KEY))) {
			request.getSession().setAttribute(LoginSession.RETURN_URL, loginReturnUrl.toString());
			
			if(!isAjax && !loginReturnUrl.toString().contains("login") && !loginReturnUrl.toString().contains("join") 
				&& !loginReturnUrl.toString().contains("admin")
				&& !loginReturnUrl.toString().contains("terms")
				&& !loginReturnUrl.toString().contains("front/main")
				&& !loginReturnUrl.toString().contains("furs")
				|| (loginReturnUrl.toString().contains("furs/question"))
				|| (loginReturnUrl.toString().contains("furs/complete")) ) {
				String tempId = StringUtils.nvl(LoginSession.getTempId(request.getSession()), "");
				if( "".equals(tempId) || "0".equals(tempId) ) {
					// 1. 사전진단 정보 없는 경우
					loginUrl = "/front/main";
				}else {
					// 2. 사전진단 완료
					loginUrl = "/front/login";
				}
			} else {
				return true;
			}
			response.sendRedirect(request.getContextPath() + loginUrl);
			return false;
			
		} else {
			
			loginUrl = "";
			
			if( "".equals(StringUtils.nvl(LoginSession.getTempId(request.getSession()), "")) ) {
				// 1. 사전진단 정보 없는 경우
				loginUrl = "/";
			}else {
				// 2. 사전진단 완료
				loginUrl = "/front/login";
			}
			
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			
			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			request.setAttribute(LoginSession.IP, ipAddress);
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
