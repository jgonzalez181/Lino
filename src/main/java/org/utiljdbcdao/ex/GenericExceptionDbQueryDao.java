package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;

abstract class GenericExceptionDbQueryDao extends GenericExceptionDbDao {

	private static final long serialVersionUID = 8992024163588380831L;

	public GenericExceptionDbQueryDao(
			@SuppressWarnings("rawtypes") Class throwerClass,
			Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.operationType = "QUERY";
	}

}
