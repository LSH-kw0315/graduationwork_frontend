package com.prompt.common.util;


import jakarta.servlet.http.HttpServletRequest;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : MessageUtil.java
 * 3. Package  : egovframework.framework.common.util
 * 4. Comment  : 세션 메세지 셋팅
 * 5. 작성자   : COMMON
 * 6. 작성일   : 2023. 5. 27 오후 11:10:09
 * </PRE>
 */
public class MessageUtil {

	public static void setMessage(HttpServletRequest request, String message) {
		request.getSession().setAttribute("message", message);
	}
}
