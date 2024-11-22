package com.prompt.repository.promptpaging;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPromptCondition {
    @NotEmpty
    private Integer memberId;
    private String promptName;
    private String promptDesc;

    public MemberPromptCondition(Integer memberId) {
        this(memberId,null,null);
    }

    public MemberPromptCondition(Integer memberId, String promptName, String promptDesc) {
        this.memberId = memberId;
        this.promptName = promptName;
        this.promptDesc = promptDesc;
    }
}
