package com.mohamed.halim.workflow.controller;

import com.mohamed.halim.workflow.model.BaseEntity;
import com.mohamed.halim.workflow.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
public abstract class CrudController<T extends BaseEntity> {
    private final CrudService<T> service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }

    @GetMapping
    public Page<T> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public T get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public T update(@RequestBody T entity, @PathVariable Long id) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
