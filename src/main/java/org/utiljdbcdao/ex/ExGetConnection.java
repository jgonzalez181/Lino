package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;


public class ExGetConnection extends GenericExceptionDbDao {

	private static final long serialVersionUID = -2102866873730658179L;

	@SuppressWarnings("rawtypes")
	public ExGetConnection(Class throwerClass, Exception thirdException, ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.title = "Comienzo de Conexión";
		super.message = "Error al intentar iniciar una conexión.";

	}

}
