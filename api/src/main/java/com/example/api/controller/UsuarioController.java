package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.CadastrarUsuarioDTO;
import com.example.api.dto.UsuarioDTO;
import com.example.api.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody CadastrarUsuarioDTO dto) {
        try {
            usuarioService.cadastrarUsuario(dto);
            return new ResponseEntity<String>("Usuário Cadastrado!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Não foi possível cadastrar o usuário", HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping("/admin/todos-os-usuarios")
    public List<UsuarioDTO> todosOsUsuarios() {
        return usuarioService.recuperarTodosOsUsuarios();
    }

    @PostMapping("/admin/tornar-admin/{id}")
    public ResponseEntity<String> tornarAdmin(@PathVariable int id){
        if(usuarioService.tornarAdmin(id)){
            return new ResponseEntity<String>("Usuário tornou-se administrador", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Não foi possível tornar o usuário um administrador", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/admin/excluir-usuario/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable int id){
        if(usuarioService.excluirUsuario(id)){
            return new ResponseEntity<String>("Usuário excluído", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Não foi possível excluir o usuário", HttpStatus.BAD_REQUEST);
        }
    }
    
}
