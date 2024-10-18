package com.weshlists.api.user.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private Users user;

    private String token;

    private LocalDateTime expirationDate;

    public PasswordResetToken(Users user, String token) {
        this.user = user;
        this.token = token;
        this.expirationDate = LocalDateTime.now().plusHours(24); // Token expira em 24h
    }

    // Getters e Setters
}
