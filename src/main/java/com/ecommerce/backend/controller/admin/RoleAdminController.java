package com.ecommerce.backend.controller.admin;

import com.ecommerce.backend.entity.Role;
import com.ecommerce.backend.model.request.RoleRequest;
import com.ecommerce.backend.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/admin/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleAdminController {
    RoleService roleService;
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Integer id) {
        return ResponseEntity.ok().body(roleService.get(id));
    }

    @PostMapping
    public ResponseEntity<Role> addRole(
            @RequestBody @Validated RoleRequest request) {
        return ResponseEntity.ok(roleService.add(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer id,
            @RequestBody @Validated RoleRequest request) {
        return ResponseEntity.ok(roleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }
}
