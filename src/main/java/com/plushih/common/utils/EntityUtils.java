package com.plushih.common.utils;


import com.plushih.common.ci.Debug;

import java.lang.reflect.Field;


/**
 * Created by sangyong on 10/10/14.
 */
public class EntityUtils {


	public static Object fieldValue(String fieldName,Class T) {
		Object resFieldValue = null;
		try {



			//Debug.log(T.getName().getClass().getDeclaredField(fieldName));
			Field[] fields=T.getClass().getDeclaredFields();
			Debug.log("########fields.length"+fields.length);
			for(int i=0;i<fields.length;i++){
				Debug.log(fields[i]);
			}
			Field plusField = T.getClass().getDeclaredField(fieldName);


			plusField.setAccessible(true);
//			try {
//				resFieldValue = plusField.get(T.getClass());
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return resFieldValue;
	}

}
