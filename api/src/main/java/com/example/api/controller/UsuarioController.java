package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.CadastrarUsuarioDTO;
import com.example.api.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/admin/hello-admin")
    public String getMethodName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "hello, " + auth.getName() + " " + auth.getAuthorities().toString();
    }

    @GetMapping("/auth/hello-all")
    public String helloAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "hello, all! You are " + auth.getName() + " " + auth.getAuthorities().toString();
    }
    
}
