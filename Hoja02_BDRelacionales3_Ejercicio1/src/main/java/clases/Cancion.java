package clases;

public class Cancion {
    private int numcancion;
    private String titulo;
    private int votos;
    public Cancion(){

    }
    public Cancion(int numcancion, String titulo, int votos) {
        this.numcancion = numcancion;
        this.titulo = titulo;
        this.votos = votos;
    }

    public int getNumcancion() {
        return numcancion;
    }

    public void setNumcancion(int numcancion) {
        this.numcancion = numcancion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
}
