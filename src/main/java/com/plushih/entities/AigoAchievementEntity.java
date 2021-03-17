package com.plushih.entities;

import java.io.Serializable;

public class AigoAchievementEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer acvId;
	private Integer subId;
	private String subName;
	private Integer levId;
	private String acvKey;
	private String acvName;
	private String acvText;
	private String acvParent;
	private Integer acvOrder;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;
	private Integer qstCnt;
	private Integer ansCnt;
	private Integer memId;
	private Integer optionalSubId;

	public Integer getAcvId(){ return acvId; }
	public void setAcvId(Integer acvId){ this.acvId = acvId; }

	public Integer getSubId(){ return subId; }
	public void setSubId(Integer subId){ this.subId = subId; }
	
	public String getSubName(){ return subName; }
	public void setSubName(String subName){ this.subName = subName; }

	public Integer getLevId(){ return levId; }
	public void setLevId(Integer levId){ this.levId = levId; }

	public String getAcvKey(){ return acvKey; }
	public void setAcvKey(String acvKey){ this.acvKey = acvKey; }

	public String getAcvName(){ return acvName; }
	public void setAcvName(String acvName){ this.acvName = acvName; }

	public String getAcvParent(){ return acvParent; }
	public void setAcvParent(String acvParent){ this.acvParent = acvParent; }

	public Integer getAcvOrder(){ return acvOrder; }
	public void setAcvOrder(Integer acvOrder){ this.acvOrder = acvOrder; }

	public String getUseYn(){ return useYn; }
	public void setUseYn(String useYn){ this.useYn = useYn; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public Integer getRegUmSeq(){ return regUmSeq; }
	public void setRegUmSeq(Integer regUmSeq){ this.regUmSeq = regUmSeq; }

	public String getUdtDate(){ return udtDate; }
	public void setUdtDate(String udtDate){ this.udtDate = udtDate; }

	public Integer getUdtUmSeq(){ return udtUmSeq; }
	public void setUdtUmSeq(Integer udtUmSeq){ this.udtUmSeq = udtUmSeq; }

	public Integer getQstCnt() { return qstCnt; }
	public void setQstCnt(Integer qstCnt) { this.qstCnt = qstCnt; }

	public Integer getAnsCnt() { return ansCnt; }
	public void setAnsCnt(Integer ansCnt) { this.ansCnt = ansCnt; }

	public Integer getMemId() { return memId; }
	public void setMemId(Integer memId) { this.memId = memId; }

	public Integer getOptionalSubId() { return optionalSubId; }
	public void setOptionalSubId(Integer optionalSubId) { this.optionalSubId = optionalSubId; }

	public String getAcvText() { return acvText; }
	public void setAcvText(String acvText) { this.acvText = acvText;  }
	
	@Override
	public String toString() {
		return "AigoAchievementEntity [acvId=" + acvId + ", subId=" + subId + ", subName=" + subName + ", levId=" + levId + ", acvKey=" + acvKey + ", acvName=" + acvName + ", acvText=" + acvText + ", acvParent=" + acvParent + ", acvOrder=" + acvOrder + ", useYn=" + useYn + ", regDate=" + regDate + ", regUmSeq=" + regUmSeq + ", udtDate=" + udtDate + ", udtUmSeq=" + udtUmSeq + ", qstCnt=" + qstCnt + ", ansCnt=" + ansCnt + ", memId=" + memId + ", optionalSubId=" + optionalSubId + "]";
	}

	
}