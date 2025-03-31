package com.example.demo.controller;

import java.util.List;
import com.example.demo.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.CategoryDTO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Tag(name = "category", description = "controlador usado para buscar categorias (assuntos)")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    @Operation(summary = "retorna todas as categorias de assuntos das disciplinas de Matemática, Física e Química")
    @ApiResponse(responseCode = "200")
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories();
    }
    
}
