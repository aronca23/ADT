package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import es.aron.model.Grupo;
import es.aron.repository.GrupoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GrupoRepositoryImpl implements GrupoRepository {
    private EntityManager em;
    public GrupoRepositoryImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public List<Grupo> listadoGrupos() {
        return em.createQuery("from Grupo g", Grupo.class).getResultList();
    }

    @Override
    public List<Grupo> gruposSinComponentes() {
        return em.createQuery("from Grupo g where size(g.componentes)=0", Grupo.class).getResultList();
    }

    @Override
    public List<Grupo> gruposSinCompania() {
        return em.createQuery("from Grupo g where g.compañia=null ", Grupo.class).getResultList();
    }

    @Override
    public List<Grupo> gruposDeAnioEstreno(String ciudad, int anio) {
        return em.createQuery("from Grupo g where g.localidad=:ciudad and g.añograbacion=:anio", Grupo.class).setParameter("ciudad",ciudad).setParameter("anio",anio).getResultList();
    }

    @Override
    public Long numeroGruposDe(String ciudad) {
        return em.createQuery("select count(g.id) from Grupo g where g.localidad=:ciudad", Long.class).setParameter("ciudad",ciudad).getSingleResult();
    }
}
