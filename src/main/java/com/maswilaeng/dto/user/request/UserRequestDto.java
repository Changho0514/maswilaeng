package com.maswilaeng.dto.user.request;

import com.maswilaeng.domain.entity.Role;
import com.maswilaeng.domain.entity.User;

import java.time.LocalDateTime;

public class UserRequestDto {
    private Long user_id;
    private String email;
    private String pw;
    private String nickName;
    private String phoneNumber;
    private String userImage;
    private String introduction;
    private String withdraw_yn;
    private Role role;
    private String refresh_token;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
    private LocalDateTime withdraw_at;

    /* DTO -> Entity */
    public User toEntity() {
        User user = User.builder()
                .id(user_id)
                .email(email)
                .pw(pw)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .userImage(userImage)
                .introduction(introduction)
                .withdraw_yn(withdraw_yn)
                .role(role.USER)
                .refresh_token(refresh_token)
                .created_at(created_at)
                .modified_at(modified_at)
                .withdraw_at(withdraw_at)
                .build();
        return user;
    }
}
