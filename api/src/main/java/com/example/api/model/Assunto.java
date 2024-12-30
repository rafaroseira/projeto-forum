package com.example.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "assuntos")
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 265)
    private String descricao;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Assunto(String nome, String descricao, Disciplina disciplina) {
        this.nome = nome;
        this.descricao = descricao;
        this.disciplina = disciplina;
    }

    public Assunto() {
    }

}
