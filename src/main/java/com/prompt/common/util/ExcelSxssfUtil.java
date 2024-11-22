package com.prompt.common.util;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * <PRE>
 * 1. ClassName :
 * 2. FileName  : ExcelSxssfUtil.java
 * 3. Package  : egovframework.framework.common.util
 * 4. Comment  : 대용량 엑셀파일 다운로드
 * 5. 작성자   : COMMON
 * 6. 작성일   : 2023. 6. 11 오전 1:13:28
 * </PRE>
 */
@Component
@RequiredArgsConstructor
public class ExcelSxssfUtil {
	private final ResourceLoader resourceLoader;

	public void downloadExcel(HttpServletRequest request, HttpServletResponse response, List<ListOrderedMap<String, Object>> dataList,
							  String[] alignList, String xlsxNm, String fileNm, boolean isNumber) throws Exception {

		InputStream resourceStream =
			resourceLoader.getResource("classpath:/templates/excel/" + xlsxNm).getInputStream();
		InputStream templateFile = new BufferedInputStream(resourceStream);

		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(templateFile);

		Sheet originSheet = xssfWorkbook.getSheetAt(0);
		int rowIdx = originSheet.getLastRowNum();

		SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook, 100);
		SXSSFSheet sheet = sxssfWorkbook.getSheetAt(0);

		try {
			if (dataList != null) {
				CellStyle cBodyStyle = sxssfWorkbook.createCellStyle();
				cBodyStyle.setBorderTop(BorderStyle.THIN);
				cBodyStyle.setBorderBottom(BorderStyle.THIN);
				cBodyStyle.setBorderLeft(BorderStyle.THIN);
				cBodyStyle.setBorderRight(BorderStyle.THIN);
				cBodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				cBodyStyle.setAlignment(HorizontalAlignment.CENTER);

				CellStyle lBodyStyle = sxssfWorkbook.createCellStyle();
				lBodyStyle.setBorderTop(BorderStyle.THIN);
				lBodyStyle.setBorderBottom(BorderStyle.THIN);
				lBodyStyle.setBorderLeft(BorderStyle.THIN);
				lBodyStyle.setBorderRight(BorderStyle.THIN);
				lBodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				lBodyStyle.setAlignment(HorizontalAlignment.LEFT);

				CellStyle rBodyStyle = sxssfWorkbook.createCellStyle();
				rBodyStyle.setBorderTop(BorderStyle.THIN);
				rBodyStyle.setBorderBottom(BorderStyle.THIN);
				rBodyStyle.setBorderLeft(BorderStyle.THIN);
				rBodyStyle.setBorderRight(BorderStyle.THIN);
				rBodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
				rBodyStyle.setAlignment(HorizontalAlignment.RIGHT);

				HashMap<String, Object> hm = new HashMap<>();
				hm.entrySet();

				for (int i = 0; i < dataList.size(); i++) {
					ListOrderedMap<String, Object> tempMap = dataList.get(i);
					SXSSFRow row = sheet.createRow(++rowIdx);
					int cellIdx = 0;
					String align = "C";

					// 일련번호 표시시
					if (isNumber) {
						SXSSFCell cell = row.createCell(cellIdx);

						align = alignList[cellIdx];
						switch (align) {
							case "C" -> cell.setCellStyle(cBodyStyle);
							case "R" -> cell.setCellStyle(rBodyStyle);
							case "L" -> cell.setCellStyle(lBodyStyle);
						}

						cell.setCellValue(dataList.size() - i);
						cellIdx++;
					}

					for (String s : tempMap.keySet()) {
						String next = s;
						SXSSFCell cell = row.createCell(cellIdx);

						align = alignList[cellIdx];
						switch (align) {
							case "C" -> cell.setCellStyle(cBodyStyle);
							case "R" -> cell.setCellStyle(rBodyStyle);
							case "L" -> cell.setCellStyle(lBodyStyle);
						}

						cell.setCellValue(tempMap.get(next).toString());
						cellIdx++;
					}

					// 데이터 시트의 컬럼 길이보다 작은경우 채워준다.
					if (alignList.length > cellIdx) {
						for (int j = 0; j < alignList.length - cellIdx; j++) {
							SXSSFCell cell = row.createCell(cellIdx);
							cell.setCellStyle(cBodyStyle);
							cell.setCellValue("");
							cellIdx++;
						}
					}

					if (rowIdx % 100 == 0) {
						sheet.flushRows(dataList.size());
					}
				}
			}

			response.setContentType("application/msexcel");
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", URLEncoder.encode(fileNm, "UTF-8")));
			sxssfWorkbook.write(response.getOutputStream());

		} catch (Exception e) {
			OutputStream out = null;
			try {
				out = response.getOutputStream();
				byte[] data = new String("Fail").getBytes();
				out.write(data, 0, data.length);
			} catch (Exception ignore) {
				ignore.printStackTrace();
			} finally {
				if (out != null) try {
					out.close();
				} catch (Exception ignore) {
				}
			}
		} finally {
			try {
				sxssfWorkbook.dispose();
				sxssfWorkbook.close();
			} catch (Exception ignore) {
				ignore.printStackTrace();
			}
		}
	}

}