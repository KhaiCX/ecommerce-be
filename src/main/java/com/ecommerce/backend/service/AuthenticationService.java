package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.RefreshToken;
import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.exception.BadRequestException;
import com.ecommerce.backend.model.request.AuthenticationRequest;
import com.ecommerce.backend.model.request.RefreshTokenRequest;
import com.ecommerce.backend.model.response.AuthenticationResponse;
import com.ecommerce.backend.provider.JwtAuthenticationProvider;
import com.ecommerce.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    RefreshTokenService refreshTokenService;
    JwtAuthenticationProvider jwtAuthenticationProvider;
    BCryptPasswordEncoder passwordEncoder;

    public AuthenticationResponse login(AuthenticationRequest request, String device) {
        String email = request.email();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Account does not exist with email: " + email));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        String accessToken = jwtAuthenticationProvider.generateToken(user.getUserId(), user.getRoles());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user, device);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken()).build();
    }

    public AuthenticationResponse refresh(RefreshTokenRequest request, String device) {
        RefreshToken refreshToken = refreshTokenService.getByTokenAndDevice(request.refreshToken(), device);
        if (refreshTokenService.verifyExpiration(refreshToken)) {
            refreshTokenService.delete(refreshToken);
            throw new RuntimeException("Refresh token invalid: " + refreshToken.getToken());
        }

        User user = refreshToken.getUser();
        String accessToken = jwtAuthenticationProvider.generateToken(user.getUserId(), user.getRoles());

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken()).build();
    }
}
