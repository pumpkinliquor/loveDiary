package com.plushih.entities.file;

import java.io.Serializable;

/**
 * Created by sangyong on 10/16/14.
 */
public class SavedFileEntity implements Serializable {

  private static final long serialVersionUID = -8769893033500260862L;

  private String savedFileName;
  private String savedFileExtension;
  private long   savedFileSize;
  private String originalFileName;
  private String savedPath;
  private Integer savedSeq;

  public String getSavedFileName() {
    return savedFileName;
  }

  public void setSavedFileName(final String mSavedFileNameParam) {
    savedFileName = mSavedFileNameParam;
  }

  public String getSavedFileExtension() {
    return savedFileExtension;
  }

  public void setSavedFileExtension(final String mSavedFileExtensionParam) {
    savedFileExtension = mSavedFileExtensionParam;
  }

  public long getSavedFileSize() {
    return savedFileSize;
  }

  public void setSavedFileSize(final long mSavedFileSizeParam) {
    savedFileSize = mSavedFileSizeParam;
  }

  public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(final String mOriginalFileNameParam) {
    originalFileName = mOriginalFileNameParam;
  }

  public String getSavedPath() {
    return savedPath;
  }

  public void setSavedPath(final String mSavedPathParam) { savedPath = mSavedPathParam; }

  public Integer getSavedSeq() { return savedSeq; }

  public void setSavedSeq(final Integer mSavedSeqParam) { savedSeq = mSavedSeqParam; }
}