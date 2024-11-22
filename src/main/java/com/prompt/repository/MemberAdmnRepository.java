package com.prompt.repository;

import com.prompt.domain.MemberAdmn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAdmnRepository extends JpaRepository<MemberAdmn, Integer> {
}
