package com.maswilaeng.dto.user.request;

import com.maswilaeng.domain.entity.Role;
import com.maswilaeng.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private Long user_id;
    private String email;
    private String pw;
    private String nickName;
    private String phoneNumber;
    private String userImage;
    private String introduction;
    private int withdrawYn;
    private Role role;
    private String refreshToken;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime withdrawAt;

    /* DTO -> Entity */
    public User toEntity() {
        User user = User.builder()
                .email(email)
                .pw(pw)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .userImage(userImage)
                .introduction(introduction)
                .withdrawYn(withdrawYn)
                .role(role.USER)
                .refreshToken(refreshToken)
                .createdAt(createdAt)
                .build();
        return user;
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, pw);
    }
}
