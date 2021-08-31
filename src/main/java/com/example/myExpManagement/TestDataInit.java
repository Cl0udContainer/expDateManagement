package com.example.myExpManagement;


import com.example.myExpManagement.domin.member.Member;
import com.example.myExpManagement.domin.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {


    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member  = new Member();
        member.setName(("테스터"));
        member.setLoginId("test");
        member.setPassword("!@test");
        memberRepository.save(member);
    }
}
