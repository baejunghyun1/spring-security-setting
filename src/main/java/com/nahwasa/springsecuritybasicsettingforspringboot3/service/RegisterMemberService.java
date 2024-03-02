package com.nahwasa.springsecuritybasicsettingforspringboot3.service;

import com.nahwasa.springsecuritybasicsettingforspringboot3.domain.Members;
import com.nahwasa.springsecuritybasicsettingforspringboot3.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterMemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository repository;

    @Autowired
    public RegisterMemberService(PasswordEncoder passwordEncoder, MemberRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public Long join(String userid, String pw) {
        Members members = Members.createUser(userid, pw, passwordEncoder);
        validateDuplicateMember(members);
        repository.save(members);

        return members.getId();
    }

    private void validateDuplicateMember(Members members) {
        repository.findByUserid(members.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
