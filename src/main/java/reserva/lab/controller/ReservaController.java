package reserva.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reserva.lab.dto.ReservaDTO;
import reserva.lab.model.Administrador;
import reserva.lab.service.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDTO> criarReserva(@RequestBody ReservaDTO reservaDTO) {
        ReservaDTO novaReserva = reservaService.criarReserva(reservaDTO);
        return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas() {
        List<ReservaDTO> reservas = reservaService.listarReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarReservaPorId(@PathVariable int id) {
        return reservaService.buscarReservaPorId(id)
                .map(reservaDTO -> new ResponseEntity<>(reservaDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> atualizarReserva(@PathVariable int id, @RequestBody ReservaDTO reservaDTO) {
        ReservaDTO reservaAtualizada = reservaService.atualizarReserva(id, reservaDTO);
        return new ResponseEntity<>(reservaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable int id) {
        reservaService.deletarReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<ReservaDTO> aprovarReserva(@PathVariable int id, @RequestBody Administrador administrador) {
        ReservaDTO reserva = reservaService.aprovarReserva(id, administrador);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @PatchMapping("/{id}/recusar")
    public ResponseEntity<ReservaDTO> recusarReserva(@PathVariable int id, @RequestBody Administrador administrador) {
        ReservaDTO reserva = reservaService.recusarReserva(id, administrador);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }
}