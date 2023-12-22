package clases;

import java.net.ServerSocket;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Grupo {
    private int id;
    private String nombre,localidad,estilo;
    private boolean esGrupo;
    private int añoGrabacion;
    private LocalDate fechaEstreno;
    private String compañia;
    private Set<Cancion> canciones;

    public Grupo() {
        canciones=new HashSet<>();
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

    public Set<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<Cancion> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Grupo{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", localidad='").append(localidad).append('\'');
        sb.append(", estilo='").append(estilo).append('\'');
        sb.append(", esGrupo=").append(esGrupo);
        sb.append(", añoGrabacion=").append(añoGrabacion);
        sb.append(", fechaEstreno=").append(fechaEstreno);
        sb.append(", compañia='").append(compañia).append('\'');
        sb.append(", canciones=").append(canciones);
        sb.append('}');
        return sb.toString();
    }
    public String mostrarCancion(){
        StringBuilder cad= new StringBuilder();
        Iterator<Cancion>it=canciones.iterator();
        while (it.hasNext()){
            Cancion cancion=it.next();
            cad.append(cancion.getTitulo());
        }
        return cad.toString();
    }
    public String mostrarCancion2(){
        StringBuilder cad= new StringBuilder();
        Iterator<Cancion>it=canciones.iterator();
        while (it.hasNext()){
            Cancion cancion=it.next();
            cad.append(cancion.getTitulo());
            cad.append(cancion.mostrarVoto());
        }
        return cad.toString();
    }
}
