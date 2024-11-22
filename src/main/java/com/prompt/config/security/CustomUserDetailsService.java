package com.prompt.config.security;

import com.prompt.admin.login.model.MenuInfoDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.prompt.domain.QMemberAdmn.memberAdmn;
import static com.prompt.domain.QTbMenu.tbMenu;
import static com.prompt.domain.QTbMenuAuth.tbMenuAuth;

/**
 * spring security 로그인시 사용하는 클래스
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final JPAQueryFactory queryFactory;

	/**
	 * 사용자 조회
	 *
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Tuple tUser = queryFactory.select(
				memberAdmn.memberId,
				memberAdmn.logintype,
				memberAdmn.usrId,
				memberAdmn.paswrd,
				memberAdmn.usrNam
			)
			.from(memberAdmn)
			.where(memberAdmn.usrId.eq(username))
			.fetchOne();

		if (tUser == null) {
			throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(tUser.get(memberAdmn.logintype)));

		return new UserInfoContext(authorities, tUser.get(memberAdmn.memberId),
			tUser.get(memberAdmn.usrId), tUser.get(memberAdmn.paswrd), tUser.get(memberAdmn.usrNam));
	}
}
