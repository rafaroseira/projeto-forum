package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Byte>{

    @Query("select new com.example.demo.dto.CategoryDTO(c.id,c.name,c.description,s.name) from Category c join c.subject s where c.subject.id = s.id")
    List<CategoryDTO> findAllCategories();

    Category findById(byte id);
}
