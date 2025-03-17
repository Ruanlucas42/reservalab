package reserva.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reserva.lab.dto.AdministradorDTO;
import reserva.lab.service.AdministradorService;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @PostMapping
    public ResponseEntity<AdministradorDTO> criarAdministrador(@RequestBody AdministradorDTO administradorDTO) {
        AdministradorDTO novoAdministrador = administradorService.criarAdministrador(administradorDTO);
        return new ResponseEntity<>(novoAdministrador, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> listarAdministradores() {
        List<AdministradorDTO> administradores = administradorService.listarAdministradores();
        return new ResponseEntity<>(administradores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> buscarAdministradorPorId(@PathVariable int id) {
        AdministradorDTO administradorDTO = administradorService.buscarAdministradorPorId(id);
        return new ResponseEntity<>(administradorDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorDTO> atualizarAdministrador(@PathVariable int id, @RequestBody AdministradorDTO administradorDTO) {
        AdministradorDTO administradorAtualizado = administradorService.atualizarAdministrador(id, administradorDTO);
        return new ResponseEntity<>(administradorAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAdministrador(@PathVariable int id) {
        administradorService.deletarAdministrador(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}