package com.plushih.common.constant;

public class Default {

  public static final String CHARSET_MS949= "MS949";
  public static final String CHARSET_UTF8 = "UTF-8";
  public static final String plus_ID = "plus";
  public static final int ZERO = 0;
  public static final int ONE  = 1;
  public static final int TWO  = 2;
  public static final int TEN  = 10;
  public static final int HUNDRED = 100;
  public static final String STRING_ZERO = "0";
  public static final String STRING_ONE  = "1";
  public static final String TOTAL  = "total";
  public static final String BASIC  = "basic";
  public static final String TRUE = "true";
  public static final String YES = "Y";
  public static final String NO  = "N";
  public static final String YES_LOWER = "y";
  public static final String NO_LOWER  = "n";
  public static final String POSSIBLE = "possible";
  public static final String IMPOSSIBLE = "impossible";
  public static final String BLANK = "";
  public static final double RESERVED_PRICE_COMMISSION = 0.01;
  public static final double OFFLINE_AUCTION_COMMISSION = 0.11;
  public static final double ACCIDENT_CANCEL_BUY_COMMISSION = 0.12;
  public static final double ACCIDENT_CANCEL_SELL_COMMISSION = 0.11;
  public static final double ACCIDENT_LOST_REPORT_COMMISSION = 0.12;
  public static final double ACCIDENT_RETURN_COMMISSION = 0.10;
  public static final double SURETY_INSURANCE_COMMISSION = 0.0052;
  public static final int CREDIT_RATING_SCORE = -5;
  public static final String ERROR = "error.unexpected";
  public static final int APPRAISAL_FEE = 30000;
  public static final int REGULAR_USER_CONDITION = 30000;

  public static final String IS_INTRANET_BOARD_ALL = "1";

  public static final class User {
    public static final String IS_ADMIN = "A";
  }

  public static final class ResultValue {
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final String EMPTY = "empty";
    public static final String REFRESH = "refresh";
    public static final String CLOSE = "close";
    public static final String HISTORY_BACK = "historyBack";
    public static final String DUPLI = "dupli";
    public static final String VALI = "vali";
    public static final String VIEW_RETURN_URL			= "returnUrl";		// 작업처리 후 리턴시킬 URL
	public static final String VIEW_ALERT_MSG			= "alertMsg";		// 처리 후 노출시킬 메시지
	public static final String RESULT_CODE				= "resultCode";		// 작업처리 후 처리결과 코드 
	public static final String RESPONSE_RESULT_MAP		= "resultMap";		// 작업처리 후 처리결과가 담긴 Map
	public static final String VIEW_ACCESS_KEY			= "accessKey";		// 비정상접근 체크 
	public static final String REQUEST_PARAMETER_MAP	= "paramMap";		// 요청 파라미터 Map을 다시 model에 set할 경우
  }

  public static final class ResultAction {
    // 사용자 세션유지 체크박스 목록 초기화
    public static final String INIT_CHECKED_LIST = "initCheckedList";
    // 팝업 오프너 윈도우 창 리플래쉬
    public static final String OPENER_REFRESH = "openerRefresh";
  }

  public static class Page {
    public static final int SIZE = 10;
    public static final int UNIT = 10;
    public static final int EXCEL_UNIT = 999999999;
    public static final int EXCEL_SIZE = 999999999;
    public static final int USER_FAQ_SIZE = 5;
    public static final int POPUP_SIZE = 5;
    public static final int TOTAL_SEARCH_ONLINE_SIZE = 9;
    public static final int TOTAL_SEARCH_OFFLINE_SIZE = 12;
    public static final int TOTAL_SEARCH_SPECIAL_SIZE = 9;
    public static final int MESSAGE_LIST_SIZE   = 5;
    public static final int MESSAGE_GROUP_SIZE  = 5;
    public static final int COMMENT_LIST_SIZE   = 10;
    public static final int COMMENT_GROUP_SIZE  = 5;
    public static final int INQUIRY_LIST_SIZE   = 10;
    public static final int INQUIRY_GROUP_SIZE  = 5;
    public static final int MAIN_LIST_SIZE      = 12;
    public static final int OFFLINE_LIST_SIZE_20     = 20;
    public static final int OFFLINE_LIST_SIZE_40     = 40;
    public static final int OFFLINE_LIST_SIZE_60     = 60;
    public static final int OFFLINE_LIST_SIZE_80     = 80;
    public static final int OFFLINE_LIST_SIZE_100    = 100;
  }

  public static class Size {
    public static final int KB = 1024;
    public static final int MB = KB * 1024;
  }

  public static class OneDay {
    public static final int HOUR = 24;
    public static final int MINUTE = HOUR * 60;
    public static final int SECOND = MINUTE * 60;
  }

  public static class ListType {
    public static class Auction {
      public static final String IMAGE_VIEW = "imageView";
      public static final String LIST_VIEW  = "listView";
    }
  }

  public static class LimitSize {
    // 파일 업로드 크기
    public static final long ITEM_IMAGE    = 30 * Size.MB;
    public static final long BOARD_FILE    = 30 * Size.MB;
    public static final long BANNER_IMAGE  = 30 * Size.MB;
    public static final long EDITOR_IMAGE  = 10 * Size.MB;
    public static final long ACCIDENT_FILE = 10 * Size.MB;
    public static final long STAFF_IMAGE   = 10 * Size.MB;

    // SMS, MMS 전송 내용
    public static final int SMS = 90;
    public static final int MMS = 4000;
  }

  public static class UploadDirectory {
    public static final String ACCIDENT = "/accident";
    public static final String BANNER = "/banner";
    public static final String EDITOR_IMAGE = "/editorImage";
    public static final String ITEM = "/item";
    public static final String BOARD = "/board";
    public static final String TEMP = "/temp";
    public static final String THUMBNAIL = "/thumbnail";
    public static final String STAFF = "/staff";
    public static final String USERS = "/users";
    public static final String RENT = "/rent";
    public static final String POPUP = "/popup";
    public static final String CORPORATION = "/corporation";
    public static final String LAND = "/land";
    public static final String BUILDING = "/building";
    public static final String PREMIUM = "/premium";
  }

  public static class FileBbs {
    public static final String CORPORATION  = "CORPORATION";
    public static final String USERS  = "USERS";
    public static final String RENT  = "RENT";
    public static final String NOTICE = "NOTICE";
    public static final String POPUP = "POPUP";
    public static final String LAND  = "LAND";
    public static final String BUILDING  = "BUILDING";
    public static final String PREMIUM  = "PREMIUM";
    public static final String QUESTION  = "QST";
  }

  public static class FileType {
    public static final String FILE  = "FILE";
    public static final String IMG = "IMG";
  }

  public static class SapFunction {
    //GET : 고객/구매처 I/F 함수: Z_RFC_FI_RE_001
    public static final String Z_RFC_FI_RE_001 = "Z_RFC_FI_RE_001";
    //GET : 자산 I/F 함수
    public static final String Z_RFC_FI_RE_002 = "Z_RFC_FI_RE_002";
    //SET
    public static final String Z_RFC_FI_RE_003 = "Z_RFC_FI_RE_003";
    //SET :
    public static final String Z_RFC_FI_RE_004 = "Z_RFC_FI_RE_004";
    //GET : 사업장정보
    public static final String Z_RFC_FI_RE_005 = "Z_RFC_FI_RE_005";
    //SET : 사업장정보
    public static final String Z_RFC_FI_RE_006 = "Z_RFC_FI_RE_006";
    //SET : 사업장정보
    public static final String Z_RFC_FI_RE_007 = "Z_RFC_FI_RE_007";
    //SET : 사업장정보
    public static final String Z_RFC_FI_RE_008 = "Z_RFC_FI_RE_008";
  }

  public static class DateFormat {
    public static final String MMDD_KR = "MM월dd일";
    public static final String YYYYMMDD_KR = "yyyy년 MM월 dd일";
    public static final String YYYYMMDD_DASHED = "yyyy-MM-dd";
    public static final String YYMM_KR = "yy년 MM월";
  }

  public static class DecimalFormat {
    public static final String SECOND_DECIMAL = "#.##";
  }

  /**
   * org.apache.com.plushih.common.lang.StringUtils.substring 사용 시
   */
  public static class LimitTextLength {
    public static final int MESSAGE_ITEM_NAME = 9; // 10자 제한
    public static final int SMS_ITEM_NAME = 4;     // 5자 제한
  }

  public static class MailSkinType {
    public static final String ITEM_INQUIRY = "productInquiry";
    public static final String ONE_TO_ONE = "faqAnswer";
  }

  public static class NoticeType  {
    public static final String RENT_END = "01";
    public static final String HIRE_END = "02";
    public static final String AUTOEXTENTION = "03";
  }
  public static class ApiCode {
    public static final String OFFICIAL_URL = "http://apis.data.go.kr/1611000/nsdi/IndvdLandPriceService/attr/getIndvdLandPriceAttr?ServiceKey=$key";
    public static final String OFFICIAL_KEY = "BFk7ehK5QFWurzhDkIemhmaI9eRGiwYri7TASmwaf%2Fo6womWLCUubt%2F7ugS72HPrZNawTNNhNEMqPxwsc5PcAw%3D%3D";
    public static final String OFFICIAL_BASE= OFFICIAL_URL.replace("$key", OFFICIAL_KEY);

    public enum RequestMethod {
        POST, GET, DELETE, PUT
    }
}

  public static final class UserType {
    public static final String A     = "A"; // 관리자
    public static final String W     = "W"; // 작업자
  }

  public static final class UserWork {
    public static final String WORK     = "01"; // 재직
    public static final String REST     = "02"; // 휴직
    public static final String OUT      = "03"; // 퇴사
    public static final String DISABLE  = "04"; // 휴면
  }

  public static final class UserStep {
    public static final String ST       = "ST"; // 대기
    public static final String OK       = "OK"; // 승인
    public static final String NO       = "NO"; // 반려
  }
  
  /** 사이트 기본정보 */
  public static final class Site {
	public static final String SERVER_DOMAIN		= "http://localhost:8080";				// 도메인
	public static final String SERVER_DOMAIN_SSL	= "https://localhost:8080";				// SSL 도메인
  }
  
  public static final class Sns {
	// SNS 타입
	public static final String KAKAO		= "kakao";
	public static final String NAVER		= "naver";
	public static final String FACEBOOK		= "facebook";
	// 개발용 Key
	public static final String KAKAO_JS_KEY				= "21a4f6373b7a5baf3a08f88d6bbd2a4d";	// 카카오 앱 Javascript SDK Key (인가코드 발급 시 사용)
	public static final String KAKAO_REST_API_KEY		= "6cc2739f12496fc8561e517b31e1cc9c";	// 카카오 앱 REST API 호출용 Key (사용자 정보에 접근할 액세스토큰/리프레시토큰 발급 시 사용) 
	//public static final String FACEBOOK_KEY				= "216234410034773";					// 페이스북 앱 ID (Key)
	//public static final String FACEBOOK_SECRET_CODE_KEY	= "d2afc0ce5bfaf86638492a2551c0da66";	// 페이스북 앱 Secret Code
	public static final String NAVER_CLIENT_ID_KEY		= "C_nYus0dkLHdctVjod_2";				// 네이버 앱 Client APP_ID
	public static final String NAVER_CLIENT_SECRET_KEY	= "T5WbOE154z";							// 네이버 앱 Client Secret Key
	// 운영용 Key
	//public static final String KAKAO_JS_KEY				= "5d748932e2f317cfa356b5e3971e2c42";	// 카카오 앱 Javascript SDK Key (인가코드 발급 시 사용)
	//public static final String KAKAO_REST_API_KEY		= "eaff158f54cc381e50c85b9fdd0f5066";	// 카카오 앱 REST API 호출용 Key (사용자 정보에 접근할 액세스토큰/리프레시토큰 발급 시 사용) 
	public static final String FACEBOOK_KEY				= "986169288573860";					// 페이스북 앱 ID (Key)
	public static final String FACEBOOK_SECRET_CODE_KEY	= "88f286088319d90c5a84bd597d2cd46a";	// 페이스북 앱 Secret Code
	//public static final String NAVER_CLIENT_ID_KEY		= "";									// 네이버 앱 Client APP_ID
	//public static final String NAVER_CLIENT_SECRET_KEY	= "";									// 네이버 앱 Client Secret Key
	
	// 사용자 정보 리턴받을 Redirect URL
	public static final String JOIN_PROC_URL	= "/front/join/joinProcSns";
	public static final String LOGIN_PROC_URL	= "/front/login/loginProcSns";
	
	// Kakao Api Url
	public static final String KAKAO_GET_TOKEN_API_URL			= "https://kauth.kakao.com/oauth/token";
	public static final String KAKAO_GET_USERINFO_API_URL		= "https://kapi.kakao.com/v2/user/me";
	public static final String NAVER_AUTH_REQUEST_API_URL		= "https://nid.naver.com/oauth2.0/authorize";
	public static final String NAVER_GET_TOKEN_API_URL			= "https://nid.naver.com/oauth2.0/token";
	public static final String NAVER_GET_USERINFO_API_URL		= "https://openapi.naver.com/v1/nid/me";
	public static final String FACEBOOK_AUTH_REQUEST_API_URL	= "https://www.facebook.com/v9.0/dialog/oauth";
	public static final String FACEBOOK_GET_TOKEN_API_URL		= "https://graph.facebook.com/v9.0/oauth/access_token";
	public static final String FACEBOOK_GET_USERINFO_API_URL	= "https://graph.facebook.com/me";
  }
  
  public static final class Http {
	public static final String REQUEST_METHOD_GET		= "GET";
	public static final String REQUEST_METHOD_POST		= "POST";
	public static final int HTTP_STATUS_CODE_200		= 200;
  }
  
}
