package com.plushih.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sangyong on 10/10/14.
 */
public class HashUtils {

  public static String encryptSHA256 (
    final String inputText ) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    return encryptByDigest( MessageDigest.getInstance( "SHA-256" ), inputText );
  }

  public static String encryptSHA256 ( final String inputText,
                                       final String salt ) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    return encryptByDigest( MessageDigest.getInstance( "SHA-256" ), inputText, salt );
  }

  public static String encyptMD5 (
    final String inputText ) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    return encryptByDigest( MessageDigest.getInstance( "MD5" ), inputText );
  }

  public static String encyptMD5 ( final String inputText,
                                   final String salt ) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    return encryptByDigest( MessageDigest.getInstance( "MD5" ), inputText, salt );
  }

  private static String encryptByDigest ( MessageDigest mDigestParam,
                                          String inputText ) throws UnsupportedEncodingException {

    return encryptByDigest( mDigestParam, inputText, null );
  }

  private static String encryptByDigest ( MessageDigest mDigestParam, String inputText,
                                          String saltKey ) throws UnsupportedEncodingException {

    if ( saltKey != null ) {
      mDigestParam.update( saltKey.getBytes( "UTF-8" ) );
    }

    byte hash[] = mDigestParam.digest( inputText.getBytes( "UTF-8" ) );

    StringBuffer hexString = new StringBuffer();
    for ( int i = 0; i < hash.length; i++ ) {
      hexString.append( StringUtils.byteToHex( hash[i] ) );
    }
    return hexString.toString();
  }

  public static String encodeBase64 ( byte[] byteArray ) {

    return Base64.encodeBase64URLSafeString( byteArray );
  }

  public static byte[] decodeBase64 ( String dataString ) {

    return Base64.decodeBase64( dataString );
  }
}
