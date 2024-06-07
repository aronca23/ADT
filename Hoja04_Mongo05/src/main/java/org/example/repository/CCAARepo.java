package org.example.repository;

import org.example.model.CCAA;

import java.util.List;

public interface CCAARepo {
    void insertar(CCAA comunidadAutonoma);
    CCAA getComunidadAutonoma(String codigo);
    Long addProvinciasComunidad(String codigo, List<String> provincias);
    Long addProvinciaComunidad(String codigo, String provincia);
    void actualizarNombre(String codigo, String nombre);
    Long eliminarProvinciaComunidad(String codigo, String provincia);
    void asignarCapital(String codigo, String nombreCapital, int habitantesCapital);
    void asignarFechaEstatuto(String codigo, String fecha);
    void eliminar(String codigo);
}
