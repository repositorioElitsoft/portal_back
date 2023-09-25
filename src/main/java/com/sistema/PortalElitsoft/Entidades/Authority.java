/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.PortalElitsoft.Entidades;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Elitsoft83
 */
public class Authority implements GrantedAuthority {
    
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
    
    
    
}
