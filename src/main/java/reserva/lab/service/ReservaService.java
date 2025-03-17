package reserva.lab.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.model.Laboratorio;
import reserva.lab.model.Reserva;
import reserva.lab.model.Status;
import reserva.lab.model.Administrador;
import reserva.lab.repository.LaboratorioRepository;
import reserva.lab.repository.ReservaRepository;
import reserva.lab.repository.AdministradorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    public Reserva criarReserva(Reserva reserva) {

        Laboratorio laboratorio = laboratorioRepository.findById(reserva.getLaboratorio().getId())
                .orElseThrow(() -> new RuntimeException("Laboratório não encontrado"));

        reserva.setLaboratorio(laboratorio);
        reserva.setStatus(Status.PENDENTE);

        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarReservaPorId(int id) {
        return reservaRepository.findById(id);
    }

    public Reserva atualizarReserva(int id, Reserva reserva) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setId(id);
        reserva.setLaboratorio(reservaExistente.getLaboratorio());
        reserva.setStatus(reservaExistente.getStatus());

        return reservaRepository.save(reserva);
    }

    public void deletarReserva(int id) {
        if (!reservaRepository.existsById(id)) {
            throw new RuntimeException("Reserva não encontrada");
        }
        reservaRepository.deleteById(id);
    }

    @Transactional
    public Reserva aprovarReserva(int id, int administradorId) {

        Administrador administrador = administradorRepository.findById(administradorId)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));


        if (!administrador.isPodeAprovarReservas()) {
            throw new RuntimeException("Apenas administradores com permissão podem aprovar reservas");
        }

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setStatus(Status.APROVADA);
        return reservaRepository.save(reserva);
    }

    public Reserva recusarReserva(int id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setStatus(Status.RECUSADA);
        return reservaRepository.save(reserva);
    }
}
