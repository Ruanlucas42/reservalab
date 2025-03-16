package reserva.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reserva.lab.model.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {
}
