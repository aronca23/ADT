package org.example.repository.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.example.baseDatos.Conexion;
import org.example.modelo.ComunidadAutonoma;
import org.example.repository.CCAARepository;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Projections.*;

public class CCAARepositoryImpl implements CCAARepository {
    private static final String COLECCION_NOMBRE = "ccaa";
    private final MongoCollection<Document> coleccion;

    public CCAARepositoryImpl()
    {
        this.coleccion =
                Conexion.getInstance().getBasedatos().getCollection(COLECCION_NOMBRE);
    }
    @Override
    public MongoCursor<Document> obtenerNombreProvinciaYCapital() {
        return coleccion.find().projection(fields(include("nombre","capital.nombre"),exclude("_id"))).iterator();
    }

    @Override
    public MongoCursor<Document> obtenerNombresYHabitantesEntreHabitantes(int habitantesMin, int habitantesMax) {
        return coleccion.find(and(gt("habitantes",habitantesMin),lt("habitantes",habitantesMax))).sort(descending("habitantes")).projection(include("nombre","habitantes")).iterator();
    }

    @Override
    public MongoCursor<Document> obtenerCCAAUniprovinciales() {
        return coleccion.find(size("provincias",1)).sort(fields(ascending("nombre"),descending("habitantes"))).iterator();
    }

    @Override
    public List<ComunidadAutonoma> obtenerNombreCapitalPorHabitantes(int habitantes) {
        FindIterable<Document> ccaa=coleccion.find(gt("capital.habitantes",habitantes)).sort(ascending("capital.nombre"));
        List<ComunidadAutonoma> result = new ArrayList<>();
        MongoCursor<Document> cursor = ccaa.iterator();
        while (cursor.hasNext()) {
            result.add(ComunidadAutonoma.fromDocumentToComunidadAutonoma(cursor.next()));
        }
        return result;
    }

    public List<ComunidadAutonoma> obtenerNombreCapitalPorHabitantes2(int habitantes) {
        MongoIterable<ComunidadAutonoma> ccaa=coleccion.find(gt("capital.habitantes",habitantes)).sort(ascending("capital.nombre"))
                .map(document -> ComunidadAutonoma.fromDocumentToComunidadAutonoma(document));

        return StreamSupport.stream(ccaa.spliterator(), false).toList();
    }

    @Override
    public MongoCursor<Document> obtenerCCAASinFechaEstatuto() {
        return coleccion.find(or(exists("fecha_estatuto"),eq("fecha_estatuto",null))).projection(include("nombre")).iterator();
    }

    @Override
    public MongoCursor<Document> obtenerProvincias(String codigo) {
        return coleccion.find(eq("codigo",codigo)).projection(include("nombre","provincias")).iterator();
    }

    @Override
    public void crearJSON() {
        File f=new File("ccaa.json");
        try (BufferedReader lector= Files.newBufferedReader(f.toPath())){
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            ComunidadAutonoma ccaa = gson.fromJson(lector, ComunidadAutonoma.class);
            coleccion.insertOne(ComunidadAutonoma.fromComunidadAutonomaToDocument(ccaa));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
