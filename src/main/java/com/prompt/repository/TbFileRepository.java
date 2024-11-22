package com.prompt.repository;

import com.prompt.domain.TbFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbFileRepository extends JpaRepository<TbFile, Long> {
}
