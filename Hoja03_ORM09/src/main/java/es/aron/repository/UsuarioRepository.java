package es.aron.repository;

import es.aron.model.Usuario;

import java.util.List;

public interface UsuarioRepository {
    List<Usuario> usuariosSinVotos();
    List<Usuario> usuariosPosterioresA1990();
}
