package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.model.request.AuthenticationRequest;
import com.ecommerce.backend.model.request.RefreshTokenRequest;
import com.ecommerce.backend.model.response.AuthenticationResponse;
import com.ecommerce.backend.service.AuthenticationService;
import com.ecommerce.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    UserService userService;
    AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Validated AuthenticationRequest request) {
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Validated AuthenticationRequest request
    , HttpServletRequest httpServletRequest) {
        String device = httpServletRequest.getHeader("User-Agent");
        return ResponseEntity.ok().body(authenticationService.login(request, device));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody @Validated RefreshTokenRequest request
    , HttpServletRequest httpServletRequest) {
        String device = httpServletRequest.getHeader("User-Agent");
        return ResponseEntity.ok(authenticationService.refresh(request, device));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest httpServletRequest) {
        String device = httpServletRequest.getHeader("User-Agent");
        authenticationService.logout(device);
        return ResponseEntity.ok().build();
    }
}
