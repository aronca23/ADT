package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import es.aron.repository.CancionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CancionRepositoryImpl implements CancionRepository {
    private EntityManager em;
    public CancionRepositoryImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public List<Object[]> numCancionesGrupos(String localidad) {
        return em.createQuery("select c.titulo,count(c.grupo.id) from Cancione c", Object[].class).setParameter("localidad",localidad).getResultList();
    }

    @Override
    public List<Object[]> duracionMediaCancionesGrupos() {

    }
}
