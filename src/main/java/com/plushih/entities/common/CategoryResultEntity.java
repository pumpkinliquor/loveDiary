package com.plushih.entities.common;

import java.io.Serializable;
import java.util.List;

public class CategoryResultEntity extends CommonResultEntity implements Serializable {

  private static final long serialVersionUID = 6577557674831735902L;
  private List<CategoryEntity> categoryEntityList;

  public List<CategoryEntity> getCategoryEntityList () {
    return categoryEntityList;
  }

  public void setCategoryEntityList ( final List<CategoryEntity> categoryEntityListParam ) {
    categoryEntityList = categoryEntityListParam;
  }
}
