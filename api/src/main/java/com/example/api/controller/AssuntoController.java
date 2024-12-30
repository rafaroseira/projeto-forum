package com.example.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.api.dto.AssuntoDTO;
import com.example.api.service.AssuntoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AssuntoController {

    @Autowired
    private AssuntoService assuntoService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/assuntos")
    public List<AssuntoDTO> listarTodosOsAssuntos() {
        return assuntoService.getAssuntos();
    }
    
}
