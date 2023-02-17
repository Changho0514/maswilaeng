package com.maswilaeng.domain.entity;

import com.maswilaeng.dto.post.request.PostUpdateDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "posts") // 이 부분을 참조하여 테이블이 생성된다.
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서의 생성을 열어 둘 필요가 없을 때 / 보안적으로 권장

// Post : 실제 DB와 매칭될 클래스 (Entity Class)
// 생성자 자동 생성 : NoArgsConstructor, AllArgsConstructor
// NoArgsConstructor : 객체 생성시 초기 인자 없이 객체를 생성할 수 있다.
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime created_at;

    @Column
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String content;

    @Column
    @LastModifiedDate
    private LocalDateTime modified_at;

    /* 게시글 수정 */
    public void update(PostUpdateDto postUpdateDto) {
        this.thumbnail = postUpdateDto.getThumbnail();
        this.title = postUpdateDto.getTitle();
        this.content = postUpdateDto.getContent();
    }

    // Java Build design pattern. 생성 시점에 값 채우기
    @Builder
    public Post(Long post_id, LocalDateTime created_at, String thumbnail, String title, String content, LocalDateTime modified_at) {

        //
        this.id = post_id;
        this.created_at = created_at;
        this.thumbnail = thumbnail;
        this.title = title;
        this.content = content;
        this.modified_at = modified_at;
    }
}

