package com.elitsoft.proyectoCuestionario_backend.Config;


import com.elitsoft.proyectoCuestionario_backend.entidades.User;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User appUser = appUserRepository
                .findByUsrEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("No existe el usuario"));

        System.out.println("____");
        System.out.println("The retrieved username is: ");
        System.out.println(appUser.getUsr_email());
        System.out.println(appUser.getUsr_pass());
        System.out.println("____");
        return new CustomUserDetails(appUser);
    }
}