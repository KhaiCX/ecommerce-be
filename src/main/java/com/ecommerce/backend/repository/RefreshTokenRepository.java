package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.RefreshToken;
import com.ecommerce.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenAndDevice(String token, String device);
    Optional<RefreshToken> findByUserAndDevice(User user, String device);
    void deleteByUser(User user);
}
