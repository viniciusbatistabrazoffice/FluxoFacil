package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entity.Lancamento;
import com.backend.repository.LancamentoRepository;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoServiceImpl(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @Override
    public void save(Lancamento lancamento) {

        lancamentoRepository.save(lancamento);
    }

    @Override
    public List<Lancamento> findAll() {

        return lancamentoRepository.findAll();
    }

    @Override
    public Lancamento findById(Long id) {

        return lancamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Lançamento não encontrado com o ID: " + id
                ));
    }

    @Override
    public void deleteById(Long id) {

        if (!lancamentoRepository.existsById(id)) {
            throw new RuntimeException(
                    "Lançamento não encontrado com o ID: " + id
            );
        }

        lancamentoRepository.deleteById(id);
    }
}