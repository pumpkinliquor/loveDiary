package com.plushih.entities;

import java.io.Serializable;

public class FieldIndexEntity  implements Serializable {
	private static final long serialVersionUID = 770506395266028181L;

	private String tableSchema;
	private String tableName;
	private String nonUnique;
	private String indexName;
	private String columnNames;

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getNonUnique() {
		return nonUnique;
	}

	public void setNonUnique(String nonUnique) {
		this.nonUnique = nonUnique;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}

	@Override
	public String toString() {
		return "FieldIndexEntity[tableSchema=" + tableSchema + ",tableName=" + tableName + ",nonUnique=" + nonUnique + ",indexName=" + indexName + ",columnNames=" + columnNames+ "]";
	}
}