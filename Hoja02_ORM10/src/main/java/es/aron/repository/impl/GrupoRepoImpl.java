package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import es.aron.model.Grupo;
import es.aron.repository.GrupoRepo;

import java.util.List;

public class GrupoRepoImpl implements GrupoRepo {
    private EntityManager em;
    public GrupoRepoImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public List<Grupo> gruposConCancionesMayoresA(int numCancion) {
        TypedQuery<Grupo> query=em.createNamedQuery("Grupo.masCanciones",Grupo.class);
        query.setParameter("numCanciones",numCancion);
        return query.getResultList();
    }

    @Override
    public List<Grupo> localidadesAnio(String localidad, int anio) {
        TypedQuery<Grupo> query=em.createNamedQuery("Grupo.localidadPrimerDisco",Grupo.class);
        query.setParameter("localidad",localidad);
        query.setParameter("anio",anio);
        return query.getResultList();
    }
}
