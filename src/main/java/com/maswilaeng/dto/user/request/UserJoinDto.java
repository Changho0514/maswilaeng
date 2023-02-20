package com.maswilaeng.dto.user.request;

import com.maswilaeng.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.maswilaeng.domain.entity.Role.USER;

@Getter
@NoArgsConstructor
public class UserJoinDto {

    private String email;

    private String pw;

    private String nickName;

    private String userImage;

    public User toEntity() {
        return User.builder()
                .email(email)
                .pw(pw)
                .nickName(nickName)
                .userImage(userImage)
                .build();
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .pw(passwordEncoder.encode(pw))
                .role(USER)
                .build();
    }
}
