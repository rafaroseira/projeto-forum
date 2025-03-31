package com.example.demo.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(){super("Categoria não encontrada!");}
}
