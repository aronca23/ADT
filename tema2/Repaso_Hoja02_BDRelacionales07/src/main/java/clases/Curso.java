package clases;

import java.util.HashSet;
import java.util.Set;

public class Curso {
    private int id;
    private String nombre;
    private int tutorId;
    private Set<Alumno> alumnos;
    private Set<Profesor> profesores;


    public Curso() {
        alumnos=new HashSet<>();
        profesores=new HashSet<>();
    }

    public Curso(int id, String nombre, int tutorId) {
        this.id = id;
        this.nombre = nombre;
        this.tutorId = tutorId;
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

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public Set<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Set<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<Profesor> profesores) {
        this.profesores = profesores;
    }
}
