package com.prompt.admin.common.vo;

import com.prompt.common.page.vo.PageNavigationVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonVO extends PageNavigationVo {
	private String schText;
	private String schType;
}
