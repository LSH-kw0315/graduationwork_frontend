package com.prompt.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class SysUtil {

	private static Log log = LogFactory.getLog(SysUtil.class);

	private final static int LENGTH_10_INT_RADIX = 9;

	public static String nvl(String src, String defaultValue) {
		return (src == null || "".equals(src.trim())) ? defaultValue : src;
	}

	public static String nvl(String src) {
		return nvl(src, "");
	}

	public static String getDocId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
//		return parseToShortUUID(uuid.toString());
	}

	public static String getFileId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
//		return parseToShortUUID(uuid.toString());
	}

	public static String parseToShortUUID(String uuid) {
		int l = ByteBuffer.wrap(uuid.getBytes()).getInt();
		return Integer.toString(l, LENGTH_10_INT_RADIX);
	}

	public static String createUrlParam(HashMap<String, Object> dataMap) throws UnsupportedEncodingException {
		String addUrlStr = "";

		if(dataMap == null) {
			return addUrlStr;
		}else {
			addUrlStr = "?";
		}

		List<String> keyList = new ArrayList<String>(dataMap.keySet());
		for (int i = 0; i < keyList.size(); i++) {
			String value = dataMap.get(keyList.get(i)) == null ? "" : dataMap.get(keyList.get(i)).toString();

			if(i == 0) {
				addUrlStr += keyList.get(i) + "=" + URLEncoder.encode(value, "UTF-8").replaceAll("\\[", "").replaceAll("\\]", "");
			}else {
				addUrlStr += "&" + keyList.get(i) + "=" + URLEncoder.encode(value, "UTF-8").replaceAll("\\[", "").replaceAll("\\]", "");
			}
		}

		return addUrlStr;
	}

	public static String objectToJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(obj);

		return jsonStr;
	}
}
