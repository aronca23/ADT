package org.example.repository;

import org.bson.Document;
import org.example.model.ComunidadAutonoma;

import java.util.List;

public interface CCAARepository {
    List<ComunidadAutonoma> getCCAAYCapitales();
    List<ComunidadAutonoma> getCCAAHabitantesEntre(int min, int max);
    List<ComunidadAutonoma> getCCAAUniprovinciales();
    List<ComunidadAutonoma> getCapitalesMasHabitantes(int habitantes);
    List<ComunidadAutonoma> getCCAASinFechaEstatuto();
    ComunidadAutonoma getProvinciasCCAA(String codigo);
    List<Document> getCCAA();

}
