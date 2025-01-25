package com.example.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.api.dto.PreviewTopicoDTO;
//import com.example.api.dto.TopicoDTO;
import com.example.api.model.Topico;
import com.example.api.model.Usuario;

public interface TopicoRepository extends JpaRepository<Topico,Integer> {
    
    @Query("select new com.example.api.dto.PreviewTopicoDTO(t.id, t.titulo, u.username) from Topico t join t.usuario u where t.assunto.id=:assuntoId")
    List<PreviewTopicoDTO> findAllByAssuntoId(@Param("assuntoId") byte assuntoId);

    /*@Query("select new com.example.api.dto.TopicoDTO(t.id, t.titulo, t.mensagem, t.imagem, u.username) from Topico t join t.usuario u where t.id = :id")
    TopicoDTO findById(@Param("id") int id);*/

    Topico findById(int id);

    List<Topico> findByUsuario(Usuario usuario);
}
