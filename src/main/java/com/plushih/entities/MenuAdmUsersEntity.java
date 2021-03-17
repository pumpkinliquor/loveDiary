package com.plushih.entities;

import java.io.Serializable;

public class MenuAdmUsersEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer maSeq;
	private String maParent;
	private String maCode;
	private String maLevel;
	private String maModule;
	private String maName;
	private String maNavi;
	private String maPath;
	private Integer maSort;
	private String maIsUse;
	private String maIcon;
	private String regDate;

	public Integer getMaSeq() {
		return maSeq;
	}

	public void setMaSeq(Integer maSeq) {
		this.maSeq = maSeq;
	}

	public String getMaParent() {
		return maParent;
	}

	public void setMaParent(String maParent) {
		this.maParent = maParent;
	}

	public String getMaCode() {
		return maCode;
	}

	public void setMaCode(String maCode) {
		this.maCode = maCode;
	}

	public String getMaLevel() {
		return maLevel;
	}

	public void setMaLevel(String maLevel) {
		this.maLevel = maLevel;
	}

	public String getMaModule() {
		return maModule;
	}

	public void setMaModule(String maModule) {
		this.maModule = maModule;
	}

	public String getMaName() {
		return maName;
	}

	public void setMaName(String maName) {
		this.maName = maName;
	}

	public String getMaNavi() {
		return maNavi;
	}

	public void setMaNavi(String maNavi) {
		this.maNavi = maNavi;
	}

	public String getMaPath() {
		return maPath;
	}

	public void setMaPath(String maPath) {
		this.maPath = maPath;
	}

	public Integer getMaSort() {
		return maSort;
	}

	public void setMaSort(Integer maSort) {
		this.maSort = maSort;
	}

	public String getMaIsUse() {
		return maIsUse;
	}

	public void setMaIsUse(String maIsUse) {
		this.maIsUse = maIsUse;
	}

	public String getMaIcon() {
		return maIcon;
	}

	public void setMaIcon(String maIcon) {
		this.maIcon = maIcon;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "MenuAdmUsersEntity[maSeq="+maSeq+",maParent="+maParent+",maCode="+maCode+",maLevel="+maLevel+",maModule="+maModule+",maName="+maName+",maNavi="+maNavi+",maPath="+maPath+",maSort="+maSort+",maIsUse="+maIsUse+",maIcon="+maIcon+",regDate="+regDate+"]";
	}
}