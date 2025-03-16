package reserva.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reserva.lab.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
