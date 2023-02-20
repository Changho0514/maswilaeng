package com.maswilaeng.jwt.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TokenDto {

    private String grantType;
    private String refreshToken;
    private String accessToken;
    private Long accessTokenExpiresIn;

}
