package com.backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entity.Conta;
import com.backend.repository.ContaRepository;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;

    public ContaServiceImpl(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void criarConta(String titular) {

        Conta conta = new Conta();

        conta.setTitular(titular);

        // Saldo inicial
        conta.setSaldo(BigDecimal.ZERO);

        contaRepository.save(conta);
    }

    @Override
    public void deletarConta(Long id) {

        if (!contaRepository.existsById(id)) {
            throw new RuntimeException(
                    "Conta não encontrada com o ID: " + id
            );
        }

        contaRepository.deleteById(id);
    }

    @Override
    public void atualizarConta(Long id, String titular) {

        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Conta não encontrada com o ID: " + id
                ));

        conta.setTitular(titular);

        contaRepository.save(conta);
    }

    @Override
    public void atualizarSaldo(Long id, BigDecimal saldo) {

        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Conta não encontrada com o ID: " + id
                ));

        conta.setSaldo(saldo);

        contaRepository.save(conta);
    }

    @Override
    public Conta getContaById(Long id) {

        return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Conta não encontrada com o ID: " + id
                ));
    }

    @Override
    public List<Conta> getAllContas() {

        return contaRepository.findAll();
    }
}