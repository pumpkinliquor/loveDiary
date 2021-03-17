package com.plushih.common.constant;

/**
 * 공통 코드 및 구분 값 정의
 */
public class Code {
	
	public static final class UmType {
		public static final String ADMIN     = "ADMIN"; // 관리자
		public static final String MEMBER = "USER"; // 작업자
	}
	
	public static final class AuctionType {
		public static final String ONLINE        = "00"; // 온라인 경매
		public static final String OFFLINE       = "10"; // 삶의 흔적
		public static final String SPECIAL       = "20"; // 기획 경매
		public static final String SECRET        = "21"; // 기획- 시크릿 경매
		public static final String TARGET        = "22"; // 기획- 타겟 경매
		public static final String ROOM          = "23"; // 기획- 경매방
		public static final String HUB_STATION   = "30"; // 허브스테이션
	}
	
	public static final class UserType {
		public static final String ASSOCIATE    = "10"; // 준회원
		public static final String REGULAR      = "20"; // 정회원
		public static final String APPRAISER    = "30"; // 감정회원
		public static final String ADMIN        = "40"; // 관리자
		public static final String SUPER_ADMIN  = "50"; // 슈퍼관리자
	}
	
	public static final class Login {
		// 로그인 결과코드
		public static final String DEFAULT_PASSWORD			= "1q2w3e4r@";
		public static final int ID_INVALID = 10;			// 로그인 아이디 필요
		public static final int PW_INVALID = 20;			// 로그인 비밀번호 필요
		public static final int ID_NULL = 30;				// 아이디가 존재하지 않음
		public static final int PW_INCORRECT = 40;			// 비밀번호와 로그인 비밀번호가 일치하지 않음
		public static final int STOP_LOGIN = 60;			// 계정 사용중지 상태
		public static final int FAIL = 80;					// 로그인 실패
		public static final int SUCCESS = 90;				// 로그인 성공 - 특별히 처리해야할 케이스가 없을 경우 사용
		public static final int SUCCESS_FUR = 91;			// 로그인 성공 - 사전진단 미완료				(Aigo 전용)
		public static final int SUCCESS_CON = 92;			// 로그인 성공 - 사전진단 완료, 모의진단 미완료	(Aigo 전용)
		public static final int SUCCESS_LEARN = 93;			// 로그인 성공 - 사전진단 완료, 모의진단 완료 	(Aigo 전용)
		public static final int TIME_OUT = 190;				// 유효시간이 지남
		public static final int SNS_ID_INVALID = 210;		// SNS 고유 아이디 필요
		public static final int SNS_SUCCESS = 220;			// SNS으로 간편로그인 성공
		public static final int LEAVED_MEMBER = 300;		// 탈퇴한 회원
		public static final String TERMS_AGREE_CODE = "Y";	// 개인정보수집 및 이용 동의코드
		public static final int FRONT_USER = 0;				// 유저 타입 - 사용자
		public static final int ADMIN = 1;					// 유저 타입 - 관리자
		public static final String DEFAULT_TEMP_ID = "0";	// 사전진단 미진행 아이디 기본값
	}
	
	public static final class Join {
		public static final String CHANNEL_KAKAO 	= "kakao";		// 가입채널 - kakao Sns
		public static final String CHANNEL_NAVER 	= "naver";		// 가입채널 - NAVER Sns
		public static final String CHANNEL_FACEBOOK	= "facebook";	// 가입채널 - Facebook Sns
		public static final String CHANNEL_APPLE	= "apple";		// 가입채널 - Apple
		public static final String CHANNEL_EMAIL	= "email";		// 가입채널 - 이메일 가입
	}
	
	public static final class HttpStatusCode {
		public static final int HTTP_STATUS_CODE_200		= 200;
	}
	
	/** 처리상태 코드 */
	/** 각 처리에 따라 코드를 추가하거나 적절히 지정해서 사용 */
	public static final class Result {
		public static final String SUCC		= "S00";
		public static final String SUCC_01	= "S01";
		public static final String SUCC_02	= "S02";
		public static final String SUCC_03	= "S03";
		public static final String FAIL_01	= "F01";
		public static final String FAIL_02	= "F02";
		public static final String FAIL_03	= "F03";
		public static final String FAIL_04	= "F04";
		public static final String FAIL_05	= "F05";
		public static final String FAIL_99	= "F99";
	}
	
	public static final class App {
		public static final String DEVICE_ANDROID	= "A";
		public static final String DEVICE_IOS		= "I";
	}
	
	/** Aigo 로직 관련코드 */
	public static final class Aigo {
		public static final String CON_GROUP_5 				= "5";		// 모의진단 수학 1등급 그룹
		public static final String CON_GROUP_4				= "4";		// 모의진단 수학 2등급 그룹
		public static final String CON_GROUP_3 				= "3";		// 모의진단 수학 3등급 그룹
		public static final String CON_GROUP_2 				= "2";		// 모의진단 수학 4등급 그룹
		public static final String CON_GROUP_1 				= "1";		// 모의진단 수학 5등급 ~ 9등급 그룹
		public static final int DEFAULT_TEMP_GRADE 			= 0;		// 사전질문 미진행 등급 기본값
		public static final int GRADE_1						= 1;		// 1등급
		public static final int GRADE_2						= 2;		// 2등급
		public static final int GRADE_3						= 3;		// 3등급
		public static final int GRADE_4						= 4;		// 4등급
		public static final int GRADE_5						= 5;		// 5등급
		public static final int GRADE_6						= 6;		// 6등급
		public static final int GRADE_7						= 7;		// 7등급
		public static final int GRADE_8						= 8;		// 8등급
		public static final int GRADE_9						= 9;		// 9등급
		public static final int MATH_I						= 1;		// 수학 I 과목코드
		public static final int MATH_II						= 2;		// 수학 II 과목코드
		public static final int GEOMETRY					= 3;		// 기하 과목코드
		public static final int PROBABILITY_STATISTICS		= 4;		// 확률과통계 과목코드
		public static final int CALCULUS					= 5;		// 미적분 과목코드
		/** 문제분류 Key */
		public static final int CATEGORY_PREV_COUNT			= 3;		// 체크할 기출문제 풀이 수
		public static final int CATEGORY_PREV				= 2;		// 기출문제
		public static final int CATEGORY_PREV_BASIC			= 36;		// 기출문제 > 기본문제
		public static final int CATEGORY_CHECK				= 3;		// 확인문제
		public static final int CATEGORY_CHECK_BASIC		= 37;		// 확인문제 > 기본문제
		public static final int CATEGORY_CHECK_SIMILAR		= 32;		// 확인문제 > 유사문제
		public static final int CATEGORY_CHECK_SIMILAR_SUB	= 33;		// 확인문제 > 유사2문제
		public static final int CATEGORY_WEEKLY				= 4;		// 주간평가
		public static final int CATEGORY_WEEKLY_BASIC		= 38;		// 주간평가 > 기본문제
		public static final int CATEGORY_WEEKLY_SIMILAR		= 34;		// 주간평가 > 유사문제
		public static final int CATEGORY_LEVEL				= 5;		// 레벨평가
		public static final int CATEGORY_AFTER				= 9999;		// 보충학습
		
		/** 사용자가 풀이 중인 문제분류 */
		public static final String QST_TYPE_FURS				= "furs";		// 모의진단
		public static final String QST_TYPE_PREV				= "prev";		// 기출문제
		public static final String QST_TYPE_CHECK				= "check";		// 확인문제
		public static final String QST_TYPE_WEEKLY				= "weekly";		// 주간평가
		public static final String QST_TYPE_LEVEL				= "level";		// 레벨평가
		public static final String QST_TYPE_AFTER				= "after";		// 보충학습
		public static final String QST_SUB_TYPE_BASIC			= "basic";		// 기본문제
		public static final String QST_SUB_TYPE_SIMILAR			= "similar";	// 유사문제
		public static final String QST_SUB_TYPE_SIMILAR_SUB		= "similarSub";	// 유사2문제
	}
	
	public static final class Gubun {
		public static final String QST_ANALYSIS_CON			= "QST_ANALYSIS_CON";	// 문항관리 > 내용영역 항목코드
	}
	
}