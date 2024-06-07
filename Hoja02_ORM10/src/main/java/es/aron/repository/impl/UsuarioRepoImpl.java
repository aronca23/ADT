package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import jakarta.persistence.EntityManager;
import es.aron.model.Usuario;
import es.aron.repository.UsuarioRepo;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioRepoImpl implements UsuarioRepo {
    private EntityManager em;
    public UsuarioRepoImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public List<Usuario> usuariosNacidosAnioMAyorA(int anio) {
        TypedQuery<Usuario> query= em.createQuery("from Usuario u where year(u.fechanacimiento)>:anio", Usuario.class);
        query.setParameter("anio",anio);
        return query.getResultList();
    }
}
