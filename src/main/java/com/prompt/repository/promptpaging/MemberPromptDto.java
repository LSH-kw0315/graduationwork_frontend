package com.prompt.repository.promptpaging;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
public class MemberPromptDto {
    private Integer member_id;
    private String user_id;

    private Integer promptSeq;
    private String prompt_name;
    private String prompt_content;

    @QueryProjection
    public MemberPromptDto(Integer member_id, String user_id, Integer promptSeq, String prompt_name, String prompt_content) {
        this.member_id = member_id;
        this.user_id = user_id;
        this.promptSeq = promptSeq;
        this.prompt_name = prompt_name;
        this.prompt_content = prompt_content;
    }
}
