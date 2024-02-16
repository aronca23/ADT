package org.example.repository;

import org.bson.Document;
import org.example.modelo.Curso;
import org.example.modelo.Tema;

import java.util.List;

public interface CursoRepo {
    Curso getCursoById(String id);
    void obtenerTemas(String id, Tema tema);
    void obtenerTituloYHorasCurso(String id);
    Integer calcularHoras(String id);
    void modificarHorasCurso(String id, int horas);
    List<Document> getNombresCurso();
}
