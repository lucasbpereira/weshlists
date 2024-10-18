package com.weshlists.api.user.dtos;

import com.weshlists.api.user.enums.UserRole;

public record LoginResponseDto(String token, int id, String firstName, String lastName, String email, UserRole role) {
    
}
