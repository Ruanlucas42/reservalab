package reserva.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reserva.lab.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
