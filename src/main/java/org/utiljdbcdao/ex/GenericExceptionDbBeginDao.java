package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;

abstract class GenericExceptionDbBeginDao extends GenericExceptionDbDao {

	private static final long serialVersionUID = -4716611390384810753L;

	public GenericExceptionDbBeginDao(
			@SuppressWarnings("rawtypes") Class throwerClass,
			Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.operationType = "TRANSACTION";
	}

}
