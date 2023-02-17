package com.maswilaeng.Domain.entity;

import com.maswilaeng.dto.user.request.UserUpdateDto;
import com.maswilaeng.dto.user.request.UserUpdateRequestDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    @Column(nullable = false, length = 100)
    private String withdraw_yn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 100)
    private Role role;

    // 수정 필요
    @Column(nullable = false, length = 1000)
    private String refresh_token;

    @Column(nullable = false, length = 100)
    @CreatedDate
    private LocalDateTime created_at;

    @Column
    @LastModifiedDate
    private LocalDateTime  modifiedAt;

    @ColumnDefault("0")
    private int withdrawYn;

    @Column
    private LocalDateTime withdrawAt;

    @Builder
    public User(String email, String pw, String nickName, String phoneNumber,
                String userImage, String introduction, String withdraw_yn, Role role) {
        this.email = email;
        this.pw = pw;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.userImage = userImage;
        this.introduction = introduction;
        this.withdraw_yn = withdraw_yn;
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
