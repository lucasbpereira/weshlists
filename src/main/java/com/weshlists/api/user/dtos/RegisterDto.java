package com.weshlists.api.user.dtos;

import com.weshlists.api.family.dtos.FamilyRequestDto;
import com.weshlists.api.family.models.Family;
import com.weshlists.api.user.enums.UserRole;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record RegisterDto(
    @NotNull String email,
    @NotNull String password, 
    @NotNull UserRole role,
    @NotNull String firstName,
    @NotNull String lastName,
    @NotNull Date birth,
    List<FamilyRequestDto> family
    ) {
    
}
