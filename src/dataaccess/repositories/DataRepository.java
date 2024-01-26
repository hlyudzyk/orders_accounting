package dataaccess.repositories;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import models.Entity;

public abstract class DataRepository<T extends Entity> {
    private Set<T> entities;

    public DataRepository(Set<T> entities) {
        this.entities = entities;
    }

    protected Set<T> getEntities(){
        return entities;
    }

    public Optional<T> find(UUID id) {
        return getEntities().stream().filter(e -> e.getId().equals(id)).findAny();
    }

    public void insert(T itemToInsert) throws RuntimeException {
        getEntities().add(itemToInsert);
    }


    public void delete(UUID id) {
        Optional<T> optionalEntity = find(id);
        if (optionalEntity.isPresent()) {
            T entity = optionalEntity.get();
            getEntities().remove(entity);
        }
    }

    public Set<T> getAll() {
        return getEntities();
    }

    public void addAll(Set<T> entities) {
        getEntities().addAll(entities);
    }

    public abstract void update(T itemToUpdate) throws RuntimeException, IOException;
}
