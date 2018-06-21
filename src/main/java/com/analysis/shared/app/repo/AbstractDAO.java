package com.analysis.shared.app.repo;

import com.analysis.shared.app.exceptions.DAOException;

public interface AbstractDAO<T> {

	T save(T object) throws DAOException;

	T saveOrUpdate(T object) throws DAOException;

	T update(T object) throws DAOException;

	T getById(long id) throws DAOException;

	T getById(String id) throws DAOException;

	T getByFields(String fieldName, Object value) throws DAOException;

}
