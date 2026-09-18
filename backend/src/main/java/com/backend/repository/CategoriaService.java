package com.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.entity.Categoria;


public interface CategoriaService extends JpaRepository<Categoria, Long> {
    
}
