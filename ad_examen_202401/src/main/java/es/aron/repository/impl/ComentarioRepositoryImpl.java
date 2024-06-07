package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import es.aron.model.Comentario;
import es.aron.repository.ComentarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ComentarioRepositoryImpl implements ComentarioRepository {
    private EntityManager em;
    public ComentarioRepositoryImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public List<Comentario> comentarioRecetasAutor(String login) {
        TypedQuery<Comentario> query= em.createNamedQuery("Comentario.getComentarios", Comentario.class);
        query.setParameter("login",login);
        return query.getResultList();
    }

    @Override
    public boolean insertarComentario(Comentario comentario) {
        em.getTransaction().begin();
        try {
            em.persist(comentario);
            em.getTransaction().commit();
            return true;
        }catch (PersistenceException e){
            System.err.println(e.getMessage());
            em.getTransaction().rollback();
            return false;
        }
    }
}
