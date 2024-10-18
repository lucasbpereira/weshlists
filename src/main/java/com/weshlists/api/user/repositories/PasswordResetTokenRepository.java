package com.weshlists.api.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import com.weshlists.api.user.models.PasswordResetToken;
import com.weshlists.api.user.models.Users;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer>{

    Optional<PasswordResetToken> findByToken(String token);



}
