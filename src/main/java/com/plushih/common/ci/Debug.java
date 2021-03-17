package com.plushih.common.ci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * 
 * @author hsk3807
 */
public class Debug {
	private static final Logger LOGGER = LoggerFactory.getLogger( Debug.class );
	public static HashMap<String,Long> runMap;
	public static void sLog(String s){
		runMap.put(s,System.currentTimeMillis());
	}
	public static void eLog(String s){
		long end = System.currentTimeMillis();

		Debug.log( "실행 시간 : " + ( end - runMap.get(s) )/1000.0 +"초");

	}
	/**
	 * 로그
	 * @param obj
	 * @param error
	 * @param s
	 * @param e
	 */
	public static void log(Object obj, boolean error,Object s,Object e) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss:S");
		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		int i=0;
		StackTraceElement realRraces = null;
		String fullLine = "";
		for(StackTraceElement stackTraceElement : traces){
			if(stackTraceElement.toString().indexOf("com.plushih")>-1){
				realRraces = stackTraceElement;
			}
			i++;
		}
		if(realRraces!=null){
			fullLine +=realRraces.toString();
		}

		if(error)
			LOGGER.info("Debug["+ dateFormat.format(new Date()) + "]{"+fullLine+"} " +s+ (obj!=null?obj.toString():"") +e);
		else
			LOGGER.info("Debug["+ dateFormat.format(new Date()) + "]{"+fullLine+"} " +s+ (obj!=null?obj.toString():"") +e);
	}

	public static void log(Object obj){
		Debug.log(obj, false,"","");
	}
	public static void log(Object obj,Object obj2){
		Debug.log(obj, false,obj2,"");
	}
	/**
	 * Standard output log
	 * @param obj
	 */
	public static void log(Object obj,String s,String e) {
		Debug.log(obj, false,"\r\n:::::"+s+"\r\n","\r\n:::::"+e);
	}
	
}
