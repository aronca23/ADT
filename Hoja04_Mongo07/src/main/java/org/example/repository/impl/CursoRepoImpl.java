package org.example.repository.impl;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.conexion.Conexion;
import org.example.model.Curso;
import org.example.model.Tema;
import org.example.repository.CursoRepo;

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
    public Curso getCurso(String id) {
        Document document=coleccion.find(eq("_id",id)).first();
        if (document != null) {
            return Curso.fromDocumentToCurso(document);
        }
        return null;
    }

    @Override
    public void anadirTemaCurso(String id, Document tema) {
        coleccion.updateOne(eq("_id",id),set("tema",tema));
    }

    @Override
    public void modificarHoras(int id) {
        Document document=coleccion.find(eq("_id",id)).first();
        Curso curso=Curso.fromDocumentToCurso(document);
        int sumaHoras=0;
        int horas=0;
        for (Tema tema:curso.getTemas()) {
            sumaHoras+=tema.getHoras();
        }
        horas=sumaHoras;
        coleccion.updateOne(eq("_id0",id),set("horas",horas));
    }
}
