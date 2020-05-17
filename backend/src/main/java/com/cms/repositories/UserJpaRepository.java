package com.cms.repositories;

import com.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserJpaRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT userId FROM User WHERE username=:username AND password=:password")
    Optional<String> login(@Param("username") String username, @Param("password") String password);
    List<User> getAllByEmailIn(List<String> emails);
    User getUserByUsername(String username);

    User getUserByEmail(String email);
}
