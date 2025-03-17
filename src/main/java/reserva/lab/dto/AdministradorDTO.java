package reserva.lab.dto;

public class AdministradorDTO extends UsuarioDTO{

    private boolean podeAprovarReservas;

    public boolean isPodeAprovarReservas() {
        return podeAprovarReservas;
    }

    public void setPodeAprovarReservas(boolean podeAprovarReservas) {
        this.podeAprovarReservas = podeAprovarReservas;
    }
}
