package com.plushih.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nahyeon on 15. 1. 27.
 */

@Constraint (validatedBy = { CheckEmptyForSelectedValueValidator.class })
@Target ({ TYPE, ANNOTATION_TYPE }) // (1)
@Retention (RUNTIME)
public @interface CheckEmptyForSelectedValue {

  Class<?>[] groups() default {};
  Class<? extends Payload >[] payload() default {};

  String field();
  String matchValue();
  String targetField();
  String message() default "{value.notEmpty}";

  @Target({ TYPE, ANNOTATION_TYPE })
  @Retention(RUNTIME)
  public @interface List {
    CheckEmptyForSelectedValue[] value();
  }
}
