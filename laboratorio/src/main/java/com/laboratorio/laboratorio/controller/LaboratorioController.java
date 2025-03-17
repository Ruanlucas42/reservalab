package com.laboratorio.laboratorio.controller;

import com.laboratorio.laboratorio.model.Laboratorio;
import com.laboratorio.laboratorio.service.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping
    public ResponseEntity<Laboratorio> criarLaboratorio(@RequestBody Laboratorio laboratorio) {
        Laboratorio novoLaboratorio = laboratorioService.criarLaboratorio(laboratorio);
        return new ResponseEntity<>(novoLaboratorio, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getLaboratorio(@PathVariable Long id) {
        Laboratorio laboratorio = laboratorioService.getLaboratorioById(id);
        return laboratorio != null ? ResponseEntity.ok(laboratorio) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Laboratorio>> getAllLaboratorios() {
        List<Laboratorio> laboratorios = laboratorioService.getAllLaboratorios();
        return ResponseEntity.ok(laboratorios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratorio(@PathVariable Long id) {
        boolean deleted = laboratorioService.deletarLaboratorio(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> atualizarLaboratorio(@PathVariable Long id, @RequestBody Laboratorio laboratorio) {
        Laboratorio laboratorioAtualizado = laboratorioService.atualizarLaboratorio(id, laboratorio);
        return laboratorioAtualizado != null ? ResponseEntity.ok(laboratorioAtualizado) : ResponseEntity.notFound().build();
    }

}
