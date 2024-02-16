package org.example.repository.impl;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.baseDatos.Conexion;
import org.example.modelo.ComunidadAutonoma;
import org.example.repository.CCAARepository;

import java.time.LocalDate;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class CCAARepositoryImpl implements CCAARepository {
    private static final String COLECCION_NOMBRE = "ccaa";
    private final MongoCollection<Document> coleccion;

    public CCAARepositoryImpl()
    {
        this.coleccion =
                Conexion.getInstance().getBasedatos().getCollection(COLECCION_NOMBRE);
    }

    @Override
    public void insertar(ComunidadAutonoma comunidadAutonoma) {
        coleccion.insertOne(comunidadAutonoma.fromComunidadAutonomaToDocument(comunidadAutonoma));

    }

    @Override
    public ComunidadAutonoma getComunidadAutonoma(String codigo) {
        Document document=coleccion.find(eq("codigo",codigo)).first();
        return ComunidadAutonoma.fromDocumentToComunidadAutonoma(document);
    }

    @Override
    public Long addProvinciaComunidad(String codigo, List<String> provincias) {
        return coleccion.updateOne(eq("codigo",codigo),set("provincias",provincias)).getModifiedCount();
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
    public Long eliminarProvinciasComunidad(String codigo, String provincia) {
        return coleccion.updateOne(and(eq("codigo",codigo),eq("provincias",provincia)),pull("provincias",provincia)).getModifiedCount();
    }

    @Override
    public void asignarCapital(String codigo, String nombre, int habitantes) {
        coleccion.updateOne(and(eq("codigo",codigo),exists("capital")),combine(set("capital.nombre",nombre),set("capital.habitantes",habitantes)));
    }

    @Override
    public void asignarFechaEstatuto(String codigo, String fechaEntradaVigor) {
        coleccion.updateOne(eq("codigo",codigo),set("fecha_estatuto", LocalDate.parse(fechaEntradaVigor)));
    }

    @Override
    public void eliminar(String codigo) {
        coleccion.deleteOne(eq("codigo",codigo));
    }
}
