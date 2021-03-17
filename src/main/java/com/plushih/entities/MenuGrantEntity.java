package com.plushih.entities;

import java.io.Serializable;

public class MenuGrantEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer ugSeq;
	private Integer umSeq;
	private Integer muSeq;
	private String mgGubun;
	private String mgGrant;
	private String regDate;

	public Integer getUgSeq(){ return ugSeq; }
	public void setUgSeq(Integer ugSeq){ this.ugSeq = ugSeq; }

	public Integer getUmSeq() {
		return umSeq;
	}

	public void setUmSeq(Integer umSeq) {
		this.umSeq = umSeq;
	}

	public Integer getMuSeq(){ return muSeq; }
	public void setMuSeq(Integer muSeq){ this.muSeq = muSeq; }

	public String getMgGubun(){ return mgGubun; }
	public void setMgGubun(String mgGubun){ this.mgGubun = mgGubun; }

	public String getMgGrant(){ return mgGrant; }
	public void setMgGrant(String mgGrant){ this.mgGrant = mgGrant; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	@Override
	public String toString() {
		return "MenuGrantEntity[ugSeq="+ugSeq+",umSeq="+umSeq+",muSeq="+muSeq+",mgGubun="+mgGubun+",mgGrant="+mgGrant+",regDate="+regDate+"]";
	}
}