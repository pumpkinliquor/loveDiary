package com.plushih.entities;



import com.plushih.entities.file.SavedFileEntity;

import java.io.Serializable;

public class SiteTempFileEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer stfSeq;
	private String stfBbs;
	private String stfType;
	private String stfOrFile;
	private String stfFile;
	private String stfPath;
	private String stfCode;
	private String stfDesc;
	private String regDate;

	public SavedFileEntity getSavedFileEntity() {
		return savedFileEntity;
	}

	public void setSavedFileEntity(SavedFileEntity savedFileEntity) {
		this.savedFileEntity = savedFileEntity;
	}

	private SavedFileEntity savedFileEntity;

	public Integer getStfSeq(){ return stfSeq; }
	public void setStfSeq(Integer stfSeq){ this.stfSeq = stfSeq; }

	public String getStfBbs(){ return stfBbs; }
	public void setStfBbs(String stfBbs){ this.stfBbs = stfBbs; }

	public String getStfType(){ return stfType; }
	public void setStfType(String stfType){ this.stfType = stfType; }

	public String getStfOrFile(){ return stfOrFile; }
	public void setStfOrFile(String stfOrFile){ this.stfOrFile = stfOrFile; }

	public String getStfFile(){ return stfFile; }
	public void setStfFile(String stfFile){ this.stfFile = stfFile; }

	public String getStfPath(){ return stfPath; }
	public void setStfPath(String stfPath){ this.stfPath = stfPath; }

	public String getStfCode(){ return stfCode; }
	public void setStfCode(String stfCode){ this.stfCode = stfCode; }

	public String getStfDesc(){ return stfDesc; }
	public void setStfDesc(String stfDesc){ this.stfDesc = stfDesc; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	@Override
	public String toString() {
		return "SiteTempFileEntity[stfSeq="+stfSeq+",stfBbs="+stfBbs+",stfType="+stfType+",stfOrFile="+stfOrFile+",stfFile="+stfFile+",stfPath="+stfPath+",stfCode="+stfCode+",stfDesc="+stfDesc+",regDate="+regDate+"]";
	}
}