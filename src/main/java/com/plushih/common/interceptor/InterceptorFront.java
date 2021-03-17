package com.plushih.common.interceptor;

import com.plushih.common.ci.Debug;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class InterceptorFront extends InterceptorCore {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String fullName = this.getClass().getSimpleName().concat(".").concat(new Object(){}.getClass().getEnclosingMethod().getName());
    Debug.log(fullName,"########## START ##########","############ END ##########");
	if(loginCheckFront(request,response)) {
		return true;
	} else {
		return false;
	}
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
          throws Exception {
    String fullName = this.getClass().getSimpleName().concat(".").concat(new Object(){}.getClass().getEnclosingMethod().getName());
    Debug.log(fullName,"########## START ##########","############ END ##########");
  }
}
