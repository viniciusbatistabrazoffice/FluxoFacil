package com.backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titular;

    @Column(
        nullable = false,
        precision = 15,
        scale = 2
    )
    private BigDecimal saldo;

    // Construtor padrão exigido pelo JPA
    public Conta() {
        this.saldo = BigDecimal.ZERO;
    }

    // Construtor
    public Conta(Long id, String titular, BigDecimal saldo) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldo != null
                ? saldo
                : BigDecimal.ZERO;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo != null
                ? saldo
                : BigDecimal.ZERO;
    }

    // Método para adicionar valor ao saldo
    public void adicionarSaldo(BigDecimal valor) {

        if (valor == null) {
            throw new IllegalArgumentException(
                    "O valor não pode ser nulo."
            );
        }

        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O valor não pode ser negativo."
            );
        }

        this.saldo = this.saldo.add(valor);
    }

    // Método para retirar valor do saldo
    public void retirarSaldo(BigDecimal valor) {

        if (valor == null) {
            throw new IllegalArgumentException(
                    "O valor não pode ser nulo."
            );
        }

        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O valor não pode ser negativo."
            );
        }

        if (this.saldo.compareTo(valor) < 0) {
            throw new IllegalArgumentException(
                    "Saldo insuficiente."
            );
        }

        this.saldo = this.saldo.subtract(valor);
    }
}