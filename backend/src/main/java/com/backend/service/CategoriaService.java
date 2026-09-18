package com.backend.service;
import com.backend.entity.Categoria;
import java.util.List;

public interface CategoriaService {
    public void save(Categoria categoria);
    public List<Categoria> findAll();
    public Categoria findById(Long id);
    public void deleteById(Long id);
    
}
