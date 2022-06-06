package com.seoylog.service;

import com.seoylog.domain.Member;
import com.seoylog.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public Member createUser(final Member member){
        if(member == null || member.getEmail() == null){
            return null;
        }

        final String email = member.getEmail();
        if(memberRepository.existsByEmail(email)){
            log.warn("이메일이 이미 존재합니다. :: {}", email);
        }

        return memberRepository.save(member);
    }

    public Member getAuthentication(final String email, final String password){
        return memberRepository.findByEmailAndPassword(email, password);
    }

}
