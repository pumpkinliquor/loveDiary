package com.plushih.entities;

import java.io.Serializable;

public class ProductEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;


	private Integer pdSeq;
	private Integer lgSeq;
	private String pdOpen;
	private String pdCtg;
	private String pdType;
	private String pdTitle;
	private String pdContents;
	private Integer pdView;
	private String pdLink;
	private String pdTags;
	private String pdGb;
	private String pdDate;
	private String regDate;
	private String regUserId;
	private String uptDate;
	private String udtUserId;

	public String getPdDate() {
		return pdDate;
	}

	public void setPdDate(String pdDate) {
		this.pdDate = pdDate;
	}

	public Integer getPdSeq(){ return pdSeq; }
	public void setPdSeq(Integer pdSeq){ this.pdSeq = pdSeq; }

	public Integer getLgSeq(){ return lgSeq; }
	public void setLgSeq(Integer lgSeq){ this.lgSeq = lgSeq; }

	public String getPdOpen(){ return pdOpen; }
	public void setPdOpen(String pdOpen){ this.pdOpen = pdOpen; }

	public String getPdCtg(){ return pdCtg; }
	public void setPdCtg(String pdCtg){ this.pdCtg = pdCtg; }

	public String getPdType(){ return pdType; }
	public void setPdType(String pdType){ this.pdType = pdType; }

	public String getPdTitle(){ return pdTitle; }
	public void setPdTitle(String pdTitle){ this.pdTitle = pdTitle; }

	public String getPdContents(){ return pdContents; }
	public void setPdContents(String pdContents){ this.pdContents = pdContents; }

	public Integer getPdView(){ return pdView; }
	public void setPdView(Integer pdView){ this.pdView = pdView; }

	public String getPdLink(){ return pdLink; }
	public void setPdLink(String pdLink){ this.pdLink = pdLink; }

	public String getPdTags(){ return pdTags; }
	public void setPdTags(String pdTags){ this.pdTags = pdTags; }

	public String getPdGb(){ return pdGb; }
	public void setPdGb(String pdGb){ this.pdGb = pdGb; }

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
		return "ProductEntity[pdSeq="+pdSeq+",lgSeq="+lgSeq+",pdOpen="+pdOpen+",pdCtg="+pdCtg+",pdType="+pdType+",pdTitle="+pdTitle+",pdContents="+pdContents+",pdView="+pdView+",pdLink="+pdLink+",pdTags="+pdTags+",pdGb="+pdGb+",regDate="+regDate+",regUserId="+regUserId+",uptDate="+uptDate+",udtUserId="+udtUserId+"]";
	}
}