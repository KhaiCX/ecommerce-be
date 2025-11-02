package com.ecommerce.backend.service;

import com.ecommerce.backend.constant.EProvider;
import com.ecommerce.backend.constant.ERole;
import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.request.AuthenticationRequest;
import com.ecommerce.backend.model.request.UserRequest;
import com.ecommerce.backend.provider.JwtAuthenticationProvider;
import com.ecommerce.backend.repository.RoleRepository;
import com.ecommerce.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    BCryptPasswordEncoder passwordEncoder;
    JwtAuthenticationProvider jwtAuthenticationProvider;

    public User register(AuthenticationRequest request) {
        String roleNameUser = ERole.ROLE_USER.name();
        Role roleUser = roleRepository.findByRole(roleNameUser)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + roleNameUser));

        User user = User.builder().
                email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .provider(EProvider.LOCAL)
                .roles(Set.of(roleUser))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    public Page<User> getAll(int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return userRepository.findAll(pageable);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by id: " + id));
    }

    public User update(UUID id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by id: " + id));
        if (Objects.nonNull(request.email())) {
            user.setEmail(request.email());
        }
        if (Objects.nonNull(request.username())) {
            user.setUsername(request.username());
        }
        if (Objects.nonNull(request.phone())) {
            user.setPhone(request.phone());
        }
        return userRepository.save(user);

    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public User addAdminUser(AuthenticationRequest request) {
        String roleNameAdmin = ERole.ROLE_ADMIN.name();
        Role roleAdmin = roleRepository.findByRole(roleNameAdmin)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + roleNameAdmin));

        User user = User.builder().
                email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .provider(EProvider.LOCAL)
                .roles(Set.of(roleAdmin))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }


}
