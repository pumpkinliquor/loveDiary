package com.plushih.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class BbsAttachFileEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer bafSeq;
	private Integer bbSeq;
	private String bafBbs;
	private String bafType;
	private String bafOrFile;
	private String bafFile;
	private String bafPath;
	private String bafCode;
	private String delBafSeq;
	private String bafDesc;
	private String regDate;

	public String getDelBafSeq() {
		return delBafSeq;
	}

	public void setDelBafSeq(String delBafSeq) {
		this.delBafSeq = delBafSeq;
	}

	public Integer getBafSeq(){ return bafSeq; }
	public void setBafSeq(Integer bafSeq){ this.bafSeq = bafSeq; }

	public Integer getBbSeq(){ return bbSeq; }
	public void setBbSeq(Integer bbSeq){ this.bbSeq = bbSeq; }

	public String getBafBbs(){ return bafBbs; }
	public void setBafBbs(String bafBbs){ this.bafBbs = bafBbs; }

	public String getBafType(){ return bafType; }
	public void setBafType(String bafType){ this.bafType = bafType; }

	public String getBafOrFile(){ return bafOrFile; }
	public void setBafOrFile(String bafOrFile){ this.bafOrFile = bafOrFile; }

	public String getBafFile(){ return bafFile; }
	public void setBafFile(String bafFile){ this.bafFile = bafFile; }

	public String getBafPath(){ return bafPath; }
	public void setBafPath(String bafPath){ this.bafPath = bafPath; }

	public String getBafCode(){ return bafCode; }
	public void setBafCode(String bafCode){ this.bafCode = bafCode; }

	public String getBafDesc(){ return bafDesc; }
	public void setBafDesc(String bafDesc){ this.bafDesc = bafDesc; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	@Override
	public String toString() {
		return "BbsAttachFileEntity[bafSeq="+bafSeq+",bbSeq="+bbSeq+",bafBbs="+bafBbs+",bafType="+bafType+",bafOrFile="+bafOrFile+",bafFile="+bafFile+",bafPath="+bafPath+",bafCode="+bafCode+",bafDesc="+bafDesc+",regDate="+regDate+"]";
	}
}