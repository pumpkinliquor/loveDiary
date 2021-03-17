package com.plushih.entities;

import java.io.Serializable;

public class FieldInfoEntity extends PlusEntity implements Serializable {
	private static final long serialVersionUID = 770506395266028181L;

	private String tableName;
	private String columnKey;
	private String columnName;
	private String columnType;
	private String columnComment;
	private String columnDefault;
	private String dataType;
	private String dataLength;
	private String isNullable;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	@Override
	public String toString() {
		return "FieldInfoEntity[tableName=" + tableName + ",columnKey=" + columnKey + ",columnName=" + columnName + ",columnType=" + columnType + ",columnComment=" + columnComment + ",columnDefault=" + columnDefault + ",dataType=" + dataType + ",dataLength=" + dataLength + ",isNullable=" + isNullable + "]";
	}
}