package io.mazy.postsservicesalimapp.repository;

import io.mazy.postsservicesalimapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAppUserId(Long appUserId);
}
