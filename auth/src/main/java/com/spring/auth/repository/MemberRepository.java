package com.spring.auth.repository;

import com.spring.auth.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
    boolean existsByUsername(String username);
}