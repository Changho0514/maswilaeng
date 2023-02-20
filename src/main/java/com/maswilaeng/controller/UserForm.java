package com.maswilaeng.controller;

import com.maswilaeng.domain.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UserForm {
    private Long user_id;
    private String email;
    private String pw;
    private String nickName;
    private Long phoneNumber;
    private String userImage;
    private String introduction;
    private String withdrawYn;
    private Role role;
    private String refreshToken;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt;
    private LocalDateTime withdrawAt;
}
