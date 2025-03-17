package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.dto.AdministradorDTO;
import reserva.lab.model.Administrador;
import reserva.lab.model.Reserva;
import reserva.lab.model.Status;
import reserva.lab.repository.AdministradorRepository;
import reserva.lab.repository.ReservaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public AdministradorDTO criarAdministrador(AdministradorDTO administradorDTO) {
        Administrador administrador = convertToEntity(administradorDTO);
        Administrador novoAdministrador = administradorRepository.save(administrador);
        return convertToDTO(novoAdministrador);
    }

    public List<AdministradorDTO> listarAdministradores() {
        return administradorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AdministradorDTO buscarAdministradorPorId(int id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
        return convertToDTO(administrador);
    }

    public AdministradorDTO atualizarAdministrador(int id, AdministradorDTO administradorDTO) {
        return administradorRepository.findById(id).map(administrador -> {
            administrador.setNome(administradorDTO.getNome());
            administrador.setEmail(administradorDTO.getEmail());
            administrador.setSenha(administradorDTO.getSenha());
            administrador.setPodeAprovarReservas(administradorDTO.isPodeAprovarReservas());
            Administrador administradorAtualizado = administradorRepository.save(administrador);
            return convertToDTO(administradorAtualizado);
        }).orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
    }

    public void deletarAdministrador(int id) {
        administradorRepository.deleteById(id);
    }

    public Reserva aprovarReserva(int id, Administrador administrador) {
        if (administrador == null || !administrador.isPodeAprovarReservas()) {
            throw new RuntimeException("Apenas administradores com permissão podem aprovar reservas");
        }

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setStatus(Status.APROVADA);
        return reservaRepository.save(reserva);
    }

    private AdministradorDTO convertToDTO(Administrador administrador) {
        AdministradorDTO administradorDTO = new AdministradorDTO();
        administradorDTO.setNome(administrador.getNome());
        administradorDTO.setEmail(administrador.getEmail());
        administradorDTO.setSenha(administrador.getSenha());
        administradorDTO.setPodeAprovarReservas(administrador.isPodeAprovarReservas());
        return administradorDTO;
    }

    private Administrador convertToEntity(AdministradorDTO administradorDTO) {
        Administrador administrador = new Administrador();
        administrador.setNome(administradorDTO.getNome());
        administrador.setEmail(administradorDTO.getEmail());
        administrador.setSenha(administradorDTO.getSenha());
        administrador.setPodeAprovarReservas(administradorDTO.isPodeAprovarReservas());
        return administrador;
    }
}