package com.backend.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Usuario;
import com.backend.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ==========================================
    // CADASTRO
    // ==========================================

    @Override
    @Transactional
    public Usuario cadastrar(
            String nome,
            String email,
            String senha) {

        validarNome(nome);
        validarEmail(email);
        validarSenha(senha);

        email = email.trim().toLowerCase();

        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException(
                    "Já existe um usuário com o e-mail: " + email
            );
        }

        Usuario usuario = new Usuario();

        usuario.setNome(nome.trim());
        usuario.setEmail(email);

        // Nunca salvar senha em texto puro
        usuario.setSenha(
                passwordEncoder.encode(senha)
        );

        return usuarioRepository.save(usuario);
    }

    // ==========================================
    // BUSCAR POR ID
    // ==========================================

    @Override
    public Usuario buscarPorId(Long id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuário não encontrado com o ID: " + id
                        )
                );
    }

    // ==========================================
    // BUSCAR POR EMAIL
    // ==========================================

    @Override
    public Usuario buscarPorEmail(String email) {

        validarEmail(email);

        return usuarioRepository
                .findByEmail(email.trim().toLowerCase())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuário não encontrado."
                        )
                );
    }

    // ==========================================
    // LISTAR TODOS
    // ==========================================

    @Override
    public List<Usuario> listarTodos() {

        return usuarioRepository.findAll();
    }

    // ==========================================
    // ATUALIZAR USUÁRIO
    // ==========================================

    @Override
    @Transactional
    public Usuario atualizar(
            Long id,
            String nome,
            String email) {

        validarNome(nome);
        validarEmail(email);

        Usuario usuario = buscarPorId(id);

        email = email.trim().toLowerCase();

        // Verifica se o novo email já pertence
        // a outro usuário
        if (!usuario.getEmail().equals(email)
                && usuarioRepository.existsByEmail(email)) {

            throw new RuntimeException(
                    "O e-mail já está sendo utilizado."
            );
        }

        usuario.setNome(nome.trim());
        usuario.setEmail(email);

        return usuarioRepository.save(usuario);
    }

    // ==========================================
    // ALTERAR SENHA
    // ==========================================

    @Override
    @Transactional
    public void alterarSenha(
            Long id,
            String senhaAtual,
            String novaSenha) {

        validarSenha(senhaAtual);
        validarSenha(novaSenha);

        Usuario usuario = buscarPorId(id);

        // Confere a senha atual
        if (!passwordEncoder.matches(
                senhaAtual,
                usuario.getSenha())) {

            throw new RuntimeException(
                    "A senha atual está incorreta."
            );
        }

        // Impede reutilizar a mesma senha
        if (passwordEncoder.matches(
                novaSenha,
                usuario.getSenha())) {

            throw new RuntimeException(
                    "A nova senha deve ser diferente da senha atual."
            );
        }

        usuario.setSenha(
                passwordEncoder.encode(novaSenha)
        );

        usuarioRepository.save(usuario);
    }

    // ==========================================
    // DELETAR
    // ==========================================

    @Override
    @Transactional
    public void deletar(Long id) {

        if (!usuarioRepository.existsById(id)) {

            throw new RuntimeException(
                    "Usuário não encontrado com o ID: " + id
            );
        }

        usuarioRepository.deleteById(id);
    }

    // ==========================================
    // AUTENTICAR
    // ==========================================

    @Override
    public boolean autenticar(
            String email,
            String senha) {

        validarEmail(email);
        validarSenha(senha);

        Usuario usuario = usuarioRepository
                .findByEmail(email.trim().toLowerCase())
                .orElse(null);

        if (usuario == null) {
            return false;
        }

        return passwordEncoder.matches(
                senha,
                usuario.getSenha()
        );
    }

    // ==========================================
    // VERIFICAR EMAIL
    // ==========================================

    @Override
    public boolean existePorEmail(String email) {

        if (email == null || email.isBlank()) {
            return false;
        }

        return usuarioRepository.existsByEmail(
                email.trim().toLowerCase()
        );
    }

    // ==========================================
    // VALIDAÇÕES
    // ==========================================

    private void validarNome(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome é obrigatório."
            );
        }

        if (nome.trim().length() < 3) {
            throw new IllegalArgumentException(
                    "O nome deve possuir pelo menos 3 caracteres."
            );
        }
    }

    private void validarEmail(String email) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "O e-mail é obrigatório."
            );
        }

        String emailFormatado = email.trim();

        if (!emailFormatado.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new IllegalArgumentException(
                    "E-mail inválido."
            );
        }
    }

    private void validarSenha(String senha) {

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException(
                    "A senha é obrigatória."
            );
        }

        if (senha.length() < 6) {
            throw new IllegalArgumentException(
                    "A senha deve possuir pelo menos 6 caracteres."
            );
        }
    }
}