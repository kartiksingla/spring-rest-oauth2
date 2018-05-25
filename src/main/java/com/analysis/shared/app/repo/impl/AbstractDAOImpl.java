package com.analysis.shared.app.repo.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.analysis.shared.app.exceptions.DAOException;
import com.analysis.shared.app.exceptions.IdNotFoundDAOException;
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

	public T load(String id) throws DAOException{
		return getCurrentSession().load(clazz, id);
	}
	@Override
	public T saveOrUpdate(T object) throws DAOException {
		getCurrentSession().saveOrUpdate(object);
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
		if(result == null){
			throw new IdNotFoundDAOException(String.valueOf(id), clazz);
		}
		return (T) result;
	}

	@Override
	public T getById(String id) throws DAOException {
		Object result = getCurrentSession().get(clazz, id);
		if(result == null){
			throw new IdNotFoundDAOException(String.valueOf(id), clazz);
		}
		return (T) result;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
