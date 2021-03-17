package com.plushih.common.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by nahyeon on 15. 1. 27.
 */
public class CheckEmptyForSelectedValueValidator implements ConstraintValidator<CheckEmptyForSelectedValue, Object> {

  private String field;
  private String matchValue;
  private String targetField;
  private String message;

  public void initialize(CheckEmptyForSelectedValue constraintAnnotation) {
    field = constraintAnnotation.field();
    matchValue = constraintAnnotation.matchValue();
    targetField = constraintAnnotation.targetField();
    message = constraintAnnotation.message();
  }

  public boolean isValid(final Object value, ConstraintValidatorContext context) {
    BeanWrapper beanWrapper = new BeanWrapperImpl(value);
    String fieldValue = String.valueOf(beanWrapper.getPropertyValue(field));
    String targetFieldValue = (String) beanWrapper.getPropertyValue(targetField);

    if (matchValue.equals( fieldValue ) && StringUtils.isEmpty(targetFieldValue)) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(message).addNode(targetField).addConstraintViolation();
      return false;
    }
    else {
      return true;
    }
  }
}
