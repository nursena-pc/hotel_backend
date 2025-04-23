package com.antalyaotel.controller;

import com.antalyaotel.dto.AuthenticationRequest;
import com.antalyaotel.dto.AuthenticationResponse;
import com.antalyaotel.dto.RegisterRequest;
import com.antalyaotel.model.User;
import com.antalyaotel.model.Role;
import com.antalyaotel.repository.UserRepository;
import com.antalyaotel.service.AuthenticationService;
import com.antalyaotel.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/register-admin")
    @PreAuthorize("hasRole('ADMIN')") // ❗ Sadece ADMIN'ler çağırabilir
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest request, @RequestHeader("Admin-Key") String adminKey) {
        if (!adminKey.equals("SECRET_ADMIN_KEY")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Yetkisiz erişim!");
        }
        AuthenticationResponse response = authenticationService.registerAdmin(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getEmail().equalsIgnoreCase("admin@gmail.com") ? Role.ROLE_ADMIN : Role.ROLE_USER)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        System.out.println("Kullanıcı kaydedildi: " + user.getEmail());

        // 🔥 201 Created döndür
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // ✅ Yeni eklenen test endpoint
    @GetMapping("/test")
    public ResponseEntity<?> testAuthentication(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No authentication found");
        }
        return ResponseEntity.ok("User: " + authentication.getName() + ", Roles: " + authentication.getAuthorities());
    }
}