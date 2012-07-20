package com.danielfariati.comente_sobre.repository.common;

import java.util.Collection;

import com.danielfariati.comente_sobre.model.common.GenericEntity;

public interface GenericRepository<T extends GenericEntity> {

	Collection<T> loadAll();

	T loadById(Long id);

	void remove(T entity);

	T save(T entity);

}
