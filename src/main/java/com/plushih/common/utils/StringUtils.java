package com.plushih.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sangyong on 10/10/14.
 */
public class StringUtils extends org.springframework.util.StringUtils {

  // 한글 유니코드 범위 시작
  private static char HANGUL_UNICODE_RANGE_START = '\uAC00';
  // 한글 유니코드 범위 끝
  private static char HANGUL_UNICODE_RANGE_END   = '\uD7AF';
  // 한글 자모음 유니코드 범위 시작
  private static char HANGUL_JAMO_UNICODE_RANGE_START = '\u3131';
  // 한글 자모음 유니코드 범위 끝
  private static char HANGUL_JAMO_UNICODE_RANGE_END = '\u3163';

  /**
   * Random Symbols 0-9a-zA-Z
   */
  private static final char[] randomSymbols;

  static {
    StringBuilder tmp = new StringBuilder();
    for (char ch = '0'; ch <= '9'; ++ch)
      tmp.append(ch);
    for (char ch = 'a'; ch <= 'z'; ++ch)
      tmp.append(ch);
    for (char ch = 'A'; ch <= 'Z'; ++ch)
      tmp.append(ch);
    randomSymbols = tmp.toString().toCharArray();
  }

  private static Random randomGenerator = new Random();

  private StringUtils() {
    throw new AssertionError();
  }

  /**
   * <br/> 추가
   *
   * @param str
   * @return
   */
  public static String nl2br(final String str) {
    String replaceStr = str.replaceAll("\r\n", "<br />");
    replaceStr = replaceStr.replaceAll("\r", "<br />");
    return replaceStr.replaceAll("\n", "<br />");
  }

  /**
   * <br/> -> \n 변경
   *
   * @param str
   * @return
   */
  public static String brToNl(final String str) {
    return str.replaceAll("<[ ]*[bB][rR][/ ]*>", "\n");
  }

  public static String addSpace(final String str, final int spaceSize) {
    StringBuffer stringBuffer = new StringBuffer();
    if (!hasText(str) || spaceSize < 1)
      return str;

    stringBuffer.append(str);

    for (int j = 0; j < spaceSize; j++) {
      stringBuffer.append(' ');
    }

    return stringBuffer.toString();
  }

  public static <T> T[] addObjectToArray(final T[] array, final Object newObject) {
    T[] newArray = Arrays.copyOf(array, array.length + 1);
    newArray[array.length] = (T) newObject;

    return newArray;
  }

  public static boolean containsInvalidChars(final String str, final char[] invalidChars) {
    if (!hasText(str) || invalidChars == null) {
      return false;
    }

    int strSize = str.length();
    int validSize = invalidChars.length;

    for (int i = 0; i < strSize; i++) {
      char ch = str.charAt(i);
      for (int j = 0; j < validSize; j++) {
        if (invalidChars[j] == ch) {
          return true;
        }
      }
    }

    return false;
  }

  public static boolean containsInvalidChars(final String str, final String invalidString) {
    if (!hasText(str) || !hasText(invalidString)) {
      return true;
    }

    return str.contains(invalidString);
  }

  /**
   * default symbols are "0-9a-zA-Z"
   *
   * @param length
   * @return
   */
  public static String getRandomString(final int length) {
    return getRandomString(randomSymbols, length);
  }

  /**
   * default symbols are "0-9a-zA-Z"
   *
   * @param symbols
   * @param length
   * @return
   */
  public static String getRandomString(final char[] symbols, final int length) {

    char[] charBuf = new char[length];

    for (int idx = 0; idx < charBuf.length; ++idx)
      charBuf[idx] = symbols[randomGenerator.nextInt(symbols.length)];
    return new String(charBuf);
  }

  /**
   * Only a-zA-Z
   *
   * @param str
   * @return
   */
  public static boolean isAlphaOnly(final String str) {
    if (!hasText(str))
      throw new IllegalArgumentException("String not found");

    return str.matches("^[a-zA-Z]*$");
  }

  /**
   * Only 0-9a-zA-Z
   *
   * @param str
   * @return
   */
  public static boolean isAlphaOrNumericOnly(final String str) {
    if (!hasText(str))
      throw new IllegalArgumentException("String not found");

    return str.matches("^[0-9a-zA-Z]*$");
  }

  /**
   * Only 0-9
   *
   * @param str
   * @return
   */
  public static boolean isNumberOnly(final String str) {
    if (!hasText(str))
      throw new IllegalArgumentException("String not found");

    return str.matches("^[0-9]*$");
  }

  public static final boolean isEmptyWithTrim(final String str) {
    if (isEmpty(str) || !hasText(str.trim()))
      return true;

    return false;
  }

  public static boolean isHangul(final char cha) {
    String unicodeBlock = Character.UnicodeBlock.of(cha).toString();
    return unicodeBlock.equals(Character.UnicodeBlock.HANGUL_JAMO.toString())
           || unicodeBlock.equals(Character.UnicodeBlock.HANGUL_SYLLABLES.toString())
           || unicodeBlock.equals(Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.toString());
  }

  public static boolean isHangul(final String hangulString) {
    char[] hanguleChars = new char[hangulString.length()];
    hangulString.getChars(0, hangulString.length(), hanguleChars, 0);
    for (int i = 0; i < hanguleChars.length; i++) {
      if (!isHangul(hanguleChars[i]))
        return false;
    }

    return true;
  }

  public static String byteToHex(byte mByte) {
    return Integer.toHexString((0xff & mByte) + 0x100).substring(1);
  }

  public static String stringToHex(String str) throws UnsupportedEncodingException {

    byte[] byteStr = str.getBytes("UTF-8");

    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < byteStr.length; i++) {
      hexString.append(byteToHex(byteStr[i]));
    }

    return hexString.toString();
  }

  public static String cutText(String text, int length, String ellipsis) {
    int strLen = 0;
    char[] charArray = text.trim().toCharArray();
    int count;
    for (count = 0; count < charArray.length; count++) {
      if (strLen > length) {
        break;
      }
      // 한글 표현 범위
      boolean isHanGul = charArray[count] >= HANGUL_UNICODE_RANGE_START && charArray[count] <= HANGUL_UNICODE_RANGE_END;
      boolean isHanGulJaMo =charArray[count] >= HANGUL_JAMO_UNICODE_RANGE_START && charArray[count] <= HANGUL_JAMO_UNICODE_RANGE_END;
      if (isHanGul || isHanGulJaMo) {
        strLen += 2;
      } else {
        strLen++;
      }
    }
    if (strLen <= length) {
      return text;
    } else {
      return text.substring(0, count - 1) + ellipsis;
    }
  }

  public static Matcher getMatcher(String pattern, String content) {
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(content);
    return m;
  }
  public static String nvl(Object str,String reVal){
	  String returnStr = String.valueOf(str);
	  if(isEmpty(str) || "null".equals(returnStr) || "".equals(returnStr)){
		  return reVal;
	  }
	  return returnStr;
  }
  public static String nvl(String str,String reVal){
    if(isEmptyWithTrim(str)){
      return reVal;
    }
    return str;
  }
  public static String replaceInt(String str,String rsp){
    if(isEmpty(str)){
      return "";
    }
    if(isEmpty(rsp)){
      return "";
    }
    str = nvl(str,"");
    Integer resInt = 0;
    try {
      resInt = Integer.parseInt(str.replace(rsp,""));
    } catch(Exception ex){
      ex.printStackTrace();;
    }
    return  String.valueOf(resInt);
  }
  public static String zeroFill(String str,int lp){
    return org.apache.commons.lang.StringUtils.leftPad(str,lp,'0');
  }
  public static String nvl(String str,String reVal,int lp){
    if(isEmpty(str)){
      return "";
    }
    if(isEmptyWithTrim(str)){
      return zeroFill(reVal,lp);
    }
    return zeroFill(str, lp);
  }
  public static String addSlashes(String s) {

    s = s.replaceAll("'", "&#39;");
    s = s.replaceAll("\\\\", "\\\\\\\\");
    s = s.replaceAll("\\n", "\\\\n");
    s = s.replaceAll("\\r", "\\\\r");
    s = s.replaceAll("\\00", "\\\\0");
    s = s.replaceAll("'", "\\\\'");
		return s;
	}
  public static String unCamel(String s){
    return s.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
  }
  public static String toCamel(String s){
     String[] parts = s.split("_");
     String camelCaseString = "";
     for (String part : parts){
        camelCaseString = camelCaseString + toProperCase(part);
     }
     return Character.toLowerCase(camelCaseString.charAt(0))+camelCaseString.substring(1);
  }

  public static String toProperCase(String s) {
      return s.substring(0, 1).toUpperCase() +
                 s.substring(1).toLowerCase();
  }
}
