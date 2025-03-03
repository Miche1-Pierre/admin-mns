package com.mns.admin.controller;

import com.mns.admin.dto.AuthRequest;
import com.mns.admin.model.Utilisateur;
import com.mns.admin.security.JwtUtil;
import com.mns.admin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://admin-mns")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/validate")
    public boolean validateToken(@RequestBody AuthRequest authRequest) {
        return jwtUtil.validateToken(authRequest.getToken());
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        String jwt = userService.verifyEmail(token);
        return ResponseEntity.ok("Email vérifié avec succès !");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            String token = userService.authenticateUser(authRequest);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Veuillez vérifier votre email.")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Un email de vérification a été envoyé. Veuillez vérifier votre email.");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
