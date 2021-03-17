package com.plushih.entities.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yusul on 15. 5. 8..
 */
public class MailingEntity implements Serializable {

  private static final long serialVersionUID = 3199573274702349316L;

  private String  userName;                 //사용자 이름
  private String  userId;                   //사용자 아이디
  private String  password;                 //사용자 임시비밀번호
  private String  itemName;                 //물품명
  private String  itemKey;                  //물품 번호
  private int     startMoney;               //경매시작가
  private String  endDate;                  //경매종료일
  private String  sellerKey;                //판매자 아이디
  private int     bidPrice;                 //낙찰가
  private String  buyerKey;                 //구매자 아이디
  private String  payKey;                   //이머니 충전 결제번호
  private String  requestDate;              //이머니 충전 신청일
  private String  emoneyAmount;             //이머니 충전 신청금액
  private String  completeDate;             //배송요청일(결재일)
  private String[]  recipient;              //수신인
  private List<String> userIdList;          //수신 회원
  private int     mailSkinKey;              //스킨키
  private String  buyCommission;            //구매 수수료
  private String  deliveryCompleteDate;     //배송 완료일
  private String  autoBuyCompleteDate;      //자동 구매 결정 예정일
  private String  returnDate;               //반품 신청일
  private String  refundDate;               //환불일
  private String  denialDate;               //판매거부 처리
  private String  accidentCompleteDate;     //미수령완료일/반품완료일
  private String  accidentInsertDate;       //미수령 신청일
  private String  offlineRound;             //삶의흔적 회차
  private String  offlineReservationPrice;  //삶의 흔적 예약금
  private String  offlineItemStartMoney;    //삶의 흔적 시작가
  private String  offlineDeliveryNo;        //송장번호 (택배사 + 송장번호)
  private String  authType;                 //이메일 수신동의 확인
  private String  mailSkinType;             //메일 스킨 타입
  private String  userEmail;                //수신 메일 지정


  public String getUserName() {
      return userName;
  }

  public void setUserName(String userName) {
      this.userName = userName;
  }

  public String getUserId() {
      return userId;
  }

  public void setUserId(String userId) {
      this.userId = userId;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public String getItemName() {
      return itemName;
  }

  public void setItemName(String itemName) {
      this.itemName = itemName;
  }

  public String getItemKey() {
      return itemKey;
  }

  public void setItemKey(String itemKey) {
      this.itemKey = itemKey;
  }

  public int getStartMoney() {
      return startMoney;
  }

  public void setStartMoney(int startMoney) {
      this.startMoney = startMoney;
  }

  public String getEndDate() {
      return endDate;
  }

  public void setEndDate(String endDate) {
      this.endDate = endDate;
  }

  public String getSellerKey() {
      return sellerKey;
  }

  public void setSellerKey(String sellerKey) {
      this.sellerKey = sellerKey;
  }

  public int getBidPrice() {
      return bidPrice;
  }

  public void setBidPrice(int bidPrice) {
      this.bidPrice = bidPrice;
  }

  public String getBuyerKey() {
      return buyerKey;
  }

  public void setBuyerKey(String buyerKey) {
      this.buyerKey = buyerKey;
  }

  public String getPayKey() {
      return payKey;
  }

  public void setPayKey(String payKey) {
      this.payKey = payKey;
  }

  public String getRequestDate() {
      return requestDate;
  }

  public void setRequestDate(String requestDate) {
      this.requestDate = requestDate;
  }

  public String getEmoneyAmount() {
      return emoneyAmount;
  }

  public void setEmoneyAmount(String emoneyAmount) {
      this.emoneyAmount = emoneyAmount;
  }

  public String getCompleteDate() {
      return completeDate;
  }

  public void setCompleteDate(String completeDate) {
      this.completeDate = completeDate;
  }

  public int getMailSkinKey() {
    return mailSkinKey;
  }

  public void setMailSkinKey(int mailSkinKey) {
    this.mailSkinKey = mailSkinKey;
  }

  public String[] getRecipient() {
    return recipient;
  }

  public void setRecipient(String[] recipient) {
    this.recipient = recipient;
  }

  public List<String> getUserIdList() {
    return userIdList;
  }

  public void setUserIdList(List<String> userIdList) {
    this.userIdList = userIdList;
  }

  public String getBuyCommission() {
    return buyCommission;
  }

  public void setBuyCommission(String buyCommission) {
    this.buyCommission = buyCommission;
  }

  public String getDeliveryCompleteDate() {
    return deliveryCompleteDate == null ? "-" : deliveryCompleteDate;
  }

  public void setDeliveryCompleteDate(String deliveryCompleteDate) {
    this.deliveryCompleteDate = deliveryCompleteDate;
  }

  public String getAutoBuyCompleteDate() {
    return autoBuyCompleteDate;
  }

  public void setAutoBuyCompleteDate(String autoBuyCompleteDate) {
    this.autoBuyCompleteDate = autoBuyCompleteDate;
  }

  public String getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(String returnDate) {
    this.returnDate = returnDate;
  }

  public String getRefundDate() {
    return refundDate;
  }

  public void setRefundDate(String refundDate) {
    this.refundDate = refundDate;
  }

  public String getDenialDate() {
    return denialDate;
  }

  public void setDenialDate(String denialDate) {
    this.denialDate = denialDate;
  }

  public String getAccidentCompleteDate() {
    return accidentCompleteDate;
  }

  public void setAccidentCompleteDate(String accidentCompleteDate) {
    this.accidentCompleteDate = accidentCompleteDate;
  }

  public String getAccidentInsertDate() {
    return accidentInsertDate;
  }

  public void setAccidentInsertDate(String accidentInsertDate) {
    this.accidentInsertDate = accidentInsertDate;
  }

  public String getOfflineRound() {
    return offlineRound;
  }

  public void setOfflineRound(String offlineRound) {
    this.offlineRound = offlineRound;
  }

  public String getOfflineReservationPrice() {
    return offlineReservationPrice;
  }

  public void setOfflineReservationPrice(String offlineReservationPrice) {
    this.offlineReservationPrice = offlineReservationPrice;
  }

  public String getOfflineItemStartMoney() {
    return offlineItemStartMoney;
  }

  public void setOfflineItemStartMoney(String offlineItemStartMoney) {
    this.offlineItemStartMoney = offlineItemStartMoney;
  }

  public String getOfflineDeliveryNo() {
    return offlineDeliveryNo;
  }

  public void setOfflineDeliveryNo(String offlineDeliveryNo) {
    this.offlineDeliveryNo = offlineDeliveryNo;
  }

  public String getAuthType() {
    return authType;
  }

  public void setAuthType(String authType) {
    this.authType = authType;
  }

  public String getMailSkinType() {
    return mailSkinType;
  }

  public void setMailSkinType(String mailSkinType) {
    this.mailSkinType = mailSkinType;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
}
