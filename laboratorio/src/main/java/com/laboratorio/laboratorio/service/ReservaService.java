package com.laboratorio.laboratorio.service;

import com.laboratorio.laboratorio.model.Reserva;
import com.laboratorio.laboratorio.model.Usuario;
import com.laboratorio.laboratorio.repository.ReservaRepository;
import com.laboratorio.laboratorio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Reserva criarReserva(Reserva reserva, Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            reserva.setUsuario(usuario.get()); // Define o usuário que fez a reserva
            return reservaRepository.save(reserva);
        }
        return null;  // Retorna null se o usuário não existir
    }

    public Reserva getReservaById(Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        return reserva.orElse(null);
    }

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public boolean deletarReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Reserva atualizarReserva(Long id, Reserva reserva) {
        Optional<Reserva> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            Reserva atualizado = reservaExistente.get();
            atualizado.setDataReserva(reserva.getDataReserva());
            atualizado.setHoraInicio(reserva.getHoraInicio());
            atualizado.setHoraFim(reserva.getHoraFim());
            atualizado.setEvento(reserva.getEvento());
            // Aqui a relação com o usuário já foi setada no método de criação
            return reservaRepository.save(atualizado);
        }
        return null;  // Caso não encontre a reserva
    }


}
