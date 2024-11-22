package com.prompt.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class HostAddrUtil {

	public static String getIp() {
		String result = "";
		try {
			result = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) { }
		return result;
	}

	/**
	 * Get internal ip string.
	 * <p>
	 * 서버 아이피 획득
	 * </p>
	 *
	 * @author  : dev@jayuroun.com
	 * @return the string
	 */
	public static String getInternalIp() {
		String result = "";

		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();

					// 내부 IP 주소는 주로 IPv4 주소 중에서 로컬 주소(127.0.0.1)를 제외한 것을 사용합니다.
					if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String getClientOS(String userAgent) {
		String os = "";
		userAgent = userAgent.toLowerCase();
		if (userAgent.indexOf("windows nt 10.0") > -1) {
			os = "Windows10";
		} else if (userAgent.indexOf("windows nt 6.1") > -1) {
			os = "Windows7";
		} else if (userAgent.indexOf("windows nt 6.2") > -1 || userAgent.indexOf("windows nt 6.3") > -1 ) {
			os = "Windows8";
		} else if (userAgent.indexOf("windows nt 6.0") > -1) {
			os = "WindowsVista";
		} else if (userAgent.indexOf("windows nt 5.1") > -1) {
			os = "WindowsXP";
		} else if (userAgent.indexOf("windows nt 5.0") > -1) {
			os = "Windows2000";
		} else if (userAgent.indexOf("windows nt 4.0") > -1) {
			os = "WindowsNT";
		} else if (userAgent.indexOf("windows 98") > -1) {
			os = "Windows98";
		} else if (userAgent.indexOf("windows 95") > -1) {
			os = "Windows95";
		} else if (userAgent.indexOf("iphone") > -1) {
			os = "iPhone";
		} else if (userAgent.indexOf("ipad") > -1) {
			os = "iPad";
		} else if (userAgent.indexOf("android") > -1) {
			os = "android";
		} else if (userAgent.indexOf("mac") > -1) {
			os = "mac";
		} else if (userAgent.indexOf("linux") > -1) {
			os = "Linux";
		} else {
			os = "Other";
		}
		return os;
	}

	// 1:iOS, 2:android
	public static String getClientOsNumber(String userAgent) {
		String os = "1";
		userAgent = userAgent.toLowerCase();
		if (userAgent.indexOf("android") > -1) {
			os = "2";
		}
		return os;
	}

	public static String getClientIp() throws Exception {

		String ip = null;
		HttpServletRequest request =
			((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

		ip = request.getHeader("X-FORWARDED-FOR");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	/**
	 * Get url string.
	 * <p>
	 * url 획득
	 * </p>
	 *
	 * @author  : dev@jayuroun.com
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String getUrl() throws Exception {
		HttpServletRequest request =
			((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

		return request.getRequestURI();
	}
}
