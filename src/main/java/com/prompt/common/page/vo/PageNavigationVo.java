package com.prompt.common.page.vo;

import com.prompt.constant.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <PRE>
 * 1. ClassName : 
 * 2. FileName  : pageNavigationVo.java
 * 3. Package  : com.prompt.common.page.vo
 * 4. Comment  : 
 * 5. 작성자   : jjh
 * 6. 작성일   : 2014. 4. 11. 오후 6:54:42
 * </PRE>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageNavigationVo implements Serializable {

	/* 총갯수 */
	private int totalCount;

	/* 기본페이지 */
	private int firstPage;

	/* 페이지당 행수 */
	private int rowPerPage = Integer.parseInt(Const.DEF_ROW_PER_PAGE);

	/* 현재페이시 번호 */
	private int currentPage = Integer.parseInt(Const.DEF_CURRENT_PAGE);

	/* 네이게이션에 보일 숫자수 */
	private int naviCount = Integer.parseInt(Const.DEF_NAVI_COUNT_05);

	/* 마지막 페이지 */
	private int lastPage;

	/* 현재 데이터 No */
	private int currDataNo;

	/* 페이지 카운트 */
	private int pageCount;

	/* current Page input 네임 */
	private String pageInputName = "currentPage";

	/* 페이지 변경시 호출될 javascript 함수 */
	private String pageCallFunction = "fnGoPage";

	private int limitStart;
	private int limitCount;
	private int limitEnd;
}
