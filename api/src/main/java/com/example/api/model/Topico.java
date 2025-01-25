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
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 45)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String mensagem;

    @Column(nullable = true, length = 100)
    private String imagem;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY) //trocado o cascade para {}
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_assunto")
    private Assunto assunto;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Topico(String titulo, String mensagem, String imagem, Assunto assunto, 
            Usuario usuario, List<Comentario> comentarios) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.imagem = imagem;
        this.usuario = usuario;
        this.assunto = assunto;
        this.comentarios = comentarios;
    }

    public Topico() {
    }

}
