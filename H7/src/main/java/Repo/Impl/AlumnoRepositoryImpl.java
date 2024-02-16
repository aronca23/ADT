package Repo.Impl;

import BasesDeDatos.Conexion;

import Repo.AlumnoRepository;
import com.mongodb.client.MongoCollection;
import model.Alumno;
import model.Curso;
import org.bson.Document;
import com.mongodb.client.*;

import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;


import java.util.*;

public class AlumnoRepositoryImpl implements AlumnoRepository {

    private MongoCollection<Document> cursos= Conexion.getInstance().getBaseDatos().getCollection("cursos");
    private MongoCollection<Document> formacionAlumnos= Conexion.getInstance().getBaseDatos().getCollection("alumnos");
    @Override
    public List<Alumno> getAlumnosFromCurso(String idCurso) {
        MongoCursor<Document> cursor = formacionAlumnos.find(eq("curso", idCurso)).projection(fields(include("_id","nombre", "apellidos"))).iterator();
        List<Alumno> alumnos= new LinkedList<>();
        if(cursor.available()>0){
            while(cursor.hasNext()){
                Document doc= cursor.next();
               Alumno a= new Alumno(doc.getString("_id"), doc.getString("nombre"), doc.getString("apellidos"));
               alumnos.add(a);
            }
        }
    return alumnos;
    }

    @Override
    public void introducirNotasAlumnos(Map<String, Double> mapaAlumnos_Notas) {
        mapaAlumnos_Notas.entrySet().stream().forEach(f-> formacionAlumnos.updateOne(eq("_id", f.getKey()), push("notas", f.getValue())));


    }

    @Override
    public List<Alumno> alumnosAprobadosDeCurso(String codigoCurso) {

            return formacionAlumnos.find(and( eq("curso", codigoCurso), gt("nota_media", 5.0))).into(new LinkedList<>()).stream().map(document -> Alumno.fromDocToAlumno(document)).toList();


    }

    @Override
    public Alumno getAlumnoById(String codigo) {
        Document doc= formacionAlumnos.find(eq("_id", codigo)).first();
        if(doc==null){
            return null;
        }
        else return Alumno.fromDocToAlumno(doc);

    }

}
