package com.maswilaeng.dto.user.response;

import com.maswilaeng.domain.entity.Role;
import com.maswilaeng.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

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
    public UserResponseDto(User user) {
        //for git
        this.user_id = user.getId();
        this.email = user.getEmail();
        this.pw = user.getPw();
        this.nickName = user.getNickName();
        this.phoneNumber = user.getPhoneNumber();
        this.userImage = user.getUserImage();
        this.introduction = user.getIntroduction();
        this.withdrawYn = user.getWithdrawYn();
        this.role = user.getRole();
        this.refreshToken = user.getRefreshToken();

    }

    public UserResponseDto(String email) {
        this.email = email;
    }

    public static UserResponseDto of(User user) {

        return new UserResponseDto(user.getEmail());
    }

}
