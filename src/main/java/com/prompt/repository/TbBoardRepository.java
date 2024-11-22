package com.prompt.repository;

import com.prompt.domain.TbBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbBoardRepository extends JpaRepository<TbBoard, Long> {
}
