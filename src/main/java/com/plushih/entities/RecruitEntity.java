package com.plushih.entities;

import java.io.Serializable;

public class RecruitEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;


	private Integer rcSeq;
	private String rcOpen;
	private Integer lgSeq;
	private String rcType;
	private String rcBbs;
	private String rcTitle;
	private String rcOccupation;
	private String rcGb;
	private String rcWork;
	private String rcJuwork;
	private String rcCapacity;
	private String rcUdae;
	private String rcHowapply;
	private String rcLink;
	private String rcStep;
	private String rcArea;
	private String rcEtc;
	private Integer rcView;
	private String rcSdate;
	private String rcEdate;
	private String rcDate;
	private String regDate;
	private String regUserId;
	private String uptDate;
	private String udtpUserId;

	public Integer getRcSeq(){ return rcSeq; }
	public void setRcSeq(Integer rcSeq){ this.rcSeq = rcSeq; }

	public String getRcOpen(){ return rcOpen; }
	public void setRcOpen(String rcOpen){ this.rcOpen = rcOpen; }

	public Integer getLgSeq(){ return lgSeq; }
	public void setLgSeq(Integer lgSeq){ this.lgSeq = lgSeq; }

	public String getRcType(){ return rcType; }
	public void setRcType(String rcType){ this.rcType = rcType; }

	public String getRcBbs(){ return rcBbs; }
	public void setRcBbs(String rcBbs){ this.rcBbs = rcBbs; }

	public String getRcTitle(){ return rcTitle; }
	public void setRcTitle(String rcTitle){ this.rcTitle = rcTitle; }

	public String getRcOccupation(){ return rcOccupation; }
	public void setRcOccupation(String rcOccupation){ this.rcOccupation = rcOccupation; }

	public String getRcGb(){ return rcGb; }
	public void setRcGb(String rcGb){ this.rcGb = rcGb; }

	public String getRcWork(){ return rcWork; }
	public void setRcWork(String rcWork){ this.rcWork = rcWork; }

	public String getRcJuwork(){ return rcJuwork; }
	public void setRcJuwork(String rcJuwork){ this.rcJuwork = rcJuwork; }

	public String getRcCapacity(){ return rcCapacity; }
	public void setRcCapacity(String rcCapacity){ this.rcCapacity = rcCapacity; }

	public String getRcUdae(){ return rcUdae; }
	public void setRcUdae(String rcUdae){ this.rcUdae = rcUdae; }

	public String getRcHowapply(){ return rcHowapply; }
	public void setRcHowapply(String rcHowapply){ this.rcHowapply = rcHowapply; }

	public String getRcLink(){ return rcLink; }
	public void setRcLink(String rcLink){ this.rcLink = rcLink; }

	public String getRcStep(){ return rcStep; }
	public void setRcStep(String rcStep){ this.rcStep = rcStep; }

	public String getRcArea(){ return rcArea; }
	public void setRcArea(String rcArea){ this.rcArea = rcArea; }

	public String getRcEtc(){ return rcEtc; }
	public void setRcEtc(String rcEtc){ this.rcEtc = rcEtc; }

	public Integer getRcView(){ return rcView; }
	public void setRcView(Integer rcView){ this.rcView = rcView; }

	public String getRcSdate(){ return rcSdate; }
	public void setRcSdate(String rcSdate){ this.rcSdate = rcSdate; }

	public String getRcEdate(){ return rcEdate; }
	public void setRcEdate(String rcEdate){ this.rcEdate = rcEdate; }

	public String getRcDate(){ return rcDate; }
	public void setRcDate(String rcDate){ this.rcDate = rcDate; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public String getRegUserId(){ return regUserId; }
	public void setRegUserId(String regUserId){ this.regUserId = regUserId; }

	public String getUptDate(){ return uptDate; }
	public void setUptDate(String uptDate){ this.uptDate = uptDate; }

	public String getUdtpUserId(){ return udtpUserId; }
	public void setUdtpUserId(String udtpUserId){ this.udtpUserId = udtpUserId; }

	@Override
	public String toString() {
		return "RecruitEntity[rcSeq="+rcSeq+",rcOpen="+rcOpen+",lgSeq="+lgSeq+",rcType="+rcType+",rcBbs="+rcBbs+",rcTitle="+rcTitle+",rcOccupation="+rcOccupation+",rcGb="+rcGb+",rcWork="+rcWork+",rcJuwork="+rcJuwork+",rcCapacity="+rcCapacity+",rcUdae="+rcUdae+",rcHowapply="+rcHowapply+",rcLink="+rcLink+",rcStep="+rcStep+",rcArea="+rcArea+",rcEtc="+rcEtc+",rcView="+rcView+",rcSdate="+rcSdate+",rcEdate="+rcEdate+",rcDate="+rcDate+",regDate="+regDate+",regUserId="+regUserId+",uptDate="+uptDate+",udtpUserId="+udtpUserId+"]";
	}
}