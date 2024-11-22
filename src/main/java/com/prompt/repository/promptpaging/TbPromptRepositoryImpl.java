package com.prompt.repository.promptpaging;

import com.prompt.constant.Const;
import com.prompt.domain.QMemberAdmn;
import com.prompt.domain.QTbPrompt;
import com.prompt.repository.TbPromptRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;


@RequiredArgsConstructor
public class TbPromptRepositoryImpl implements TbPromptRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberPromptDto> search(MemberPromptCondition condition, Pageable pageable) {
        QTbPrompt prompt=QTbPrompt.tbPrompt;
        QMemberAdmn memberAdmn=QMemberAdmn.memberAdmn;
        Integer memberId=condition.getMemberId();

        JPAQuery<Long> count=
                queryFactory.select(prompt.count())
                        .from(prompt)
                        .join(prompt.reg_id,memberAdmn)
                        .where(memberIdEq(memberId),
                                promptNameLike(condition.getPromptName()),
                                promptContentLike(condition.getPromptDesc()))

                ;


        List<MemberPromptDto> queryContent=
        queryFactory.select(
                new QMemberPromptDto(
                        memberAdmn.memberId,
                        memberAdmn.usrId,
                        prompt.promptSeq,
                        prompt.prompt_name,
                        prompt.prompt_content
                )
        ).from(prompt)
                .join(prompt.reg_id, memberAdmn)
                .where(memberIdEq(memberId),
                        promptNameLike(condition.getPromptName()),
                        promptContentLike(condition.getPromptDesc())
                ).orderBy(prompt.promptSeq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();



        return PageableExecutionUtils.getPage(
                queryContent,pageable, count::fetchOne
        );
    }

    private BooleanExpression memberIdEq(Integer memberId) {
        if(memberId==null){
            return null;
        }
        return QTbPrompt.tbPrompt.reg_id.memberId.eq(memberId);
    }

    private BooleanExpression promptNameLike(String pattern){
        if(!StringUtils.hasText(pattern)) {
            return null;
        }
        return QTbPrompt.tbPrompt.prompt_name.contains(pattern);
    }

    private BooleanExpression promptContentLike(String pattern){
        if(!StringUtils.hasText(pattern)){
            return null;
        }
        return QTbPrompt.tbPrompt.prompt_content.contains(pattern);
    }

}
