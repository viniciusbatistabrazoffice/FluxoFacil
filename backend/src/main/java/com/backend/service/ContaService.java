package com.backend.service;

import java.math.BigDecimal;
import java.util.List;

import com.backend.entity.Conta;

public interface ContaService {

    void criarConta(String titular);

    void deletarConta(Long id);

    void atualizarConta(Long id, String titular);

    void atualizarSaldo(Long id, BigDecimal saldo);

    Conta getContaById(Long id);

    List<Conta> getAllContas();
}