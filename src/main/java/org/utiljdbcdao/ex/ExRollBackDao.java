package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;

public class ExRollBackDao extends GenericExceptionDbRollBackDao {

	private static final long serialVersionUID = -6120023563624756822L;

	@SuppressWarnings("rawtypes")
	public ExRollBackDao(Class throwerClass, Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.title = "Deshacer de Cambios de una Transacción";
		super.message = "Error al intentar deshacer los cambios de una transacción.";

	}

}
