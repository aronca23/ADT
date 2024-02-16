package org.example.repository.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.example.baseDatos.Conexion;
import org.example.modelo.Curso;
import org.example.modelo.Tema;
import org.example.repository.CursoRepo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class CursoRepoImpl implements CursoRepo {
    private static final String COLECCION_NOMBRE = "cursos";
    private final MongoCollection<Document> coleccion;

    public CursoRepoImpl()
    {
        this.coleccion =
                Conexion.getInstance().getBasedatos().getCollection(COLECCION_NOMBRE);
    }
    @Override
    public Curso getCursoById(String id) {
        Document document=coleccion.find(eq("id",id)).first();
        if (document==null) return null;
        else return Curso.fromDocumentToCapital(document);
    }

    @Override
    public void obtenerTemas(String id, Tema tema) {
        coleccion.updateOne(eq("_id",id),addToSet("temas",Tema.fromTemaToDocument(tema)));
    }

    @Override
    public void obtenerTituloYHorasCurso(String id) {

    }

    @Override
    public Integer calcularHoras(String id) {
        return null;
    }

    @Override
    public void modificarHorasCurso(String id, int horas) {
        coleccion.updateOne(eq("_id",id),set("horas",horas));
    }

    @Override
    public List<Document> getNombresCurso() {
        List<Document> cursos=new LinkedList<>();
        coleccion.aggregate(
                Arrays.asList(
                        Aggregates.group("$id", Accumulators.sum("num",1))
                )
        ).forEach(cursos::add);
        return cursos;
    }
}
