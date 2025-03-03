package com.example.api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.CriarTopicoDTO;
import com.example.api.dto.VisualizarTopicosDTO;
import com.example.api.dto.PreviewTopicoDTO;
import com.example.api.dto.TopicoDTO;
import com.example.api.service.TopicoService;
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

    @PostMapping("/auth/criar-topico")
    public ResponseEntity<String> criarTopico(@RequestBody CriarTopicoDTO dto) {
        
        if(topicoService.criarTopico(dto)){
            return new ResponseEntity<String>("Tópico criado", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Não foi possível criar o tópico", HttpStatus.BAD_REQUEST);
        }
        
    }
    
    @GetMapping("/auth/meus-topicos")
    public List<VisualizarTopicosDTO> meusTopicos(){
        return topicoService.recuperarTopicos();
    }

    @DeleteMapping("/auth/excluir-topico/{id}")
    public ResponseEntity<String> excluirTopico(@PathVariable int id){
        if(topicoService.excluirTopico(id)){
            return new ResponseEntity<String>("Tópico excluído", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Não foi possível excluir o tópico", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/admin/todos-os-topicos")
    public List<VisualizarTopicosDTO> todosOsTopicos() {
        return topicoService.recuperarTodosOsTopicos();
    }
    
    
}
