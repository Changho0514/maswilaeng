package com.maswilaeng.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

<<<<<<< HEAD
@NoArgsConstructor(access = AccessLevel.PROTECTED)
=======
@AllArgsConstructor
@NoArgsConstructor
@Builder
>>>>>>> e6e817d (before merge)
@Getter
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String pw;

    @Column(unique = true)
    private String nickName;

    private String phoneNumber;

    private String userImage;

    @Column(length = 100)
    private String introduction;

    @ColumnDefault("0")
    private String withdrawYn;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 수정 필요
    @Column(length=1000)
    private String refreshToken;

    @OneToMany(mappedBy = "post_id")
    private List<Post> post;

}
