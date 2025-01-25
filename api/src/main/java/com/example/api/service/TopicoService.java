package com.example.api.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.api.dto.ComentarioDTO;
import com.example.api.dto.CriarTopicoDTO;
import com.example.api.dto.VisualizarTopicosDTO;
import com.example.api.dto.PreviewTopicoDTO;
import com.example.api.dto.TopicoDTO;
import com.example.api.model.Assunto;
import com.example.api.model.Comentario;
import com.example.api.model.Topico;
import com.example.api.model.Usuario;
import com.example.api.repository.TopicoRepository;
import com.example.api.repository.AssuntoRepository;
import com.example.api.repository.UsuarioRepository;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PreviewTopicoDTO> listarTopicosPorAssunto(byte idAssunto){
        return topicoRepository.findAllByAssuntoId(idAssunto);
    }

    public TopicoDTO getTopico(int id){
        Topico topico = topicoRepository.findById(id);
        List<ComentarioDTO> comentarios = new ArrayList<ComentarioDTO>();

        for(Comentario comentario : topico.getComentarios()){
            
            ComentarioDTO comentarioDTO = new ComentarioDTO(
                comentario.getId(), 
                comentario.getMensagem(),
                comentario.getImagem(), 
                comentario.getUsuario().getUsername()
                //verificarRespostas(comentario)
            );

            comentarios.add(comentarioDTO);
        }

        return new TopicoDTO(
            topico.getId(), 
            topico.getTitulo(), 
            topico.getMensagem(),
            topico.getImagem(),
            topico.getUsuario().getUsername(), 
            comentarios
        );
    }

    @Transactional
    public boolean criarTopico(CriarTopicoDTO dto){

        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Usuario usuario = usuarioRepository.findByUsername(auth.getName());
            Assunto assunto = assuntoRepository.findById(Byte.parseByte(dto.idAssunto())).get();
            Topico topico = new Topico(
                dto.titulo(),
                dto.mensagem(),
                dto.imagem(),
                assunto,
                usuario,
                null
            );

            topicoRepository.save(topico);
            return true;
            
        } catch (Exception e){
            return false;
        }
    }

    @Transactional
    public List<VisualizarTopicosDTO> recuperarTopicos(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findByUsername(auth.getName());
        List<Topico> topicos = topicoRepository.findByUsuario(usuario);
        List<VisualizarTopicosDTO> meusTopicos = new ArrayList<VisualizarTopicosDTO>();

        for(Topico topico : topicos){
            meusTopicos.add(new VisualizarTopicosDTO(topico.getId(), topico.getTitulo(), topico.getAssunto().getNome(), auth.getName()));
        }

        return meusTopicos;
    }

    @Transactional
    public boolean excluirTopico(int id){
        try{
            Topico topico = topicoRepository.findById(id);
            topicoRepository.delete(topico);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /*int verificarRespostas(Comentario comentario){
        if(comentario.getResposta()==null)
            return 0;
        else
            return comentario.getResposta().getId();
    }*/
}
