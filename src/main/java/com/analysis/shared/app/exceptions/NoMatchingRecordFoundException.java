package com.analysis.shared.app.exceptions;

public class NoMatchingRecordFoundException extends DAOException {

	private static final long serialVersionUID = 7652999946864277320L;

	public NoMatchingRecordFoundException(String fieldName, Object value, Class<?> clazz) {
		super(String.format("Object of class %s with field name %s equal to %s does not exist in database.",
				clazz.getName(), fieldName, value));
	}

	public NoMatchingRecordFoundException(String s) {
		super(s);
	}

}
