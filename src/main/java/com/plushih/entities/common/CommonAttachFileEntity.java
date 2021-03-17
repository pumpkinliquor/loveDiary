package com.plushih.entities.common;

import java.io.Serializable;
import java.util.Date;

public class CommonAttachFileEntity implements Serializable {
  private static final long serialVersionUID = -2765205487978772931L;

  private int commonFileKey;            // 파일첨부 고유키
  private String fileName;        // 파일명
  private String filePath;        // 파일 경로
  private int fileSize;           // 파일 크기
  private int isDelete;           // 삭제여부 (1:삭제, 0:미삭제)
  private Date insertDate;        // 수정일
  private Date deleteDate;        // 삭제일

  public int getCommonFileKey() {
    return commonFileKey;
  }

  public void setCommonFileKey(int commonFileKey) {
    this.commonFileKey = commonFileKey;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public int getFileSize() {
    return fileSize;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }

  public int getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(int isDelete) {
    this.isDelete = isDelete;
  }

  public Date getInsertDate() {
    return insertDate == null ? null : new Date(insertDate.getTime());
  }

  public void setInsertDate(final Date insertDate) {
    this.insertDate = insertDate == null ? null : new Date(insertDate.getTime());
  }

  public Date getDeleteDate() {
    return deleteDate == null ? null : new Date(deleteDate.getTime());
  }

  public void setDeleteDate(final Date deleteDate) {
    this.deleteDate = deleteDate == null ? null : new Date(deleteDate.getTime());
  }
}
