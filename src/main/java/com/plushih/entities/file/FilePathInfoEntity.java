package com.plushih.entities.file;

import java.io.Serializable;

/**
 * Created by sangyong on 10/16/14.
 */
public class FilePathInfoEntity implements Serializable {

  private static final long serialVersionUID = 8805464957598065733L;

  private String fileName;
  private String fileNameExcludeExtension;
  private String fileExtension;

  public String getFileName() {
    return fileName;
  }

  public void setFileName(final String mFileNameParam) {
    fileName = mFileNameParam;
  }

  public String getFileNameExcludeExtension() {
    return fileNameExcludeExtension;
  }

  public void setFileNameExcludeExtension(final String mFileNameExcludeExtesionParam) {
    fileNameExcludeExtension = mFileNameExcludeExtesionParam;
  }

  public String getFileExtension() {
    return fileExtension;
  }

  public void setFileExtension(final String mFileExtensionParam) {
    fileExtension = mFileExtensionParam;
  }

}
