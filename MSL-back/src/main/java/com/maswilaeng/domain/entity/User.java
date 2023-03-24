package com.maswilaeng.domain.entity;

import javax.persistence.*;

import com.maswilaeng.dto.user.request.UserUpdateRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.*;


@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Column(nullable = false, length = 1000)
    private String password;

    @Column(nullable = false, length = 30, unique = true)
    private String nickName;

    private String phoneNumber;

    private String userImage;

    @Column(length = 100)
    private String introduction;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(length = 1000)
    private String refreshToken;

    @ColumnDefault("0")
    private int withdrawYn;

    private LocalDateTime withdrawAt;

    @OneToMany(mappedBy = "toUser")
    private Set<Follow> followerList = new HashSet<>();

    @OneToMany(mappedBy = "fromUser")
    private Set<Follow> followingList = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<PostLike> postLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @Builder
    public User(String email, String password, String nickName, String phoneNumber,
                String userImage, String introduction) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.userImage = userImage;
        this.introduction = introduction;
        this.withdrawYn = 0;
        this.role = Role.USER;
    }

    public void destroyRefreshToken() {
        this.refreshToken = null;
    }

    public void update(UserUpdateRequestDto userUpdateRequestDto) {
        this.nickName = userUpdateRequestDto.getNickName();
        this.userImage = userUpdateRequestDto.getUserImage();
        this.introduction = userUpdateRequestDto.getIntroduction();
    }

    public void withdraw() {
        this.withdrawYn = 1;
        this.withdrawAt = LocalDateTime.now();
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void encryptPassword(String encryptedPw) {
        this.password = encryptedPw;
    }


}
