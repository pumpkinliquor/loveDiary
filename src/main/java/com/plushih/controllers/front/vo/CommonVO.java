package com.plushih.controllers.front.vo;

public class CommonVO {
	
	String search;
	
	//페이징 관련
	int listLength;
	int startIdx;
	public CommonVO(){
		startIdx 	= 0;
		listLength 	= 10;
	}
	public int getListLength() {
		return listLength;
	}
	public void setListLength(int listLength) {
		this.listLength = listLength;
	}
	public int getStartIdx() {
		return startIdx;
	}
	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	
	
}
