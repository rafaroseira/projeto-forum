package com.example.demo.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){super("Usuário não encontrado!");}
}
