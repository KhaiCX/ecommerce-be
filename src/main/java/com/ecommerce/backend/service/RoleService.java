package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.request.RoleRequest;
import com.ecommerce.backend.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role get(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
    }

    public Role add(RoleRequest request) {
        Role role = new Role();
        role.setRole(request.role());
        return roleRepository.save(role);
    }

    public Role update(Integer id, RoleRequest request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));

        role.setRole(request.role());
        return roleRepository.save(role);
    }

    public void delete(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));

        roleRepository.delete(role);
    }
}
