package org.example.repository.impl;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.conexion.Conexion;
import org.example.model.CCAA;
import org.example.repository.CCAARepo;

import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class CCAARepoImpl implements CCAARepo {
    private static final String COLECCION_NOMBRE = "ccaa";
    private final MongoCollection<Document> coleccion;

    public CCAARepoImpl()
    {
        this.coleccion =
                Conexion.getInstance().getBasedatos().getCollection(COLECCION_NOMBRE);
    }
    @Override
    public void insertar(CCAA comunidadAutonoma) {
        coleccion.insertOne(comunidadAutonoma.fromComunidadAutonomaToDocument(comunidadAutonoma));
    }

    @Override
    public CCAA getComunidadAutonoma(String codigo) {
        Document document=coleccion.find(eq("codigo",codigo)).first();
        if (document != null) {
            return CCAA.fromDocumentToComunidadAutonoma(document);
        }
        return null;
    }

    @Override
    public Long addProvinciasComunidad(String codigo, List<String> provincias) {
        return coleccion.updateOne(eq("codigo",codigo),push("provincias",provincias)).getModifiedCount();
    }

    @Override
    public Long addProvinciaComunidad(String codigo, String provincia) {
        return coleccion.updateOne(eq("codigo",codigo),addToSet("provincias",provincia)).getModifiedCount();
    }

    @Override
    public void actualizarNombre(String codigo, String nombre) {
        coleccion.updateOne(eq("codigo",codigo),set("nombre",nombre));
    }

    @Override
    public Long eliminarProvinciaComunidad(String codigo, String provincia) {
        return coleccion.updateOne(eq("codigo",codigo),pull("provincias",provincia)).getModifiedCount();
    }

    @Override
    public void asignarCapital(String codigo, String nombreCapital, int habitantesCapital) {
        coleccion.updateOne(eq("codigo",codigo),push("capital.nombre",nombreCapital));
        coleccion.updateOne(eq("codigo",codigo),push("capital.habitantes",habitantesCapital));
    }

    @Override
    public void asignarFechaEstatuto(String codigo, String fecha) {
        coleccion.updateOne(eq("codigo",codigo),set("fecha_estatuto",fecha));
    }

    @Override
    public void eliminar(String codigo) {
        coleccion.deleteOne(eq("codigo",codigo));
    }
}
