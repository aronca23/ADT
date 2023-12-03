package clases;

import java.time.LocalDate;

public class Voto {
    private String usuario;
    private LocalDate fecha;
    private int cancion;

    public Voto() {
    }

    public Voto(String usuario, LocalDate fecha, int cancion) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.cancion = cancion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCancion() {
        return cancion;
    }

    public void setCancion(int cancion) {
        this.cancion = cancion;
    }
}
