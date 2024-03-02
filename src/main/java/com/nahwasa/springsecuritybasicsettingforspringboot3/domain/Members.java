package com.nahwasa.springsecuritybasicsettingforspringboot3.domain;

import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userid;

    private String pw;

    private String roles;

    private Members(Long id, String userid, String pw, String roleUser) {
        this.id = id;
        this.userid = userid;
        this.pw = pw;
        this.roles = roleUser;
    }

    protected Members() {}

    public static Members createUser(String userId, String pw, PasswordEncoder passwordEncoder) {
        return new Members(null, userId, passwordEncoder.encode(pw), "USER");
    }

    public Long getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getPw() {
        return pw;
    }

    public String getRoles() {
        return roles;
    }
}
