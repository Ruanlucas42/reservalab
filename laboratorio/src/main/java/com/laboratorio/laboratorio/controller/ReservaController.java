package com.laboratorio.laboratorio.controller;

import com.laboratorio.laboratorio.model.Reserva;
import com.laboratorio.laboratorio.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody Reserva reserva, @RequestParam Long usuarioId) {
        Reserva reservaCriada = reservaService.criarReserva(reserva, usuarioId);
        return reservaCriada != null ? ResponseEntity.status(HttpStatus.CREATED).body(reservaCriada) : ResponseEntity.badRequest().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReserva(@PathVariable Long id) {
        Reserva reserva = reservaService.getReservaById(id);
        return reserva != null ? ResponseEntity.ok(reserva) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        boolean deleted = reservaService.deletarReserva(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> atualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        Reserva reservaAtualizada = reservaService.atualizarReserva(id, reserva);
        return reservaAtualizada != null ? ResponseEntity.ok(reservaAtualizada) : ResponseEntity.notFound().build();
    }

}
