package clases;

import java.time.LocalDate;

public class Grupo {
    private int id;
    private String nombre,localidad,estilo;
    private boolean esGrupo;
    private int añoGrabacion;
    private LocalDate fechaEstreno;
    private String compañia;

    public Grupo() {
    }

    public Grupo(int id, String nombre, String localidad, String estilo, boolean esGrupo, int añoGrabacion, LocalDate fechaEstreno, String compañia) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
        this.estilo = estilo;
        this.esGrupo = esGrupo;
        this.añoGrabacion = añoGrabacion;
        this.fechaEstreno = fechaEstreno;
        this.compañia = compañia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public boolean isEsGrupo() {
        return esGrupo;
    }

    public void setEsGrupo(boolean esGrupo) {
        this.esGrupo = esGrupo;
    }

    public int getAñoGrabacion() {
        return añoGrabacion;
    }

    public void setAñoGrabacion(int añoGrabacion) {
        this.añoGrabacion = añoGrabacion;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }
}
