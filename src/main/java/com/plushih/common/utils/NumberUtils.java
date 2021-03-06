package com.plushih.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

/**
 * Created by sangyong on 10/20/14.
 */
public class NumberUtils {

  private static Random randomGenerator = new Random(System.currentTimeMillis());

  private NumberUtils() {
    throw new AssertionError();
  }

  /**
   * double 값을 10진수 형태의 문자열로 변환하여 반환.<br>
   * <p/>
   * <div class="ko">
   * - java.text.DecimalFormat을 사용하여 처리한다.<br>
   * - 예를 들어, 5277095325298.2523이라는 값에 "###,###.##"이라는 포맷을 적용하면 "5,277,095,325,298.25"로 변환됨.<br>
   * - 그러나, 5277095325298.2523이라는 값에 "###,###.####"이라는 포맷을 적용하면 "5,277,095,325,298.252"로 변환됨.<br>
   * - 마찬가지로 111115277095325298.2523이라는 값에 "###,###.##"이라는 포맷을 적용하면 "111,115,277,095,325,296"로 변환됨.<br>
   * - 이것은, Java에서의 double 한계에 기인함.<br>
   * </div>
   * <pre>
   * String numberString = NumberUtil.formatNumber(12345.67d, "###,###.#"); // numberString : "12,345.7"
   * </pre>
   *
   * @param d      double값.
   * @param format : 값의 형태.
   * @return 변환된 숫자형태의 문자열값.
   */
  public static String formatNumber(double d, String format) {
    DecimalFormat decimalformat = new DecimalFormat(format);
    return decimalformat.format(d);
  }

  /**
   * double 값을 10진수 형태의 문자열로 변환하여 반환함.
   * <p/>
   * <pre>
   * NumberUtil.formatNumber(1023412, &quot;###,###,###&quot;) = &quot;1,023,412&quot;;
   * NumberUtil.formatNumber(1023412123, &quot;###,###&quot;) = &quot;1,023,412,123&quot;;
   * NumberUtil.formatNumber(1023412123, &quot;##,##&quot;) = &quot;10,23,41,21,23&quot;;
   * NumberUtil.formatNumber(1023412123, &quot;##.##&quot;) = &quot;1023412123&quot;;
   * </pre>
   *
   * @param intValue 정수값.
   * @param format   변환할 형태.
   * @return 변환된 숫자형 문자열.
   */
  public static String formatNumber(int intValue, String format) {
    DecimalFormat df = new DecimalFormat(format);
    return df.format(intValue);
  }

  /**
   * long값을 10진수 형태의 문자열로 변환하여 반환.<br>
   * <pre>
   * String numberString = NumberUtil.formatNumber(12345.67L, "###,###.#"); // numberString : "12,345.7"
   * </pre>
   *
   * @param l      long값.
   * @param format 변환할 형태.
   * @return 변환된 long타입의 문자열.
   */
  public static String formatNumber(long l, String format) {
    DecimalFormat decimalformat = new DecimalFormat(format);
    return decimalformat.format(l);
  }

  /**
   * 숫자형태의 문자열을 해당 포멧형식의 숫자형 문자열로 변환하여 반환.<br>
   * 화폐 표시에 사용하는 ,와 소수점을 표시하는 .을 제외한 다른 문자가 포함되어 있으면 안됨
   * <p/>
   * <pre>
   * String numberString = NumberUtil.formatNumber("1234567", "#,##0.000"); // numberString : "1,234,567.000"
   * </pre>
   *
   * @param str    숫자형 문자열값.
   * @param format 변환형식.
   * @return 변환된 숫자형 문자열.
   */
  public static String formatNumber(String str, String format) throws Exception {
    DecimalFormat df = new DecimalFormat();
    df.applyPattern(format);
    Number sourceNumber = df.parse(str.replaceAll(",", ""));
    return df.format(sourceNumber);
  }

  /**
   * Locale 설정에 따른  currency mark 가 적용된 문자열 반환.<br>
   * <p/>
   * <pre>
   * NumberUtil.formatNumberByLocale(3527900, Locale.KOREA) = &quot;￦3,527,900&quot;;
   * NumberUtil.formatNumberByLocale(3527900, Locale.US) = &quot;$3,527,900.00&quot;;
   * </pre>
   *
   * @param intValue 변환할 정수형 값.
   * @param locale   Locale 설정값.
   * @return 변환된 숫자형 문자열.
   */
  public static String formatNumberByLocale(int intValue, Locale locale) {
    NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
    return nf.format(intValue);
  }

  /**
   * double 값에 decimal points를 적용한 문자열을 반환함.
   * <p/>
   * <pre>
   * NumberUtil.formatNumberByPoint(10231023123.1213, 2) = &quot;10,231,023,123.12&quot;;
   * NumberUtil.formatNumberByPoint(10231023123.1213, 6) = &quot;10,231,023,123.121&quot;;
   * </pre>
   *
   * @param inputValue double 값.
   * @param point      decimal points 를 적용할 자리수.(기본값은 3.)
   * @return double 값에 decimal points를 적용한 문자열.
   */
  public static String formatNumberByPoint(double inputValue, int point) {
    StringBuilder stringBuilder = new StringBuilder();
    if (point > 0) {
      stringBuilder.append("###,###,###.##0.");
      for (int i = 1; i < point; i++) {
        stringBuilder.append("0");
      }
    } else {
      stringBuilder.append("###,###,###.###");
    }

    DecimalFormat df = new DecimalFormat(stringBuilder.toString());
    return String.valueOf(df.format(inputValue));
  }

  /**
   * 랜덤한 값을 반환함.
   *
   * @param targetClass 값의 유형을 지정함. <code>Integer</code>,
   *                    <code>Long</code>, <code>Float</code>, <code>Double</code>
   * @return 랜덤한 값.
   */
  public static <T extends Number> T getRandomNumber(Class<T> targetClass) {
    return getRandomNumber(targetClass, true);
  }

  /**
   * 랜덤한 값을 반환함.
   *
   * @param targetClass 값의 유형을 지정함. <code>Integer</code>,
   *                    <code>Long</code>, <code>Float</code>, <code>Double</code>
   * @param sign        양수/음수 여부를 지정함. (true인 경우 양수임.)
   * @return 랜덤한 값.
   */
  public static <T extends Number> T getRandomNumber(Class<T> targetClass, boolean sign) {
    if (targetClass.equals(Integer.class)) {
      return (T) (Integer) (randomGenerator.nextInt() * (sign ? 1 : -1));
    } else if (targetClass.equals(Long.class)) {
      return (T) (Long) (randomGenerator.nextLong() * (sign ? 1 : -1));
    } else if (targetClass.equals(Float.class)) {
      return (T) (Float) (randomGenerator.nextFloat() * (sign ? 1 : -1));
    } else if (targetClass.equals(Double.class)) {
      return (T) (Double) (randomGenerator.nextDouble() * (sign ? 1 : -1));
    }
    return (T) null;
  }

  /**
   * 랜덤한 값을 반환함.
   *
   * @param targetClass 값의 유형을 지정함.<code>Integer</code>,
   *                    <code>Long</code>, <code>Float</code>, <code>Double</code>
   * @param fixedLength 길이 값.
   * @return 랜덤한 값.
   */
  public static <T extends Number> T getRandomNumber(Class<T> targetClass, int fixedLength) {
    if (fixedLength < 0)
      return (T) null;

    double randomNumber = 0.0;

    if (targetClass.equals(Integer.class)) {
      randomNumber = getRandomNumber(fixedLength, 10, Integer.MAX_VALUE);
      return (T) (Integer) (int) randomNumber;
    } else if (targetClass.equals(Long.class)) {
      randomNumber = getRandomNumber(fixedLength, 19, Long.MAX_VALUE);
      return (T) (Long) (long) randomNumber;
    } else if (targetClass.equals(Float.class)) {
      int digit = randomGenerator.nextInt(fixedLength);
      randomNumber = getRandomNumber(digit, 39, Float.MAX_VALUE);
      return (T) (Float) (float) randomNumber;
    } else if (targetClass.equals(Double.class)) {
      int digit = randomGenerator.nextInt(fixedLength);
      randomNumber = getRandomNumber(digit, 309, Double.MAX_VALUE);
      return (T) (Double) randomNumber;
    }
    return (T) null;
  }

  /**
   * 랜덤한 값을 반환함.
   *
   * @param targetClass 값의 유형을 지정함. <code>Integer</code>
   *                    , <code>Long</code>, <code>Float</code>, <code>Double</code>
   * @param min         minimum value 최소값.
   * @param max         maximum value 최대값.
   * @return 랜덤한 값.
   */
  public static <T extends Number> T getRandomNumber(Class<T> targetClass, T min, T max) {
    double minDouble = org.springframework.util.NumberUtils.convertNumberToTargetClass(min, Double.class);
    double maxDouble = org.springframework.util.NumberUtils.convertNumberToTargetClass(max, Double.class);
    double randomNumber = (Math.random() * (maxDouble - minDouble + 1)) + minDouble;

    if (targetClass.equals(Integer.class)) {
      return (T) (Integer) (int) randomNumber;
    } else if (targetClass.equals(Long.class)) {
      return (T) (Long) (long) randomNumber;
    } else if (targetClass.equals(Float.class)) {
      return (T) (Float) (float) randomNumber;
    } else if (targetClass.equals(Double.class)) {
      return (T) (Double) randomNumber;
    }
    return (T) null;
  }

  /**
   * 랜덤한 값을 반환함.
   *
   * @param fixedLength 숫자 길이값.
   * @param maxLength   최대 길이값.
   * @param maxValue    최대값.
   * @return 랜덤한 값.
   */
  public static double getRandomNumber(int fixedLength, int maxLength, double maxValue) {
    double max = 0;
    double base = Math.pow(10, fixedLength - 1);

    if (fixedLength < maxLength) {
      max = Math.pow(10, fixedLength) - 1;
    } else if (fixedLength == maxLength) {
      max = maxValue;
    } else {
      return -1;
    }
    return (Math.random() * (max - base + 1)) + base;
  }

  /**
   * 문자열이 숫자형태인지 여부를 반환함.
   *
   * @param str 문자열 값.
   * @return 문자열이 숫자형태인 경우 true.
   */
  public static boolean isNumber(String str) {
    if (str == null || str == "")
      return false;

    if (str.matches("^[-+]?\\d+(\\.\\d+)?$")) {
      return true;
    } else {
      try {
        @SuppressWarnings("unused")
        double doubleVal = Double.parseDouble(str);
        return true;
      } catch (NumberFormatException de) {
        try {
          @SuppressWarnings("unused")
          BigDecimal bigDecimalVal = new BigDecimal(str);
          return true;
        } catch (NumberFormatException be) {
          return false;
        }
      }
    }
  }

  /**
   * 문자열을 정수형으로 변환하여 반환함.
   *
   * @param str 문자열 값.
   * @return 문자열을 정수형으로 변환한 값.
   */
  public static int stringToInt(String str) {
    if (StringUtils.isEmptyWithTrim(str))
      return -1;

    return Integer.parseInt(str.trim());
  }
}
