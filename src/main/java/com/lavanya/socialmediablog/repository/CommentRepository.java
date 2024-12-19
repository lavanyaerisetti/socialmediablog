package com.lavanya.socialmediablog.repository;

import com.lavanya.socialmediablog.dto.CommentDto;
import com.lavanya.socialmediablog.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    @Query(value="SELECT * FROM comments where post_id=?1",nativeQuery=true)
    List<CommentEntity> findByPostId(long postId);

    @Query(value="SELECT * FROM comments where post_id=?1 and id=?2",nativeQuery=true)
    CommentEntity findByPostIdAndCommentId(long postId, long commentId);

    @Query(value="SELECT * FROM comments where post_id=?1 and user_name=?2",nativeQuery=true)
    List<CommentEntity> findByPostIdAndCommentUserName(long postId, String userName);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM comments where post_id=?1",nativeQuery = true)
    void deleteCommentsByPostId(long postId);

}
