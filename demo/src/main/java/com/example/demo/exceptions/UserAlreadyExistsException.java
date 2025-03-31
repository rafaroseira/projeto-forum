package com.example.demo.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){super("Usuário já existe!");}
}
