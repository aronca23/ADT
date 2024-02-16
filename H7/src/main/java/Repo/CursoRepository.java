package Repo;

import model.Curso;
import model.Tema;
import org.bson.Document;

import java.util.List;
import java.util.OptionalDouble;

public interface CursoRepository {

    Curso getCursoById (String codigo);

    void introducirTemaEnCurso(String codigoCurso, Tema tema);

    List<Document> getNombresCursos();

    void actualizarHorasDeCurso(String codigoCurso, int horas);

    Double getMediaCurso(String codigoCurso);
}
