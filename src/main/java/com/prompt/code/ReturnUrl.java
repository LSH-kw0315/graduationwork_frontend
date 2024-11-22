package com.prompt.code;

/**
 * <PRE>
 * 1. ClassName :
 * 2. FileName  : ReturnUrl.java
 * 3. Package  : egovframework.ot.login.vo
 * 4. Comment  : 권한별 리턴 URL 정의
 * 5. 작성자   : JJH
 * 6. 작성일   : 2023. 7. 9. 오전 5:08:41
 * </PRE>
 */
public enum ReturnUrl {
	A("/summary/operating.do"),
	B("/monitoring/electronicMap.do"),
	C("/monitoring/routeMap.do"),
	D("/cancel/driving.do");

	private final String url;

	ReturnUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
