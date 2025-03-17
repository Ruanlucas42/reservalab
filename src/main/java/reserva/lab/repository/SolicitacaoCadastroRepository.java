package reserva.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reserva.lab.model.SolicitacaoCadastro;
import reserva.lab.model.Status;

import java.util.List;

@Repository
public interface SolicitacaoCadastroRepository extends JpaRepository<SolicitacaoCadastro, Integer> {
    List<SolicitacaoCadastro> findByStatus(Status status);
}
