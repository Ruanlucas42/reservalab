package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.model.Laboratorio;
import reserva.lab.repository.LaboratorioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public Laboratorio criarLaboratorio(Laboratorio laboratorio){
        return laboratorioRepository.save(laboratorio);
    }

    public List<Laboratorio> listarLaboratorios(){
        return laboratorioRepository.findAll();
    }

    public Optional<Laboratorio> buscarLaboratorioPorId(int id){
        return laboratorioRepository.findById(id);
    }

    public Laboratorio atualizarLaboratorio(int id, Laboratorio laboratorio){
        laboratorio.setId(id);
        return laboratorioRepository.save(laboratorio);
    }

    public void deletarLaboratorio(int id){
        laboratorioRepository.deleteById(id);
    }
}
