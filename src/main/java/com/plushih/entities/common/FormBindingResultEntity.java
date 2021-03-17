package com.plushih.entities.common;

import com.plushih.common.utils.StringUtils;

import java.io.Serializable;

/**
 * Created by nahyeon on 14. 12. 26.
 */
public class FormBindingResultEntity implements Serializable {

  private static final long serialVersionUID = -8783003058976089168L;

  private String objectName;
  private String code;
  private String errorCode;
  private String field;
  private String message;
  private String url;
  private String resultData;

  public String getUrl() {
      return url;
  }

  public void setUrl(String url) {
      this.url = url;
  }

  public String getObjectName() {
  return objectName;
}

  public void setObjectName(final String objectNameParam) {
    objectName = objectNameParam;
  }

  public String getCode() {
    return code;
  }

  public void setCode(final String codeParam) {
    code = codeParam;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(final String errorCodeParam) {
    errorCode = errorCodeParam;
  }

  public String getField() {
    return field;
  }

  public void setField(final String fieldParam) {
    field = fieldParam;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String messageParam) {
    message = messageParam;
  }

  public void setResultEntity(FormBindingResultEntity formValidationResult) {
    if (!StringUtils.isEmpty(formValidationResult.getObjectName())) {
      this.objectName = formValidationResult.getObjectName();
    }

    if (!StringUtils.isEmpty(formValidationResult.getCode())) {
      this.code = formValidationResult.getCode();
    }

    if (!StringUtils.isEmpty(formValidationResult.getErrorCode())) {
      this.errorCode = formValidationResult.getErrorCode();
    }

    if (!StringUtils.isEmpty(formValidationResult.getField())) {
      this.field = formValidationResult.getField();
    }

    if (!StringUtils.isEmpty(formValidationResult.getMessage())) {
      this.message = formValidationResult.getMessage();
    }

	  if (!StringUtils.isEmpty(formValidationResult.getUrl())) {
		  this.url = formValidationResult.getUrl();
	  }
  }

  public String getResultData () {
    return resultData;
  }

  public void setResultData ( final String resultData ) {
    this.resultData = resultData;
  }
}
