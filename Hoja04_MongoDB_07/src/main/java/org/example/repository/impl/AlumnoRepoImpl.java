package org.example.repository.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.example.baseDatos.Conexion;
import org.example.modelo.Alumno;
import org.example.repository.AlumnoRepo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Updates.*;

public class AlumnoRepoImpl implements AlumnoRepo {
    private static final String COLECCION_NOMBRE = "alumnos";
    private final MongoCollection<Document> coleccion;

    public AlumnoRepoImpl()
    {
        this.coleccion =
                Conexion.getInstance().getBasedatos().getCollection(COLECCION_NOMBRE);
    }

    public List<Alumno> getAlumnosFromCurso(String idCurso) {
        MongoCursor<Document> cursor = coleccion.find(eq("curso", idCurso)).projection(fields(include("_id","nombre", "apellidos"))).iterator();
        List<Alumno> result = new ArrayList<>();
        while (cursor.hasNext()) {
            result.add(Alumno.fromDocumentToAlumno(cursor.next()));
        }
        return result;
    }
    @Override
    public void añadirNotas(Map<String,Double> notas_curso) {
        notas_curso.entrySet().stream().forEach(n -> coleccion.updateOne(eq("_id",n.getKey()),set("notas",n.getValue())));
    }

    @Override
    public List<Alumno> obtenerAlumnosNotaMedia(String curso) {
        return coleccion.find(and( eq("curso", curso), gt("nota_media", 5.0))).into(new LinkedList<>()).stream().map(document -> Alumno.fromDocumentToAlumno(document)).toList();    }

    @Override
    public List<Double> calcularNotaMedia(String id) {
        return null;
    }

    @Override
    public Alumno getAlumnoById(String id) {
        Document document=coleccion.find(eq("_id",id)).first();
        if (document!=null) return Alumno.fromDocumentToAlumno(document);
        else return null;
    }
}
