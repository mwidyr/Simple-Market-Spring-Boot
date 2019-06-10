package com.example.simplemarket.repository;

import com.example.simplemarket.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
    @Query("Select a from UserDetail a where a.username = :username and a.password = :password")
    UserDetail findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    @Query("Select a from UserDetail a where a.username = :username")
    UserDetail findByUsername(@Param("username") String username);
    @Query("Select a from UserDetail a where a.email = :email")
    UserDetail findByEmail(@Param("email") String email);
    @Query("Select a from UserDetail a where a.mobileNumber = :mobileNumber")
    UserDetail findByMobileNumber(@Param("mobileNumber") Long mobileNumber);
}
