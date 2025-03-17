package com.laboratorio.laboratorio.service;

import com.laboratorio.laboratorio.model.Laboratorio;
import com.laboratorio.laboratorio.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public Laboratorio criarLaboratorio(Laboratorio laboratorio) {
        return laboratorioRepository.save(laboratorio);
    }

    public Laboratorio getLaboratorioById(Long id) {
        Optional<Laboratorio> laboratorio = laboratorioRepository.findById(id);
        return laboratorio.orElse(null);
    }

    public List<Laboratorio> getAllLaboratorios() {
        return laboratorioRepository.findAll();
    }

    public boolean deletarLaboratorio(Long id) {
        if (laboratorioRepository.existsById(id)) {
            laboratorioRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Laboratorio atualizarLaboratorio(Long id, Laboratorio laboratorio) {
        Optional<Laboratorio> laboratorioExistente = laboratorioRepository.findById(id);
        if (laboratorioExistente.isPresent()) {
            Laboratorio atualizado = laboratorioExistente.get();
            atualizado.setNome(laboratorio.getNome());
            atualizado.setDescricao(laboratorio.getDescricao());
            return laboratorioRepository.save(atualizado);
        }
        return null;
    }

}
