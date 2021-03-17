package com.plushih.entities.common;

import java.io.Serializable;
import java.util.List;

public class CommonResultEntity implements Serializable {

  private static final long serialVersionUID = -8264804965208752968L;

  private int resultPrimaryKey;
  private String resultCode;
  private String resultMessage;
  private String resultUrl;
  private String resultAction;
  private Object resultValue;
  private List<?> resultList;

  public int getResultPrimaryKey() {
    return resultPrimaryKey;
  }

  public void setResultPrimaryKey(int resultPrimaryKey) {
    this.resultPrimaryKey = resultPrimaryKey;
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

  public String getResultUrl() {
    return resultUrl;
  }

  public void setResultUrl(String resultUrl) {
    this.resultUrl = resultUrl;
  }

  public String getResultAction() {
    return resultAction;
  }

  public void setResultAction(String resultAction) {
    this.resultAction = resultAction;
  }

  public Object getResultValue() {
    return resultValue;
  }

  public void setResultValue(Object resultValue) {
    this.resultValue = resultValue;
  }

  public List<?> getResultList() {
    return resultList;
  }

  public void setResultList(List<?> resultList) {
    this.resultList = resultList;
  }
}
