package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.dto.UsuarioDTO;
import reserva.lab.model.Usuario;
import reserva.lab.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return convertToDTO(novoUsuario);
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarUsuarioPorId(int id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        return usuarioOpt.map(this::convertToDTO);
    }

    public UsuarioDTO atualizarUsuario(int id, UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        return usuarioRepository.findById(id).map(existingUsuario -> {
            existingUsuario.setNome(usuario.getNome());
            existingUsuario.setEmail(usuario.getEmail());
            existingUsuario.setSenha(usuario.getSenha());
            Usuario usuarioAtualizado = usuarioRepository.save(existingUsuario);
            return convertToDTO(usuarioAtualizado);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deletarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        return usuarioDTO;
    }

    public Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        return usuario;
    }
}
