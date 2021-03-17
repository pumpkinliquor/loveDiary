package com.plushih.common.interceptor;

import com.plushih.common.ci.Debug;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class InterceptorPlusAdmin extends InterceptorCore {


  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String fullName = this.getClass().getSimpleName().concat(".").concat(new Object(){}.getClass().getEnclosingMethod().getName());
    Debug.log(fullName,"########## START ##########","############ END ##########");
    boolean check = loginCheck(request,response);
    if(check==true){
       adminCheck(request,response);
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
          throws Exception {
    String fullName = this.getClass().getSimpleName().concat(".").concat(new Object(){}.getClass().getEnclosingMethod().getName());
    //Debug.log(fullName,"########## START ##########","############ END ##########");
  }
}
