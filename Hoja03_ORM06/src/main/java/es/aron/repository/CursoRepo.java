package es.aron.repository;

import es.aron.model.Curso;

import java.util.List;

public interface CursoRepo {
    Curso datosCurso(String id);
    List<Curso> listadoCursos();
    void modifarTituloCurso(Curso curso);
    void addCurso(Curso curso);

    void addModificarCurso(String id, String nombre, Curso curso);
    Curso getCurso(String id, String nombre);

    void ModificarTutorCurso(int id, Curso curso);
}
