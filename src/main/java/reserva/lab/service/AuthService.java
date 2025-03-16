package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.model.Administrador;
import reserva.lab.model.Usuario;
import reserva.lab.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Autentifica usuario(login)
    public Usuario autenticar(String email, String senha){
        return usuarioRepository.findByEmailAndSenha(email,senha).orElseThrow(() -> new RuntimeException
                ("Credenciais inválidas"));
    }

    // verifica se o usuario é administrador
    public boolean isAdministrador(int usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuario instanceof Administrador;
    }
}
