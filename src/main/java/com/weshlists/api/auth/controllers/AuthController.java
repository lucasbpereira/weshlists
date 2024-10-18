package com.weshlists.api.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weshlists.api.auth.services.AuthorizationService;
import com.weshlists.api.user.dtos.AutheticationDto;
import com.weshlists.api.user.dtos.ForgotPasswordRequestDto;
import com.weshlists.api.user.dtos.LoginResponseDto;
import com.weshlists.api.user.dtos.RegisterDto;
import com.weshlists.api.user.dtos.ResetPasswordRequestDto;
import com.weshlists.api.user.models.Users;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {
   
    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AutheticationDto authetinticationDto){
        System.out.println("teste");
        return authorizationService.login(authetinticationDto);
    }


    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register (@RequestBody RegisterDto registerDto){
        return authorizationService.register(registerDto);
    }

    @PostMapping("/forgot")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequestDto request) {
        authorizationService.forgotPassword(request.email());
        return ResponseEntity.ok("E-mail de redefinição de senha enviado.");
    }

    @PostMapping("/reset-password")
public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDto request) {
    authorizationService.resetPassword(request.token(), request.newPassword());
    return ResponseEntity.ok("Senha redefinida com sucesso.");
}

}
