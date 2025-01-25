package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.CriarComentarioDTO;
import com.example.api.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    
    @PostMapping("/auth/criar-comentario")
    public ResponseEntity<String> criarComentario(@RequestBody CriarComentarioDTO dto) {
        
        if(comentarioService.criarComentario(dto)){
            return new ResponseEntity<String>("Comentário enviado", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Não foi possível enviar o comentário", HttpStatus.BAD_REQUEST);
        }
    }
    
}
