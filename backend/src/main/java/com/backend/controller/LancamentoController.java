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

import com.backend.entity.Lancamento;
import com.backend.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(
            LancamentoService lancamentoService) {

        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody Lancamento lancamento) {

        lancamentoService.save(lancamento);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Lancamento>> findAll() {

        return ResponseEntity.ok(
                lancamentoService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                lancamentoService.findById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody Lancamento lancamento) {

        Lancamento existente =
                lancamentoService.findById(id);

        existente.setDescricao(lancamento.getDescricao());
        existente.setValor(lancamento.getValor());
        existente.setData(lancamento.getData());
        existente.setTipo(lancamento.getTipo());
        existente.setCategoria(lancamento.getCategoria());
        existente.setConta(lancamento.getConta());

        lancamentoService.save(existente);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id) {

        lancamentoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}