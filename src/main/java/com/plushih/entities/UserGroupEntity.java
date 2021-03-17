package com.plushih.entities;

import java.io.Serializable;

public class UserGroupEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer ugSeq;
	private String ugName;
	private Integer ugIsDefault;
	private String regDate;
	private Integer ugOrder;
	private Integer ugPermission;
	private String ugDesc;

	public Integer getUgSeq(){ return ugSeq; }
	public void setUgSeq(Integer ugSeq){ this.ugSeq = ugSeq; }

	public String getUgName(){ return ugName; }
	public void setUgName(String ugName){ this.ugName = ugName; }

	public Integer getUgIsDefault(){ return ugIsDefault; }
	public void setUgIsDefault(Integer ugIsDefault){ this.ugIsDefault = ugIsDefault; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public Integer getUgOrder(){ return ugOrder; }
	public void setUgOrder(Integer ugOrder){ this.ugOrder = ugOrder; }

	public Integer getUgPermission(){ return ugPermission; }
	public void setUgPermission(Integer ugPermission){ this.ugPermission = ugPermission; }

	public String getUgDesc(){ return ugDesc; }
	public void setUgDesc(String ugDesc){ this.ugDesc = ugDesc; }

	@Override
	public String toString() {
		return "UserGroupEntity[ugSeq="+ugSeq+",ugName="+ugName+",ugIsDefault="+ugIsDefault+",regDate="+regDate+",ugOrder="+ugOrder+",ugPermission="+ugPermission+",ugDesc="+ugDesc+"]";
	}
}