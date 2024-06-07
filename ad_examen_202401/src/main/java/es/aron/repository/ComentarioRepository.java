package es.aron.repository;

import es.aron.model.Comentario;

import java.util.List;

public interface ComentarioRepository {
    List<Comentario> comentarioRecetasAutor(String login);
    boolean insertarComentario(Comentario comentario);
}
