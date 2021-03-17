package com.plushih.entities;

import com.plushih.common.utils.StringUtils;
import com.plushih.entities.PlusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

public class AigoBannerEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer banId;
	private String banStartDate;
	private String banEndDate;
	private String banName;
	private String banUrl;
	private String banTarget;
	private String banDevice;
	private Integer banWidth;
	private Integer banHeight;
	private Integer banHit;
	private Integer banOrder;
	private String banImage;
	private String banIp;
	private String userActive;
	private Integer memId;
	private String regDate;
	private String udtDate;
	private Integer regUmSeq;
	private Integer udtUmSeq;
	private String regSysdate;
	private String udtSysdate;

	public Integer getBanId(){ return banId; }
	public void setBanId(Integer banId){ this.banId = banId; }

	public String getBanStartDate(){ return banStartDate; }
	public void setBanStartDate(String banStartDate){ this.banStartDate = banStartDate; }

	public String getBanEndDate(){ return banEndDate; }
	public void setBanEndDate(String banEndDate){ this.banEndDate = banEndDate; }

	public String getBanName(){ return banName; }
	public void setBanName(String banName){ this.banName = banName; }

	public String getBanUrl(){ return banUrl; }
	public void setBanUrl(String banUrl){ this.banUrl = banUrl; }

	public String getBanTarget(){ return banTarget; }
	public void setBanTarget(String banTarget){ this.banTarget = banTarget; }

	public String getBanDevice(){ return banDevice; }
	public void setBanDevice(String banDevice){ this.banDevice = banDevice; }

	public Integer getBanWidth(){ return banWidth; }
	public void setBanWidth(Integer banWidth){ this.banWidth = banWidth; }

	public Integer getBanHeight(){ return banHeight; }
	public void setBanHeight(Integer banHeight){ this.banHeight = banHeight; }

	public Integer getBanHit(){ return banHit; }
	public void setBanHit(Integer banHit){ this.banHit = banHit; }

	public Integer getBanOrder(){ return banOrder; }
	public void setBanOrder(Integer banOrder){ this.banOrder = banOrder; }

	public String getBanImage(){ return banImage; }
	public void setBanImage(String banImage){ this.banImage = banImage; }

	public String getBanIp(){ return banIp; }
	public void setBanIp(String banIp){ this.banIp = banIp; }

	public String getUserActive(){ return userActive; }
	public void setUserActive(String userActive){ this.userActive = userActive; }

	public Integer getMemId(){ return memId; }
	public void setMemId(Integer memId){ this.memId = memId; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public String getUdtDate(){ return udtDate; }
	public void setUdtDate(String udtDate){ this.udtDate = udtDate; }

	public Integer getRegUmSeq(){ return regUmSeq; }
	public void setRegUmSeq(Integer regUmSeq){ this.regUmSeq = regUmSeq; }

	public Integer getUdtUmSeq(){ return udtUmSeq; }
	public void setUdtUmSeq(Integer udtUmSeq){ this.udtUmSeq = udtUmSeq; }

	public String getRegSysdate(){ return regSysdate; }
	public void setRegSysdate(String regSysdate){ this.regSysdate = regSysdate; }

	public String getUdtSysdate(){ return udtSysdate; }
	public void setUdtSysdate(String udtSysdate){ this.udtSysdate = udtSysdate; }

	@Override
	public String toString() {
		return "AigoBannerEntity[banId="+banId+",banStartDate="+banStartDate+",banEndDate="+banEndDate+",banName="+banName+",banUrl="+banUrl+",banTarget="+banTarget+",banDevice="+banDevice+",banWidth="+banWidth+",banHeight="+banHeight+",banHit="+banHit+",banOrder="+banOrder+",banImage="+banImage+",banIp="+banIp+",userActive="+userActive+",memId="+memId+",regDate="+regDate+",udtDate="+udtDate+",regUmSeq="+regUmSeq+",udtUmSeq="+udtUmSeq+",regSysdate="+regSysdate+",udtSysdate="+udtSysdate+"]";
	}
}