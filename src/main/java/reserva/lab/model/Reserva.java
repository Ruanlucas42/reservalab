package reserva.lab.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horarioInicio;

    @Column(nullable = false)
    private LocalTime horarioFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @ManyToOne
    @JoinColumn(name = "administrador_id")
    private Administrador aprovador;


    @ManyToOne
    @JoinColumn(name = "laboratorio_id", nullable = false)
    private Laboratorio laboratorio;

    public Reserva(){
    }

    public Reserva(LocalDate data, LocalTime horarioInicio, LocalTime horarioFim, Status status, SolicitacaoCadastro solicitacaoCadastro, Administrador aprovador, Laboratorio laboratorio) {
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.status = status;
        this.aprovador = aprovador;
        this.laboratorio = laboratorio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorarioInicio() {

        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {

        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {

        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {

        this.horarioFim = horarioFim;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
}
