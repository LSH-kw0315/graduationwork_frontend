package com.prompt.admin.login.web;

import com.prompt.admin.auth.model.AuthVO;
import com.prompt.admin.auth.service.AuthService;
import com.prompt.common.util.HostAddrUtil;
import com.prompt.common.util.MessageSourceUtil;
import com.prompt.properties.GlobalsProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;


/**
 * <PRE>
 * 1. ClassName :
 * 2. FileName  : LoginController.java
 * 3. Package  : com.prompt.admin.login.web
 * 4. Comment  :
 * 5. 작성자   : COMMON
 * 6. 작성일   : 2024. 4. 11 오후 10:36:16
 * </PRE>
 */
@Controller
@RequiredArgsConstructor
public class LoginController {
	private final MessageSourceUtil messageSourceUtil;
	private final GlobalsProperties globalsProperties;
	private final AuthService authService;

	/**
	 * <PRE>
	 * 1. MethodName : login
	 * 2. ClassName  : LoginController
	 * 3. Comment   : 로그인 폼
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2024. 4. 11 오후 10:38:11
	 * </PRE>
	 *
	 * @param model
	 * @param request
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		return "login/login";
	}

	/**
	 * 로그인 완료
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/loginSuccess.do")
	public String loginSuccess(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		//return "redirect:/auth/auth.do";
		return "redirect:/web/main/main.do";
	}

	/**
	 * 로그인 실패
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/loginFailure.do")
	public String loginFailure(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		// 작업 성공 메시지를 Flash Attribute로 추가
		redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("error.login"));

		return "redirect:" + globalsProperties.getDoLogin();
	}

	/**
	 * 로그아웃한경우; security에서 이미 데이터 삭제후 호출
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/logoutCompleted.do")
	public String logoutCompleted(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("msg.logout"));
		return "redirect:" + globalsProperties.getDoLogin();
	}

	/**
	 * 중복로그인 방지
	 * @param model
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/loginExpire.do")
	public String loginExpire(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("error.forceLogout"));
		return "redirect:" + globalsProperties.getDoLogin();
	}

	@GetMapping(value = "/join.do")
	public String joinPage(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		return "/login/join";
	}

	@PostMapping(value = "/join.do")
	public String joinInsert(HttpServletRequest request, @ModelAttribute("paramVO") AuthVO authVO, ModelMap model,
							 RedirectAttributes redirectAttributes) throws Exception {

		// 실제 아이디 존재하는지 확인
		String checkYn = authService.checkIdYn(authVO.getUsrId());

		if (checkYn.equals("N")) {
			redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("error.exist.id"));
			return "redirect:/login.do";
		}

		authVO.setIpaddress(HostAddrUtil.getClientIp());

		authService.insertAuth(authVO);
		redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("succ.data.insert"));

		return "redirect:/login.do";
	}
}
