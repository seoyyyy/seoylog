package com.seoylog.domain;

import com.seoylog.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class Member {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    private String profileImg;

    private String profile;

    public static Member makeEntity(MemberDto dto){
        return Member.builder()
                .email(dto.getEmail())
                .nickName(dto.getNickName())
                .profile(dto.getProfile())
                .profileImg(dto.getProfileImg())
                .password(dto.getPassword()).build();
    }
}
