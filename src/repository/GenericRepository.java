package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenericRepository<T, ID> {
    protected List<T> entities;

    public GenericRepository() {
        this.entities = new ArrayList<>();
    }

    public void add(T entity) {
        entities.add(entity);
    }

    public Optional<T> findById(ID id) {
        return entities.stream()

                .filter(entity -> entity.equals(id))
                .findFirst();
    }
    public List<T> findAll() {
        return new ArrayList<>(entities);
    }

    public boolean remove(T entity) {
        return entities.remove(entity);
    }
}