package com.example.Oauth.Server.repository;

import com.example.Oauth.Server.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String>{

    Authority findByName(String name);

}
