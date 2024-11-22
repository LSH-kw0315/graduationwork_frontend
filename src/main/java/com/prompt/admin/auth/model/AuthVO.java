package com.prompt.admin.auth.model;

import com.prompt.admin.common.vo.CommonVO;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthVO extends CommonVO {

	private Integer memberId;
	private String loginType;
	private String logintypeNm;
	private String bagicId;
	private String usrId;
	private String paswrd;
	private String usrNam;
	private String tel1;
	private String tel2;
	private String tel3;
	private String ipaddress;
	private String serialno;
	private String usrMal;
	private String regdat;
	private String delFg;
	private String deldat;
	private String bigo;
}
