package com.plushih.entities;

import java.io.Serializable;

public class SiteAttachFileEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer safSeq;
	private Integer seq;

	private String safBbs;
	private String safType;
	private String safOrFile;
	private String safFile;
	private String safPath;
	private String safCode;
	private String delSafSeq;
	private String safDesc;
	private String regDate;

	public String getDelSafSeq() {
		return delSafSeq;
	}

	public void setDelSafSeq(String delSafSeq) {
		this.delSafSeq = delSafSeq;
	}

	public Integer getSafSeq(){ return safSeq; }
	public void setSafSeq(Integer safSeq){ this.safSeq = safSeq; }

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getSafBbs(){ return safBbs; }
	public void setSafBbs(String safBbs){ this.safBbs = safBbs; }

	public String getSafType(){ return safType; }
	public void setSafType(String safType){ this.safType = safType; }

	public String getSafOrFile(){ return safOrFile; }
	public void setSafOrFile(String safOrFile){ this.safOrFile = safOrFile; }

	public String getSafFile(){ return safFile; }
	public void setSafFile(String safFile){ this.safFile = safFile; }

	public String getSafPath(){ return safPath; }
	public void setSafPath(String safPath){ this.safPath = safPath; }

	public String getSafCode(){ return safCode; }
	public void setSafCode(String safCode){ this.safCode = safCode; }

	public String getSafDesc(){ return safDesc; }
	public void setSafDesc(String safDesc){ this.safDesc = safDesc; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	@Override
	public String toString() {
		return "SiteAttachFileEntity[safSeq="+safSeq+",seq="+seq+",safBbs="+safBbs+",safType="+safType+",safOrFile="+safOrFile+",safFile="+safFile+",safPath="+safPath+",safCode="+safCode+",safDesc="+safDesc+",regDate="+regDate+"]";
	}
}