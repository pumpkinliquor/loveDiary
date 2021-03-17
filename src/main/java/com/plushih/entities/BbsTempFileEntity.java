package com.plushih.entities;



import com.plushih.entities.file.SavedFileEntity;

import java.io.Serializable;
import java.sql.Timestamp;

public class BbsTempFileEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer btfSeq;
	private String btfBbs;
	private String btfType;
	private String btfOrFile;
	private String btfFile;
	private String btfPath;
	private String btfCode;
	private String btfDesc;
	private String regDate;

	public SavedFileEntity getSavedFileEntity() {
		return savedFileEntity;
	}

	public void setSavedFileEntity(SavedFileEntity savedFileEntity) {
		this.savedFileEntity = savedFileEntity;
	}

	private SavedFileEntity savedFileEntity;

	public Integer getBtfSeq(){ return btfSeq; }
	public void setBtfSeq(Integer btfSeq){ this.btfSeq = btfSeq; }

	public String getBtfBbs(){ return btfBbs; }
	public void setBtfBbs(String btfBbs){ this.btfBbs = btfBbs; }

	public String getBtfType(){ return btfType; }
	public void setBtfType(String btfType){ this.btfType = btfType; }

	public String getBtfOrFile(){ return btfOrFile; }
	public void setBtfOrFile(String btfOrFile){ this.btfOrFile = btfOrFile; }

	public String getBtfFile(){ return btfFile; }
	public void setBtfFile(String btfFile){ this.btfFile = btfFile; }

	public String getBtfPath(){ return btfPath; }
	public void setBtfPath(String btfPath){ this.btfPath = btfPath; }

	public String getBtfCode(){ return btfCode; }
	public void setBtfCode(String btfCode){ this.btfCode = btfCode; }

	public String getBtfDesc(){ return btfDesc; }
	public void setBtfDesc(String btfDesc){ this.btfDesc = btfDesc; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	@Override
	public String toString() {
		return "BbsTempFileEntity[btfSeq="+btfSeq+",btfBbs="+btfBbs+",btfType="+btfType+",btfOrFile="+btfOrFile+",btfFile="+btfFile+",btfPath="+btfPath+",btfCode="+btfCode+",btfDesc="+btfDesc+",regDate="+regDate+"]";
	}
}