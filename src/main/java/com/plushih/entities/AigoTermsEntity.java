package com.plushih.entities;

import com.plushih.common.utils.StringUtils;
import com.plushih.entities.PlusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

public class AigoTermsEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer trmId;
	private String trmGb;
	private String trmTarget;
	private String trmText;
	private String trmVersion;
	private String trmDate;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;
	private String regSysdate;
	private String udtSysdate;

	public Integer getTrmId(){ return trmId; }
	public void setTrmId(Integer trmId){ this.trmId = trmId; }

	public String getTrmGb(){ return trmGb; }
	public void setTrmGb(String trmGb){ this.trmGb = trmGb; }

	public String getTrmTarget(){ return trmTarget; }
	public void setTrmTarget(String trmTarget){ this.trmTarget = trmTarget; }

	public String getTrmText(){ return trmText; }
	public void setTrmText(String trmText){ this.trmText = trmText; }

	public String getTrmVersion(){ return trmVersion; }
	public void setTrmVersion(String trmVersion){ this.trmVersion = trmVersion; }

	public String getTrmDate(){ return trmDate; }
	public void setTrmDate(String trmDate){ this.trmDate = trmDate; }

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

	public String getRegSysdate(){ return regSysdate; }
	public void setRegSysdate(String regSysdate){ this.regSysdate = regSysdate; }

	public String getUdtSysdate(){ return udtSysdate; }
	public void setUdtSysdate(String udtSysdate){ this.udtSysdate = udtSysdate; }

	@Override
	public String toString() {
		return "AigoTermsEntity[trmId="+trmId+",trmGb="+trmGb+",trmTarget="+trmTarget+",trmText="+trmText+",trmVersion="+trmVersion+",trmDate="+trmDate+",useYn="+useYn+",regDate="+regDate+",regUmSeq="+regUmSeq+",udtDate="+udtDate+",udtUmSeq="+udtUmSeq+",regSysdate="+regSysdate+",udtSysdate="+udtSysdate+"]";
	}
}