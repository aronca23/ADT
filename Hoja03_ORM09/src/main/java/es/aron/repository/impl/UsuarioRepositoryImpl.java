package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import es.aron.model.Usuario;
import es.aron.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private EntityManager em;
    public UsuarioRepositoryImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public List<Usuario> usuariosSinVotos() {
        return em.createQuery("from Usuario u where size(u.votos)=0 ", Usuario.class).getResultList();
    }

    @Override
    public List<Usuario> usuariosPosterioresA1990() {
        return em.createQuery("from Usuario u where year(u.fechanacimiento)>1990", Usuario.class).getResultList();
    }
}
