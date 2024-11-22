package com.prompt.admin.auth.service;

import com.prompt.admin.auth.model.AuthDto;
import com.prompt.admin.auth.model.AuthVO;
import com.prompt.admin.common.vo.CommonVO;
import org.springframework.ui.ModelMap;

import java.util.List;


public interface AuthService {

	Integer insertAuth(AuthVO vo) throws Exception;
	void updateAuth(AuthVO vo) throws Exception;
	void updateAuthPassword(AuthVO vo) throws Exception;
	void deleteAuth(AuthVO vo) throws Exception;
	AuthDto selectAuth(AuthVO vo) throws Exception;
	AuthDto selectAuthPassword(AuthVO vo) throws Exception;
	List<AuthDto> selectAuthList(CommonVO commonVo, ModelMap model) throws Exception;
	List<AuthDto> selectAuthListExcel(CommonVO commonVo, ModelMap model) throws Exception;
	String checkIdYn(String id) throws Exception;

}
