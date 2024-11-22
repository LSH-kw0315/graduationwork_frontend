package com.prompt.config.security;

import com.prompt.admin.login.model.MenuInfoDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserInfoContext extends User {
	private final Integer memberId;
	private final String usrNam;


	public UserInfoContext(List<GrantedAuthority> authorities, Integer memberId, String usrId, String password,
						   String usrNam) {
		super(usrId, password, authorities);
		this.memberId = memberId;
		this.usrNam = usrNam;
	}
}
