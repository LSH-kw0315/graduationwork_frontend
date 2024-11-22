package com.prompt.repository;

import com.prompt.domain.TbMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbMenuRepository extends JpaRepository<TbMenu, Long> {
}
