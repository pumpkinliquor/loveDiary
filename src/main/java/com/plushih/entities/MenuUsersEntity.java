package com.plushih.entities;

import java.io.Serializable;

public class MenuUsersEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer muSeq;
	private String muParent;
	private String muCode;
	private String muLevel;
	private String muModule;
	private String muName;
	private String muNavi;
	private String muPath;
	private Integer muSort;
	private String muIsUse;
	private String muIcon;
	private String regDate;

	public Integer getMuSeq(){ return muSeq; }
	public void setMuSeq(Integer muSeq){ this.muSeq = muSeq; }

	public String getMuParent(){ return muParent; }
	public void setMuParent(String muParent){ this.muParent = muParent; }

	public String getMuCode(){ return muCode; }
	public void setMuCode(String muCode){ this.muCode = muCode; }

	public String getMuLevel(){ return muLevel; }
	public void setMuLevel(String muLevel){ this.muLevel = muLevel; }

	public String getMuModule(){ return muModule; }
	public void setMuModule(String muModule){ this.muModule = muModule; }

	public String getMuName(){ return muName; }
	public void setMuName(String muName){ this.muName = muName; }

	public String getMuNavi(){ return muNavi; }
	public void setMuNavi(String muNavi){ this.muNavi = muNavi; }

	public String getMuPath(){ return muPath; }
	public void setMuPath(String muPath){ this.muPath = muPath; }

	public Integer getMuSort(){ return muSort; }
	public void setMuSort(Integer muSort){ this.muSort = muSort; }

	public String getMuIsUse(){ return muIsUse; }
	public void setMuIsUse(String muIsUse){ this.muIsUse = muIsUse; }

	public String getMuIcon(){ return muIcon; }
	public void setMuIcon(String muIcon){ this.muIcon = muIcon; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	@Override
	public String toString() {
		return "MenuUsersEntity[muSeq="+muSeq+",muParent="+muParent+",muCode="+muCode+",muLevel="+muLevel+",muModule="+muModule+",muName="+muName+",muNavi="+muNavi+",muPath="+muPath+",muSort="+muSort+",muIsUse="+muIsUse+",muIcon="+muIcon+",regDate="+regDate+"]";
	}
}