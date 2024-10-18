package com.weshlists.api.user.dtos;

public record ResetPasswordRequestDto(String token, String newPassword) {

}
