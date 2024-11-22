package com.prompt.prompt.model;

import com.prompt.domain.MemberAdmn;
import com.prompt.domain.TbPrompt;
import com.prompt.domain.TbUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PromptCreateRequestDto {

    private String prompt_name;

    public void setPrompt_name(String prompt_name) {
        this.prompt_name = prompt_name;
    }

    public void setPrompt_content(String prompt_content) {
        this.prompt_content = prompt_content;
    }

    private String prompt_content;
    private MemberAdmn reg_id;

    public PromptCreateRequestDto(String prompt_name, String prompt_content) {
        this.prompt_name = prompt_name;
        this.prompt_content = prompt_content;
    }

    public void setReg_id(MemberAdmn reg_id){
        this.reg_id = reg_id;
    }

    public TbPrompt toEntity() {
        return TbPrompt.builder()
            .prompt_name(prompt_name)
            .prompt_content(prompt_content)
            .reg_id(reg_id)
            .build();
    }

    public String toString() {
        return "PromptCreateRequestDto{" +
            "prompt_name='" + prompt_name + "'" +
            ", prompt_content='" + prompt_content + "'" +
            ", reg_id='" + reg_id + "'" +
            "}";
    }
}
