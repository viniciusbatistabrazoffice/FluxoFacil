package com.backend.service;

import java.util.List;

import com.backend.entity.Usuario;

public interface UsuarioService {

    // Cadastro
    Usuario cadastrar(
            String nome,
            String email,
            String senha
    );

    // Buscar
    Usuario buscarPorId(Long id);

    Usuario buscarPorEmail(String email);

    List<Usuario> listarTodos();

    // Atualização
    Usuario atualizar(
            Long id,
            String nome,
            String email
    );

    void alterarSenha(
            Long id,
            String senhaAtual,
            String novaSenha
    );

    // Exclusão
    void deletar(Long id);

    // Autenticação
    boolean autenticar(
            String email,
            String senha
    );

    // Verificações
    boolean existePorEmail(String email);
}