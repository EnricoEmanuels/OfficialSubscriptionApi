package sr.unasat.subscription.api.repositories;

import java.util.List;

public interface EntityRepository<T> {
    void save(T entity);
    void update(T entity);
    List<T> getAll();
}
