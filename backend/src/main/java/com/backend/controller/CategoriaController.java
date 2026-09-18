package com.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.Categoria;
import com.backend.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody Categoria categoria) {

        categoriaService.save(categoria);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {

        List<Categoria> categorias = categoriaService.findAll();

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(
            @PathVariable Long id) {

        Categoria categoria = categoriaService.findById(id);

        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {

        Categoria categoriaExistente = categoriaService.findById(id);

        categoriaExistente.setNome(categoria.getNome());

        categoriaService.save(categoriaExistente);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id) {

        categoriaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}