package com.nahwasa.springsecuritybasicsettingforspringboot3.service;

import com.nahwasa.springsecuritybasicsettingforspringboot3.domain.Members;
import com.nahwasa.springsecuritybasicsettingforspringboot3.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Optional<Members> findOne(String userId) {
        return repository.findByUserid(userId);
    }

    public boolean isValidMember(String userId, String password) {
        Optional<Members> member = findOne(userId);
        if (member.isPresent()) {
            return member.get().getPw().equals(password);
        }
        return false;
    }
}