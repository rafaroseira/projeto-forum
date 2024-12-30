package com.example.api.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 2000)
    private String mensagem;

    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL)
    private List<ImagemComentario> imagens;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topico")
    private Topico topico;

    /*ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "resposta")
    private Comentario resposta;*/

    /*@OneToMany(mappedBy = "resposta") //em dúvida se vai para o construtor
    private List<Comentario> respostas;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public List<ImagemComentario> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemComentario> imagens) {
        this.imagens = imagens;
    }

    /*public Comentario getResposta() {
        return resposta;
    }

    public void setResposta(Comentario resposta) {
        this.resposta = resposta;
    }

    public List<Comentario> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Comentario> respostas) {
        this.respostas = respostas;
    }*/

    public Comentario(String mensagem, List<ImagemComentario> imagens,Usuario usuario, Topico topico) {
        this.mensagem = mensagem;
        this.imagens = imagens;
        this.usuario = usuario;
        this.topico = topico;
        //this.resposta = resposta;
        //this.respostas = respostas;
    }

    public Comentario() {
    }

}
