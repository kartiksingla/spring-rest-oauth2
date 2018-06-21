package com.analysis.shared.app.repo.impl;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.analysis.shared.app.exceptions.DAOException;
import com.analysis.shared.app.exceptions.IdNotFoundDAOException;
import com.analysis.shared.app.exceptions.NoMatchingRecordFoundException;
import com.analysis.shared.app.repo.AbstractDAO;

public class AbstractDAOImpl<T> implements AbstractDAO<T> {

	private Class<T> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	protected AbstractDAOImpl(Class<T> clazz, SessionFactory sessionFactory) {
		this.clazz = clazz;
		this.sessionFactory = sessionFactory;
	}

	protected AbstractDAOImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T save(T object) throws DAOException {
		getCurrentSession().save(object);
		return object;

	}

	public T load(String id) throws DAOException {
		return getCurrentSession().load(clazz, id);
	}

	@Override
	public T saveOrUpdate(T object) throws DAOException {
		getCurrentSession().save(object);
		return object;
	}

	@Override
	public T update(T object) throws DAOException {
		getCurrentSession().update(object);
		return object;
	}

	@Override
	public T getById(long id) throws DAOException {
		Object result = getCurrentSession().get(clazz, id);
		if (result == null) {
			throw new IdNotFoundDAOException(String.valueOf(id), clazz);
		}
		return (T) result;
	}
	
	@Override
	public T getById(String id) throws DAOException {
		Object result = getCurrentSession().get(clazz, id);
		if (result == null) {
			throw new IdNotFoundDAOException(String.valueOf(id), clazz);
		}
		return (T) result;
	}
	
	@Override
	public T getByFields(String fieldName, Object value) throws DAOException {
		CriteriaQuery<T> cQuery = getCriteriaQuery(clazz);
		Root<T> root = cQuery.from(clazz);
		cQuery.select(root).where(getCriteriaBuilder().equal(root.get(fieldName), value));
		Query<T> finalQuery = getCurrentSession().createQuery(cQuery);
		T resultRecord = null;
		try {
			resultRecord = finalQuery.getSingleResult();
		} catch (NoResultException exception) {
			throw new NoMatchingRecordFoundException(fieldName, value, clazz);
		}
		return resultRecord;
	}

	public <I> CriteriaQuery<I> getCriteriaQuery(Class<I> queryClass){
		return getCriteriaBuilder().createQuery(queryClass);
	}

	private CriteriaBuilder getCriteriaBuilder() {
		return getCurrentSession().getCriteriaBuilder();
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
