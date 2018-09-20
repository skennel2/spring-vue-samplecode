package org.almansa.app.repository;

import java.util.List;

import org.almansa.app.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	List<Comment> findByPostId(Long postId);
}
