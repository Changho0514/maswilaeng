package com.maswilaeng.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "board") // 이 부분을 참조하여 테이블이 생성된다.
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서의 생성을 열어 둘 필요가 없을 때 / 보안적으로 권장

// Post : 실제 DB와 매칭될 클래스 (Entity Class)
// 생성자 자동 생성 : NoArgsConstructor, AllArgsConstructor
// NoArgsConstructor : 객체 생성시 초기 인자 없이 객체를 생성할 수 있다.
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column
    private String thumbnail;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String content;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User user;

    /* 게시글 수정 */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Java Build design pattern. 생성 시점에 값 채우기
    @Builder
    public Post(Long post_id, LocalDateTime createdAt, String thumbnail, String title, String content, LocalDateTime modifiedAt) {

        //
        this.id = post_id;
        this.createdAt = createdAt;
        this.thumbnail = thumbnail;
        this.title = title;
        this.content = content;
        this.modifiedAt = modifiedAt;
    }
}

