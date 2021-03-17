package com.plushih.common.utils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * example:
 * <if test="@com.plushih.common.utils.SqlValid@isNotEmpty(userId)">
 *   and user_id = #{userId}
 * </if>
 */
public class SqlValid {

  /**
   * Map, Collection, String, Array isEmpty
   * @param o
   * @return
   */
  public static boolean isEmpty(Object o) throws IllegalArgumentException {
    if(o == null) return true;

    if(o instanceof String) {
      if(((String)o).length() == 0){
        return true;
      }
    } else if(o instanceof Collection) {
      if(((Collection)o).isEmpty()){
        return true;
      }
    } else if(o.getClass().isArray()) {
      if(Array.getLength(o) == 0){
        return true;
      }
    } else if(o instanceof Map) {
      if(((Map)o).isEmpty()){
        return true;
      }
    }else {
      return false;
    }
    return false;
  }

  public static boolean isEmpty(Object o1, Object o2) {
    return isEmpty(o1) && isEmpty(o2);
  }

  public static boolean isEmpty(Object o1, Object o2, Object o3) {
    return isEmpty(o1) && isEmpty(o2) && isEmpty(o3);
  }

  public static boolean isNotEmpty(Object o) {
    return !isEmpty(o);
  }

  public static boolean isNotEmpty(Object o1, Object o2) {
    return !isEmpty(o1) && !isEmpty(o2);
  }

  public static boolean isNotEmpty(Object o1, Object o2, Object o3) {
    return !isEmpty(o1) && !isEmpty(o2) && !isEmpty(o3);
  }

  public static boolean isNotBlank(Object o) {
    return !isBlank(o);
  }

  public static boolean isNumber(Object o) {
    if(o == null) return false;
    if(o instanceof Number) {
      return true;
    }
    if(o instanceof String) {
      String str = (String)o;
      if(str.length() == 0) return false;
      if(str.trim().length() == 0) return false;
      return org.apache.commons.lang.StringUtils.isNumeric(str);
    }
    return false;
  }

  public static boolean isBlank(Object o) {
    if(o == null)
      return true;
    if(o instanceof String) {
      String str = (String)o;
      return isBlank(str);
    }
    return false;
  }

  public static boolean isBlank(String str) {
    if(str == null || str.length() == 0) {
      return true;
    }

    for (int i = 0; i < str.length(); i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }


}