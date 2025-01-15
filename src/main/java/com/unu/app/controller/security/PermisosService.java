package com.unu.app.controller.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.unu.app.entity.Usuario;
import com.unu.app.service.UsuarioService;

import org.springframework.ui.Model;

@Service
@Qualifier("configPermisos")
public class PermisosService {

    @Autowired
    private UsuarioService usuarioService;

    public void configurarPermisos(Model model) {
        model.addAttribute("isUserLogin", LoginStatusListener.isUserLogin);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String roles = userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(", "));
                
                model.addAttribute("rol", roles.replace("ROLE_", ""));
                Usuario user = usuarioService.getUserByUsername(userDetails.getUsername());
               
               model.addAttribute("username", user.getUsuario());
                
                if(roles.replace("ROLE_", "").equals("Administrador")) {
                	model.addAttribute("isUserAdmin", true);
                }else {
                	model.addAttribute("isUserAdmin", false);
                }
                
            }
        }
    }
}
