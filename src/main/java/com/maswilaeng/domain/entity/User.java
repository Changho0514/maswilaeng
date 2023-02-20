package com.maswilaeng.domain.entity;

import com.maswilaeng.dto.user.request.UserUpdateRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    @Column(nullable = false, length = 100)
    private String pw;

    @Column(nullable = false, length = 30, unique = true)
    private String nickName;

    @Column(nullable = false, length = 100)
    private String phoneNumber;

    @Column
    private String userImage;

    @Column
    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private Role role;

    // 수정 필요
    @Column(nullable = false, length = 1000)
    private String refreshToken;

    @Column(nullable = false, length = 100)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    @LastModifiedDate
    private LocalDateTime  modifiedAt;

    @ColumnDefault("0")
    private int withdrawYn;

    @Column
    private LocalDateTime withdrawAt;

    @Builder
    public User(String email, String pw, String nickName, String phoneNumber, String refreshToken,
                LocalDateTime createdAt,
                String userImage, String introduction, int withdrawYn, Role role) {
        this.email = email;
        this.pw = pw;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.userImage = userImage;
        this.introduction = introduction;
        this.createdAt = createdAt;
        this.withdrawYn = withdrawYn;
        this.role = role;
    }

    @Builder
    public User(String email, String pw, Role role) {
        this.email = email;
        this.pw = pw;
        this.role = role;
    }


    public void update(UserUpdateRequestDto userUpdateRequestDto) {

        this.pw = userUpdateRequestDto.getPw();
        this.phoneNumber = userUpdateRequestDto.getPhoneNumber();
        this.nickName = userUpdateRequestDto.getNickName();
        this.userImage = userUpdateRequestDto.getUserImage();
        this.introduction = userUpdateRequestDto.getIntroduction();
    }

    public void withdraw() {
        this.withdrawYn = 1;
        this.withdrawAt = LocalDateTime.now();
    }
    // 질문: 필요없는 부분일까요?
//    @OneToMany(mappedBy = "post_id")
//    private List<Post> post  = new ArrayList<>();

}
