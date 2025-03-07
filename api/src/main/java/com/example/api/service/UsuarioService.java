package com.example.api.service;

import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList;
//import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.api.dto.CadastrarUsuarioDTO;
import com.example.api.dto.UsuarioDTO;
import com.example.api.model.Usuario;
//import com.example.api.repository.RoleRepository;
import com.example.api.repository.UsuarioRepository;
import com.example.api.model.EnumRole;
//import com.example.api.model.Role;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /*@Autowired
    private RoleRepository roleRepository;*/

    @Transactional
    public void cadastrarUsuario(CadastrarUsuarioDTO dto){    
        //Role roleUser = roleRepository.findByNomeRole(EnumRole.ROLE_USER);
        //List<Role> roles = new ArrayList<Role>();
        //roles.add(roleUser);
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        Usuario usuario = new Usuario(dto.username(), senhaCriptografada, dto.email(), EnumRole.ROLE_USER);
        usuario = usuarioRepository.save(usuario);
    }

    @Transactional
    public List<UsuarioDTO> recuperarTodosOsUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();

        for(Usuario usuario : usuarios){
            usuariosDTO.add(new UsuarioDTO(usuario.getId(), usuario.getUsername(), usuario.getRole().name()));
        }

        return usuariosDTO;
    }

    @Transactional
    public boolean excluirUsuario(int id){
        try{
            Usuario usuario = usuarioRepository.findById(id).get();
            usuarioRepository.delete(usuario);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean tornarAdmin(int id){

        try{
            Usuario usuario = usuarioRepository.findById(id).get();
            usuario.setRole(EnumRole.ROLE_ADMIN);
            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
