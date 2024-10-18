package com.weshlists.api.auth.services;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.weshlists.api.exceptions.BadCredentialsLoginException;
import com.weshlists.api.exceptions.EmailAlreadyInUseException;
import com.weshlists.api.family.models.Family;
import com.weshlists.api.security.TokenService;
import com.weshlists.api.user.dtos.AutheticationDto;
import com.weshlists.api.user.dtos.LoginResponseDto;
import com.weshlists.api.user.dtos.RegisterDto;
import com.weshlists.api.user.enums.UserRole;
import com.weshlists.api.user.models.PasswordResetToken;
import com.weshlists.api.user.models.Users;
import com.weshlists.api.user.repositories.PasswordResetTokenRepository;
import com.weshlists.api.user.repositories.UserRepository;
import com.weshlists.api.user.services.EmailService;

import jakarta.validation.Valid;

@Service
public class AuthorizationService implements UserDetailsService{
    @Autowired
    private ApplicationContext context;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    private AuthenticationManager authenticationManager;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    } 

    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AutheticationDto data){
        authenticationManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var user = (Users) auth.getPrincipal();
            var token = tokenService.generateToken((Users) auth.getPrincipal()); 
            return ResponseEntity.ok(new LoginResponseDto(token, user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserRole()));
        } catch (Exception e) {
            throw new BadCredentialsLoginException("Credenciais incorretas");
        }
    }


    public ResponseEntity<LoginResponseDto> register(@RequestBody RegisterDto registerDto){
        if (this.userRepository.findByEmail(registerDto.email()) != null ) {
            throw new EmailAlreadyInUseException("O email já está em uso.");
        };
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

        UserRole role = UserRole.ADMIN;

        if(registerDto.role() != null) {
            role = registerDto.role();
        } else {
            role = UserRole.GUEST;
        }

        Users newUser = new Users(registerDto.email(), encryptedPassword, role, registerDto.birth(), registerDto.firstName(), registerDto.lastName());
        newUser.setCreatedAt(new Date(System.currentTimeMillis()));

        List<Family> family = registerDto.family().stream().map(f -> {
            Family familyMember = new Family();
            familyMember.setFirstName(f.firsName());
            familyMember.setLastName(f.lastName());
            familyMember.setBirth(f.birth());
            familyMember.setUsers(newUser);
            familyMember.setCreatedAt(new Date(System.currentTimeMillis()));
            return familyMember;
        }).collect(Collectors.toList());

        newUser.setFamily(family); // Associa a família ao usuário

        Users usuarioGravado = this.userRepository.save(newUser);

        return login(new AutheticationDto(registerDto.email(), registerDto.password()));
    }

    public void forgotPassword(String email) {
        Users user = this.userRepository.findUserByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com esse email"));;
        
        String token = tokenService.createPasswordResetToken(user);

        emailService.sendPasswordResetEmail(user.getEmail(), token);
        
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Token inválido ou expirado"));

        Users user = resetToken.getUser();

        userRepository.save(user);
    }

    
}

