package com.plushih.entities;

import com.plushih.common.utils.StringUtils;
import com.plushih.entities.PlusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

public class AigoLevelEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer levId;
	private String levKey;
	private String levName;
	private Integer levOrder;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;

	public Integer getLevId(){ return levId; }
	public void setLevId(Integer levId){ this.levId = levId; }

	public String getLevKey(){ return levKey; }
	public void setLevKey(String levKey){ this.levKey = levKey; }

	public String getLevName(){ return levName; }
	public void setLevName(String levName){ this.levName = levName; }

	public Integer getLevOrder(){ return levOrder; }
	public void setLevOrder(Integer levOrder){ this.levOrder = levOrder; }

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

	@Override
	public String toString() {
		return "AigoLevelEntity[levId="+levId+",levKey="+levKey+",levName="+levName+",levOrder="+levOrder+",useYn="+useYn+",regDate="+regDate+",regUmSeq="+regUmSeq+",udtDate="+udtDate+",udtUmSeq="+udtUmSeq+"]";
	}
}