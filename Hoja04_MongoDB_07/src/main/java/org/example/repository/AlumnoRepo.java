package org.example.repository;

import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.example.modelo.Alumno;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

public interface AlumnoRepo {
    void añadirNotas(Map<String,Double> notas_curso);
    List<Alumno> obtenerAlumnosNotaMedia(String curso);
    List<Double> calcularNotaMedia(String id);
    Alumno getAlumnoById(String id);
}
