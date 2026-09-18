package com.backend.service;

import com.backend.entity.Lancamento;
import java.util.List;

public interface LancamentoService {
    public void save(Lancamento lancamento);
    public List<Lancamento> findAll();
    public Lancamento findById(Long id);
    public void deleteById(Long id);
}
