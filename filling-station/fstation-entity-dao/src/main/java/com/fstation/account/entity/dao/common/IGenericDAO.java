package com.fstation.account.entity.dao.common;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, ID extends Serializable> {

	public T save(T entity);

	public T saveFlush(T entity);

	public T update(T entity);

	public T updateFlush(T entity);

	public T persist(T entity);

	public T persistFlush(T entity);

	public boolean remove(T entity);

	public List<T> findAll(Class<T> type);

	public T find(Class<T> type, ID id);

	public boolean removeById(Class<?> type, ID id);

	public Integer executeNativeQuery(String nativeQuery, Object[] values);

	public List<T> findByNativeQuery(String nativeQuery, Object[] values);

	public List findByNativeQuery(String nativeQuery, Object[] values,
			Class theClass);

}
