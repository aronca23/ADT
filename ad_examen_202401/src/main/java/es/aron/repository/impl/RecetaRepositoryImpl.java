package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import es.aron.model.Ingrediente;
import es.aron.model.Receta;
import es.aron.repository.RecetaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RecetaRepositoryImpl implements RecetaRepository {
    private EntityManager em;
    public RecetaRepositoryImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public Receta getReceta(int id) {
        return em.find(Receta.class,id);
    }

    @Override
    public List<Receta> recetaMasVotosPositivos() {
        TypedQuery<Receta> query= em.createQuery("from Receta r where r.autor.login in (select u.login from Usuario u where u.login=r.autor.login) and r.positivos>r.negativos order by r.positivos desc ", Receta.class);
        return query.getResultList();
    }

    @Override
    public int incrementarDificultad() {
        em.getTransaction().begin();
        try {
            em.createQuery("update Receta r set r.dificultad=r.dificultad+1 where r.tiempo>60 and r.positivos>r.negativos").executeUpdate();
            em.getTransaction().commit();
            return 1;
        }catch (PersistenceException e){
            System.err.println(e.getMessage());
            em.getTransaction().rollback();
            return 0;
        }
    }

    @Override
    public List<String> getNombreIngredienres(int id) {
        TypedQuery<Ingrediente> query= em.createQuery("from Ingrediente i join fetch i.recetas r where r.id=:id", Ingrediente.class);
        query.setParameter("id",id);
        return query.getResultList().stream().map(Ingrediente::getNombre).toList();
    }
}
