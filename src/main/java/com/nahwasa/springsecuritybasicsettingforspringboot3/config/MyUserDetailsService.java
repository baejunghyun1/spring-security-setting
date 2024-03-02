package com.nahwasa.springsecuritybasicsettingforspringboot3.config;

import com.nahwasa.springsecuritybasicsettingforspringboot3.domain.Members;
import com.nahwasa.springsecuritybasicsettingforspringboot3.service.MemberService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    public MyUserDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        Optional<Members> findOne = memberService.findOne(insertedUserId);
        Members members = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다 ㅠ"));

        return User.builder()
                .username(members.getUserid())
                .password(members.getPw())
                .roles(members.getRoles())
                .build();
    }
}