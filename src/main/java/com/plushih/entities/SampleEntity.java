package com.plushih.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class SampleEntity  extends PlusEntity implements Serializable{

	private static final long serialVersionUID = 158105902005757160L;

	private int sampleSeq;
	private String sampleStr;
	private Timestamp sampleTimestamp;
	private List<Map<String, Object>> sampleList;
	
	public int getSampleSeq() {
		return sampleSeq;
	}
	public void setSampleSeq(int sampleSeq) {
		this.sampleSeq = sampleSeq;
	}
	public String getSampleStr() {
		return sampleStr;
	}
	public void setSampleStr(String sampleStr) {
		this.sampleStr = sampleStr;
	}
	public Timestamp getSampleTimestamp() {
		return sampleTimestamp;
	}
	public void setSampleTimestamp(Timestamp sampleTimestamp) {
		this.sampleTimestamp = sampleTimestamp;
	}
	public List<Map<String, Object>> getSampleList() {
		return sampleList;
	}
	public void setSampleList(List<Map<String, Object>> sampleList) {
		this.sampleList = sampleList;
	}
	
	@Override
	public String toString() {
		return "SampleEntity [sampleSeq=" + sampleSeq + ", sampleStr=" + sampleStr + ", sampleTimestamp="
				+ sampleTimestamp + ", sampleList=" + sampleList + "]";
	}
	
}