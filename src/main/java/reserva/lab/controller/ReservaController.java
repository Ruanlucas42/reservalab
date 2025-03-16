package reserva.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reserva.lab.model.Reserva;
import reserva.lab.service.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;


    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva) {
        Reserva novaReserva = reservaService.criarReserva(reserva);
        return new ResponseEntity<>(novaReserva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas() {
        List<Reserva> reservas = reservaService.listarReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarReservaPorId(@PathVariable int id) {
        return reservaService.buscarReservaPorId(id)
                .map(reserva -> new ResponseEntity<>(reserva, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable int id, @RequestBody Reserva reservaAtualizada) {
        Reserva reserva = reservaService.atualizarReserva(id, reservaAtualizada);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReserva(@PathVariable int id) {
        reservaService.deletarReserva(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/aprovar")
    public ResponseEntity<Reserva> aprovarReserva(@PathVariable int id) {
        Reserva reserva = reservaService.aprovarReserva(id);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @PatchMapping("/{id}/recusar")
    public ResponseEntity<Reserva> recusarReserva(@PathVariable int id) {
        Reserva reserva = reservaService.recusarReserva(id);
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }
}
