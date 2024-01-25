package com.elitsoft.proyectoCuestionario_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.elitsoft.proyectoCuestionario_backend.entities.Role;
import com.elitsoft.proyectoCuestionario_backend.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/name/{name}")
    public Role getRoleByName(@PathVariable String name) {
        return roleService.getRoleByName(name);
    }

    @GetMapping("/{userId}/roles")
    public List<Role> getRolesByUserId(@PathVariable Long userId) {
        return roleService.getRolesByUserId(userId);
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT})
    public void saveOrUpdateRole(@RequestBody Role role, @RequestParam Long userId) {
        roleService.saveOrUpdateRole(role, userId);
    }


    @DeleteMapping("/{userId}/{roleId}")
    public void deleteRole(@PathVariable Long userId, @PathVariable Long roleId) {
        roleService.deleteRole(roleId, userId);
    }



}
