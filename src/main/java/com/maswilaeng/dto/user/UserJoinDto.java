package com.maswilaeng.dto.user;

import com.maswilaeng.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserJoinDto {

    private String email;

    private String pw;

    private String phoneNumber;

    private String nickName;

    private String userImage;

    private String Introduction;

    public User toEntity() {
        return User.builder()
                .email(email)
                .pw(pw)
                .phoneNumber(phoneNumber)
                .nickName(nickName)
                .userImage(userImage)
                .introduction(Introduction)
                .build();
    }
}