package org.example.repository;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.example.modelo.ComunidadAutonoma;

import java.util.List;

public interface CCAARepository {
    MongoCursor<Document> obtenerNombreProvinciaYCapital();
    MongoCursor<Document> obtenerNombresYHabitantesEntreHabitantes(int habitantesMin,int habitantesMax);
    MongoCursor<Document> obtenerCCAAUniprovinciales();
    List<ComunidadAutonoma> obtenerNombreCapitalPorHabitantes(int habitantes);
    MongoCursor<Document> obtenerCCAASinFechaEstatuto();
    MongoCursor<Document> obtenerProvincias(String codigo);
    void crearJSON();
}
