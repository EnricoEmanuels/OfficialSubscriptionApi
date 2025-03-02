package sr.unasat.subscription.api.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPAConfig {
    private static EntityManagerFactory emf ;
    private static EntityManager em ;
    private EntityTransaction transaction = em.getTransaction();

    public static EntityManagerFactory getEntityMangerFactory(){
        if (emf == null ) {
            emf = Persistence.createEntityManagerFactory("subscription");
        }
        return emf;
    }

    public static EntityManager getEntityManger(){
        if (em == null ) {
            em  = emf.createEntityManager();
        }
        return em;
    }
}

/*public class JPAConfig {
    private static EntityManagerFactory emf;
    // EntityManager wordt niet statisch! Altijd een nieuwe instantie maken per transactie.
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("hrmPersistenceUnit");
        }
        return emf;
    }
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();  // Nieuwe instantie per transactie
    }
}
*/