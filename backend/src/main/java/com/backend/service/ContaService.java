package com.backend.service;

import java.math.BigDecimal;
import java.util.List;

import com.backend.entity.Conta;

public interface ContaService {
    public void criarConta(String titular);
    public void deletarConta(Long id);
    public void atualizarConta(Long id, String titular);
    public void atualizarSaldo(Long id, BigDecimal saldo);
    public Conta getContaById(Long id);
    public List<Conta> getAllContas();
}
