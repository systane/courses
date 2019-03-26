package com.example.Oauth.Server.repository;

import com.example.Oauth.Server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<User, String> {

}
