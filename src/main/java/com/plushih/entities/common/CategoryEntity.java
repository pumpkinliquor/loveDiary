package com.plushih.entities.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by nahyeon on 15. 1. 15.
 */
public class CategoryEntity implements Serializable{

  private static final long serialVersionUID = 8412328827068252931L;
  private String categoryCode;
  private String categoryGroupCode;
  private String categoryName;
  private String categoryComment;
  private String highCategoryCode;
  private int    depth;
  private String flag;
  private int    sortnum;
  private String writerKey;
  private String editorKey;
  private Date   insertDate;
  private Date   updateDate;
  private int    isLowCategory;

  private List<CategoryEntity> categoryList;

  public CategoryEntity () {
  }

  public CategoryEntity ( String categoryGroupCode ) {
    this.categoryGroupCode = categoryGroupCode;
    this.highCategoryCode = "00";
  }

  public String getCategoryCode () {
    return categoryCode;
  }

  public void setCategoryCode ( final String categoryCodeParam ) {
    categoryCode = categoryCodeParam;
  }

  public String getCategoryGroupCode () {
    return categoryGroupCode;
  }

  public void setCategoryGroupCode ( final String categoryGroupCodeParam ) {
    categoryGroupCode = categoryGroupCodeParam;
  }

  public String getCategoryName () {
    return categoryName;
  }

  public void setCategoryName ( final String categoryNameParam ) {
    categoryName = categoryNameParam;
  }

  public String getCategoryComment () {
    return categoryComment;
  }

  public void setCategoryComment ( final String categoryCommentParam ) {
    categoryComment = categoryCommentParam;
  }

  public String getHighCategoryCode () {
    return highCategoryCode;
  }

  public void setHighCategoryCode ( final String highCategoryCodeParam ) {
    highCategoryCode = highCategoryCodeParam;
  }

  public int getDepth () {
    return depth;
  }

  public void setDepth ( final int depthParam ) {
    depth = depthParam;
  }

  public String getFlag () {
    return flag;
  }

  public void setFlag ( final String flagParam ) {
    flag = flagParam;
  }

  public int getSortnum () {
    return sortnum;
  }

  public void setSortnum ( final int sortnumParam ) {
    sortnum = sortnumParam;
  }

  public String getWriterKey () {
    return writerKey;
  }

  public void setWriterKey ( final String writerKeyParam ) {
    writerKey = writerKeyParam;
  }

  public String getEditorKey () {
    return editorKey;
  }

  public void setEditorKey ( final String editorKeyParam ) {
    editorKey = editorKeyParam;
  }

  public Date getInsertDate () {
    return insertDate == null ? null : new Date(insertDate.getTime());
  }

  public void setInsertDate ( final Date insertDateParam ) {
    insertDate = insertDateParam == null ? null : new Date(insertDateParam.getTime());
  }

  public Date getUpdateDate () {
    return updateDate == null ? null : new Date(updateDate.getTime());
  }

  public void setUpdateDate ( final Date updateDateParam ) {
    updateDate = updateDateParam == null ? null : new Date(updateDateParam.getTime());
  }

  public int getIsLowCategory () {
    return isLowCategory;
  }

  public void setIsLowCategory ( final int isLowCategoryParam ) {
    isLowCategory = isLowCategoryParam;
  }

  public List<CategoryEntity> getCategoryList () {
    return categoryList;
  }

  public void setCategoryList ( final List<CategoryEntity> categoryListParam ) {
    categoryList = categoryListParam;
  }
}
