package com.plushih.entities.common;

import java.io.Serializable;

public class EditorAttachResultEntity implements Serializable{
  private static final long serialVersionUID = -3993411303271324678L;
  
  private String resultCode;
  private String resultMessage;
  private String resultUrl;
  private SavedFileEntity savedFile;

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

  public SavedFileEntity getSavedFile() {
    return savedFile;
  }

  public void setSavedFile(SavedFileEntity savedFile) {
    this.savedFile = savedFile;
  }
}
