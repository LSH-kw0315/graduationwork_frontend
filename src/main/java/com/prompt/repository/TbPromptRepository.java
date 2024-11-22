package com.prompt.repository;

import com.prompt.domain.TbPrompt;
import com.prompt.domain.TbUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbPromptRepository extends JpaRepository<TbPrompt, Integer>, TbPromptRepositoryCustom {
    List<TbPrompt> findAllByOrderByPromptSeqDesc();
}
