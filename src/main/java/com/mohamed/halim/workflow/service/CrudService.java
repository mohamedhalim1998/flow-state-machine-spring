package com.mohamed.halim.workflow.service;

import com.mohamed.halim.workflow.model.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public abstract class CrudService<T extends BaseEntity> {
    private final JpaRepository<T, Long> repository;

    public T create(T entity) {
        return repository.save(entity);
    }

    public Page<T> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public T update(Long id, T entity) {
        entity.setId(id);
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
