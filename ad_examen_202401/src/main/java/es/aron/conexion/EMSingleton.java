package es.aron.conexion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMSingleton {
    private static EMSingleton instance;
    private EntityManager em;
    private EMSingleton()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hola");
        this.em = emf.createEntityManager();
    }
    public EntityManager getEM()
    {
        return em;
    }
    public static EMSingleton getInstance()
    {
        if (instance == null)
            instance = new EMSingleton();
        return instance;
    }
}
