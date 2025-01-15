package com.unu.app.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.unu.app.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Qualifier("usuarioService")
    private UsuarioService usuarioService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                String role = authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .filter(authority -> authority.startsWith("ROLE_"))
                        .map(authority -> authority.substring(5))
                        .findFirst()
                        .orElse(null);
                switch (role) {
                    case "Administrador":
                        response.sendRedirect("/Dashboard");
                        break;
                    case "Cliente":
                        response.sendRedirect("/Inicio");
                        break;
                    case "Empleado":
                        response.sendRedirect("/Dashboard");
                        break;
                }
            }
        };
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas
                        .requestMatchers("/Inicio").permitAll()
                        .requestMatchers("/Register").permitAll()
                        .requestMatchers("/Login").permitAll()
                        .requestMatchers("/Contacto").permitAll()
                        .requestMatchers("/Styles/**", "/Scripts/**", "/Images/**", "/SVG/**").permitAll()
                        // Rutas protegidas por roles
                        .requestMatchers("/Dashboard/**").hasAnyRole("Administrador", "Empleado")
                        // Resto de rutas requieren autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/Login")
                        .successHandler(customAuthenticationSuccessHandler())
                        .failureUrl("/Login?Error=true")
                )
                .exceptionHandling()
                    .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .logout(config -> config
                        .logoutUrl("/Logout")
                        .logoutSuccessUrl("/Login")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                )
                .build();
    }
}

@Component
class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendRedirect("/Inicio");
    }
}
