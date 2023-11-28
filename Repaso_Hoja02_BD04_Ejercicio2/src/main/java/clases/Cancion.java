package clases;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Cancion {
    private int id,idGrupo;
    private LocalTime duracion;
    private String titulo;
    Set<Voto>votos;

    public Cancion(int id, int idGrupo, LocalTime duracion, String titulo) {
        this.id = id;
        this.idGrupo = idGrupo;
        this.duracion = duracion;
        this.titulo = titulo;
    }

    public Cancion() {
        votos=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Voto> getVotos() {
        return votos;
    }

    public void setVotos(Set<Voto> votos) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cancion{");
        sb.append("id=").append(id);
        sb.append(", idGrupo=").append(idGrupo);
        sb.append(", duracion=").append(duracion);
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
