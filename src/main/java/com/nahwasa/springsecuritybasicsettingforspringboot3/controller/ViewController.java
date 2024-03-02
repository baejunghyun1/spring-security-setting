package com.nahwasa.springsecuritybasicsettingforspringboot3.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("loginId", user.getUsername());
        model.addAttribute("loginRoles", user.getAuthorities());
        return "dashboard";
    }

    @GetMapping("/setting/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String adminSettingPage() {
        return "admin_setting";
    }

    @GetMapping("/setting/user")
    @PreAuthorize("hasAnyRole('USER')")
    public String userSettingPage() {
        return "user_setting";
    }
}