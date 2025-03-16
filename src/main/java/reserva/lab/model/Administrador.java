package reserva.lab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario{

    @Column(nullable = false)
    private boolean podeAprovarReservas = true;

    public Administrador(){
    }

    public Administrador( String nome, String email, String senha) {
        super(nome, email, senha);
        this.podeAprovarReservas = true;
    }

    public boolean isPodeAprovarReservas() {
        return podeAprovarReservas;
    }

    public void setPodeAprovarReservas(boolean podeAprovarReservas) {
        this.podeAprovarReservas = podeAprovarReservas;
    }
}
