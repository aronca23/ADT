package es.aron.repository;

import es.aron.model.Receta;

import java.util.List;

public interface RecetaRepository {
    Receta getReceta(int id);
    List<Receta> recetaMasVotosPositivos();
    int incrementarDificultad();
    List<String> getNombreIngredienres(int id);
}
