package com.prompt.prompt.model;

import com.prompt.domain.TbPrompt;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PromptDeleteRequestDto {

    private Integer promptSeq;
    public PromptDeleteRequestDto(Integer promptSeq) {
        this.promptSeq =
                promptSeq;
    }
    public TbPrompt toEntity() {
        return TbPrompt.builder()
            .promptSeq(promptSeq)
            .build();
    }
}
