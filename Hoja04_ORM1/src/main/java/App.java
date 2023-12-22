import es.aron.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("Hoja04_ORM1");
        EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        LocalDate fechaAlta=LocalDate.of(2023,6,15);
        Empleado e1=new Empleado("Arón","Programador",fechaAlta,1400,100);
        Empleado e2=new Empleado("Juan","Camionero",fechaAlta,2000,0);
        em.persist(e1);
        em.persist(e2);
        em.getTransaction().commit();
        em.close();
    }
}
