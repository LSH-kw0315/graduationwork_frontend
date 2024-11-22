package com.prompt.admin.auth.service.impl;

import com.prompt.admin.auth.model.AuthDto;
import com.prompt.admin.auth.model.AuthVO;
import com.prompt.admin.auth.service.AuthService;
import com.prompt.admin.common.vo.CommonVO;
import com.prompt.common.page.util.PageNavigationUtil;
import com.prompt.common.util.SysUtil;
import com.prompt.domain.MemberAdmn;
import com.prompt.repository.MemberAdmnRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.prompt.domain.QMemberAdmn.memberAdmn;


/**
 * @author 개발프레임웍크 실행환경 개발팀
 * @version 1.0
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
 * @Modification Information
 * @
 * @ 수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 * @see Copyright (C) by MOPAS All right reserved.
 * @since 2009. 03.16
 */

@Slf4j
@RequiredArgsConstructor
@Service("authService")
public class AuthServiceImpl implements AuthService {

	private final JPAQueryFactory queryFactory;
	private final PageNavigationUtil pageNavigationUtil;
	private final BCryptPasswordEncoder passwordEncoder;
	private final MemberAdmnRepository memberAdmnRepository;

	/**
	 * <PRE>
	 * 1. MethodName : insertAuth
	 * 2. ClassName  : AuthServiceImpl
	 * 3. Comment   : 등록
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 17 오전 12:23:03
	 * </PRE>
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Integer insertAuth(AuthVO vo) throws Exception {
		MemberAdmn memberAdmn1 = memberAdmnRepository.save(MemberAdmn.builder()
			.logintype(vo.getLoginType())
			.usrId(vo.getUsrId())
				.usrNam(vo.getUsrId())
			.paswrd(passwordEncoder.encode(vo.getPaswrd()))
			.bigo(vo.getBigo())
			.regiDt(LocalDateTime.now())
			.build());
		return memberAdmn1.getMemberId();
	}


	/**
	 * <PRE>
	 * 1. MethodName : updateAuth
	 * 2. ClassName  : AuthServiceImpl
	 * 3. Comment   : 수정
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 17 오전 1:04:28
	 * </PRE>
	 *
	 * @param vo
	 * @throws Exception
	 */
	public void updateAuth(AuthVO vo) throws Exception {
		JPAUpdateClause query = queryFactory.update(memberAdmn)
			.set(memberAdmn.logintype, vo.getLoginType())
			.set(memberAdmn.bigo, vo.getBigo());

		if (vo.getPaswrd() != null && !vo.getPaswrd().isEmpty()) {
			query.set(memberAdmn.paswrd, passwordEncoder.encode(vo.getPaswrd()));
		}

		query.execute();
	}

	/**
	 * <PRE>
	 * 1. MethodName : updateAuthPassword
	 * 2. ClassName  : AuthServiceImpl
	 * 3. Comment   : 수정
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 8. 13 오후 4:56:50
	 * </PRE>
	 *
	 * @param vo
	 * @throws Exception
	 */
	public void updateAuthPassword(AuthVO vo) throws Exception {
		queryFactory.update(memberAdmn)
			.set(memberAdmn.paswrd, passwordEncoder.encode(vo.getPaswrd()))
			.where(memberAdmn.memberId.eq(vo.getMemberId()))
			.execute();
	}

	/**
	 * <PRE>
	 * 1. MethodName : deleteAuth
	 * 2. ClassName  : AuthServiceImpl
	 * 3. Comment   : 삭제
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 17 오전 1:12:44
	 * </PRE>
	 *
	 * @param vo
	 * @throws Exception
	 */
	public void deleteAuth(AuthVO vo) throws Exception {
		queryFactory.delete(memberAdmn)
			.where(memberAdmn.memberId.eq(vo.getMemberId()))
			.execute();
	}


	/**
	 * <PRE>
	 * 1. MethodName : selectAuth
	 * 2. ClassName  : AuthServiceImpl
	 * 3. Comment   : 상세조회
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 6. 16 오후 11:51:09
	 * </PRE>
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AuthDto selectAuth(AuthVO vo) throws Exception {

		return queryFactory.select(Projections.bean(AuthDto.class,
				memberAdmn.memberId,
				memberAdmn.logintype,
				memberAdmn.usrId,
				memberAdmn.paswrd,
				memberAdmn.regiDt,
				memberAdmn.delFg,
				memberAdmn.deldat,
				memberAdmn.bigo
			))
			.from(memberAdmn)
			.where(memberAdmn.memberId.eq(vo.getMemberId()))
			.fetchOne();
	}

	/**
	 * <PRE>
	 * 1. MethodName : selectAuthPassword
	 * 2. ClassName  : AuthServiceImpl
	 * 3. Comment   : 상세조회
	 * 4. 작성자    : COMMON
	 * 5. 작성일    : 2023. 8. 13 오후 4:56:50
	 * </PRE>
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public AuthDto selectAuthPassword(AuthVO vo) throws Exception {
		AuthDto resultDto = queryFactory.select(Projections.bean(AuthDto.class,
				memberAdmn.memberId,
				memberAdmn.logintype,
				memberAdmn.usrId,
				memberAdmn.paswrd
			))
			.from(memberAdmn)
			.where(memberAdmn.usrId.eq(vo.getUsrId()))
			.fetchOne();

		return resultDto;
	}

	/**
	 * 글 목록을 조회한다.
	 *
	 * @return 글 목록
	 * @throws Exception
	 */

	public List<AuthDto> selectAuthList(CommonVO commonVo, ModelMap model) throws Exception {
		List<AuthDto> resultList = new ArrayList();

		BooleanBuilder where = new BooleanBuilder();
		if (!SysUtil.nvl(commonVo.getSchText()).equals("")) {
			if (commonVo.getSchType().equals("id")) {
				where.and(memberAdmn.usrId.like("%" + commonVo.getSchText() + "%"));
			}
		}

		Long totCnt = queryFactory.select(
				memberAdmn.usrId.count()
			)
			.from(memberAdmn)
			.where(where)
			.fetchOne();
		commonVo.setTotalCount(Math.toIntExact(totCnt));
		pageNavigationUtil.createNavigationInfo(model, commonVo);

		if (totCnt > 0) {
			resultList = queryFactory.select(Projections.bean(AuthDto.class,
					memberAdmn.memberId,
					memberAdmn.logintype,
					memberAdmn.usrId,
					memberAdmn.paswrd,
					memberAdmn.bigo,
					memberAdmn.regiDt,
					memberAdmn.delFg,
					memberAdmn.deldat
				))
				.from(memberAdmn)
				.where(where)
				.orderBy(memberAdmn.logintype.asc(), memberAdmn.memberId.asc(), memberAdmn.bigo.asc())
				.offset(commonVo.getLimitStart())
				.limit(commonVo.getLimitCount())
				.fetch();
		}

		return resultList;
	}

	public List<AuthDto> selectAuthListExcel(CommonVO commonVo, ModelMap model) throws Exception {
		List<AuthDto> resultList = new ArrayList();

		BooleanBuilder where = new BooleanBuilder();
		if (!SysUtil.nvl(commonVo.getSchText()).equals("")) {
			if (commonVo.getSchType().equals("id")) {
				where.and(memberAdmn.usrId.like("%" + commonVo.getSchText() + "%"));
			}
		}

		Long totCnt = queryFactory.select(
				memberAdmn.usrId.count()
			)
			.from(memberAdmn)
			.where(where)
			.fetchOne();

		if (totCnt > 0) {
			resultList = queryFactory.select(Projections.bean(AuthDto.class,
					memberAdmn.memberId,
					memberAdmn.logintype,
					memberAdmn.usrId,
					memberAdmn.paswrd,
					memberAdmn.bigo,
					memberAdmn.regiDt,
					memberAdmn.delFg,
					memberAdmn.deldat
				))
				.from(memberAdmn)
				.where(where)
				.orderBy(memberAdmn.logintype.asc(), memberAdmn.memberId.asc(), memberAdmn.bigo.asc())
				.fetch();
		}

		return resultList;
	}


	/**
	 * <PRE>
	 * 1. MethodName : checkIdYn
	 * 2. ClassName  : AuthServiceImpl
	 * 3. Comment   : 중복 아이디 체크
	 * 4. 작성자    : JJH
	 * 5. 작성일    : 2023. 7. 9. 오전 1:18:12
	 * </PRE>
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String checkIdYn(String id) throws Exception {
		Long cnt = queryFactory.select(
				memberAdmn.usrId.count()
			)
			.from(memberAdmn)
			.where(memberAdmn.usrId.eq(id))
			.fetchOne();

		if (cnt != null && cnt > 0L) {
			return "N";
		} else {
			return "Y";
		}
	}
}
