package com.ecommerce.backend.handler;

import com.ecommerce.backend.constant.ERole;
import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.RoleRepository;
import com.ecommerce.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Set;

@Configuration
@Slf4j
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {

            //init data role
            Role roleAdmin = new Role();
            roleAdmin.setRole("ROLE_ADMIN");

            Role roleUser = new Role();
            roleUser.setRole("ROLE_USER");

            Role roleUserInserted = roleRepository.saveAndFlush(roleUser);
            Role roleAdminInserted = roleRepository.saveAndFlush(roleAdmin);

            //init admin account
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@gmail.com");
                admin.setUsername("admin");
                admin.setPassword(new BCryptPasswordEncoder().encode("123456"));
                admin.setRoles(Set.of(roleAdminInserted));
                userRepository.saveAndFlush(admin);

                log.info("✅ Default admin account created!");
            }

            //init user account
            if (userRepository.findByEmail("user@gmail.com").isEmpty()) {
                User user = new User();
                user.setEmail("user@gmail.com");
                user.setUsername("user");
                user.setPassword(new BCryptPasswordEncoder().encode("123456"));
                user.setRoles(Set.of(roleUserInserted));
                userRepository.saveAndFlush(user);

                log.info("✅ Default user account created!");
            }
        };
    }
}
