package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.RefreshToken;
import com.ecommerce.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenAndDeviceAndRevokedFalse(String token, String device);
    Optional<RefreshToken> findByUserAndDeviceAndRevokedFalse(User user, String device);
    List<RefreshToken> findByUserAndRevokedFalse(User user);
    void deleteByUser(User user);
}
