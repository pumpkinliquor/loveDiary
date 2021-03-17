package com.plushih.common.constant;

public class Regex {

  public static final String SQUARE_BRACKET = "\\[|\\]|\\p{Space}";

  public static final String START_WITH = "^";

  public static final String ESSENTIAL_USER_ID = "(\\p{Lower}|\\p{Digit}){5,20}";

  public static final String PASSWORD = "([a-z0-9]){6,20}";

  public static final String UPDATE_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=[a-z0-9]+$).{6,20}$";

  public static final String TELEPHONE = "((02|0[3-9]{1}[0-9]{1})-([0-9]{3,4})-([0-9]{4}))?";

  public static final String CELLPHONE = "((01([0|1|6|7|8|9]))-([0-9]{3,4})-([0-9]{4}))?";

  public static final String ESSENTIAL_CELLPHONE = "(01([0|1|6|7|8|9]))-([0-9]{3,4})-([0-9]{4})";

  public static final String BUSINESS_REGISTRATION_NUMBER = "([0-9]{3})-([0-9]{2})-([0-9]{5})";

  public static final String CORPORATE_NUMBER = "(([0-9]{6})-([0-9]{7}))?";

}
