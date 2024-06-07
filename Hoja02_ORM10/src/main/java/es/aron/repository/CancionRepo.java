package es.aron.repository;

import es.aron.model.Cancione;

import java.util.List;

public interface CancionRepo {
    List<Cancione> getCancionesGrupo(List<String> nombreGrupo);
}
