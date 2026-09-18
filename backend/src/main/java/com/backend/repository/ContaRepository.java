package com.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Conta;


public interface ContaRepository extends JpaRepository<Conta, Long> {
    
}
