package com.plushih.common.advice;

import com.plushih.common.constant.Default;
import com.plushih.common.utils.StringUtils;
import com.plushih.common.messages.Messages;
import com.plushih.entities.common.CheckAccessException;
import com.plushih.entities.common.CommonResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
  @Autowired
  private Messages messages;

  @ExceptionHandler(CheckAccessException.class)
  public Object checkAccess(HttpServletRequest request, CheckAccessException e) {
    String accept = request.getHeader("Accept");
    String resultMessage = StringUtils.isEmpty(e.getResultMessage()) ? "" : messages.getMessage(e.getResultMessage());
    String resultAction = StringUtils.isEmpty(e.getResultAction()) ? Default.ResultValue.HISTORY_BACK : e.getResultAction();
    if (accept.contains("json")) {
      /** message alert는 utils.js AjaxHandler error 수정 (request.responseText로 출력) */
      return new ResponseEntity(resultMessage, HttpStatus.NOT_ACCEPTABLE);
    } else {
      ModelAndView mav = new ModelAndView("error");
      CommonResultEntity resultEntity = new CommonResultEntity();
      resultEntity.setResultCode(Default.ResultValue.FAIL);
      resultEntity.setResultMessage(resultMessage);
      resultEntity.setResultAction(resultAction);
      mav.addObject("result", resultEntity);
      return mav;
    }
  }
}
