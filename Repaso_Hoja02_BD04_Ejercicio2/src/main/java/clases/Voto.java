package clases;

import java.time.LocalDate;

public class Voto {
    private String usuario;
    private LocalDate fecha;
    private int id_cancion;

    public Voto(String usuario, LocalDate fecha, int id_cancion) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.id_cancion = id_cancion;
    }

    public Voto() {
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

    public int getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }
}
