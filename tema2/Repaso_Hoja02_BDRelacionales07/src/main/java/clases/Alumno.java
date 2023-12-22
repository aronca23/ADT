package clases;

public class Alumno {
    private int id;
    private String nombre;
    private double notaMedia;
    private int curso;

    public Alumno() {
    }

    public Alumno(int id, String nombre, double notaMedia, int curso) {
        this.id = id;
        this.nombre = nombre;
        this.notaMedia = notaMedia;
        this.curso = curso;
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

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
}
