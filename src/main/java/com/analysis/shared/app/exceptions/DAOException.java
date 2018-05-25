package com.analysis.shared.app.exceptions;

public class DAOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5235298454473952274L;

	/**
	 * {@inheritDoc}
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * {@inheritDoc}
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
