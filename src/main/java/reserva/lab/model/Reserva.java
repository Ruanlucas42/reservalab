package reserva.lab.model;

import java.time.LocalTime;
import java.util.Date;

public class Reserva {
    private Integer id;
    private Date data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    public Reserva(){
    }

    public Reserva(Integer id, Date data, LocalTime horarioInicio, LocalTime horarioFim) {
        this.id = id;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
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
}
