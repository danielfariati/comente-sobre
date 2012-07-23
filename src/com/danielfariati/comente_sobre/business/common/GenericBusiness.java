package com.danielfariati.comente_sobre.business.common;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import br.com.caelum.vraptor.Validator;

import com.danielfariati.comente_sobre.model.common.GenericEntity;
import com.danielfariati.comente_sobre.repository.common.GenericRepository;

public class GenericBusiness<T extends GenericEntity> implements GenericRepository<T> {

	protected final EntityManager manager;
	protected final Validator validator;
	protected final Class<T> clazz;

	protected GenericBusiness(EntityManager manager, Validator validator) {
		this.manager = manager;
		this.validator = validator;

		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		this.clazz = clazz;
	}

	public Collection<T> loadAll() {
		Query query = manager.createQuery("from " + clazz.getName());

		@SuppressWarnings("unchecked")
		Collection<T> resultList = query.getResultList();

		return resultList;
	}

	public T loadById(Long id) {
		try {
			return manager.find(clazz, id);
		} catch(EntityNotFoundException e) {
			return null;
		}
	}

	public void remove(T entity) {
		manager.remove(manager.getReference(clazz, entity.getId()));
	}

	public T save(T entity) {
		return manager.merge(entity);
	}

}
