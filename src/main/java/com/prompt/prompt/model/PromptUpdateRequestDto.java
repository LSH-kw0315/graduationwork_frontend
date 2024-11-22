package com.prompt.prompt.model;

import com.prompt.domain.MemberAdmn;
import com.prompt.domain.TbPrompt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
//@Setter
@NoArgsConstructor
public class PromptUpdateRequestDto {

    private Integer promptSeq;
    private String prompt_name;
    private String prompt_content;
    private Integer reg_id;

    public PromptUpdateRequestDto(TbPrompt prompt) {
        this.prompt_name = prompt.getPrompt_name();
        this.prompt_content = prompt.getPrompt_content();
        this.promptSeq = prompt.getPromptSeq();
        this.reg_id=prompt.getReg_id().getMemberId();
    }
    public void setPrompt_name(String prompt_name) {
        this.prompt_name = prompt_name;
    }
    public void setPrompt_content(String prompt_content) {
        this.prompt_content = prompt_content;
    }
    public void setpromptSeq(Integer promptSeq) { this.promptSeq = promptSeq; }

    public void setReg_id(Integer reg_id){this.reg_id=reg_id;}

//    public TbPrompt toEntity() {
//        return TbPrompt.builder()
//            .prompt_name(prompt_name)
//            .prompt_content(prompt_content)
//            .promptSeq(promptSeq)
//                .reg_id(reg_id)
//            .build();
//    }
}
