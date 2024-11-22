package com.prompt.prompt.service;

import com.prompt.domain.TbPrompt;
import com.prompt.prompt.model.PromptCreateRequestDto;
import com.prompt.prompt.model.PromptDeleteRequestDto;
import com.prompt.prompt.model.PromptListResponseDto;
import java.util.List;

import com.prompt.prompt.model.PromptUpdateRequestDto;

import com.prompt.repository.promptpaging.MemberPromptCondition;
import com.prompt.repository.promptpaging.MemberPromptDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

public interface PromptService {
    @Transactional
    public Integer create(PromptCreateRequestDto requestDto);

    @Transactional
    public void delete(PromptDeleteRequestDto requestDto);

    @Transactional(readOnly = true)
    public List<PromptListResponseDto> selectListPrompt();

    public PromptListResponseDto promptDetailProcess(Integer promptId, Model model);

    public void update(Integer promptSeq,PromptUpdateRequestDto dto);

    @Transactional(readOnly = true)
    public Page<MemberPromptDto> getPage(MemberPromptCondition condition, Pageable pageable);
}
