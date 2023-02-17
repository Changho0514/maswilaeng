package com.maswilaeng.dto.user.response;

import com.maswilaeng.Domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {

    private String email;

    private String nickname;

    private String userImage;

    private String phoneNumber;

    private String introduction;

    public static UserInfoResponseDto of(User user) {
        return new UserInfoResponseDto(
                user.getEmail(),
                user.getNickName(),
                user.getPhoneNumber(),
                user.getUserImage(),
                user.getIntroduction());
    }
}
