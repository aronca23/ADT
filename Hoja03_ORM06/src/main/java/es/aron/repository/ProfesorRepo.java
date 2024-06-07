package es.aron.repository;

import es.aron.model.Profesor;

import java.util.List;

public interface ProfesorRepo {
    List<Profesor> getProfesoresNoTutores();
    Profesor getProfesor(int id);
}
