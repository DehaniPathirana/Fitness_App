package com.paf.socialfitnessapi.repository;

import com.paf.socialfitnessapi.model.Post;
import com.paf.socialfitnessapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);

    Optional<Post> findByUserAndId(User user, Long id);
}
