package com.weshlists.api.wishlist.models;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weshlists.api.user.models.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    @NotNull
    @Size(max = 120)
    @Column(nullable = false, length = 120)    
    private String titleProduct;

    @Size(max = 500)
    @Column(nullable = true, length = 500)    
    private String subtitle;

    @Size(max = 1000)
    @Column(nullable = true, length = 1000)    
    private String linkShopee;

    @Size(max = 1000)
    @Column(nullable = true, length = 1000)    
    private String linkAmazon;

    @Size(max = 1000)
    @Column(nullable = true, length = 1000)    
    private String linkOutros;

    @Column(nullable = true)    
    private String thumbnail;

    @Column(nullable = true)    
    private String donor_email;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    @JsonIgnore
    private Users users;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

}
