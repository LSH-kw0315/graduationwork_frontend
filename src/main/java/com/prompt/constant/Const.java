package com.prompt.constant;

/**
 * <PRE>
 * 1. ClassName :
 * 2. FileName  : Const.java
 * 3. Package  : egovframework.framework.common.constant
 * 4. Comment  : constant
 * 5. 작성자   : kimkm
 * 6. 작성일   : 2020. 4. 21. 오후 5:19:53
 * </PRE>
 */
public class Const {

	//페이징처리시 기본설정
	public static final String DEF_FIRST_PAGE 				= "1"; 			// 디폴트 첫번째 페이지
	public static final String DEF_ROW_PER_PAGE 			= "10"; 		// 디폴트 한화면에 보여줘야할 컨텐츠 수
	public static final String DEF_ROW_PER_PAGE_05 			= "5"; 			// 디폴트 한화면에 보여줘야할 컨텐츠 수
	public static final String DEF_ROW_PER_PAGE_20 			= "20"; 		// 디폴트 한화면에 보여줘야할 컨텐츠 수
	public static final String DEF_CURRENT_PAGE 			= "1"; 			// 디폴트 현재페이지
	public static final String DEF_NAVI_COUNT 				= "10"; 		// 디폴트 페이지 갯수
	public static final String DEF_NAVI_COUNT_05 			= "5"; 			// 디폴트 페이지 갯수

	//TB_MENU
	public static final String MENU_ID_CMS 					= "10001";		// CMS MENU ID
	public static final String MENU_ID_TOP 					= "100010001100";	// 홈페이지 관리

	//TB_CODE 그룹코드 리스트
	public static final String UPCODE_USE_YN 				= "R000010";	// 사용 여부 코드
	public static final String UPCODE_DEL_YN 				= "R000020";	// 삭제 여부 코드
	public static final String UPCODE_SYS_CODE 				= "R000030";	// 시스템 코드
	public static final String UPCODE_ROW_PER_PAGE			= "R000040";	// 페이지 당 목록 수
	public static final String UPCODE_FACIL_CODE			= "R000050";	// 시설 코드
	public static final String UPCODE_COORD_CODE			= "R000060";	// 좌표 코드
	public static final String UPCODE_MENU_TYPE_CODE 		= "R000070";	// 메뉴유형코드
	public static final String UPCODE_URL_TY_CODE			= "R000080";	// URL구분코드
	public static final String UPCODE_CTTPC_SE 				= "R000090";	// 연락처구분코드
	public static final String UPCODE_USER_STTUS 			= "R000100";	// 사용자상태코드
	public static final String UPCODE_USER_SE 				= "R000110";	// 사용자구분코드
	public static final String UPCODE_BBS_TY 				= "R000120";	// 게시판구분코드
	public static final String UPCODE_BBS_CNTNTS_POSITION 	= "R000130"; 	// 일반게시판 컨텐츠 위치 코드
	public static final String UPCODE_POPUP_SE 				= "R000140";	// 팝업구분코드
	public static final String UPCODE_BANNER_ZONE 			= "R000150";	// 배너 구역 코드
	public static final String UPCODE_QUICK_ZONE 			= "R000160";	// 퀵메뉴 구역 코드

	public static final String UPCODE_BANNER_DISP_CODE 		= "R000270";	// 배너 표시 코드
	public static final String UPCODE_SEX_CODE 				= "R000280";	// 성별 코드
	public static final String UPCODE_AGE_GROUP_CODE 		= "R000290";	// 연령대 코드
	public static final String UPCODE_SCORE_CODE 			= "R000300";	// 만족도 코드
	public static final String UPCODE_OTHBC_YN 			= "R000310";	// 공개여부 코드
	public static final String UPCODE_DISP_YN 			= "R000320";	// 홈페이지 표시여부(전시여부) 코드

	public static final String UPCODE_G100 = "G100";	// 민원 - 대분류코드
	public static final String UPCODE_G200 = "G200";	// 민원 - 시설구분코드-중분류
	public static final String UPCODE_G300 = "G300";	// 민원 - 시설구분코드-소분류
	public static final String UPCODE_G400 = "G400";	// 민원 - 예산유형코드
	public static final String UPCODE_G500 = "G500";	// 민원 - 발생유형코드
	public static final String UPCODE_G600 = "G600";	// 민원 - 민원유형코드
	public static final String UPCODE_G700 = "G700";	// 민원 - 처리결과코드
	public static final String UPCODE_G800 = "G800";	// 민원 - 처리시간코드
	public static final String UPCODE_G900 = "G900";	// 민원 - 접수경로코드
	public static final String UPCODE_G1000 = "G1000";	// 민원 - 처리구분코드

	public static final String UPCODE_G1200 = "G1200";	// FAQ-광진문화예술회관 분류코드
	public static final String UPCODE_G1300 = "G1300";	// FAQ-광진구민체육센터 분류코드
	public static final String UPCODE_G1400 = "G1400";	// FAQ-중곡문화체육센터 분류코드
	public static final String UPCODE_G1500 = "G1500";	// FAQ-아차산배수지체육공원 분류코드
	public static final String UPCODE_G1600 = "G1600";	// FAQ-광장동실내배드민턴장 분류코드
	public static final String UPCODE_G1700 = "G1700";	// FAQ-중랑천파크골프장 분류코드
	public static final String UPCODE_G1900 = "G1900";	// 클린신고 분류코드
	public static final String UPCODE_G1100 = "G1100";	// 공표 목록 분류코드
	public static final String UPCODE_PUBLICT_CYCLE_G2100 = "G2100";	// 공표 시기 분류코드
	public static final String UPCODE_G1800 = "G1800";	// 사전정보공표-공표시기


	public static final String BBS_CODE_A1010 = "A1010";	// 클린신고 게시판
	public static final String BBS_CODE_A1021 = "A1021";	// 카드뉴스 게시판
	public static final String BBS_CODE_A1022 = "A1022";	// 포토갤러리 게시판


	public static final String UPCODE_STTUS_CODE = "G2000";	// 광나루365! 접수 상태 코드

	// 기본 시스템
	public static final String DEFAULT_SYS_CODE = "10";				// 홈페이지

	// 게시판 유형 코드
	public static final String BBS_TY_10 = "10";					// 일반
	public static final String BBS_TY_20 = "20";					// 뉴스레터
	public static final String BBS_TY_30 = "30";					// 포토갤러리
	public static final String BBS_TY_40 = "40";					// 자료실
	public static final String BBS_TY_50 = "50";					// FAQ

	//메뉴 구분 코드
	public static final String URL_TY_CODE_CNTS_ID 		= "10";		// 콘텐츠 ID
	public static final String URL_TY_CODE_BBS				= "20";		// 게시판 코드
	public static final String URL_TY_CODE_USER_INSERT 	= "30";		// 직접입력

	//어드민 상태코드
	public static final String USER_STTUS_CODE_TEMP_JOIN 	= "0";		// 임시가입
	public static final String USER_STTUS_CODE_ACTIVE 		= "10";		// 활동
	public static final String USER_STTUS_CODE_OUT 			= "20";		// 탈퇴
	public static final String USER_STTUS_CODE_STOP 		= "99";		// 정지

	public static final String CODE_AUTHOR_ADMIN 			= "ADMIN";	// 권한코드 - 관리자

	public static final String MBER_SE_10 					= "10";		// 기업회원
	public static final String MBER_SE_20 					= "20";		// 일반회원
	public static final String MBER_SE_30 					= "30";		// 위탁회원

	// 배치 유형 코드
	public static final String BATCH_TY_10 = "10";		// 신청후 결제 하지 않는 주문건 삭제
	public static final String BATCH_TY_20 = "20";		// 입금신청후 입금 하지 않는 주문건 삭제

	// 결과 코드
	public static final String RESULT_10 = "10";		// 성공
	public static final String RESULT_20 = "20";		// 실패

	// 프롬프트(장고) 프로젝트 주소
	public static final String PROMPT_ENGINE_URL_MAIN = "http://124.198.81.185:18649";
	public static final String PROMPT_ENGINE_URL_MAIN_LOCAL = "http://localhost:8000";
}
