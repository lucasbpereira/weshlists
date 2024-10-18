package com.weshlists.api.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
 
    public void sendPasswordResetEmail(String toEmail, String token) {
        // Criar a mensagem de email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@yourdomain.com"); // Remetente
        message.setTo(toEmail);                    // Destinatário
        message.setSubject("Redefinição de senha"); // Assunto

        // Corpo do email com o link de redefinição de senha
        String resetUrl = "http://localhost:8080/api/v1/auth/reset-password?token=" + token;
        message.setText("Você solicitou a redefinição de sua senha. Use o link a seguir para redefinir sua senha:\n" + resetUrl);

        // Enviar o email
        mailSender.send(message);
    }
}
