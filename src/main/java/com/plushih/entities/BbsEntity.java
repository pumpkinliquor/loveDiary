package com.plushih.entities;

import java.io.Serializable;

public class BbsEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;


	private Integer bbSeq;
	private Integer biSeq;
	private Integer bcSeq;
	private Integer cgSeq;
	private Integer lgSeq;
	private String bbType;
	private String bbBbs;
	private String bbOpen;
	private String bbDelyn;
	private String bbTitle;
	private String bbEtc01;
	private String bbContents;
	private Integer bbView;
	private String bbDate;
	private String regDate;
	private String regUserId;
	private String uptDate;
	private String udtUserId;

	public Integer getBbSeq(){ return bbSeq; }
	public void setBbSeq(Integer bbSeq){ this.bbSeq = bbSeq; }

	public Integer getBiSeq(){ return biSeq; }
	public void setBiSeq(Integer biSeq){ this.biSeq = biSeq; }

	public Integer getBcSeq(){ return bcSeq; }
	public void setBcSeq(Integer bcSeq){ this.bcSeq = bcSeq; }

	public Integer getCgSeq(){ return cgSeq; }
	public void setCgSeq(Integer cgSeq){ this.cgSeq = cgSeq; }

	public Integer getLgSeq(){ return lgSeq; }
	public void setLgSeq(Integer lgSeq){ this.lgSeq = lgSeq; }

	public String getBbType(){ return bbType; }
	public void setBbType(String bbType){ this.bbType = bbType; }

	public String getBbBbs(){ return bbBbs; }
	public void setBbBbs(String bbBbs){ this.bbBbs = bbBbs; }

	public String getBbOpen(){ return bbOpen; }
	public void setBbOpen(String bbOpen){ this.bbOpen = bbOpen; }

	public String getBbDelyn(){ return bbDelyn; }
	public void setBbDelyn(String bbDelyn){ this.bbDelyn = bbDelyn; }

	public String getBbTitle(){ return bbTitle; }
	public void setBbTitle(String bbTitle){ this.bbTitle = bbTitle; }

	public String getBbEtc01(){ return bbEtc01; }
	public void setBbEtc01(String bbEtc01){ this.bbEtc01 = bbEtc01; }

	public String getBbContents(){ return bbContents; }
	public void setBbContents(String bbContents){ this.bbContents = bbContents; }

	public Integer getBbView(){ return bbView; }
	public void setBbView(Integer bbView){ this.bbView = bbView; }

	public String getBbDate(){ return bbDate; }
	public void setBbDate(String bbDate){ this.bbDate = bbDate; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public String getRegUserId(){ return regUserId; }
	public void setRegUserId(String regUserId){ this.regUserId = regUserId; }

	public String getUptDate(){ return uptDate; }
	public void setUptDate(String uptDate){ this.uptDate = uptDate; }

	public String getUdtUserId(){ return udtUserId; }
	public void setUdtUserId(String udtUserId){ this.udtUserId = udtUserId; }

	@Override
	public String toString() {
		return "BbsEntity[bbSeq="+bbSeq+",biSeq="+biSeq+",bcSeq="+bcSeq+",cgSeq="+cgSeq+",lgSeq="+lgSeq+",bbType="+bbType+",bbBbs="+bbBbs+",bbOpen="+bbOpen+",bbDelyn="+bbDelyn+",bbTitle="+bbTitle+",bbEtc01="+bbEtc01+",bbContents="+bbContents+",bbView="+bbView+",bbDate="+bbDate+",regDate="+regDate+",regUserId="+regUserId+",uptDate="+uptDate+",udtUserId="+udtUserId+"]";
	}
}