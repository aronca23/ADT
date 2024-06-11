package org.example.repository;

import org.example.model.Alumno;

import java.util.List;

public interface AlumnoRepo {
    Alumno getAlumno(String id);
    void anadirCurso(String curso);
    List<Alumno> getAlumnosCurso(String curso);
    void calcularNotaMedia(String id);
    List<Alumno> getAlumnos(String id);
    void anadirNota(String alumno,double nota);
}
