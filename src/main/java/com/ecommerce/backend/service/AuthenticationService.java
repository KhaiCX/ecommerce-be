package com.ecommerce.backend.service;

import com.ecommerce.backend.custom.CustomUserDetails;
import com.ecommerce.backend.entity.RefreshToken;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.exception.BadRequestException;
import com.ecommerce.backend.model.request.AuthenticationRequest;
import com.ecommerce.backend.model.request.RefreshTokenRequest;
import com.ecommerce.backend.model.response.AuthenticationResponse;
import com.ecommerce.backend.provider.JwtAuthenticationProvider;
import com.ecommerce.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.access-token-expiration}")
    private Long accessTokenExpiration;
    @Value("${jwt.refresh-token-expiration}")
    private Long refreshTokenExpiration;

    public AuthenticationResponse login(AuthenticationRequest request, String device) {
        String email = request.email();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Account does not exist with email: " + email));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }

        updateTimeLogin(user);

        String accessToken = jwtAuthenticationProvider.generateToken(user.getUserId(), user.getRoles());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user, device);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken()).build();
    }

    public AuthenticationResponse refresh(RefreshTokenRequest request, String device) {
        RefreshToken refreshToken = refreshTokenService.getByTokenAndDeviceAndRevokedFalse(request.refreshToken(), device);
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

    public void logout(String device) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        RefreshToken refreshToken = refreshTokenService.getByUserAndDeviceAndRevokedFalse(user, device);
        refreshToken.setRevoked(true);
        refreshTokenService.save(refreshToken);
    }

    private void updateTimeLogin(User user) {
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }
}
