package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;
import com.elitsoft.proyectoCuestionario_backend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elitsoft.proyectoCuestionario_backend.entities.Role;
import com.elitsoft.proyectoCuestionario_backend.repositories.RoleRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }

    @Override
    public void saveOrUpdateRole(Role role, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return;
        }
        userOptional.get().getRoles().add(role);
        userRepository.save(userOptional.get());
    }

    @Override
    public void deleteRole(Long roleId, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Role> roles = user.getRoles();

            // Busca el rol con el roleId proporcionado en la lista de roles del usuario
            for (Role role : roles) {
                if (role.getId().equals(roleId)) {
                    roles.remove(role);
                    userRepository.save(user);
                    return; // Termina el método después de eliminar el rol
                }
            }
        }
    }



    @Override
    public List<Role> getRolesByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getRoles();
        } else {
            return Collections.emptyList();
        }
    }
}
