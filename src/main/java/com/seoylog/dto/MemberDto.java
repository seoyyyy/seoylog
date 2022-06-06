package com.seoylog.dto;

import com.seoylog.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String token;
    private String email;
    private String nickName;
    private String profile;
    private String profileImg;
    private String password;

    public static MemberDto makeSecMember(Member member){
        MemberDto dto =MemberDto.builder()
                .email(member.getEmail())
                .nickName(member.getNickName())
                .profile(member.getProfile())
                .profileImg(member.getProfileImg())
                .build();

        return dto;
    }
}
