package com.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Lancamento;



public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    
}
