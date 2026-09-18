package com.backend.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.Conta;
import com.backend.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<Void> criarConta(
            @RequestParam String titular) {

        contaService.criarConta(titular);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(
            @PathVariable Long id) {

        contaService.deletarConta(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarConta(
            @PathVariable Long id,
            @RequestParam String titular) {

        contaService.atualizarConta(id, titular);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/saldo")
    public ResponseEntity<Void> atualizarSaldo(
            @PathVariable Long id,
            @RequestParam BigDecimal saldo) {

        contaService.atualizarSaldo(id, saldo);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getContaById(
            @PathVariable Long id) {

        Conta conta = contaService.getContaById(id);

        return ResponseEntity.ok(conta);
    }

    @GetMapping
    public ResponseEntity<List<Conta>> getAllContas() {

        List<Conta> contas = contaService.getAllContas();

        return ResponseEntity.ok(contas);
    }
}