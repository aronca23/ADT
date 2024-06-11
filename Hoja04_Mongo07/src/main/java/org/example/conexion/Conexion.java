package org.example.conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Conexion {
    public static Conexion instance;
    private MongoDatabase basedatos;
    private static final String NOMBRE_BASEDATOS = "formacion";

    private Conexion()
    {
        MongoClient cliente = MongoClients.create("mongodb://root:root@localhost:27017");
        basedatos = cliente.getDatabase(NOMBRE_BASEDATOS);
    }

    public MongoDatabase getBasedatos()
    {
        return basedatos;
    }

    public static Conexion getInstance()
    {
        if (instance == null)
            instance = new Conexion();

        return instance;
    }
}
