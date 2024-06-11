package org.example.repository.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.example.conexion.Conexion;
import org.example.model.Capital;
import org.example.model.ComunidadAutonoma;
import org.example.repository.CCAARepository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

public class CCAARepositoryImpl implements CCAARepository {
    private static final String COLECCION_NOMBRE = "ccaa";
    private final MongoCollection<Document> coleccion;

    public CCAARepositoryImpl()
    {
        this.coleccion =
                Conexion.getInstance().getBasedatos().getCollection(COLECCION_NOMBRE);
    }

    @Override
    public List<ComunidadAutonoma> getCCAAYCapitales() {
        MongoCursor<Document> cursor=coleccion.find().projection(include("nombre","capital.nombre")).iterator();
        List<ComunidadAutonoma> comunidadAutonomas=new ArrayList<>();
        while (cursor.hasNext()){
            Document doc=cursor.next();
            ComunidadAutonoma comunidadAutonoma=new ComunidadAutonoma();
            comunidadAutonoma.setNombre(doc.getString("nombre"));
            Document capitalDoc= (Document) doc.get("capital");
            if (capitalDoc!=null){
                Capital capital=new Capital();
                capital.setNombre(capitalDoc.getString("nombre"));
                comunidadAutonoma.setCapital(capital);
            }
            comunidadAutonomas.add(comunidadAutonoma);
        }
        return comunidadAutonomas;
    }

    @Override
    public List<ComunidadAutonoma> getCCAAHabitantesEntre(int min, int max) {
        MongoCursor<Document> cursor=coleccion.find(and(gt("habitantes",min),lt("habitantes",max))).sort(descending("habitantes")).projection(include("nombre","habitantes")).iterator();
        List<ComunidadAutonoma> comunidadAutonomas=new ArrayList<>();
        while (cursor.hasNext()){
            Document doc=cursor.next();
            ComunidadAutonoma comunidadAutonoma=new ComunidadAutonoma();
            comunidadAutonoma.setNombre(doc.getString("nombre"));
            comunidadAutonoma.setHabitantes(doc.getInteger("habitantes"));
            comunidadAutonomas.add(comunidadAutonoma);
        }
        return comunidadAutonomas;
    }

    @Override
    public List<ComunidadAutonoma> getCCAAUniprovinciales() {
        MongoCursor<Document> cursor=coleccion.find(size("provincias",1)).projection(include("nombre")).iterator();
        List<ComunidadAutonoma> comunidadAutonomas=new ArrayList<>();
        while (cursor.hasNext()){
            Document doc=cursor.next();
            ComunidadAutonoma comunidadAutonoma=new ComunidadAutonoma();
            comunidadAutonoma.setNombre(doc.getString("nombre"));
        }
        return comunidadAutonomas;
    }

    @Override
    public List<ComunidadAutonoma> getCapitalesMasHabitantes(int habitantes) {
        MongoCursor<Document> cursor=coleccion.find(gt("capital.habitantes",habitantes)).projection(include("capital.nombre")).iterator();
        List<ComunidadAutonoma> comunidadAutonomas=new ArrayList<>();
        while (cursor.hasNext()){
            Document doc=cursor.next();
            ComunidadAutonoma comunidadAutonoma=new ComunidadAutonoma();
            Document capitalDoc= (Document) doc.get("capital");
            if (capitalDoc!=null){
                Capital capital=new Capital();
                capital.setNombre(capitalDoc.getString("nombre"));
                comunidadAutonoma.setCapital(capital);
            }
            comunidadAutonomas.add(comunidadAutonoma);
        }
        return comunidadAutonomas;
    }

    @Override
    public List<ComunidadAutonoma> getCCAASinFechaEstatuto() {
        MongoCursor<Document> cursor=coleccion.find(exists("fecha_estatuto",false)).projection(include("nombre")).iterator();
        List<ComunidadAutonoma> comunidadAutonomas=new ArrayList<>();
        while (cursor.hasNext()){
            Document doc=cursor.next();
            ComunidadAutonoma comunidadAutonoma=new ComunidadAutonoma();
            comunidadAutonoma.setNombre(doc.getString("nombre"));
            comunidadAutonomas.add(comunidadAutonoma);
        }
        return comunidadAutonomas;
    }

    @Override
    public ComunidadAutonoma getProvinciasCCAA(String codigo) {
        Document document=coleccion.find(eq("codigo",codigo)).first();
        if (document != null) {
            return ComunidadAutonoma.fromDocumentToComunidadAutonoma(document);
        }
        return null;
    }

    @Override
    public List<Document> getCCAA() {
        MongoCursor<Document> cursor=coleccion.find().projection(include("nombre","capital","provincias","habitantes","extension")).iterator();
        List<Document> documents=new ArrayList<>();
        while (cursor.hasNext()){
            Document document=cursor.next();
            documents.add(document);
        }
        return documents;
    }
}
