package com.prompt.prompt.model;

import com.prompt.domain.MemberAdmn;
import com.prompt.domain.TbPrompt;
import com.prompt.domain.TbUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class PromptListResponseDto {

    private Integer promptSeq;
    private String prompt_name;
    private String prompt_content;
    private String reg_id;

    public PromptListResponseDto(TbPrompt tbPrompt) {
        this.promptSeq = tbPrompt.getPromptSeq();
        this.prompt_name = tbPrompt.getPrompt_name();
        this.prompt_content = tbPrompt.getPrompt_content();
        this.reg_id = tbPrompt.getReg_id().getUsrId();
    }

    public PromptListResponseDto(Integer promptSeq, String prompt_name, String prompt_content, String reg_id) {
        this.promptSeq = promptSeq;
        this.prompt_name = prompt_name;
        this.prompt_content = prompt_content;
        this.reg_id = reg_id;
    }

    public String toString() {
        return "PromptListResponseDto{" +
            "prompt_name='" + prompt_name + "'" +
            ", prompt_content='" + prompt_content + "'" +
            ", reg_id='" + reg_id + "'" +
            "}\n";
    }
}
