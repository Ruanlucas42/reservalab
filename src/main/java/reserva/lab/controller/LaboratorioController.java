package reserva.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reserva.lab.model.Laboratorio;
import reserva.lab.service.LaboratorioService;

import java.util.List;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping
    public ResponseEntity<Laboratorio>  criarLaboratorio(@RequestBody Laboratorio laboratorio){
        Laboratorio novoLaboratorio = laboratorioService.criarLaboratorio(laboratorio);
        return new ResponseEntity<>(novoLaboratorio, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Laboratorio>> listarLaboratorios() {
        List<Laboratorio> laboratorios = laboratorioService.listarLaboratorios();
        return new ResponseEntity<>(laboratorios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> buscarLaboratorioPorId(@PathVariable int id) {
        return laboratorioService.buscarLaboratorioPorId(id)
                .map(laboratorio -> new ResponseEntity<>(laboratorio, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> atualizarLaboratorio(@PathVariable int id, @RequestBody Laboratorio laboratorioAtualizado) {
        return laboratorioService.buscarLaboratorioPorId(id).map(laboratorio -> {
                    Laboratorio atualizado = laboratorioService.atualizarLaboratorio(id, laboratorioAtualizado);
                    return new ResponseEntity<>(atualizado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
