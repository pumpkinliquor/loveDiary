package com.plushih.common.validation;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by nahyeon on 15. 1. 26.
 */
public class ConfirmValidator implements ConstraintValidator<Confirm, Object>  {
  private String field;

  private String confirmField;

  private String message;

  public void initialize(Confirm constraintAnnotation) {
    field = constraintAnnotation.field();
    confirmField = "confirm" + StringUtils.capitalize( field );
    message = constraintAnnotation.message();
  }

  public boolean isValid(Object value, ConstraintValidatorContext context) {
    BeanWrapper beanWrapper = new BeanWrapperImpl(value);
    Object fieldValue = beanWrapper.getPropertyValue(field);
    Object confirmFieldValue = beanWrapper.getPropertyValue(confirmField);
    boolean matched = ObjectUtils.nullSafeEquals( fieldValue, confirmFieldValue );
    if (matched) {
      return true;
    } else {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(message).addNode(field).addConstraintViolation();
      return false;
    }
  }
}
