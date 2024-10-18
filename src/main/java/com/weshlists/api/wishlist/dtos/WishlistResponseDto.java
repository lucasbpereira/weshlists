package com.weshlists.api.wishlist.dtos;

import java.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistResponseDto {
        private int id;
        private String name;
        private String imageBase64;
    
        public WishlistResponseDto(int id, String name, byte[] imageBytes) {
            this.id = id;
            this.name = name;
            this.imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        }
    
        // Getters e Setters
    
}
