
package com.elitsoft.proyectoCuestionario_backend.Config;


import com.elitsoft.proyectoCuestionario_backend.Config.JWT.JwtAuthenticationFilter;
import com.elitsoft.proyectoCuestionario_backend.Config.JWT.JwtAuthorizationFilter;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig {


    private final UserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final static String ADMIN = "ROLE_ADMIN";
    private final static String GUEST = "ROLE_GUEST";
    private final static String REC = "ROLE_REC";


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                      AuthenticationManager authManager) throws Exception{
        //Preparación de filtros y de URL objetivo
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        http.
                csrf().disable()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests()
                //Permitir solamente métodos GETs autorizados con el rol de ADMIN
                //.requestMatchers(HttpMethod.GET,"/**").hasAuthority(ADMIN)
                .requestMatchers(new AntPathRequestMatcher("/usuarios/","POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/usuarios/verificar/**","GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/usuarios/verificar/**","POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/usuarios/pedir-restauracion-pass","POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**","PUT")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/cargoselitsoft/**","GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/paises/**","GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**","DELETE")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**","DELETE")).hasAnyAuthority(ADMIN,GUEST)
             //   .requestMatchers(new AntPathRequestMatcher("/**","GET")).hasAnyAuthority(ADMIN,GUEST)
                .requestMatchers(new AntPathRequestMatcher("/**","GET")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**","POST")).hasAnyAuthority(ADMIN,GUEST)
                //.requestMatchers(new AntPathRequestMatcher("/usuarios/","PUT")).hasAnyAuthority(ADMIN,GUEST)

                //.requestMatchers(new AntPathRequestMatcher("/laboral/**","DELETE")).hasAnyAuthority(ADMIN,GUEST)
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    protected AuthenticationManager authManager(HttpSecurity http) throws Exception{
        //Definición de authentication manager con UserDetails personalizado
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        //Se configura Cors para no permitir que ninguna IP fuera de la aplicación se conecte a la API
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("http://localhost:4200");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}