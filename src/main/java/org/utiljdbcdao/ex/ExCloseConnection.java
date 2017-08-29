package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;

public class ExCloseConnection extends GenericExceptionDbDao {

	private static final long serialVersionUID = -8151654714014791263L;

	@SuppressWarnings("rawtypes")
	public ExCloseConnection(Class throwerClass, Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.title = "Cierre de Conexión";
		super.message = "Error al intentar cerrar una conexión.";

	}

}
