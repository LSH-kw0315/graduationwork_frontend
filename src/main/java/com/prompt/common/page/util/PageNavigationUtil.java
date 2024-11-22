package com.prompt.common.page.util;

import com.prompt.common.page.vo.PageNavigationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;



 /**
 * <PRE>
 * 1. ClassName :
 * 2. FileName  : PageNavigationUtil.java
 * 3. Package  : com.prompt.common.page.util
 * 4. Comment  : 페이지 네비게이션
 * 5. 작성자   : COMMON
 * 6. 작성일   : 2024. 4. 11 오후 3:41:41
 * </PRE>
 */
@Slf4j
@Component
public class PageNavigationUtil {

	public PageNavigationVo createNavigationInfo(ModelMap model, PageNavigationVo pnv) {
		int lastPage = pnv.getTotalCount() / pnv.getRowPerPage();
		int dummyPage = 0;
		if (pnv.getTotalCount() % pnv.getRowPerPage() > 0) { //나머지가 존재할경우 1페이지 추가
			dummyPage = 1;
		}
		pnv.setLastPage(lastPage + dummyPage);

		if(pnv.getLastPage()==0){
			pnv.setLastPage(1);
		}
		int plusPage = pnv.getCurrentPage() % pnv.getNaviCount() == 0 ? -1 * pnv.getNaviCount() + 1 : 1;
		pnv.setFirstPage(pnv.getCurrentPage() / pnv.getNaviCount() * pnv.getNaviCount() + plusPage);
		pnv.setCurrDataNo(pnv.getTotalCount() - ((pnv.getCurrentPage() - 1) * pnv.getRowPerPage()));

		model.addAttribute("pageNavigationVo", pnv);

		pnv.setLimitStart((pnv.getCurrentPage() - 1) * pnv.getRowPerPage());
		pnv.setLimitCount(pnv.getRowPerPage());

		// mysql 경우 limit 이용시 끝에는 한페이지에 보여줄 게시물수만 있으면 된다.
		pnv.setLimitEnd(pnv.getRowPerPage());

		String naviBar = createNavigationBar(pnv);
		// 페이지 관련 태그 스트링 등록
		model.addAttribute("navigationBar", naviBar);

		return pnv;
	}

	public String createNavigationBar(PageNavigationVo pnv) {

		StringBuffer rtnStr = new StringBuffer();
		int nextPage = 0;

		if (pnv.getTotalCount() > 0) {
			rtnStr.append("<div class=\"pagenation\">");

			if (pnv.getFirstPage() + pnv.getNaviCount() > pnv.getLastPage()) {
				nextPage = pnv.getLastPage() + 1;
			} else {
				nextPage = pnv.getFirstPage() + pnv.getNaviCount();
			}

			rtnStr.append("<span class=\"before\"><a href=\"#\" class=\"page-link\" title=\"맨앞으로가기\" onclick=\"" + pnv.getPageCallFunction() + "('1'); return false;\"></a></span>");

			if (pnv.getFirstPage() > pnv.getNaviCount()) {
				rtnStr.append("<span class=\"prev\"><a href=\"#\" class=\"page-link\" title=\"앞으로가기\" onclick=\"" + pnv.getPageCallFunction() + "('" + (pnv.getFirstPage() - 1)
						+ "'); return false;\"></a></span>");
			}

			for (int i = pnv.getFirstPage(); i < nextPage; i++) {
				if (pnv.getCurrentPage() == i) {
					rtnStr.append("<span class=\"num on\"><a href=\"#\" class=\"page-link\" onclick=\"" + pnv.getPageCallFunction() + "('" + i + "'); return false;\">" + i + "</a></span>");
				} else {
					rtnStr.append("<span class=\"num\"><a href=\"#\" class=\"page-link\" onclick=\"" + pnv.getPageCallFunction() + "('" + i + "'); return false;\">" + i + "</a></span>");
				}
			}
			
			if (pnv.getFirstPage() + pnv.getNaviCount() - 1 < pnv.getLastPage()) {
				rtnStr.append("<span class=\"next\"><a href=\"#\" class=\"page-link\" title=\"뒤로가기\" onclick=\"" + pnv.getPageCallFunction() + "('" + (pnv.getFirstPage() + pnv.getNaviCount()) + "'); return false;\"></a></span>");
			}

			rtnStr.append("<span class=\"after\"><a href=\"#\" class=\"page-link\" title=\"맨뒤로가기\" onclick=\"" + pnv.getPageCallFunction() + "('" + pnv.getLastPage()
					+ "'); return false;\"></a></span>");
			rtnStr.append("</div>");

			rtnStr.append("<input type=\"hidden\" name=\"" + pnv.getPageInputName() + "\" id=\"" + pnv.getPageInputName() + "\" value=\"" + pnv.getCurrentPage() + "\"/>");
		}

		log.debug("#######rtnStr.toString():"+rtnStr.toString());
		return rtnStr.toString();
	}
}
