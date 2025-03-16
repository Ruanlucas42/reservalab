package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.model.Usuario;
import reserva.lab.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(int id){
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(int id, Usuario usuario){
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(int id){
        usuarioRepository.deleteById(id);
    }
}
