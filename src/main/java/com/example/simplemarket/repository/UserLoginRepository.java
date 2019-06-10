package com.example.simplemarket.repository;

import com.example.simplemarket.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
    @Query("Select a from UserLogin a where a.username = :username and a.password = :password")
    UserLogin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    @Query("Select a from UserLogin a where a.username = :username")
    UserLogin findByUsername(@Param("username") String username);

}
