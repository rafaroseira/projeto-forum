package com.example.api.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.dto.CadastrarUsuarioDTO;
import com.example.api.model.Usuario;
import com.example.api.repository.RoleRepository;
import com.example.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import com.example.api.model.EnumRole;
import com.example.api.model.Role;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void cadastrarUsuario(CadastrarUsuarioDTO dto){    
        Role roleUser = roleRepository.findByNomeRole(EnumRole.USER);
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleUser);
        Usuario usuario = new Usuario(dto.username(), dto.senha(), dto.email(), roles);
        usuario = usuarioRepository.save(usuario);
    }
}
