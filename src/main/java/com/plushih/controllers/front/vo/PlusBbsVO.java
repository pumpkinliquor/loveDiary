package com.plushih.controllers.front.vo;

import java.util.Map;

public class PlusBbsVO extends CommonVO{
	
	String bbSeq;
	String biSeq;
	String bcSeq;
	String cgSeq;
	String lgSeq;
	String bbType;
	String bbBbs;
	String bbOpen;
	String bbDelyn;
	String bbTitle;
	String bbEtc01;
	String bbContents;
	String bbView;
	String bbDate;
	String regDate;
	String regUserId;
	String uptDate;
	String udtUserId;
	String bafSeq;
	String pcPlayerBafPath; //파일 저장 경로
	String pcPlayerBafFile; //파일명
	String pcThumbBafPath; //파일 저장 경로
	String pcThumbBafFile; //파일명
	
	Map<String,Object> fileMap;
	

	
	public String getBbSeq() {
		return bbSeq;
	}
	public void setBbSeq(String bbSeq) {
		this.bbSeq = bbSeq;
	}
	public String getBiSeq() {
		return biSeq;
	}
	public void setBiSeq(String biSeq) {
		this.biSeq = biSeq;
	}
	public String getBcSeq() {
		return bcSeq;
	}
	public void setBcSeq(String bcSeq) {
		this.bcSeq = bcSeq;
	}
	public String getCgSeq() {
		return cgSeq;
	}
	public void setCgSeq(String cgSeq) {
		this.cgSeq = cgSeq;
	}
	public String getLgSeq() {
		return lgSeq;
	}
	public void setLgSeq(String lgSeq) {
		this.lgSeq = lgSeq;
	}
	public String getBbType() {
		return bbType;
	}
	public void setBbType(String bbType) {
		this.bbType = bbType;
	}
	public String getBbBbs() {
		return bbBbs;
	}
	public void setBbBbs(String bbBbs) {
		this.bbBbs = bbBbs;
	}
	public String getBbOpen() {
		return bbOpen;
	}
	public void setBbOpen(String bbOpen) {
		this.bbOpen = bbOpen;
	}
	public String getBbDelyn() {
		return bbDelyn;
	}
	public void setBbDelyn(String bbDelyn) {
		this.bbDelyn = bbDelyn;
	}
	public String getBbTitle() {
		return bbTitle;
	}
	public void setBbTitle(String bbTitle) {
		this.bbTitle = bbTitle;
	}
	public String getBbEtc01() {
		return bbEtc01;
	}
	public void setBbEtc01(String bbEtc01) {
		this.bbEtc01 = bbEtc01;
	}
	public String getBbContents() {
		return bbContents;
	}
	public void setBbContents(String bbContents) {
		this.bbContents = bbContents;
	}
	public String getBbView() {
		return bbView;
	}
	public void setBbView(String bbView) {
		this.bbView = bbView;
	}
	public String getBbDate() {
		return bbDate;
	}
	public void setBbDate(String bbDate) {
		this.bbDate = bbDate;
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

	public String getPcPlayerBafPath() {
		return pcPlayerBafPath;
	}

	public void setPcPlayerBafPath(String pcPlayerBafPath) {
		this.pcPlayerBafPath = pcPlayerBafPath;
	}

	public String getPcPlayerBafFile() {
		return pcPlayerBafFile;
	}

	public void setPcPlayerBafFile(String pcPlayerBafFile) {
		this.pcPlayerBafFile = pcPlayerBafFile;
	}

	public String getPcThumbBafPath() {
		return pcThumbBafPath;
	}

	public void setPcThumbBafPath(String pcThumbBafPath) {
		this.pcThumbBafPath = pcThumbBafPath;
	}

	public String getPcThumbBafFile() {
		return pcThumbBafFile;
	}

	public void setPcThumbBafFile(String pcThumbBafFile) {
		this.pcThumbBafFile = pcThumbBafFile;
	}

	public String getBafSeq() {
		return bafSeq;
	}

	public void setBafSeq(String bafSeq) {
		this.bafSeq = bafSeq;
	}
	public Map<String, Object> getFileMap() {
		return fileMap;
	}
	public void setFileMap(Map<String, Object> fileMap) {
		this.fileMap = fileMap;
	}
}
