package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import es.aron.conexion.EMSingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import es.aron.model.Cancione;
import es.aron.repository.CancionRepo;

import java.util.ArrayList;
import java.util.List;

public class CancionRepoImpl implements CancionRepo {
    private EntityManager em;
    public CancionRepoImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public List<Cancione> getCancionesGrupo(List<String> nombreGrupo) {
        List<Cancione> canciones=new ArrayList<>();
        for (String nombre:nombreGrupo) {
            TypedQuery<Cancione> query= em.createQuery("select c from Cancione c where c.grupo.nombre=:nombre", Cancione.class);
            query.setParameter("nombre",nombre);
            canciones.add(query.getSingleResult());
        }
        return canciones;
    }
}
