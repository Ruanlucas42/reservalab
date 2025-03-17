package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.model.Administrador;
import reserva.lab.model.Usuario;
import reserva.lab.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar um novo usuário
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar um usuário por ID
    public Optional<Usuario> buscarUsuarioPorId(int id) {
        return usuarioRepository.findById(id);
    }

    // Atualizar um usuário
    public Usuario atualizarUsuario(int id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Deletar um usuário
    public void deletarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public List<Administrador> listarAdministradores() {
        return usuarioRepository.findAll().stream()
                .filter(usuario -> usuario instanceof Administrador)
                .map(usuario -> (Administrador) usuario)
                .toList();
    }

    public Administrador criarAdministrador(Administrador administrador) {
        return usuarioRepository.save(administrador);
    }


}
