package com.maswilaeng.domain.repository;

import com.maswilaeng.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join fetch c.user u where c.parent.id = :parentId")
    List<Comment> findByParentId(@Param("parentId") Long parentId);
//
//    /** 댓글의 id로 조회하면서 자신의 부모와 fetch join된 결과 반환
//     * join fetch의 기본 설정이 inner join 이기에
//     * 부모가 없는 댓글도 정상적으로 조회하기위해 left join fetch 함.
//     * */
//    @Query("select c from Comment c left join fetch c.parent where c.id =:id")
//    Optional<Comment> findWithParentById(Long id);
//
//    /** 부모 아이디로 오름차순 정렬(null 우선), 다음은 자신의 아이디로 오름차순 정렬하여 조회 -> 모든 댓글 목록 조회시 사용*/
//    @Query("select c from Comment c join fetch c.user left join fetch c.parent where c.post.id =:postId order by c.parent.id asc nulls first, c.id asc")
//    List<Comment> findAllWithUserAndParentByPostIdOrderByParentIdAscNullsFirstCommentIdAsc(@Param("postId") Long postId);

    List<Comment> findAllByPostId(Long id);

    @Modifying
    @Query("UPDATE Comment c SET c.likeCount = c.likeCount + 1 WHERE c.id = :comment_Id")
    void addLikeCount(@Param("comment_Id") Long commentId);

    @Modifying
    @Query("UPDATE Comment c SET c.likeCount = c.likeCount - 1 WHERE c.id = :comment_Id")
    void subLikeCount(@Param("comment_Id") Long commentId);

    @Modifying
    @Query("UPDATE Comment c SET c.hateCount = c.hateCount + 1 WHERE c.id = :comment_Id")
    void addHateCount(@Param("comment_Id") Long commentId);

    @Modifying
    @Query("UPDATE Comment c SET c.hateCount = c.hateCount - 1 WHERE c.id = :comment_Id")
    void subHateCount(@Param("comment_Id") Long commentId);

    @Query("select c from Comment c where c.user.nickName =:content")
    List<Comment> findByNickName(@Param("content") String content);

    @Query("select c from Comment c where c.content like %:content%")
    List<Comment> findByContent(@Param("content")String content);

}
