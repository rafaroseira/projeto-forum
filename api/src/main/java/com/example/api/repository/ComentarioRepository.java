package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario,Integer>{

}
