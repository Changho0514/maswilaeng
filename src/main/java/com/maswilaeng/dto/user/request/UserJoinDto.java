package com.maswilaeng.dto.user.request;

import com.maswilaeng.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class UserJoinDto {

    private String email;

    private String pw;

    private String nickName;

    private String userImage;

    public UserJoinDto(String email, String pw, String nickName, String userImage) {
        this.email = email;
        this.pw = pw;
        this.nickName = nickName;
        this.userImage = userImage;
    }

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
                .nickName(nickName)
                .userImage(userImage)
                .build();
    }
}
