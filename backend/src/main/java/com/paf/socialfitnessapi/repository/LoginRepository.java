package com.paf.socialfitnessapi.repository;

import com.paf.socialfitnessapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailAndPassword(String email,String password);
}
