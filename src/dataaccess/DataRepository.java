package dataaccess;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DataRepository<T> {
    Optional< T > find(String id);

    void insert(T itemToInsert) throws RuntimeException, IOException;

    void update(T itemToUpdate) throws RuntimeException, IOException;

    void delete(T itemToDelete) throws RuntimeException;
    List<T> getAll();

}