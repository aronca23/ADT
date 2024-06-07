package es.aron.repository;

import es.aron.model.Usuario;

import java.util.List;

public interface UsuarioRepo {
    List<Usuario> usuariosNacidosAnioMAyorA(int anio);
}
