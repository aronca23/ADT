package es.aron.repository;

import java.util.List;
import java.util.Objects;

public interface CancionRepository {
    List<Object[]> numCancionesGrupos(String localidad);
    List<Object[]> duracionMediaCancionesGrupos();
}
