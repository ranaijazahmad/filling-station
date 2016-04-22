package com.fstation.account.entity.dao.common;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fstation.account.helper.EntityManagerHelper;

/**
 * @author m.ijaz
 * 
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements
		IGenericDAO<T, ID> {

	protected EntityManager em() {
		return EntityManagerHelper.getEntityManager();
	}

	public T find(Class<T> type, ID id) {
		// TODO Auto-generated method stub
		return em().find(type, id);

	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> type) {
		// TODO Auto-generated method stub
		return em().createQuery("select _it_ from " + type.getName() + " _it_")
				.getResultList();
	}

	public boolean remove(T entity) {
		// TODO Auto-generated method stub
		boolean deletedFlag = false;

		if (entity != null) {
			em().remove(entity);
			deletedFlag = true;
		}
		return deletedFlag;
	}

	public T save(T entity) {
		// TODO Auto-generated method stub
		T e;
		e = em().merge(entity);
		return e;
	}

	public T saveFlush(T entity) {
		// TODO Auto-generated method stub
		T e;
		e = em().merge(entity);
		em().flush();
		return e;
	}

	public T persist(T entity) {
		// TODO Auto-generated method stub
		em().persist(entity);
		return entity;
	}

	public T persistFlush(T entity) {
		// TODO Auto-generated method stub
		em().persist(entity);
		em().flush();
		return entity;
	}

	public T update(T entity) {
		// TODO Auto-generated method stub
		T t = em().merge(entity);
		return t;
	}

	public T updateFlush(T entity) {
		// TODO Auto-generated method stub
		T t = em().merge(entity);
		em().flush();
		return t;
	}

	public boolean removeById(Class<?> type, ID id) {

		boolean deleteFlag = false;

		Query query = em().createQuery(
				"select id from " + type.getName() + " where id = ?")
				.setParameter(1, id);
		if (query.getResultList().size() != 0) {
			em().remove(em().getReference(type, id));
			deleteFlag = true;
		}
		return deleteFlag;

	}

	@SuppressWarnings("unchecked")
	public List findJPQL(String jpql, Object[] values) {
		int paramCount = 1;
		Query query = em().createQuery(jpql);

		for (Object parametr : values) {
			query.setParameter(paramCount, parametr);
			paramCount++;
		}
		List resultList = query.getResultList();
		return resultList;

	}

	@SuppressWarnings("unchecked")
	public Integer executeNativeQuery(String nativeQuery, Object[] values) {

		int paramCount = 1;
		int returnValue = 0;
		Query query = (Query) em().createNativeQuery(nativeQuery);
		if (values != null) {
			for (Object parametr : values) {

				query.setParameter(paramCount, parametr);
				paramCount++;
			}
		}
		returnValue = query.executeUpdate();

		return returnValue;

	}

	@SuppressWarnings("unchecked")
	public List findByNativeQuery(String nativeQuery, Object[] values) {

		int paramCount = 1;
		Query query = em().createNativeQuery(nativeQuery);
		if (values != null) {

			for (Object parametr : values) {

				query.setParameter(paramCount, parametr);
				paramCount++;
			}
		}
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List findByNativeQuery(String nativeQuery, Object[] values,
			Class theClass) {

		int paramCount = 1;
		Query query = em().createNativeQuery(nativeQuery, theClass);
		if (values != null) {
			for (Object parametr : values) {
				query.setParameter(paramCount, parametr);
				paramCount++;
			}
		}
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public int executeNativeQuery(String nativeQuery) {

		Query query = em().createNativeQuery(nativeQuery);

		return query.executeUpdate();

	}

}
