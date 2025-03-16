package reserva.lab.model;

import java.time.LocalTime;
import java.util.Date;

public class Reserva {
    private Integer id;
    private Date data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private Status status;

    public Reserva(){
    }

    public Reserva(Integer id, Date data, LocalTime horarioInicio, LocalTime horarioFim, Status status) {
        this.id = id;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.status = status;
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
}
