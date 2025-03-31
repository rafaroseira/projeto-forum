package com.example.demo.exceptions;

public class TopicNotFoundException extends RuntimeException{
    public TopicNotFoundException(){super("Tópico não encontrado!");}

    public TopicNotFoundException(String msg){super(msg);}
}
