package com.example.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.api.dto.AssuntoDTO;
import com.example.api.model.Assunto;

public interface AssuntoRepository extends JpaRepository<Assunto,Byte> {

    @Query("select new com.example.api.dto.AssuntoDTO(a.id,a.nome,a.descricao,d.nome) from Assunto a join a.disciplina d where a.disciplina.id = d.id")
    List<AssuntoDTO> findAllAssuntos();
}
