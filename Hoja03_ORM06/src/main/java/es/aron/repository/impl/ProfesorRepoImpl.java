package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import es.aron.model.Alumno;
import es.aron.model.Profesor;
import es.aron.repository.ProfesorRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProfesorRepoImpl implements ProfesorRepo {
    private EntityManager em;
    public ProfesorRepoImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public List<Profesor> getProfesoresNoTutores() {
        TypedQuery<Profesor> query= em.createQuery("from Profesor p where p.id not in (select c.tutor from Curso c)", Profesor.class);
        return query.getResultList();
    }

    @Override
    public Profesor getProfesor(int id) {
        return em.find(Profesor.class,id);
    }
}
