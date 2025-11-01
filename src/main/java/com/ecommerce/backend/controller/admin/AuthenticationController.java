package com.ecommerce.backend.controller.admin;

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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> register(@RequestBody @Validated AuthenticationRequest request) {
        return ResponseEntity.ok().body(userService.register(request));
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

}
