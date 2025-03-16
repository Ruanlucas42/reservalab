package reserva.lab.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private LocalTime horarioInicio;

    @Column(nullable = false)
    private LocalTime horarioFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    private Solicitante solicitante;


    @ManyToOne
    @JoinColumn(name = "laboratorio_id", nullable = false)
    private Laboratorio laboratorio;

    public Reserva(){
    }

    public Reserva(Integer id, Date data, LocalTime horarioInicio, LocalTime horarioFim, Status status, Solicitante solicitante, Laboratorio laboratorio) {
        this.id = id;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.status = status;
        this.solicitante = solicitante;
        this.laboratorio = laboratorio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {

        return data;
    }

    public void setData(Date data) {

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

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }
}
