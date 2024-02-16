package Repo.Impl;

import BasesDeDatos.Conexion;
import Repo.CursoRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import model.Curso;
import model.Tema;
import org.bson.Document;

import java.util.*;

import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class CursoRepositoryImpl implements CursoRepository {
    private MongoCollection<Document> cursos= Conexion.getInstance().getBaseDatos().getCollection("cursos");
    private MongoCollection<Document> formacionAlumnos= Conexion.getInstance().getBaseDatos().getCollection("alumnos");

    @Override
    public Curso getCursoById(String codigo) {
        Document doc= cursos.find(eq("_id", codigo)).first();
        if(doc==null){
            return null;
        }
        else return Curso.fromDocToCurso(doc);

    }

    @Override
    public void introducirTemaEnCurso(String codigoCurso, Tema tema) {
        cursos.updateOne(eq("_id", codigoCurso), addToSet("temas",tema.fromTemaToDoc()));
    }

    @Override
    public List<Document> getNombresCursos() {
        //return cursos.find().projection( include("titulo")).into(new LinkedList<>()).stream().map(document -> new Curso(document.getString("_id"), document.getString("titulo"))).toList();
        List<Document> cursos= new LinkedList<>();
        formacionAlumnos.aggregate(
                Arrays.asList(
                        Aggregates.group("$curso", Accumulators.sum("num", 1))
                )).forEach(f-> cursos.add(f));
       return cursos;

    }

    @Override
    public void actualizarHorasDeCurso(String codigoCurso, int horas) {
        cursos.updateOne(eq("_id", codigoCurso), set("horas", horas));
    }

    @Override
    public Double getMediaCurso(String codigoCurso) {
        List<Double> notasMedias=new ArrayList<>();
        formacionAlumnos.find(eq("curso", codigoCurso)).projection(include("nota_media")).into(new ArrayList<>()).stream().forEach(f-> notasMedias.add(f.getDouble("nota_media")));
        return notasMedias.stream().mapToDouble(Double::doubleValue).average().getAsDouble();


    }
}
