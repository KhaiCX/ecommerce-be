package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.RefreshToken;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.repository.RefreshTokenRepository;
import com.ecommerce.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RefreshTokenService {
    RefreshTokenRepository refreshTokenRepository;
    UserRepository userRepository;

    public RefreshToken createRefreshToken(User user, String device) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserAndDevice(user, device)
                        .orElse(new RefreshToken());
        refreshToken.setUser(user);
        refreshToken.setDevice(device);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(7 * 24 * 60 * 60));
        refreshToken.setCreatedAt(LocalDateTime.now());
        return refreshTokenRepository.save(refreshToken);
    }

    public boolean verifyExpiration(RefreshToken token) {
        return token.getExpiryDate().isBefore(LocalDateTime.now());
    }

    public RefreshToken getByTokenAndDevice(String token, String device) {
        return refreshTokenRepository.findByTokenAndDevice(token, device).orElseThrow(
                () -> new ResourceNotFoundException("Token not found: " + token + " with device; " + device)
        );
    }

    public void delete(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }
}
