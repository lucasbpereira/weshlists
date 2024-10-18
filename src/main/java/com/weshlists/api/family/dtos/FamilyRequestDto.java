package com.weshlists.api.family.dtos;

import java.util.Date;


import jakarta.validation.constraints.NotNull;

public record FamilyRequestDto(
    @NotNull String firsName,
    @NotNull String lastName,
    @NotNull Date birth,
    Date createdAt,
    Date updatedAt
) {

}
