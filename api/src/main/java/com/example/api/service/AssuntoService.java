package com.example.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.dto.AssuntoDTO;
import com.example.api.repository.AssuntoRepository;

@Service
public class AssuntoService {

    @Autowired
    private AssuntoRepository assuntoRepository;

    public List<AssuntoDTO> getAssuntos(){

        return assuntoRepository.findAllAssuntos();
    }
}
