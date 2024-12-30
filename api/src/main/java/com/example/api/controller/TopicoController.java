package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.PreviewTopicoDTO;
import com.example.api.dto.TopicoDTO;
import com.example.api.service.TopicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping("/topicos/assunto/{id}")
    public List<PreviewTopicoDTO> getTopicosPorAssunto(@PathVariable byte id) {
        return topicoService.listarTopicosPorAssunto(id);
    }

    @GetMapping("/topico/{id}")
    public TopicoDTO getTopico(@PathVariable int id) {
        return topicoService.getTopico(id);
    }
    
    
}
