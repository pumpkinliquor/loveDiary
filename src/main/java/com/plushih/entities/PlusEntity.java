package com.plushih.entities;

import com.plushih.common.utils.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PlusEntity implements Serializable{

	/**
     * Draw counter. This is used by DataTables to ensure that the Ajax returns from
     * server-side processing requests are drawn in sequence by DataTables (Ajax
     * requests are asynchronous and thus can return out of sequence). This is used
     * as part of the draw return parameter.
     */
    private int draw;

    /**
     * Paging first record indicator. This is the start point in the current data
     * set (0 index based - i.e. 0 is the first record).
     */
    private int start;

    /**
     * Number of records that the table can display in the current draw. It is
     * expected that the number of records returned will be equal to this number,
     * unless the server has fewer records to return. Note that this can be -1 to
     * indicate that all records should be returned (although that negates any
     * benefits of server-side processing!)
     */
    private int length;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	private String biName;

	public String getBiName() {
		return biName;
	}

	public void setBiName(String biName) {
		this.biName = biName;
	}

	private static final long serialVersionUID = 770506395266028181L;

	private static Set<String> classFields = new HashSet<String>();
	private static HashMap<String,String> classFieldsMap = new HashMap<String,String>();
	private Set<String> getAllFields(final Class<?> type) {

		if(classFields.size()>0) return classFields;
        for (Field field : type.getDeclaredFields()) {
            classFields.add(field.getName().toLowerCase());
            classFields.add(StringUtils.unCamel(field.getName()));
            classFieldsMap.put(StringUtils.unCamel(field.getName()),field.getName());
            classFieldsMap.put(field.getName().toLowerCase(),field.getName());
        }

        if (type.getSuperclass() != null) {
            classFields.addAll(getAllFields(type.getSuperclass()));
        }
        return classFields;
    }
    private boolean existsField(final String propertyName) {
        if (classFields.contains(propertyName)) {
            return true;
        }
        return false;
    }
//    public Type getType(String fieldName){
//		getAllFields(this.getClass());
//		Type objType = null;
//		try {
//			if (!existsField(fieldName)) {
//				return null;
//			}
//			Field field = this.getClass().getDeclaredField(classFieldsMap.get(fieldName));
//			objType =  field.getGenericType();
//		} catch (NoSuchFieldException e) {
//			e.printStackTrace();
//		}
//		return objType;
//	}
	//public String get(String fieldName,)

	public void set(String fieldName,Object setValue){

		getAllFields(this.getClass());
		fieldName = fieldName.toLowerCase();

		try {

			if(!existsField(fieldName)) {
				return ;
			}

			Field field = this.getClass().getDeclaredField(classFieldsMap.get(fieldName));

			if(field!=null){
				field.setAccessible(true);

				switch (field.getType().getName()){
					case "String":

					field.set(this,(String)setValue);
					break;
					case "int":
						field.set(this,(int)setValue);
					break;
					case "Integer":
						field.set(this,(Integer)setValue);
					break;
					case "Long":
						field.set(this,(Long)setValue);
					break;
					default:
						field.set(this,setValue);
						break;
				}

			}


		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	public Object get(String fieldName){
		String res = null;
		getAllFields(this.getClass());
		fieldName = fieldName.toLowerCase();

		try {
			if(!existsField(fieldName)) {
				return res;
			}
			Field field = this.getClass().getDeclaredField(classFieldsMap.get(fieldName));

//			Debug.log("field.getType().getName()");
//				Debug.log(field.getType().getName());
//				Debug.log(field.getType().getSimpleName());
			if(field!=null){
				field.setAccessible(true);
				Object obj = field.get(this);

				switch (field.getType().getName()){
					case "String":

					res = obj==null?"":obj.toString();
					break;
					case "int":
						res = String.valueOf((Integer)Integer.parseInt(obj.toString()));
					break;
					case "Integer":
						res = String.valueOf((Integer)Integer.parseInt(obj.toString()));
					break;
					case "Long":
						//field.set(this,(Long)setValue);

						res = String.valueOf(Long.parseLong(obj.toString()));
					break;
					default:
						res = obj==null?"":obj.toString();
						break;
				}

			}

		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return res;
	}
}