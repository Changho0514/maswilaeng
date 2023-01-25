package com.maswilaeng.dto.user.response;

import com.maswilaeng.Domain.entity.Role;
import com.maswilaeng.Domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private Long phoneNumber;
    private String userImage;
    private String introduction;
    private String withdraw_yn;
    private Role role;
    private String refresh_token;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
    private LocalDateTime withdraw_at;

    /* DTO -> Entity */
    public UserResponseDto(User user) {
        this.user_id = user_id;
        this.email = email;
        this.email = user.getEmail();
        this.pw = user.getPw();
        this.nickName = user.getNickName();
        this.phoneNumber = user.getPhoneNumber();
        this.userImage = user.getUserImage();
        this.introduction = user.getIntroduction();
        this.withdraw_yn = user.getWithdraw_yn();
        this.role = user.getRole();
        this.refresh_token = user.getRefresh_token();






    }
}
