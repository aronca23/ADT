package es.aron.repository;

import es.aron.model.Grupo;

import java.util.List;

public interface GrupoRepo {
    List<Grupo> gruposConCancionesMayoresA(int numCancion);
    List<Grupo> localidadesAnio(String localidad, int anio);
}
