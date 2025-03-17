package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.dto.LaboratorioDTO;
import reserva.lab.model.Laboratorio;
import reserva.lab.repository.LaboratorioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public LaboratorioDTO criarLaboratorio(LaboratorioDTO laboratorioDTO) {
        Laboratorio laboratorio = convertToEntity(laboratorioDTO);
        Laboratorio novoLaboratorio = laboratorioRepository.save(laboratorio);
        return convertToDTO(novoLaboratorio);
    }

    public List<LaboratorioDTO> listarLaboratorios() {
        return laboratorioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<LaboratorioDTO> buscarLaboratorioPorId(int id) {
        return laboratorioRepository.findById(id)
                .map(this::convertToDTO);
    }

    public LaboratorioDTO atualizarLaboratorio(int id, LaboratorioDTO laboratorioDTO) {
        return laboratorioRepository.findById(id).map(laboratorio -> {
            laboratorio.setNome(laboratorioDTO.getNome());
            laboratorio.setCapacidade(laboratorioDTO.getCapacidade());
            Laboratorio laboratorioAtualizado = laboratorioRepository.save(laboratorio);
            return convertToDTO(laboratorioAtualizado);
        }).orElseThrow(() -> new RuntimeException("Laboratório não encontrado"));
    }

    public void deletarLaboratorio(int id) {
        laboratorioRepository.deleteById(id);
    }

    private Laboratorio convertToEntity(LaboratorioDTO laboratorioDTO) {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNome(laboratorioDTO.getNome());
        laboratorio.setCapacidade(laboratorioDTO.getCapacidade());
        return laboratorio;
    }

    private LaboratorioDTO convertToDTO(Laboratorio laboratorio) {
        LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
        laboratorioDTO.setNome(laboratorio.getNome());
        laboratorioDTO.setCapacidade(laboratorio.getCapacidade());
        return laboratorioDTO;
    }
}