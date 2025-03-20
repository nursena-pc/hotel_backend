package com.antalyaotel.service;

import com.antalyaotel.dto.AuthenticationRequest;
import com.antalyaotel.dto.AuthenticationResponse;
import com.antalyaotel.dto.RegisterRequest;
import com.antalyaotel.model.User;
import com.antalyaotel.repository.UserRepository;
import com.antalyaotel.config.JwtTokenProvider;
import com.antalyaotel.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    /**
     * Kullanıcı kimlik doğrulama işlemi.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtTokenProvider.generateToken(user.getEmail(), String.valueOf(user.getRole())); // ✅ Artık rolü de ekledik!
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        // 🟢 Aynı e-posta var mı kontrol edelim
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Bu e-posta adresi zaten kayıtlı!");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
        String jwt = jwtTokenProvider.generateToken(user.getEmail(), String.valueOf(user.getRole()));

        return new AuthenticationResponse(jwt);
    }
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        // 🟢 Aynı e-posta var mı kontrol edelim
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Bu e-posta adresi zaten kayıtlı!");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_ADMIN) // ✅ Artık admin olarak kaydolacak
                .build();

        userRepository.save(user);
        String jwt = jwtTokenProvider.generateToken(user.getEmail(), String.valueOf(user.getRole()));

        return new AuthenticationResponse(jwt);
    }


}