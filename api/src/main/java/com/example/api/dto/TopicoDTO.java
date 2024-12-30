package com.example.api.dto;

import java.util.List;

public record TopicoDTO(int id, String titulo, String mensagem, List<String> imagens, String usuario, 
    List<ComentarioDTO> comentarios) {

}
