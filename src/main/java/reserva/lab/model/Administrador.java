package reserva.lab.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {

    @Column(nullable = false)
    private boolean podeAprovarReservas = true;

    public Administrador() {
    }

    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public boolean isPodeAprovarReservas() {
        return podeAprovarReservas;
    }

    public void setPodeAprovarReservas(boolean podeAprovarReservas) {
        this.podeAprovarReservas = podeAprovarReservas;
    }
}