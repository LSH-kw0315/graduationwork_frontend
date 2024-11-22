package com.prompt.repository;

import com.prompt.repository.promptpaging.MemberPromptCondition;
import com.prompt.repository.promptpaging.MemberPromptDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TbPromptRepositoryCustom {
    Page<MemberPromptDto> search(MemberPromptCondition condition, Pageable pageable);
}
