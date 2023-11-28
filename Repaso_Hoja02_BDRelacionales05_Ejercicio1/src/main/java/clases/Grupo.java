package clases;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Grupo {
    private int id;
    private String nombre,localidad,estilo;
    private boolean esGrupo;
    private int annoGrabacion;
    private LocalDate fechaEstreno;
    private String compannia;

    public Grupo(int id, String nombre, String localidad, String estilo, boolean esGrupo, int annoGrabacion, LocalDate fechaEstreno, String compannia) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
        this.estilo = estilo;
        this.esGrupo = esGrupo;
        this.annoGrabacion = annoGrabacion;
        this.fechaEstreno = fechaEstreno;
        this.compannia = compannia;
    }

    public Grupo() {
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

    public int getAnnoGrabacion() {
        return annoGrabacion;
    }

    public void setAnnoGrabacion(int annoGrabacion) {
        this.annoGrabacion = annoGrabacion;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getCompannia() {
        return compannia;
    }

    public void setCompannia(String compannia) {
        this.compannia = compannia;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Grupo{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", localidad='").append(localidad).append('\'');
        sb.append(", estilo='").append(estilo).append('\'');
        sb.append(", esGrupo=").append(esGrupo);
        sb.append(", annoGrabacion=").append(annoGrabacion);
        sb.append(", fechaEstreno=").append(fechaEstreno);
        sb.append(", compannia='").append(compannia).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
