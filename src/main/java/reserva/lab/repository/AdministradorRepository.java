package reserva.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reserva.lab.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}
