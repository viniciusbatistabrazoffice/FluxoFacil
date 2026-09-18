package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.backend.dto.CadastroRequest;
import com.backend.dto.LoginRequest;
import com.backend.dto.LoginResponse;
import com.backend.entity.Usuario;
import com.backend.security.JwtService;
import com.backend.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            UsuarioService usuarioService,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastro(
            @RequestBody CadastroRequest request) {

        usuarioService.cadastrar(
                request.getNome(),
                request.getEmail(),
                request.getSenha()
        );

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        Usuario usuario =
                usuarioService.buscarPorEmail(request.getEmail());

        if (!passwordEncoder.matches(
                request.getSenha(),
                usuario.getSenha())) {

            throw new RuntimeException(
                    "E-mail ou senha inválidos.");
        }

        String token =
                jwtService.gerarToken(usuario.getEmail());

        return ResponseEntity.ok(
                new LoginResponse(token)
        );
    }
}