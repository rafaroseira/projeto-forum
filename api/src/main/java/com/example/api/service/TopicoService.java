package com.example.api.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.api.dto.ComentarioDTO;
import com.example.api.dto.PreviewTopicoDTO;
import com.example.api.dto.TopicoDTO;
import com.example.api.model.Comentario;
import com.example.api.model.ImagemComentario;
import com.example.api.model.ImagemTopico;
import com.example.api.model.Topico;
import com.example.api.repository.TopicoRepository;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public List<PreviewTopicoDTO> listarTopicosPorAssunto(byte idAssunto){
        return topicoRepository.findAllByAssuntoId(idAssunto);
    }

    public TopicoDTO getTopico(int id){
        Topico topico = topicoRepository.findById(id);
        List<ComentarioDTO> comentarios = new ArrayList<ComentarioDTO>();
        List<String> imagensTopico = new ArrayList<String>();
        

        for(ImagemTopico imagem : topico.getImagens()){
            imagensTopico.add(imagem.getLink());
        }

        for(Comentario comentario : topico.getComentarios()){
            List<String> imagensComentario = new ArrayList<String>();
            for(ImagemComentario imagem : comentario.getImagens()){
                
                imagensComentario.add(imagem.getLink());
            }
            
            ComentarioDTO comentarioDTO = new ComentarioDTO(
                comentario.getId(), 
                comentario.getMensagem(),
                imagensComentario, 
                comentario.getUsuario().getUsername()
                //verificarRespostas(comentario)
            );

            comentarios.add(comentarioDTO);
        }

        return new TopicoDTO(
            topico.getId(), 
            topico.getTitulo(), 
            topico.getMensagem(),
            imagensTopico,
            topico.getUsuario().getUsername(), 
            comentarios
        );
    }

    /*int verificarRespostas(Comentario comentario){
        if(comentario.getResposta()==null)
            return 0;
        else
            return comentario.getResposta().getId();
    }*/
}
