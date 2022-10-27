package rikkei.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T> {
    Iterable<T> findAll();

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);
}
