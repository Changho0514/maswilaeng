package com.maswilaeng.jwt.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Table(name = "refresh_token")
@Entity

/**
 * Access Token과 Refresh Token을 함께 사용하기에 저장이 필요하다
 * Token이 만료될 때 자동으로 삭제 처리하기 위해 Redis를 많이 사용하지만,
 * RDB를 저장소로 사용하면 배치 작업을 통해 만료된 토큰을 삭제해줘야한다.
 */
public class RefreshToken {

    @Id
    @Column(name = "rt_key")
    private String key;

    @Column(name = "rt_value")
    private String value;

    @Builder
    public RefreshToken(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

    //RDB로 구현시 생성/수정 시간 컬럼을 추가하여 배치 작업으로 만료토큰 삭제해주어야 한다.
}
