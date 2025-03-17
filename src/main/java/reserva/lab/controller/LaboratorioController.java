package reserva.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reserva.lab.dto.LaboratorioDTO;
import reserva.lab.service.LaboratorioService;

import java.util.List;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping
    public ResponseEntity<LaboratorioDTO> criarLaboratorio(@RequestBody LaboratorioDTO laboratorioDTO) {
        LaboratorioDTO novoLaboratorio = laboratorioService.criarLaboratorio(laboratorioDTO);
        return new ResponseEntity<>(novoLaboratorio, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LaboratorioDTO>> listarLaboratorios() {
        List<LaboratorioDTO> laboratorios = laboratorioService.listarLaboratorios();
        return new ResponseEntity<>(laboratorios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioDTO> buscarLaboratorioPorId(@PathVariable int id) {
        return laboratorioService.buscarLaboratorioPorId(id)
                .map(laboratorioDTO -> new ResponseEntity<>(laboratorioDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratorioDTO> atualizarLaboratorio(@PathVariable int id, @RequestBody LaboratorioDTO laboratorioDTO) {
        return laboratorioService.buscarLaboratorioPorId(id).map(laboratorio -> {
            LaboratorioDTO atualizado = laboratorioService.atualizarLaboratorio(id, laboratorioDTO);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLaboratorio(@PathVariable int id) {
        return laboratorioService.buscarLaboratorioPorId(id)
                .map(laboratorio -> {
                    laboratorioService.deletarLaboratorio(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}