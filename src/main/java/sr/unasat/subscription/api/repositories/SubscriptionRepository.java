package sr.unasat.subscription.api.repositories;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import sr.unasat.subscription.api.entities.Subscription;
import sr.unasat.subscription.api.config.JPAConfig;

import java.util.List;

public class SubscriptionRepository implements EntityRepository<Subscription> {
    EntityManager em = JPAConfig.getEntityManger();

    @Override
    public void save(Subscription subscription) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(subscription);
        transaction.commit();

    }

    @Override
    public void update(Subscription subscription) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Subscription updateSubscription = em.find(Subscription.class, subscription.getId());
        updateSubscription.setFirstname(subscription.getFirstname());
        updateSubscription.setLastname(subscription.getLastname());
        updateSubscription.setEmail(subscription.getEmail());
        updateSubscription.setPhonenumber(subscription.getPhonenumber());
        transaction.commit();

    }

    @Override
    public List<Subscription> getAll() {
        return em.createQuery("SELECT s FROM Subscription s", Subscription.class).getResultList();
    }


}


/*public class SubscriptionRepository implements EntityRepository<Subscription> {
    @Override
    public void save(Subscription subscription) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(subscription);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();  // BELANGRIJK! EntityManager moet gesloten worden
        }
    }
    @Override
    public void update(Subscription subscription) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Subscription updateSubscription = em.find(Subscription.class, subscription.getId());
            if (updateSubscription != null) {
                updateSubscription.setFirstname(subscription.getFirstname());
                updateSubscription.setLastname(subscription.getLastname());
                updateSubscription.setEmail(subscription.getEmail());
                updateSubscription.setPhonenumber(subscription.getPhonenumber());
                em.merge(updateSubscription);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

*/