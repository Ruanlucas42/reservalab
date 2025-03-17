package com.laboratorio.laboratorio.service;

import com.laboratorio.laboratorio.model.Usuario;
import com.laboratorio.laboratorio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public boolean deletarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario atualizado = usuarioExistente.get();
            atualizado.setNome(usuario.getNome());
            atualizado.setEmail(usuario.getEmail());
            atualizado.setLogin(usuario.getLogin());
            atualizado.setSenha(usuario.getSenha());
            atualizado.setCategoria(usuario.getCategoria());
            return usuarioRepository.save(atualizado);
        }
        return null;
    }

}
