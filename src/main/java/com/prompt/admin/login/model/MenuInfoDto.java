package com.prompt.admin.login.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuInfoDto {
	private Integer menuSeq;
	private String menuNm;
	private Integer upMenuSeq;
	private String menuYn;
	private String url;
	private String authYn;
	@Builder.Default
	private List<MenuInfoDto> children = new ArrayList<>();

	public void addChild(MenuInfoDto child) {
		children.add(child);
	}
}
