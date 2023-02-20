package com.maswilaeng.domain.entity;

import com.maswilaeng.dto.user.request.UserUpdateRequestDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Column(nullable = false)
    private String pw;

    @Column(unique = true)
    private String nickName;

    private String phoneNumber;

    private String userImage;

    @Column(length = 100)
    private String introduction;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 수정 필요
    @Column(length = 1000)
    private String refreshToken;

    @ColumnDefault("0")
    private int withdrawYn;

    private LocalDateTime withdrawAt;

    @Builder
    public User(String email, String pw, String nickName, String phoneNumber,
                String userImage, String introduction) {
        this.email = email;
        this.pw = pw;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.userImage = userImage;
        this.introduction = introduction;
        this.withdrawYn = 0;
        this.role = Role.USER;
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
