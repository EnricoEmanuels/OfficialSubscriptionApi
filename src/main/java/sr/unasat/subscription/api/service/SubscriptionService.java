package sr.unasat.subscription.api.service;

import sr.unasat.subscription.api.entities.Subscription;
import sr.unasat.subscription.api.repositories.SubscriptionRepository;

import java.util.List;

public class  SubscriptionService {
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionService() {
        this.subscriptionRepository = new SubscriptionRepository();
    }

    public void saveSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    public Subscription saveSubscriptionWithPara(String firstname, String lastname, String email , String phonenumber) {
        Subscription subscription = new Subscription();
        subscription.setFirstname(firstname);
        subscription.setLastname(lastname);
        subscription.setEmail(email);
        subscription.setPhonenumber(phonenumber);
        subscriptionRepository.save(subscription);

        return subscription;
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.getAll();
   }


}