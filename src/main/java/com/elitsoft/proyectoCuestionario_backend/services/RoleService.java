package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role getRoleByName(String name);
    void saveOrUpdateRole(Role role, Long userId);
    void deleteRole(Long roleId, Long userId);
    List<Role> getRolesByUserId(Long userId);
}
