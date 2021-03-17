package com.plushih.entities;

import com.plushih.common.utils.StringUtils;
import com.plushih.entities.PlusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

public class NotificationEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer notId;
	private Integer memId;
	private Integer targetMemId;
	private Integer targetGrpId;
	private String notOs;
	private String notType;
	private Integer notContentId;
	private String notTitle;
	private String notMessage;
	private String notUrl;
	private String notDatetime;
	private String notDate;
	private String notTime;
	private String notReadDatetime;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;
	private String regSysdate;
	private String udtSysdate;

	public Integer getNotId(){ return notId; }
	public void setNotId(Integer notId){ this.notId = notId; }

	public Integer getMemId(){ return memId; }
	public void setMemId(Integer memId){ this.memId = memId; }

	public Integer getTargetMemId(){ return targetMemId; }
	public void setTargetMemId(Integer targetMemId){ this.targetMemId = targetMemId; }

	public Integer getTargetGrpId(){ return targetGrpId; }
	public void setTargetGrpId(Integer targetGrpId){ this.targetGrpId = targetGrpId; }

	public String getNotOs(){ return notOs; }
	public void setNotOs(String notOs){ this.notOs = notOs; }

	public String getNotType(){ return notType; }
	public void setNotType(String notType){ this.notType = notType; }

	public Integer getNotContentId(){ return notContentId; }
	public void setNotContentId(Integer notContentId){ this.notContentId = notContentId; }

	public String getNotTitle(){ return notTitle; }
	public void setNotTitle(String notTitle){ this.notTitle = notTitle; }

	public String getNotMessage(){ return notMessage; }
	public void setNotMessage(String notMessage){ this.notMessage = notMessage; }

	public String getNotUrl(){ return notUrl; }
	public void setNotUrl(String notUrl){ this.notUrl = notUrl; }

	public String getNotDatetime(){ return notDatetime; }
	public void setNotDatetime(String notDatetime){ this.notDatetime = notDatetime; }

	public String getNotDate(){ return notDate; }
	public void setNotDate(String notDate){ this.notDate = notDate; }

	public String getNotTime(){ return notTime; }
	public void setNotTime(String notTime){ this.notTime = notTime; }

	public String getNotReadDatetime(){ return notReadDatetime; }
	public void setNotReadDatetime(String notReadDatetime){ this.notReadDatetime = notReadDatetime; }

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
		return "NotificationEntity[notId="+notId+",memId="+memId+",targetMemId="+targetMemId+",targetGrpId="+targetGrpId+",notOs="+notOs+",notType="+notType+",notContentId="+notContentId+",notTitle="+notTitle+",notMessage="+notMessage+",notUrl="+notUrl+",notDatetime="+notDatetime+",notDate="+notDate+",notTime="+notTime+",notReadDatetime="+notReadDatetime+",regDate="+regDate+",regUmSeq="+regUmSeq+",udtDate="+udtDate+",udtUmSeq="+udtUmSeq+",regSysdate="+regSysdate+",udtSysdate="+udtSysdate+"]";
	}
}