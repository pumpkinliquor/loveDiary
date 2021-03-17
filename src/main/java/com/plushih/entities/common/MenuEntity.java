package com.plushih.entities.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yusul on 15. 5. 8..
 */
public class MenuEntity implements Serializable {

  private static final long serialVersionUID = 3199573274702349316L;

  private int  immSeq;
  private String  immParent;
  private String  immCode;
  private String  immLevel;
  private String  immModule;
  private String  immName;
  private String  immNavi;
  private String  immPath;
  private String  immSort;
  private String  immIsUse;
  private String  immIcon;
  private Date    immRegDate;
  private String  immId;
  private Date    immUpdDate;


  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public int getImmSeq() {
    return immSeq;
  }

  public void setImmSeq(int immSeq) {
    this.immSeq = immSeq;
  }

  public String getImmParent() {
    return immParent;
  }

  public void setImmParent(String immParent) {
    this.immParent = immParent;
  }

  public String getImmCode() {
    return immCode;
  }

  public void setImmCode(String immCode) {
    this.immCode = immCode;
  }

  public String getImmLevel() {
    return immLevel;
  }

  public void setImmLevel(String immLevel) {
    this.immLevel = immLevel;
  }

  public String getImmModule() {
    return immModule;
  }

  public void setImmModule(String immModule) {
    this.immModule = immModule;
  }

  public String getImmName() {
    return immName;
  }

  public void setImmName(String immName) {
    this.immName = immName;
  }

  public String getImmNavi() {
    return immNavi;
  }

  public void setImmNavi(String immNavi) {
    this.immNavi = immNavi;
  }

  public String getImmPath() {
    return immPath;
  }

  public void setImmPath(String immPath) {
    this.immPath = immPath;
  }

  public String getImmSort() {
    return immSort;
  }

  public void setImmSort(String immSort) {
    this.immSort = immSort;
  }

  public String getImmIsUse() {
    return immIsUse;
  }

  public void setImmIsUse(String immIsUse) {
    this.immIsUse = immIsUse;
  }

  public String getImmIcon() {
    return immIcon;
  }

  public void setImmIcon(String immIcon) {
    this.immIcon = immIcon;
  }

  public Date getImmRegDate() {
    return immRegDate;
  }

  public void setImmRegDate(Date immRegDate) {
    this.immRegDate = immRegDate;
  }

  public String getImmId() {
    return immId;
  }

  public void setImmId(String immId) {
    this.immId = immId;
  }

  public Date getImmUpdDate() {
    return immUpdDate;
  }

  public void setImmUpdDate(Date immUpdDate) {
    this.immUpdDate = immUpdDate;
  }
}
