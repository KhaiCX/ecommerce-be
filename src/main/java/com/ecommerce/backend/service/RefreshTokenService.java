package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.RefreshToken;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.repository.RefreshTokenRepository;
import com.ecommerce.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Value("${jwt.refresh-token-expiration}")
    private Long refreshTokenExpiration;

    public RefreshToken createRefreshToken(User user, String device) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserAndDeviceAndRevokedFalse(user, device)
                        .orElse(new RefreshToken());
        refreshToken.setUser(user);
        refreshToken.setDevice(device);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenExpiration));
        refreshToken.setCreatedAt(LocalDateTime.now());
        return refreshTokenRepository.save(refreshToken);
    }

    public boolean verifyExpiration(RefreshToken token) {
        return token.getExpiryDate().isBefore(LocalDateTime.now());
    }

    public RefreshToken getByTokenAndDeviceAndRevokedFalse(String token, String device) {
        return refreshTokenRepository.findByTokenAndDeviceAndRevokedFalse(token, device).orElseThrow(
                () -> new ResourceNotFoundException("Token not found: " + token + " with device: " + device)
        );
    }

    public RefreshToken getByUserAndDeviceAndRevokedFalse(User user, String device) {
        return refreshTokenRepository.findByUserAndDeviceAndRevokedFalse(user, device)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh Token not found with User: " + user.getEmail() + " and device: " + device));
    }

    public void save(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    public void delete(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }
}
