package com.plushih.controllers.front.vo;

public class ProductVO extends CommonVO{
	
	String pdSeq 	  	; //'시퀀스',        
	String lgSeq 		; //'언어',         
	String pdOpen 	    ; //'상태:공개/비공개',  
	String pdCtg 		; //'카테고리',       
	String pdTitle 		; //'제품명',        
	String pdContents 	; //'기타내용',       
	String pdView 	    ; //'조회수',        
	String pdType 	    ;         
	String pdLink 	    ; //'식약처링크',      
	String pdTags 	    ; //'효능별검색어',     
	String pdGb 		; //'성분별분류',      
	String pdDate 	    ; //'등록일',        
	String regDate 		; //'등록일',        
	String regUserId  	; //'작성자',        
	String uptDate 		; //'업데이트일자',     
	String udtUserId  	; //'작성자',
	String pcThumbPath	; //pc용 썸네일
	String mbThumbPath	; //mobile용 썸네일
	public String getPdSeq() {
		return pdSeq;
	}
	public void setPdSeq(String pdSeq) {
		this.pdSeq = pdSeq;
	}
	public String getLgSeq() {
		return lgSeq;
	}
	public void setLgSeq(String lgSeq) {
		this.lgSeq = lgSeq;
	}
	public String getPdOpen() {
		return pdOpen;
	}
	public void setPdOpen(String pdOpen) {
		this.pdOpen = pdOpen;
	}
	public String getPdCtg() {
		return pdCtg;
	}
	public void setPdCtg(String pdCtg) {
		this.pdCtg = pdCtg;
	}
	public String getPdTitle() {
		return pdTitle;
	}
	public void setPdTitle(String pdTitle) {
		this.pdTitle = pdTitle;
	}
	public String getPdContents() {
		return pdContents;
	}
	public void setPdContents(String pdContents) {
		this.pdContents = pdContents;
	}
	public String getPdView() {
		return pdView;
	}
	public void setPdView(String pdView) {
		this.pdView = pdView;
	}
	public String getPdLink() {
		return pdLink;
	}
	public void setPdLink(String pdLink) {
		this.pdLink = pdLink;
	}
	public String getPdTags() {
		return pdTags;
	}
	public void setPdTags(String pdTags) {
		this.pdTags = pdTags;
	}
	public String getPdGb() {
		return pdGb;
	}
	public void setPdGb(String pdGb) {
		this.pdGb = pdGb;
	}
	public String getPdDate() {
		return pdDate;
	}
	public void setPdDate(String pdDate) {
		this.pdDate = pdDate;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRegUserId() {
		return regUserId;
	}
	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}
	public String getUptDate() {
		return uptDate;
	}
	public void setUptDate(String uptDate) {
		this.uptDate = uptDate;
	}
	public String getUdtUserId() {
		return udtUserId;
	}
	public void setUdtUserId(String udtUserId) {
		this.udtUserId = udtUserId;
	}
	public String getPcThumbPath() {
		return pcThumbPath;
	}
	public void setPcThumbPath(String pcThumbPath) {
		this.pcThumbPath = pcThumbPath;
	}
	public String getMbThumbPath() {
		return mbThumbPath;
	}
	public void setMbThumbPath(String mbThumbPath) {
		this.mbThumbPath = mbThumbPath;
	}
	public String getPdType() {
		return pdType;
	}
	public void setPdType(String pdType) {
		this.pdType = pdType;
	}
	
}
