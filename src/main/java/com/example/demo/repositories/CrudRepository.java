package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    T save(T t);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(T t);
    void deleteById(ID id);
    Long count();
    Boolean existById(ID id);
}
