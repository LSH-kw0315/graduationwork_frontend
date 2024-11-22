package com.prompt.prompt.service.impl;

import com.prompt.config.security.AuthUtil;
import com.prompt.domain.MemberAdmn;
import com.prompt.domain.TbPrompt;
import com.prompt.prompt.model.PromptCreateRequestDto;
import com.prompt.prompt.model.PromptDeleteRequestDto;
import com.prompt.prompt.model.PromptListResponseDto;
import com.prompt.prompt.model.PromptUpdateRequestDto;
import com.prompt.prompt.service.PromptService;
import com.prompt.repository.TbPromptRepository;
import java.util.List;
import java.util.stream.Collectors;

import com.prompt.repository.promptpaging.MemberPromptCondition;
import com.prompt.repository.promptpaging.MemberPromptDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Service
public class PromptServiceImpl implements PromptService {
    private final TbPromptRepository tbPromptRepository;

    @Transactional
    public Integer create(PromptCreateRequestDto requestDto) {
        System.out.println(requestDto.toEntity());
        return tbPromptRepository.save(requestDto.toEntity()).getPromptSeq();
    }

    @Transactional
    public void delete(PromptDeleteRequestDto requestDto) {
        //tbPromptRepository.deleteById(Long.parseLong(requestDto.getPromptSeq()));
        //tbPromptRepository.deleteById(requestDto.getPromptSeq());
        tbPromptRepository.deleteById(requestDto.getPromptSeq());
    }

    @Transactional(readOnly = true)
    public List<PromptListResponseDto> selectListPrompt() {
        return tbPromptRepository.findAllByOrderByPromptSeqDesc().stream()
            .map(PromptListResponseDto::new)
            .collect(Collectors.toList());
    }

    @Override
    public PromptListResponseDto promptDetailProcess(Integer promptId, Model model) {
        PromptListResponseDto result = tbPromptRepository.findById(promptId)
                .map(PromptListResponseDto::new).orElse(null);
        return result;
    }

    @Override
    @Transactional
    public void update(Integer promptSeq,PromptUpdateRequestDto dto) {
        TbPrompt findPrompt=tbPromptRepository.findById(promptSeq).orElseThrow();
        findPrompt.edit(dto.getPrompt_name(),dto.getPrompt_content());
    }

    @Transactional(readOnly = true)
    public Page<MemberPromptDto> getPage(MemberPromptCondition condition, Pageable pageable){
        return tbPromptRepository.search(condition,pageable);
    }
}
