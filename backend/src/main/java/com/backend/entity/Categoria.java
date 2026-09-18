package com.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "categoria",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "nome")
    }
)
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        nullable = false,
        length = 100
    )
    private String nome;

    // Construtor padrão exigido pelo JPA
    public Categoria() {
    }

    // Construtor completo
    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Construtor para nova categoria
    public Categoria(String nome) {
        this.nome = nome;
    }

    // ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // NOME
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}