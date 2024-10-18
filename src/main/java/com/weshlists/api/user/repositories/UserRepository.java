package com.weshlists.api.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.weshlists.api.user.models.Users;


public interface UserRepository extends JpaRepository<Users, Integer>{
    UserDetails findByEmail(String email);

    Optional<Users> findById(int userId);

     @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findUserByEmail(@Param("email") String email);


}
