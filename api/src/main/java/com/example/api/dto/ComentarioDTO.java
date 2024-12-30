package com.example.api.dto;

import java.util.List;

//depois adicionar int resposta
public record ComentarioDTO(int id, String mensagem, List<String> imagens,String usuario) {

}
