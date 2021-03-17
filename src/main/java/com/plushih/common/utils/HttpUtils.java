package com.plushih.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sangyong on 10/10/14.
 */
public class HttpUtils {

  /**
   * Request객체에서 파라메터를 전부 가져온 후 QueryString 형태로 만들어서 반환함.
   *
   * @param request
   * @return
   */
  public static String getParamsToStr ( HttpServletRequest request ) {

    StringBuilder sb = new StringBuilder();

    Enumeration< String > parameterNames = request.getParameterNames();
    while ( parameterNames.hasMoreElements() ) {
      String id = parameterNames.nextElement();
      sb.append( sb.length() == 0 ? "?" : "&" );
      sb.append( id );
      sb.append( "=" );
      sb.append( request.getParameter( id ) );
    }
    return sb.toString();
  }

  /**
   * exceptId 같은id를 가진 파라메터는 쿼리 생성시 제외
   *
   * @param request
   * @param exceptId
   * @return
   */
  public static String getParamsToStrExcludeById ( HttpServletRequest request, String exceptId ) {

    StringBuilder sb = new StringBuilder();

    Enumeration< String > parameterNames = request.getParameterNames();
    while ( parameterNames.hasMoreElements() ) {
      String id = parameterNames.nextElement();
      if ( id.equals( exceptId ) )
        continue;

      sb.append( sb.length() == 0 ? "?" : "&" );
      sb.append( id );
      sb.append( "=" );
      sb.append( request.getParameter( id ) );
    }
    return sb.toString();
  }

  private static String buildParameters ( Map< String, Object > params ) throws Exception {

    StringBuilder sb = new StringBuilder();
    Iterator< String > keysSet = params.keySet().iterator();
    for ( Iterator< String > i = keysSet; i.hasNext(); ) {
      String key = i.next();
      sb.append( key );
      sb.append( "=" );
      sb.append( URLEncoder.encode( String.valueOf( params.get( key ) ), "UTF-8" ) );
      if ( i.hasNext() )
        sb.append( "&" );
    }
    return sb.toString();
  }

  public static String sendRequest ( URL url, String method, Map< String, Object > params ) throws Exception {

    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod( method );
    con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
    con.setDoInput( true );

    if ( method.equals( "POST" ) ) {
      con.setDoOutput( true );
      OutputStream out = con.getOutputStream();
      String paramstr = "";
      if ( params != null ) {
        paramstr = buildParameters( params );
        out.write( paramstr.getBytes( "UTF-8" ) );
        out.flush();
        out.close();
      }
    }

    InputStream in = null;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    byte[] buf = new byte[4096];
    String output = null;

    try {
      in = con.getInputStream();
      while ( true ) {
        int readlen = in.read( buf );
        if ( readlen < 1 )
          break;
        bos.write( buf, 0, readlen );
      }
      output = new String( bos.toByteArray(), "UTF-8" );
    } catch ( IOException e ) {
      e.printStackTrace();
      if ( con.getResponseCode() == 500 ) {
        bos.reset();
        InputStream err = con.getErrorStream();
        while ( true ) {
          int readlen = err.read( buf );
          if ( readlen < 1 )
            break;
          bos.write( buf, 0, readlen );
        }
        output = new String( bos.toByteArray(), "UTF-8" );
        System.err.println( output );
      }
      throw e;
    } finally {
      if ( in != null )
        in.close();
      if ( con != null )
        con.disconnect();
      if ( bos != null )
        bos.close();
    }
    return output;
  }

  public static byte[] getContentsFromUrl ( final String urlParam ) throws IOException {

    URL url = null;
    InputStream is = null;
    ByteArrayOutputStream os = null;
    try {
      url = new URL( urlParam );
      is = url.openStream();
      os = new ByteArrayOutputStream();
      byte[] buf = new byte[4096];
      int n;
      while ( ( n = is.read( buf ) ) >= 0 ) { os.write( buf, 0, n ); }
      return os.toByteArray();
    } catch ( IOException e ) {
      throw e;
    } finally {
      if ( os != null ) os.close();
      if ( is != null ) is.close();
    }
  }

}