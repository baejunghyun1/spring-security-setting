package com.nahwasa.springsecuritybasicsettingforspringboot3.repository;

import com.nahwasa.springsecuritybasicsettingforspringboot3.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Long> {

    Optional<Members> findByUserid(String userId);
}
