package com.unir.Usuarios.service;

import com.unir.Usuarios.data.UsuariosRepository;
import com.unir.Usuarios.model.db.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UsuariosRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String correo){
        Usuario usuario = userRepository.getByCorreo(correo);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(usuario.getCorreo(), usuario.getContrasena(), authorities);
    }
}
