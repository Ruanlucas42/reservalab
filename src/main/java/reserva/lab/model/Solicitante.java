package reserva.lab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Solicitante extends Usuario{

    @Column(nullable = false)
    private String categoria;

   public Solicitante(){
   }

    public Solicitante(String nome, String email, String senha, String categoria) {
        super(nome, email, senha);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
