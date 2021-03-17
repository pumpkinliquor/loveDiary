package com.plushih.entities.common;

public class CommonException extends Exception {

  private String resultCode;
  private String resultMessage;
  private String resultData;

  public CommonException(final String msg) {
    this.resultMessage = msg;
  }

  public CommonException(final String msg, final String code) {
    this.resultMessage = msg;
    this.resultCode = code;
  }

  public CommonException(final String msg, final String code, final String data) {
    this.resultMessage = msg;
    this.resultCode = code;
    this.resultData = data;
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

  public String getResultData () {
    return resultData;
  }

  public void setResultData ( final String resultDataParam ) {
    resultData = resultDataParam;
  }
}
