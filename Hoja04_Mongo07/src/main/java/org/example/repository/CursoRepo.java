package org.example.repository;

import org.bson.Document;
import org.example.model.Curso;
import org.example.model.Tema;

public interface CursoRepo {
    Curso getCurso(String id);
    void anadirTemaCurso(String id, Document tema);
    void modificarHoras(int id);
}
