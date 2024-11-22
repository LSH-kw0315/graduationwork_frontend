package com.prompt.common.util;

import com.prompt.properties.GlobalsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class TransReturnUtil {
	private final GlobalsProperties globalsProperties;

	/**
	 * <PRE>
	 * 1. MethodName : returnPage
	 * 2. ClassName  : TransReturnUtil
	 * 3. Comment   : 입력, 수정, 삭제후 공통 리턴페이지 설정
	 * 4. 작성자    : JJH
	 * 5. 작성일    : 2020. 6. 5. 오전 9:12:25
	 * </PRE>
	 *   @return String
	 *   @param method
	 *   @param returnUrl
	 *   @param model
	 *   @return
	 * @throws Exception
	 */
	public String returnPage(String method, String returnUrl, HashMap<String, Object> returnParam, ModelMap model) throws Exception {

		// return redirectStr
		String redirectStr = "";

		if (method.equals(globalsProperties.getMethodGet())) {
			// GET 방식
			String urlParam = SysUtil.createUrlParam(returnParam);
			redirectStr = "redirect:" + returnUrl + urlParam;
		} else if (method.equals(globalsProperties.getMethodPost())) {
			// POST 방식
			// 최종적으로 넘길 데이터
			HashMap<String, Object> formParam = new HashMap<>();
			formParam.put("method", method);
			formParam.put("redirectUrl", returnUrl);
			model.addAttribute("formParam", formParam);
			model.addAttribute("returnParam", returnParam);
			redirectStr = "common/redirect";
		} else {
			throw new Exception();
		}

		return redirectStr;
	}
}
