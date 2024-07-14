package com.example.springbootthymeleafpostgresql.controller;

import com.example.springbootthymeleafpostgresql.model.Member;
import com.example.springbootthymeleafpostgresql.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member/profile")
    public String viewProfile(@AuthenticationPrincipal User user, Model model) {
        Member member = memberRepository.findByEmail(user.getUsername());
        model.addAttribute("member", member);
        return "member/profile";
    }

    @GetMapping("/api/members")
    @ResponseBody
    public List<Member> getMembersByName(@RequestParam String name) {
        return memberRepository.findByNameContainingIgnoreCase(name);
    }
}
