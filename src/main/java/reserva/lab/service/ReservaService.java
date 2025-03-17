package reserva.lab.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.dto.ReservaDTO;
import reserva.lab.model.Laboratorio;
import reserva.lab.model.Reserva;
import reserva.lab.model.Status;
import reserva.lab.model.Administrador;
import reserva.lab.repository.LaboratorioRepository;
import reserva.lab.repository.ReservaRepository;
import reserva.lab.repository.AdministradorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    public ReservaDTO criarReserva(ReservaDTO reservaDTO) {
        Laboratorio laboratorio = laboratorioRepository.findById(reservaDTO.getLaboratorioId())
                .orElseThrow(() -> new RuntimeException("Laboratório não encontrado"));

        Reserva reserva = new Reserva();
        reserva.setData(reservaDTO.getData());
        reserva.setHorarioInicio(reservaDTO.getHorarioInicio());
        reserva.setHorarioFim(reservaDTO.getHorarioFim());
        reserva.setStatus(Status.PENDENTE);
        reserva.setLaboratorio(laboratorio);

        Reserva novaReserva = reservaRepository.save(reserva);
        return convertToDTO(novaReserva);
    }

    public List<ReservaDTO> listarReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        return reservas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReservaDTO> buscarReservaPorId(int id) {
        return reservaRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ReservaDTO atualizarReserva(int id, ReservaDTO reservaDTO) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reservaExistente.setData(reservaDTO.getData());
        reservaExistente.setHorarioInicio(reservaDTO.getHorarioInicio());
        reservaExistente.setHorarioFim(reservaDTO.getHorarioFim());
        reservaExistente.setStatus(Status.valueOf(reservaDTO.getStatus()));

        Reserva reservaAtualizada = reservaRepository.save(reservaExistente);
        return convertToDTO(reservaAtualizada);
    }

    public void deletarReserva(int id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reservaRepository.delete(reserva);
    }

    @Transactional
    public ReservaDTO recusarReserva(int id, Administrador administrador) {
        if (administrador == null || !administrador.isAdministrador()) {
            throw new RuntimeException("Apenas administradores podem recusar reservas");
        }

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setStatus(Status.RECUSADA);
        Reserva reservaRecusada = reservaRepository.save(reserva);
        return convertToDTO(reservaRecusada);
    }

    @Transactional
    public ReservaDTO aprovarReserva(int id, Administrador administrador) {
        if (administrador == null || !administrador.isAdministrador()) {
            throw new RuntimeException("Apenas administradores podem aprovar reservas");
        }

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setStatus(Status.APROVADA);
        Reserva reservaAprovada = reservaRepository.save(reserva);
        return convertToDTO(reservaAprovada);
    }

    private ReservaDTO convertToDTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setData(reserva.getData());
        dto.setHorarioInicio(reserva.getHorarioInicio());
        dto.setHorarioFim(reserva.getHorarioFim());
        dto.setStatus(reserva.getStatus().name());
        dto.setLaboratorioId(reserva.getLaboratorio().getId());
        return dto;
    }
}