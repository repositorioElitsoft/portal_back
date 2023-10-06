package com.elitsoft.proyectoCuestionario_backend.Config;

import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    //Se hace override de métodos teniendo en cuenta  los roles de usuario
    private final Usuario appuser;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(new SimpleGrantedAuthority("ROLE_"+ appuser.getUsr_rol().toUpperCase()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return appuser.getUsr_pass();
    }

    @Override
    public String getUsername() {
        return appuser.getUsr_email();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
