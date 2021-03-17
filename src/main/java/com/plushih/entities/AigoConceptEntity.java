package com.plushih.entities;

import java.io.Serializable;

public class AigoConceptEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer cptId;
	private Integer subId;
	private Integer acvId;
	private String cptKey;
	private String cptName;
	private String cotValue;
	private Integer cptOrder;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;
	private AigoAchievementEntity aigoAchieveVO;

	public Integer getCptId(){ return cptId; }
	public void setCptId(Integer cptId){ this.cptId = cptId; }

	public Integer getSubId(){ return subId; }
	public void setSubId(Integer subId){ this.subId = subId; }

	public Integer getAcvId(){ return acvId; }
	public void setAcvId(Integer acvId){ this.acvId = acvId; }

	public String getCptKey(){ return cptKey; }
	public void setCptKey(String cptKey){ this.cptKey = cptKey; }

	public String getCptName(){ return cptName; }
	public void setCptName(String cptName){ this.cptName = cptName; }

	public String getCotValue(){ return cotValue; }
	public void setCotValue(String cotValue){ this.cotValue = cotValue; }

	public Integer getCptOrder(){ return cptOrder; }
	public void setCptOrder(Integer cptOrder){ this.cptOrder = cptOrder; }

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
	
	public AigoAchievementEntity getAigoAchieveVO(){ return aigoAchieveVO; }
	public void setAigoAchieveVO(AigoAchievementEntity aigoAchieveVO){ this.aigoAchieveVO = aigoAchieveVO; }
	
	@Override
	public String toString() {
		return "AigoConceptEntity[cptId="+cptId+",subId="+subId+",acvId="+acvId+",cptKey="+cptKey+",cptName="+cptName+",cotValue="+cotValue+",cptOrder="+cptOrder+",useYn="+useYn+",regDate="+regDate+",regUmSeq="+regUmSeq+",udtDate="+udtDate+",udtUmSeq="+udtUmSeq+"]";
	}
}