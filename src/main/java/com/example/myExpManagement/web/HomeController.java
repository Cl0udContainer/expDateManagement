package com.example.myExpManagement.web;

import com.example.myExpManagement.domin.member.Member;
import com.example.myExpManagement.domin.member.MemberRepository;
import com.example.myExpManagement.domin.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    public String home() { return "home"; }

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);

        if (session == null)
            return "home";

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (loginMember == null)
            return "home";

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
