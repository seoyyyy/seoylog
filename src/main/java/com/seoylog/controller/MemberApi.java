package com.seoylog.controller;

import com.seoylog.domain.Member;
import com.seoylog.dto.MemberDto;
import com.seoylog.dto.ResponseDto;
import com.seoylog.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class MemberApi {
    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody MemberDto memberDto) {
        try {
            Member member = Member.makeEntity(memberDto);

            Member registeredUser = memberService.createUser(member);
            MemberDto responseMember = memberDto.builder()
                    .email(registeredUser.getEmail())
                    .nickName(registeredUser.getNickName())
                    .profile(registeredUser.getProfile())
                    .profileImg(registeredUser.getProfileImg()).build();

            return ResponseEntity.ok().body(responseMember);

        }catch (Exception e){
            ResponseDto<Object> responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDto memberDto){
        Member member = memberService.getAuthentication(memberDto.getEmail(), memberDto.getPassword());


        if(member != null){
            MemberDto responseMember = MemberDto.makeSecMember(member);
            System.out.println("member = " + responseMember);

            return ResponseEntity.ok().body(responseMember);
        }else{
            ResponseDto<Object> responseDto = ResponseDto.builder().error("Login Failed").build();
            return ResponseEntity.badRequest().body(responseDto);
        }

    }
}
