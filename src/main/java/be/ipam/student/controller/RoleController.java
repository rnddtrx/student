package be.ipam.student.controller;

import be.ipam.student.model.RoleEntity;
import be.ipam.student.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // Create a new role
    @PostMapping
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity role) {
        RoleEntity savedRole = roleService.saveOrUpdateRole(role);
        return ResponseEntity.ok(savedRole);
    }

    // Get all roles
    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<RoleEntity> getAllRoles() {
        return roleService.getAllRoles();
    }


    // Get a single role by id
    @Secured("ROLE_VISITOR")
    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing role
    @PutMapping("/{id}")
    public ResponseEntity<RoleEntity> updateRole(@PathVariable Long id, @RequestBody RoleEntity role) {
        return roleService.getRoleById(id)
                .map(existingRole -> {
                    role.setRoleId(existingRole.getRoleId());
                    return ResponseEntity.ok(roleService.saveOrUpdateRole(role));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
