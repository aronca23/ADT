package org.example.repository;

import org.example.modelo.ComunidadAutonoma;

import java.util.List;

public interface CCAARepository {
    void insertar(ComunidadAutonoma comunidadAutonoma);

    ComunidadAutonoma getComunidadAutonoma(String codigo);

    Long addProvinciaComunidad(String codigo, List<String> provincias);
    Long addProvinciaComunidad(String codigo, String provincia);
    void actualizarNombre(String codigo,String nombre);
    Long eliminarProvinciasComunidad(String codigo,String provincia);
    void asignarCapital(String codigo,String nombre, int habitantes);
    void asignarFechaEstatuto(String codigo,String fechaEntradaVigor);
    void eliminar(String codigo);
}