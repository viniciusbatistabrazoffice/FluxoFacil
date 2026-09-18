package com.backend.service;

import java.util.List;

import com.backend.entity.Lancamento;

public interface LancamentoService {

    void save(Lancamento lancamento);

    List<Lancamento> findAll();

    Lancamento findById(Long id);

    void deleteById(Long id);
}