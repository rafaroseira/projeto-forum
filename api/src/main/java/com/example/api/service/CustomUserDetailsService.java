package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.api.repository.UsuarioRepository;

public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserDetails usuario = usuarioRepository.findByUsername(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return usuario;
    }
}
