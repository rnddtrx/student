package be.ipam.student.service;

import be.ipam.student.model.RoleEntity;
import be.ipam.student.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    // Method to add or update a role
    public RoleEntity saveOrUpdateRole(RoleEntity role) {
        return roleRepository.save(role);
    }

    // Method to fetch all roles
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    // Method to fetch a single role by ID
    public Optional<RoleEntity> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    // Method to delete a role by ID
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
