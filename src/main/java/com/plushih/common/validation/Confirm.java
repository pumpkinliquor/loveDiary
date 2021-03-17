package com.plushih.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by nahyeon on 15. 1. 26.
 */
@Constraint (validatedBy = { ConfirmValidator.class })
@Target ({ TYPE, ANNOTATION_TYPE }) // (1)
@Retention (RUNTIME)
public @interface Confirm {

  Class<?>[] groups() default {};
  Class<? extends Payload >[] payload() default {};

  /**
   * Field name
   */
  String field();
  /**
   * Message
   */
  String message() default "{user.update.confirm.password.notEqual}";

  @Target({ TYPE, ANNOTATION_TYPE })
  @Retention(RUNTIME)
  public @interface List {
    Confirm[] value();
  }
}
