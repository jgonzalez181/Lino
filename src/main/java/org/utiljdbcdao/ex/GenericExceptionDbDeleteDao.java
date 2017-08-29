package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;

abstract class GenericExceptionDbDeleteDao extends GenericExceptionDbDao {

	private static final long serialVersionUID = 1113037776456666027L;

	public GenericExceptionDbDeleteDao(
			@SuppressWarnings("rawtypes") Class throwerClass,
			Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.operationType = "DELETE";
	}

}
