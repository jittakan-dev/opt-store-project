package dev.jittakan.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> listing() throws Exception;
    T byId(Long id) throws Exception;
    void save(T t) throws Exception;
    void eliminate(Long id) throws Exception;
}
