package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import es.aron.model.Alumno;
import es.aron.repository.AlumnoRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AlumnoRepoImpl implements AlumnoRepo {
    private EntityManager em;
    public AlumnoRepoImpl() {
        this.em= EMSingleton.getInstance().getEM();
    }
    @Override
    public Alumno datosAlumno(int id) {
        return em.find(Alumno.class,id);
    }

    @Override
    public List<Alumno> listadoAlumnosCurso(String id) {
        TypedQuery<Alumno> query= em.createQuery("from Alumno a where a.curso.id=:id", Alumno.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

    @Override
    public void eliminarAlumno(int id) {
        Alumno alumno=em.find(Alumno.class,id);
        em.getTransaction().begin();
        try {
            em.remove(alumno);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            System.err.println(e.getMessage());
            em.getTransaction().rollback();
        }
    }

}
