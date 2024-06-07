package es.aron.repository.impl;

import es.aron.conexion.EMSingleton;
import es.aron.model.Curso;
import es.aron.model.Profesor;
import es.aron.repository.CursoRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CursoRepoImpl implements CursoRepo {
    private EntityManager em;
    private ProfesorRepoImpl profesorRepo;
    public CursoRepoImpl() {
        this.em= EMSingleton.getInstance().getEM();
        profesorRepo=new ProfesorRepoImpl();
    }
    @Override
    public Curso datosCurso(String id) {
        return em.find(Curso.class,id);
    }

    @Override
    public List<Curso> listadoCursos() {
        TypedQuery<Curso> query= em.createQuery("from Curso c", Curso.class);
        return query.getResultList();
    }

    @Override
    public void modifarTituloCurso(Curso curso) {
        em.getTransaction().begin();
        try {
            em.merge(curso);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void addCurso(Curso curso) {
        em.getTransaction().begin();
        try {
            em.persist(curso);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void addModificarCurso(String id, String nombre, Curso curso) {
        curso=getCurso(id, nombre);
        em.getTransaction();
        try {
            if (curso!=null) em.merge(curso);
            else em.persist(curso);
            em.getTransaction().commit();
        }catch (PersistenceException e){
            em.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Curso getCurso(String id, String nombre) {
        TypedQuery<Curso> query=em.createQuery("from Curso c where c.id=:id and c.nombre=:nombre", Curso.class);
        query.setParameter("id",id);
        query.setParameter("nombre",nombre);
        return query.getSingleResult();
    }

    @Override
    public void ModificarTutorCurso(int id, Curso curso) {
        List<Profesor> profesores=profesorRepo.getProfesoresNoTutores();
        for (Profesor profesor:profesores) {
            if (id==profesor.getId()){
                em.getTransaction().begin();
                try {
                    em.merge(curso);
                    em.getTransaction().commit();
                }catch (PersistenceException e){
                    System.err.println(e.getMessage());
                    em.getTransaction().rollback();
                }
            }
        }
    }
}
