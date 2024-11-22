package com.prompt.admin.auth.constant;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : LoginType.java
 * 3. Package  : egovframework.ot.auth.constant
 * 4. Comment  : 로그인 타입
 * 5. 작성자   : COMMON
 * 6. 작성일   : 2023. 6. 17 오후 11:22:39
 * </PRE>
 */ 
public enum LoginType {
	A("관리자")
	, B("운영자")
	, C("이용자")
	, D("결행정보");

	private String code;

	LoginType(String code){
		this.code = code;
	}

	/**
	 * <PRE>
	 * 1. MethodName : getCode
	 * 2. ClassName  : PayType
	 * 3. Comment   : 코드 조회
	 * 4. 작성자    : JJH
	 * 5. 작성일    : 2022. 6. 28. 오후 4:07:36
	 * </PRE>
	 *   @return String
	 *   @return
	 */
	public String getCode() {
		return code;
	}
}
