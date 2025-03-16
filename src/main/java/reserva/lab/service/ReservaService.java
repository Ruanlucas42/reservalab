package reserva.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reserva.lab.model.Reserva;
import reserva.lab.model.Status;
import reserva.lab.repository.ReservaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva criarReserva(Reserva reserva){
        reserva.setStatus(Status.PENDENTE);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> listarReservas(){
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarReservaPorId(int id){
        return reservaRepository.findById(id);
    }

    public Reserva atualizarReserva(int id, Reserva reserva){
        reserva.setId(id);
        return reservaRepository.save(reserva);
    }

    public void deletarReserva(int id){
        reservaRepository.deleteById(id);
    }

    public Reserva aprovarReserva(int id){
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException
                ("Reserva não encontrada"));
        reserva.setStatus(Status.APROVADA);
        return reservaRepository.save(reserva);
    }

    public Reserva recusarReserva(int id){
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException
                ("Reserva não encontrada"));
        reserva.setStatus(Status.RECUSADA);
        return reservaRepository.save(reserva);
    }
}
