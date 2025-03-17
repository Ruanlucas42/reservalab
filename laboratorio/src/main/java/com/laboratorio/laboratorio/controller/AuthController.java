package com.laboratorio.laboratorio.controller;

import com.laboratorio.laboratorio.model.Usuario;
import com.laboratorio.laboratorio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Usuario loginRequest) {
        // Recupera o usuário pelo login
        Usuario usuario = usuarioRepository.findByLogin(loginRequest.getLogin());

        // Verifica se o usuário existe e se a senha corresponde
        if (usuario != null && loginRequest.getSenha().equals(usuario.getSenha())) {

            Map<String, Object> response = new HashMap<>();
            response.put("usuarioId", usuario.getId());

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login ou senha incorretos.");

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }
}
