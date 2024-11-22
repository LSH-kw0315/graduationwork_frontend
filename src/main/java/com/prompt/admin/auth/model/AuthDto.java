package com.prompt.admin.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

	private Integer memberId;
	private String logintype;
	private String usrId;
	private String paswrd;
	private String usrNam;
	private String usrMal;
	private LocalDateTime regiDt;
	private String delFg;
	private LocalDateTime deldat;
	private String bigo;
}
