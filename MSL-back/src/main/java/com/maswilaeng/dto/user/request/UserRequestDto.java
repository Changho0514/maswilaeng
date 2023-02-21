package com.maswilaeng.dto.user.request;

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
    private String password;
    private String nickName;
    private String phoneNumber;
    private String userImage;
    private String introduction;
    private int withdrawYn;
    private String refreshToken;
    private LocalDateTime withdrawAt;

    /* DTO -> Entity */
    public User toEntity() {
        User user = User.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .phoneNumber(phoneNumber)
                .userImage(userImage)
                .introduction(introduction)
                .build();
        return user;
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
