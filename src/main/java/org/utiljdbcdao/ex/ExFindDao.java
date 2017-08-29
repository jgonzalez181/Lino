package org.utiljdbcdao.ex;

import org.utiljdbc.ConnectionWrapper;

public class ExFindDao extends GenericExceptionDbQueryDao {

	private static final long serialVersionUID = -7006496101956738739L;

	@SuppressWarnings("rawtypes")
	public ExFindDao(Class throwerClass, Exception thirdException,
			ConnectionWrapper connectionWrapper) {

		super(throwerClass, thirdException, connectionWrapper);

		super.title = "Consultando Listado de Registros";
		super.message = "Error al intentar consultar un listado del total de registros.";

	}

}
