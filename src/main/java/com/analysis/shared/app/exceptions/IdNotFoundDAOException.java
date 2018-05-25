package com.analysis.shared.app.exceptions;

public class IdNotFoundDAOException extends DAOException {

	private static final long serialVersionUID = -664099648666881951L;

	public IdNotFoundDAOException(String s) {
        super(s);
    }

    public IdNotFoundDAOException(String id, Class<?> clazz) {
        super(String.format("Object of class %swith id %s does not exist in database.", clazz.getName(), id));
    }
}
