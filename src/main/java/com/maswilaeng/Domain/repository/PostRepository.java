package com.maswilaeng.Domain.repository;

import com.maswilaeng.Domain.entity.Post;
import com.maswilaeng.Domain.entity.User;
import com.maswilaeng.dto.post.response.PostListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

    Post findByUser(User user);

    Optional<Post> findByPostId(Long postId);

    Long findUserIdByPostId(Long postId);

    Page<Post> findByUserId(@Param("userId") Long userId, PageRequest createdAt);

    @Query("select new Maswillaeng.MSLback.dto.post.reponse.PostListResponseDto(p.id,p.user.nickName,p.user.userImage,p.thumbnail,p.title,p.createdAt,p.likeList.size,p.commentList.size) from Post p order by p.createdAt DESC ")
    List<PostListResponseDto> findAllFetchJoin(Pageable pageable);
}
