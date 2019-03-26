package com.example.Oauth.Server.repository;

import com.example.Oauth.Server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE LOWER(u.username) =  LOWER(:username)")
    Optional<User> findByUsername(@Param("username") String username);
}
