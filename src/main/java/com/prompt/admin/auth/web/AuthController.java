package com.prompt.admin.auth.web;

import com.prompt.admin.auth.constant.LoginType;
import com.prompt.admin.auth.model.AuthDto;
import com.prompt.admin.auth.model.AuthVO;
import com.prompt.admin.auth.service.AuthService;
import com.prompt.admin.common.vo.CommonVO;
import com.prompt.common.util.ExcelSxssfUtil;
import com.prompt.common.util.HostAddrUtil;
import com.prompt.common.util.MessageSourceUtil;
import com.prompt.common.util.TransReturnUtil;
import com.prompt.properties.GlobalsProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final MessageSourceUtil messageSourceUtil;
	private final TransReturnUtil transReturnUtil;
	private final GlobalsProperties globalsProperties;
	private final ExcelSxssfUtil excelSxssfUtil;

	/**
	 * <PRE>
	 * 1. MethodName : auth
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 리스트 조회
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 16 오후 11:54:21
	 * </PRE>
	 *
	 * @param commonVO
	 * @param model
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/auth.do")
	public String auth(@ModelAttribute("commonVO") CommonVO commonVO, ModelMap model) throws Exception {
		List<AuthDto> list = authService.selectAuthList(commonVO, model);

		List<AuthVO> resultList = new ArrayList<>();
		// 데이터 셋팅
		for (AuthDto authDto : list) {
			resultList.add(AuthVO.builder()
				.memberId(authDto.getMemberId())
				.loginType(authDto.getLogintype())
				.logintypeNm(LoginType.valueOf(authDto.getLogintype()).getCode())
				.usrId(authDto.getUsrId())
				.usrNam(authDto.getUsrNam())
				.regdat(authDto.getRegiDt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
				.bigo(authDto.getBigo())
				.build());
		}

		model.addAttribute("resultList", resultList);

		return "auth/auth";
	}

	/**
	 * <PRE>
	 * 1. MethodName : authReg
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 등록폼
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 16 오후 11:54:32
	 * </PRE>
	 *
	 * @param authVO
	 * @param request
	 * @param model
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/auth/authReg.do")
	public String authReg(@ModelAttribute("paramVO") AuthVO authVO, HttpServletRequest request, ModelMap model) throws Exception {
		return "auth/authReg";
	}


	/**
	 * <PRE>
	 * 1. MethodName : authMod
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 수정폼
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 25 오전 1:14:15
	 * </PRE>
	 *
	 * @param authVO
	 * @param request
	 * @param model
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/auth/authMod.do")
	public String authMod(@ModelAttribute("paramVO") AuthVO authVO, HttpServletRequest request, ModelMap model) throws Exception {

		AuthDto authDto = authService.selectAuth(authVO);
		model.addAttribute("resultVO", AuthVO.builder()
			.memberId(authDto.getMemberId())
			.loginType(authDto.getLogintype())
			.logintypeNm(LoginType.valueOf(authDto.getLogintype()).getCode())
			.usrId(authDto.getUsrId())
			.usrNam(authDto.getUsrNam())
			.regdat(authDto.getRegiDt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
			.bigo(authDto.getBigo())
			.build());

		return "auth/authMod";
	}
//
//	/**
//	 * <PRE>
//	 * 1. MethodName : authPasswordMod
//	 * 2. ClassName  : AuthController
//	 * 3. Comment   : 수정폼
//	 * 4. 작성자    : COMMON
//	 * 5. 작성일    : 2023. 8. 13 오후 4:56:50
//	 * </PRE>
//	 *   @return String
//	 *   @param authVO
//	 *   @param request
//	 *   @param model
//	 *   @return
//	 *   @throws Exception
//	 */
//	@RequestMapping(value = "/auth/authPasswordMod.do", method = RequestMethod.GET)
//	public String authPasswordMod(@ModelAttribute("paramVO") AuthVO authVO, HttpServletRequest request, ModelMap model) throws Exception {
//		// session검사
//		HttpSession session = request.getSession();
//
//		System.out.println(authVO.getUsrMal());
//		UserInfoVO userInfoVo = (UserInfoVO)session.getAttribute("userInfoVO");
//		authVO.setUsrId(userInfoVo.getUsrId());
//
//		AuthVO resultVO = authService.selectAuthPassword(authVO);
//		model.addAttribute("resultVO", resultVO);
//
//		return "auth/authPasswordMod.onlyContent";
//	}
//
//	/**
//	 * <PRE>
//	 * 1. MethodName : authPasswordMod
//	 * 2. ClassName  : AuthController
//	 * 3. Comment   : 수정폼
//	 * 4. 작성자    : COMMON
//	 * 5. 작성일    : 2023. 8. 13 오후 4:56:50
//	 * </PRE>
//	 *   @return String
//	 *   @param authVO
//	 *   @param request
//	 *   @param model
//	 *   @return
//	 *   @throws Exception
//	 */
//	@RequestMapping(value = "/auth/authPasswordModResult.do", method = RequestMethod.GET)
//	public String authPasswordModResult(@ModelAttribute("paramVO") AuthVO authVO, HttpServletRequest request, ModelMap model) throws Exception {
//		// session검사
//		HttpSession session = request.getSession();
//
//		System.out.println(authVO.getUsrMal());
//		UserInfoVO userInfoVo = (UserInfoVO)session.getAttribute("userInfoVO");
//		authVO.setUsrId(userInfoVo.getUsrId());
//
//		AuthVO resultVO = authService.selectAuthPassword(authVO);
//		model.addAttribute("resultVO", resultVO);
//
//		return "auth/authPasswordModResult.onlyContent";
//	}
//

	/**
	 * <PRE>
	 * 1. MethodName : authPasswordMod
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 수정폼
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 8. 13 오후 4:56:50
	 * </PRE>
	 *
	 * @param authVO
	 * @param request
	 * @param model
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/auth/authPasswordInit.do")
	public String authPasswordInit(@ModelAttribute("paramVO") AuthVO authVO, HttpServletRequest request, ModelMap model) throws Exception {
		AuthDto authDto = authService.selectAuthPassword(authVO);

		model.addAttribute("resultVO", AuthVO.builder()
			.memberId(authDto.getMemberId())
			.loginType(authDto.getLogintype())
			.usrId(authDto.getUsrId())
			.build());

		return "auth/authPasswordInit";
	}

	/**
	 * <PRE>
	 * 1. MethodName : authPasswordMod
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 수정폼
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 8. 13 오후 4:56:50
	 * </PRE>
	 *
	 * @param authVO
	 * @param request
	 * @param model
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/auth/authPasswordInitResult.do")
	public String authPasswordInitResult(@ModelAttribute("paramVO") AuthVO authVO, HttpServletRequest request, ModelMap model) throws Exception {
		AuthDto authDto = authService.selectAuth(authVO);
		model.addAttribute("resultVO", AuthVO.builder()
			.memberId(authDto.getMemberId())
			.loginType(authDto.getLogintype())
			.usrId(authDto.getUsrId())
			.build());

		return "auth/authPasswordInitResult";
	}

	/**
	 * <PRE>
	 * 1. MethodName : mgmt
	 * 2. ClassName  : BoardController
	 * 3. Comment   : 등록
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 1 오전 10:56:22
	 * </PRE>
	 *
	 * @param request
	 * @param model
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/auth/authInsert.do")
	public String authInsert(HttpServletRequest request, @ModelAttribute("paramVO") AuthVO authVO, ModelMap model,
							 RedirectAttributes redirectAttributes) throws Exception {

		// 실제 아이디 존재하는지 확인
		String checkYn = authService.checkIdYn(authVO.getUsrId());

		if (checkYn.equals("N")) {
			redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("error.exist.id"));
			return "redirect:/auth/authReg.do";
		}

		authVO.setIpaddress(HostAddrUtil.getClientIp());

		authService.insertAuth(authVO);
		redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("succ.data.insert"));

		return "redirect:/auth/auth.do";
	}


	/**
	 * <PRE>
	 * 1. MethodName : authUpdate
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 수정
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 25 오전 1:11:19
	 * </PRE>
	 *
	 * @param request
	 * @param authVO
	 * @param model
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/auth/authUpdate.do")
	public String authUpdate(HttpServletRequest request, @ModelAttribute("paramVO") AuthVO authVO, ModelMap model,
							 RedirectAttributes redirectAttributes) throws Exception {

		authService.updateAuth(authVO);
		redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("succ.data.update"));

		// 상세에 필요한 파라미터만 셋팅
		HashMap<String, Object> resultParam = new HashMap<>();
		resultParam.put("schType", authVO.getSchType());
		resultParam.put("schText", authVO.getSchText());
		resultParam.put("currentPage", authVO.getCurrentPage());
		resultParam.put("memberId", authVO.getMemberId());
		resultParam.put("usrMal", "1");

		return transReturnUtil.returnPage(globalsProperties.getMethodGet(), "/auth/authView.do",
			resultParam, model);
	}

//	/**
//	 * <PRE>
//	 * 1. MethodName : authPasswordUpdate
//	 * 2. ClassName  : AuthController
//	 * 3. Comment   : 수정
//	 * 4. 작성자    : COMMON
//	 * 5. 작성일    : 2023. 8. 13 오후 4:56:50
//	 * </PRE>
//	 *   @return String
//	 *   @param request
//	 *   @param authVO
//	 *   @param model
//	 *   @return
//	 *   @throws Exception
//	 */
//	@PostMapping(value = "/auth/authPasswordUpdate.do")
//	public String authPasswordUpdate(HttpServletRequest request, @ModelAttribute("paramVO") AuthVO authVO,
//									 ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
//
//		authService.updateAuthPassword(authVO);
//		redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("succ.data.update"));
//
//		// 상세에 필요한 파라미터만 셋팅
//		HashMap<String, Object> resultParam = new HashMap<>();
//		resultParam.put("memberId", authVO.getMemberId());
//
//		return transReturnUtil.returnPage(globalsProperties.getMethodGet(), "/auth/authPasswordModResult.do",
//			resultParam, model);
//	}

	/**
	 * <PRE>
	 * 1. MethodName : authPasswordUpdate
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 수정
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 8. 13 오후 4:56:50
	 * </PRE>
	 *
	 * @param request
	 * @param authVO
	 * @param model
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/auth/authPasswordInitUpdate.do")
	public String authPasswordInitUpdate(HttpServletRequest request, @ModelAttribute("paramVO") AuthVO authVO,
										 ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		authService.updateAuthPassword(authVO);
		redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("succ.data.update"));

		// 상세에 필요한 파라미터만 셋팅
		HashMap<String, Object> resultParam = new HashMap<>();
		resultParam.put("memberId", authVO.getMemberId());

		return transReturnUtil.returnPage(globalsProperties.getMethodGet(), "/auth/authPasswordInitResult.do",
			resultParam, model);
	}


	/**
	 * <PRE>
	 * 1. MethodName : authView
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 상세
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 17 오전 1:04:16
	 * </PRE>
	 *
	 * @param request
	 * @param authVO
	 * @param model
	 * @return String
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/auth/authView.do")
	public String authView(HttpServletRequest request, @ModelAttribute("paramVO") AuthVO authVO, ModelMap model) throws Exception {

		AuthDto authDto = authService.selectAuth(authVO);

		model.addAttribute("resultVO", AuthVO.builder()
			.memberId(authDto.getMemberId())
			.loginType(authDto.getLogintype())
			.logintypeNm(LoginType.valueOf(authDto.getLogintype()).getCode())
			.usrId(authDto.getUsrId())
			.usrNam(authDto.getUsrNam())
			.regdat(authDto.getRegiDt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
			.bigo(authDto.getBigo())
			.build());

		return "auth/authView";
	}

	/**
	 * <PRE>
	 * 1. MethodName : authDel
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 삭제
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 17 오후 10:25:11
	 * </PRE>
	 *   @return String
	 *   @param request
	 *   @param authVO
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@PostMapping(value = "/auth/authDel.do")
	public String authDel(HttpServletRequest request, @ModelAttribute("paramVO") AuthVO authVO, ModelMap model,
						  RedirectAttributes redirectAttributes) throws Exception {

		authService.deleteAuth(authVO);
		redirectAttributes.addFlashAttribute("message", messageSourceUtil.getMessage("succ.data.delete"));

		return "redirect:/auth/auth.do";
	}

	/**
	 * <PRE>
	 * 1. MethodName : excelDownload
	 * 2. ClassName  : BoardController
	 * 3. Comment   : 엑셀 다운로드(템플릿)
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 11 오전 12:11:24
	 * </PRE>
	 *   @return String
	 *   @param request
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@RequestMapping(value = "/auth/excelDownload.do")
	public void excelDownload(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("commonVO") CommonVO commonVO, ModelMap model) throws Exception {
		List<AuthDto> list = authService.selectAuthListExcel(commonVO, model);

		String[] alignList = new String[] {"C", "C", "C", "C", "L"};

		// 데이터 셋팅
		List<ListOrderedMap<String, Object>> eDataList = new ArrayList<>();
		ListOrderedMap<String, Object> listOrderedMap = new ListOrderedMap<>();

		for (AuthDto authDto : list) {
			// 엑셀 출력대로 입력
			listOrderedMap.put("regiDt", authDto.getRegiDt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
			listOrderedMap.put("usrId", authDto.getUsrId());
			listOrderedMap.put("loginType", LoginType.valueOf(authDto.getLogintype()).getCode());
			listOrderedMap.put("bigo", authDto.getBigo());
			eDataList.add(listOrderedMap);
		}

		excelSxssfUtil.downloadExcel(request, response, eDataList, alignList, "authList.xlsx",
			"권한리스트_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) + ".xlsx",
			true);
	}

	/**
	 * <PRE>
	 * 1. MethodName : checkIdAjax
	 * 2. ClassName  : AuthController
	 * 3. Comment   : 중복 아이디 체크
	 * 4. 작성자    : JJH
	 * 5. 작성일    : 2023. 7. 9. 오전 1:14:07
	 * </PRE>
	 *   @return EgovMap
	 *   @param request
	 *   @param response
	 *   @param id
	 *   @param model
	 *   @return
	 *   @throws Exception
	 */
	@GetMapping(value = "/auth/checkIdAjax.do")
	public @ResponseBody HashMap checkIdAjax(HttpServletRequest request, HttpServletResponse response,
											 @RequestParam("id") String id, ModelMap model) throws Exception {
		HashMap returnMap = new HashMap();
		HashMap resultStats = new HashMap();

		// 중복 아이디 체크
		String checkYn = authService.checkIdYn(id);

		resultStats.put("resultCode", "success");
		resultStats.put("resultMsg", "");
		returnMap.put("resultStats", resultStats);
		returnMap.put("checkYn", checkYn);

		return returnMap;
	}
}
