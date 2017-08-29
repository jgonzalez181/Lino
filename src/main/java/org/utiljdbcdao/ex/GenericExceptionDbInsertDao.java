package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;

abstract class GenericExceptionDbInsertDao extends GenericExceptionDbDao {

	private static final long serialVersionUID = -4036457990362824219L;

	public GenericExceptionDbInsertDao(
			@SuppressWarnings("rawtypes") Class throwerClass,
			Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.operationType = "INSERT";
	}

}
