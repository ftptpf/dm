package ru.ftptpf.dao;

import ru.ftptpf.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    List<E> findAll();
    Optional<E> findById(K id);
    void update(E entity);
    Ticket save(E entity);
    boolean delete(K id);
}
