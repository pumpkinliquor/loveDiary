package com.plushih.common.advice;

import com.plushih.common.messages.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//@ControllerAdvice(annotations = RestController.class)
public class ExceptionRestControllerAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionRestControllerAdvice.class);
  @Autowired
  private Messages messages;
// "redirect:/common/error?resultCode=fail&resultMessage=" +resultMessage + "&redirectType=historyBack";


//  @ExceptionHandler(CheckAccessException.class)
//  @ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE)
//  @ResponseBody
//  public ResponseEntity<Object>  handleCustomException(CheckAccessException e) {
//    //TODO-lms0123: @ControllerAdvice(annotations 에 RestController 주어도 ExceptionControllerAdvice 쪽이 호출됨)
//    return new ResponseEntity(messages.getMessage(e.getResultMessage()), HttpStatus.NOT_ACCEPTABLE);
//  }
}
