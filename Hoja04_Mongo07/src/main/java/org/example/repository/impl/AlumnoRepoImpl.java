package org.example.repository.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.example.Main;
import org.example.conexion.Conexion;
import org.example.model.Alumno;
import org.example.model.Curso;
import org.example.repository.AlumnoRepo;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.*;

public class AlumnoRepoImpl implements AlumnoRepo {
    private static final String COLECCION_NOMBRE = "alumnos";
    private final MongoCollection<Document> coleccion;

    public AlumnoRepoImpl()
    {
        this.coleccion =
                Conexion.getInstance().getBasedatos().getCollection(COLECCION_NOMBRE);
    }
    @Override
    public Alumno getAlumno(String id) {
        Document document=coleccion.find(eq("_id",id)).first();
        if (document != null) {
            return Alumno.fromDocumentToAlumno(document);
        }
        return null;
    }

    @Override
    public void anadirCurso(String curso) {

    }

    @Override
    public List<Alumno> getAlumnosCurso(String curso) {
        MongoCursor<Document> cursor=coleccion.find(eq("curso",curso)).sort(ascending("nombre")).iterator();
        List<Alumno> alumnos=new ArrayList<>();
        while (cursor.hasNext()){
            Document document=cursor.next();
            Alumno alumno=new Alumno();
            alumno.setNotas((List<Double>) document.get("notas"));
            alumnos.add(alumno);
        }
        return alumnos;
    }

    @Override
    public void calcularNotaMedia(String id) {
        Document document=coleccion.find(eq("_id",id)).first();
        Alumno alumno=Alumno.fromDocumentToAlumno(document);
        List<Double> notas = new ArrayList<>(alumno.getNotas());
        int cont=0;
        double suma=0;
        double nota_media=0;
        for (Double nota:notas) {
            suma+=nota;
            cont++;
        }
        nota_media=suma/cont;
        coleccion.updateOne(eq("_id",id),set("nota_media",nota_media));
    }

    @Override
    public List<Alumno> getAlumnos(String id) {
        MongoCursor<Document> cursor=coleccion.find(eq("curso",id)).sort(ascending("nombre")).iterator();
        List<Alumno> alumnos=new ArrayList<>();
        while (cursor.hasNext()){
            Document document=cursor.next();
            Alumno alumno=new Alumno();
            alumno.setNombre(document.getString("nombre"));
            alumno.setApellidos(document.getString("apellidos"));
            alumnos.add(alumno);
        }
        return alumnos;
    }

    @Override
    public void anadirNota(String alumno, double nota) {
        coleccion.updateOne(eq("_id",alumno),push("notas",nota));
    }
}
