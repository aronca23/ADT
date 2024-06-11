package es.aron.repository;

import es.aron.model.Grupo;

import java.util.List;

public interface GrupoRepository {
    List<Grupo> listadoGrupos();
    List<Grupo> gruposSinComponentes();
    List<Grupo>gruposSinCompania();
    List<Grupo> gruposDeAnioEstreno(String ciudad, int anio);
    Long numeroGruposDe(String ciudad);
}
