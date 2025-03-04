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
        if (updateSubscription != null) {
            updateSubscription.setFirstname(subscription.getFirstname());
            updateSubscription.setLastname(subscription.getLastname());
            updateSubscription.setEmail(subscription.getEmail());
            updateSubscription.setPhonenumber(subscription.getPhonenumber());
            em.merge(updateSubscription);  // Zorg ervoor dat de update wordt toegepast
        }
        transaction.commit();
    }
    @Override
    public List<Subscription> getAll() {
        return em.createQuery("SELECT s FROM Subscription s", Subscription.class).getResultList();
    }

    public Subscription findById(int id) {
        return em.find(Subscription.class, id);
    }

    @Override
    public void delete(Subscription subscription) {
        if (subscription != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Subscription foundSubscription = em.find(Subscription.class, subscription.getId());
            if (foundSubscription != null) {
                em.remove(foundSubscription);
            }
            transaction.commit();
        }
    }

    public void deleteById(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Subscription subscription = em.find(Subscription.class, id);
        if (subscription != null) {
            em.remove(subscription);
        }
        transaction.commit();
    }
}

