package com.plushih.entities.common;

public class CommonRuntimeException extends RuntimeException {

  private String resultCode;
  private String resultMessage;

  public CommonRuntimeException(final String msg) {
    this.resultMessage = msg;
  }

  public CommonRuntimeException(final String msg, final String code) {
    this.resultMessage = msg;
    this.resultCode = code;
  }

  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getResultMessage() {
    return resultMessage;
  }

  public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
  }

}
