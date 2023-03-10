package com.maswilaeng.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponseDto {

    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;

}
