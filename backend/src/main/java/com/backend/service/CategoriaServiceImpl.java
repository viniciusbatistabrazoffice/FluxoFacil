package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entity.Categoria;
import com.backend.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Categoria não encontrada com o ID: " + id
                ));
    }

    @Override
    public void deleteById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException(
                    "Categoria não encontrada com o ID: " + id
            );
        }

        categoriaRepository.deleteById(id);
    }
}