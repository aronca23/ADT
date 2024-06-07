package es.aron.repository;

import es.aron.model.Alumno;

import java.util.List;

public interface AlumnoRepo {
    Alumno datosAlumno(int id);
    List<Alumno> listadoAlumnosCurso(String id);
    void eliminarAlumno(int id);
}
