package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.api.dto.CriarComentarioDTO;
import com.example.api.model.Comentario;
import com.example.api.model.Topico;
import com.example.api.model.Usuario;
import com.example.api.repository.ComentarioRepository;
import com.example.api.repository.TopicoRepository;
import com.example.api.repository.UsuarioRepository;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public boolean criarComentario(CriarComentarioDTO dto){

        try{
            Topico topico = topicoRepository.findById(dto.idTopico());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepository.findByUsername(auth.getName());

            Comentario comentario = new Comentario(dto.mensagem(), dto.imagem(), usuario, topico);
            comentarioRepository.save(comentario);

            return true;
        } catch (Exception e){
            return false;
        }
    }
}
