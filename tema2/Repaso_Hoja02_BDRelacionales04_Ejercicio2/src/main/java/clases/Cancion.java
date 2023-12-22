package clases;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Cancion {
    private int id,grupo;
    private LocalTime duracion;
    private String titulo;
    private Set<Voto> votos;

    public Cancion() {
        votos=new HashSet<>();
    }

    public Cancion(int id, int grupo, LocalTime duracion, String titulo) {
        this.id = id;
        this.grupo = grupo;
        this.duracion = duracion;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
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
    public String mostrarVoto(){
        StringBuilder cad= new StringBuilder();
        Iterator<Voto> it=votos.iterator();
        while (it.hasNext()){
            Voto voto=it.next();
            cad.append(voto.getCancion());
        }
        return cad.toString();
    }
}
